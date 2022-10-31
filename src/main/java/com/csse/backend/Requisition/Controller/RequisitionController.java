package com.csse.backend.Requisition.Controller;

import com.csse.backend.Requisition.Dto.OrderDto;
import com.csse.backend.Requisition.Model.Order;
import com.csse.backend.Requisition.Service.RequisitionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/requisition")
@CrossOrigin("*")
public class RequisitionController {

    @Autowired
    RequisitionService requisitionService;

    @PostMapping("/add-new")
    public ResponseEntity<?> insertRequisition(@RequestBody OrderDto orderDto){
        return requisitionService.insertNewRequisition(orderDto);

    }
    @GetMapping("/get-pending-orders/{name}")
    public ResponseEntity<?> getPendingOrders(@PathVariable String name){
        return requisitionService.getPendingOrdersByName(name);

    }
    @GetMapping("/get-approved-requisitions/{name}")
    public ResponseEntity<?> getApprovedRequisition(@PathVariable String name){
        return requisitionService.getApprovedOrdersByName(name);
    }
}
