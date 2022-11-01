package com.csse.backend.controllers;

import com.csse.backend.domains.Order;
import com.csse.backend.services.OrderItemService;

import java.util.List;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
@Slf4j
public class OrderItemController {

    @Autowired
    private OrderItemService orderItemService;

    /**
     * Get all approved customer approved purchase requisitions by supplier identification
     *
     * @return ResponseEntity object containing requested data
     */
    @GetMapping("/all-customer-approved-pr")
    public ResponseEntity<List<Order>> getAllCustomerApprovedPurchaseRequisitions() {
        try {
            List<Order> orders = orderItemService.getAllCustomerApprovedPurchaseRequisitions();

            if (orders != null) {
                return new ResponseEntity<>(orders, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            log.error("{}", e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
