package com.csse.backend.Requisition.Controller;

import com.csse.backend.Requisition.Dto.OrderDto;
import com.csse.backend.Requisition.Service.RequisitionService;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/requisition")
@CrossOrigin("*")
public class RequisitionController {

    @Autowired
    RequisitionService requisitionService;

    @PostMapping("/add-new")
    public ResponseEntity<?> insertRequisition(@RequestBody OrderDto orderDto){ //get requisition order data

        //call the insertNewRequisition service method to save body
        return requisitionService.insertNewRequisition(orderDto);

    }
    @GetMapping("/get-pending-orders")
    public ResponseEntity<?> getPendingOrders(){ //get pending orders
        return requisitionService.getPendingOrdersByName(); //return pending orders

    }
    @GetMapping("/get-approved-requisitions")
    public ResponseEntity<?> getApprovedRequisition(){ //get approved requisitions orders

        return requisitionService.getApprovedOrdersByName(); //return approved orders
    }

    @GetMapping("/get-lists")
    public ResponseEntity<?> getLists() throws JSONException, JSONException { //get Item List
        return requisitionService.getList();
    }
}