package com.csse.backend.controllers;

import com.csse.backend.domains.Item;
import com.csse.backend.dtos.AcceptOrRejectPendingPr;
import com.csse.backend.dtos.SupplierAcceptPrDto;
import com.csse.backend.services.SupplierOrderQuotationService;

import lombok.extern.slf4j.Slf4j;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/supplier")
@Slf4j
public class SupplierOrderQuotationController {

    @Autowired
    SupplierOrderQuotationService supplierOrderQuotationService;

    /**
     * Accept purchase requisitions which are received from customer by supplier identification
     *
     * @param supplierAcceptPrDto - Details which needs to create a supplier order quotation
     * @return - ResponseEntity object containing the status of the requested functionality
     */
    @PostMapping("/accept-customer-approved-pr")
    public ResponseEntity acceptCustomerApprovedPurchaseRequisition(@RequestBody SupplierAcceptPrDto supplierAcceptPrDto) {
        boolean response = supplierOrderQuotationService.acceptCustomerApprovedPurchaseRequisition(supplierAcceptPrDto);

        if (response) {
            return new ResponseEntity(HttpStatus.CREATED);
        } else {
            return new ResponseEntity(HttpStatus.CONFLICT);
        }
    }

    /**
     * Get all ready to deliver order items by supplier identification
     *
     * @param supplierId - Supplier identification
     * @return - ResponseEntity object containing ready to deliver items
     */
    @GetMapping("/all-customer-supplier-accepted-pr/{supplierId}")
    public ResponseEntity<List<Item>> getAllCustomerAndSupplierAcceptedPurchaseRequisitions(@PathVariable Long supplierId) {
        List<Item> orderItems = supplierOrderQuotationService.getAllCustomerAndSupplierAcceptedPurchaseRequisitions(supplierId);

        if (orderItems != null) {
            return new ResponseEntity<>(orderItems, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Upload an advice note for a ready to deliver item
     *
     * @param supplierOrderQuotationId - Supplier order quotation identification
     * @param link                     - Advice note updated link
     * @return - ResponseEntity object containing the status of the requested functionality
     */
    @PutMapping("/update-advice-note/{supplierOrderQuotationId}/{link}")
    public ResponseEntity updateAdviceNote(@PathVariable long supplierOrderQuotationId, @PathVariable String link) {
        boolean response = supplierOrderQuotationService.updateAdviceNote(supplierOrderQuotationId, link);
        if (response) {
            return new ResponseEntity(HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity(HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    /**
     * Upload an advice note for a ready to deliver item
     *
     * @param supplierOrderQuotationId - Supplier order quotation identification
     * @param link                     - Invoide note updated link
     * @return - ResponseEntity object containing the status of the requested functionality
     */
    @PutMapping("/update-invoice/{supplierOrderQuotationId}/{link}")
    public ResponseEntity updateInvoice(@PathVariable long supplierOrderQuotationId, @PathVariable String link) {
        boolean response = supplierOrderQuotationService.updateInvoice(supplierOrderQuotationId, link);
        if (response) {
            return new ResponseEntity(HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity(HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    /**
     * Get all customer accepted supplier order quotations but supplier yet be decided whether the ordered item
     * is going to deliver or not by supplier identification
     *
     * @param supplierId - Supplier identification
     * @return - ResponseEntity object containing all customer accepted supplier order quotations
     */
    @GetMapping("/all-customer-accepted-pr/{supplierId}")
    public ResponseEntity<List<Item>> getAllCustomerAcceptedPurchaseRequisitions(@PathVariable long supplierId) {
        List<Item> orderItems = supplierOrderQuotationService.getAllCustomerAcceptedPurchaseRequisitions(supplierId);

        if (orderItems != null) {
            return new ResponseEntity<>(orderItems, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Supplier accept, customer accepted supplier order quotation by supplier order quotation identification
     *
     * @param supplierOrderQuotationId - Supplier order quotation identification
     * @return - ResponseEntity object containing the status of the requested functionality
     */
    @PutMapping("/accept-customer-accepted-pr/{supplierOrderQuotationId}")
    public ResponseEntity acceptCustomerAcceptedPurchaseRequisition(@PathVariable long supplierOrderQuotationId) {
        boolean response = supplierOrderQuotationService.acceptCustomerAcceptedOrderQuotation(supplierOrderQuotationId);

        if (response) {
            return new ResponseEntity(HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity(HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    /**
     * Get all supplier order quotation list from employee user identification
     *
     * @param employeeUserId - Employee user identification
     * @return ResponseEntity object containing all customer approval pending supplier order quotations list
     */
    @GetMapping("/all-customer-approval-pending-soq/{employeeUserId}")
    public ResponseEntity<List<Item>> getAllCustomerApprovalPendingSoq(@PathVariable long employeeUserId) {
        List<Item> orderItems = supplierOrderQuotationService.getAllCustomerApprovalPendingSoq(employeeUserId);

        if (orderItems != null) {
            return new ResponseEntity<>(orderItems, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Accept or reject supplier order quotation
     *
     * @param command - Information required to accept or reject a pending supplier order identification
     * @return - ResponseEntity object containing the status of the requested functionality
     */
    @PutMapping("/accept-or-reject-approval-pending-soq")
    public ResponseEntity acceptOrRejectCustomerApprovalPendingSoq(@RequestBody AcceptOrRejectPendingPr command) {
        boolean response = supplierOrderQuotationService.acceptOrRejectCustomerApprovalPendingSoq(command);

        if (response) {
            return new ResponseEntity(HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

}
