package com.csse.backend.Requisition.Service;

import com.csse.backend.Requisition.Dto.OrderDto;
import com.csse.backend.Requisition.Model.Order;
import com.csse.backend.Requisition.Repository.RequisitionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
            Order order = new Order(orderDto.getDeliveryAddress(),
                    orderDto.getItemName(),
                    orderDto.getDeliveryDate(),
                    orderDto.getQuantity(),
                    orderDto.getNotes(),
                    orderDto.getSiteManagerName(),
                    orderDto.getSupplierName(),
                    orderDto.getUnitType(),
                    "Pending");

            Order result = requisitionRepository.save(order);
            LOGGER.info("Successfully Created Order Requisition");
            return ResponseEntity.status(HttpStatus.CREATED).body(result);
        }catch(Exception e){
            LOGGER.info("Cannot create Order Requisition"+e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Cannot create new Order Requisition");

        }

    }

    @Override
    public ResponseEntity<?> getPendingOrdersByName(String name) {

        try{
            List<Order> orderResult = requisitionRepository.getPrivileges(name);
            LOGGER.info("Successfully get Requisition in Pending");
            return ResponseEntity.status(HttpStatus.OK).body(orderResult);
        }catch(Exception e){
            LOGGER.info("Cannot get Requisition in Pending"+e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Cannot get Requisition in Pending");

        }

    }
}
