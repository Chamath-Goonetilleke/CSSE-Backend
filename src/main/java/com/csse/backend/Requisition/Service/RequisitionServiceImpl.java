package com.csse.backend.Requisition.Service;

import com.csse.backend.Requisition.Dto.OrderDto;
import com.csse.backend.Requisition.Model.Orders;
import com.csse.backend.Requisition.Repository.RequisitionRepository;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class RequisitionServiceImpl implements RequisitionService{

    private static final Logger LOGGER = LoggerFactory.getLogger(RequisitionServiceImpl.class);

    @Autowired
    RequisitionRepository requisitionRepository;

    @Override
    public ResponseEntity<?> insertNewRequisition(OrderDto orderDto) {

        try{
            Orders order = new Orders(
                    orderDto.getDeliveryAddress(),
                    orderDto.getItemName(),
                    orderDto.getDeliveryDate(),
                    orderDto.getQuantity(),
                    orderDto.getNotes(),
                    orderDto.getSiteManagerName(),
                    orderDto.getSupplierName(),
                    orderDto.getUnitType(),
                    1);

            Orders result = requisitionRepository.save(order);
            LOGGER.info("Successfully Created Order Requisition");
            return ResponseEntity.status(HttpStatus.CREATED).body(result);
        }catch(Exception e){
            LOGGER.info("Cannot create Order Requisition"+e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Cannot create new Order Requisition");

        }

    }

    @Override
    public ResponseEntity<?> getPendingOrdersByName() {

        try{
            List<Orders> orderResult = requisitionRepository.getPendingRequisitions();
            LOGGER.info("Successfully get Requisition in Pending");
            return ResponseEntity.status(HttpStatus.OK).body(orderResult);
        }catch(Exception e){
            LOGGER.info("Cannot get Requisition in Pending"+e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Cannot get Requisition in Pending");

        }

    }

    @Override
    public ResponseEntity<?> getApprovedOrdersByName() {
        try{
            List<Orders> orderResult = requisitionRepository.getApprovedRequisitions();
            LOGGER.info("Successfully get Requisition in Approved");
            return ResponseEntity.status(HttpStatus.OK).body(orderResult);
        }catch(Exception e){
            LOGGER.info("Cannot get Requisition in Approved"+e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Cannot get Requisition in Approved");

        }
    }

    @Override
    public ResponseEntity<?> getList() {


        try{
            List<String> items = requisitionRepository.getListsItems();
            List<String> supplier = requisitionRepository.getListsSupplier();

            List<String> series = new ArrayList<>();
            JSONObject dataObj = new JSONObject();
            dataObj.put("list", items);
            dataObj.put("supplier", supplier);
            series.add(String.valueOf(dataObj));

            System.out.println("series"+series);
            LOGGER.info("Successfully get List");
            return ResponseEntity.status(HttpStatus.OK).body(series);
        }catch(Exception e){
            LOGGER.info("Cannot get List"+e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Cannot get List");

        }

    }
}
