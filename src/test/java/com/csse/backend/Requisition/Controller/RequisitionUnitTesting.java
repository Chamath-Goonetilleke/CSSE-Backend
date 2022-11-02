package com.csse.backend.Requisition.Controller;

import com.csse.backend.Requisition.Dto.OrderDto;
import com.csse.backend.Requisition.Repository.RequisitionRepository;
import com.csse.backend.Requisition.Service.RequisitionServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;

import java.util.Date;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class RequisitionUnitTesting {

    @Autowired
    RequisitionServiceImpl requisitionService;

    @MockBean
    RequisitionRepository requisitionRepository;

    @Test
    @DisplayName("AddNewRequisitionSuccess")
    void addNewRequisition(){
        OrderDto orderDto = new OrderDto();
        orderDto.setDeliveryAddress("Colombo, Road");
        orderDto.setItemName("Bottle");
        orderDto.setDeliveryDate(new Date());
        orderDto.setQuantity(20);
        orderDto.setNotes("Good quality");
        orderDto.setSiteManagerName("Kavindu");
        orderDto.setSupplierName("Madushan");
        orderDto.setUnitType("Kg");

        Mockito.when(requisitionRepository.save(Mockito.any())).thenReturn(orderDto);

        ResponseEntity<?> result= requisitionService.insertNewRequisition(orderDto);
        Assertions.assertEquals(result,result);
    }

    @Test
    @DisplayName("AddNewRequisitionFail")
    void addNewRequisitionFailed(){

        OrderDto orderDto = new OrderDto();
        orderDto.setDeliveryAddress("Colombo, Road");
        orderDto.setItemName("Bottle");
        orderDto.setDeliveryDate(new Date());
        orderDto.setQuantity(20);
        orderDto.setNotes("Good quality");
        orderDto.setSiteManagerName("Kavindu");
        orderDto.setSupplierName("Madushan");
        orderDto.setUnitType("Kg");

        Mockito.when(requisitionRepository.save(Mockito.any())).thenReturn(orderDto);

        ResponseEntity<?> result= requisitionService.insertNewRequisition(orderDto);
        Assertions.assertEquals(orderDto,result);
    }

}
