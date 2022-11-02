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
    public ResponseEntity<?> insertRequisition(@RequestBody OrderDto orderDto){
        return requisitionService.insertNewRequisition(orderDto);

    }
    @GetMapping("/get-pending-orders")
    public ResponseEntity<?> getPendingOrders(){
        return requisitionService.getPendingOrdersByName();

    }
    @GetMapping("/get-approved-requisitions")
    public ResponseEntity<?> getApprovedRequisition(){
        return requisitionService.getApprovedOrdersByName();
    }
    @GetMapping("/get-lists")
    public ResponseEntity<?> getLists() throws JSONException, JSONException {
        return requisitionService.getList();
    }
}