package com.csse.backend.controllers;

import com.csse.backend.domains.SupplierOrderQuotation;
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

    /*
        Accept purchase requisitions which are received from customer by supplier identification
     */
    @PostMapping("/accept-customer-approved-pr")
    public ResponseEntity acceptCustomerApprovedPurchaseRequisition(@RequestBody SupplierAcceptPrDto supplierAcceptPrDto) {
        try {
            boolean response = supplierOrderQuotationService.acceptCustomerApprovedPurchaseRequisition(supplierAcceptPrDto);

            if (response) {
                return new ResponseEntity(HttpStatus.CREATED);
            } else {
                return new ResponseEntity(HttpStatus.CONFLICT);
            }
        } catch (Exception e) {
            log.error("{}", e.getMessage());
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /*
        Get all ready to deliver order items by supplier identification
     */
    @GetMapping("/all-customer-supplier-accepted-pr/{supplierId}")
    public ResponseEntity<List<SupplierOrderQuotation>> getAllCustomerAndSupplierAcceptedPurchaseRequisitions(@PathVariable Long supplierId) {
        try {
            List<SupplierOrderQuotation> orderItems = supplierOrderQuotationService.getAllCustomerAndSupplierAcceptedPurchaseRequisitions(supplierId);

            if (orderItems != null) {
                return new ResponseEntity<>(orderItems, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            log.error("{}", e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /*
        Upload an advice note for a ready to deliver item
     */
    @PutMapping("/update-advice-note/{supplierOrderQuotationId}/{link}")
    public ResponseEntity updateAdviceNote(@PathVariable long supplierOrderQuotationId, @PathVariable String link) {
        try {
            boolean response = supplierOrderQuotationService.updateAdviceNote(supplierOrderQuotationId, link);
            if (response) {
                return new ResponseEntity(HttpStatus.ACCEPTED);
            } else {
                return new ResponseEntity(HttpStatus.UNPROCESSABLE_ENTITY);
            }
        } catch (Exception e) {
            log.error("{}", e.getMessage());
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /*
        Upload an invoice for a ready to deliver item
     */
    @PutMapping("/update-invoice/{supplierOrderQuotationId}/{link}")
    public ResponseEntity updateInvoice(@PathVariable long supplierOrderQuotationId, @PathVariable String link) {
        try {
            boolean response = supplierOrderQuotationService.updateInvoice(supplierOrderQuotationId, link);
            if (response) {
                return new ResponseEntity(HttpStatus.ACCEPTED);
            } else {
                return new ResponseEntity(HttpStatus.UNPROCESSABLE_ENTITY);
            }
        } catch (Exception e) {
            log.error("{}", e.getMessage());
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /*
        Get all customer accepted supplier order quotations but supplier yet be decided whether the ordered item
        is going to deliver or not by supplier identification
     */
    @GetMapping("/all-customer-accepted-pr/{supplierId}")
    public ResponseEntity<List<SupplierOrderQuotation>> getAllCustomerAcceptedPurchaseRequisitions(@PathVariable long supplierId) {
        try {
            List<SupplierOrderQuotation> orderItems = supplierOrderQuotationService.getAllCustomerAcceptedPurchaseRequisitions(supplierId);

            if (orderItems != null) {
                return new ResponseEntity<>(orderItems, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            log.error("{}", e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /*
        Supplier accept, customer accepted supplier order quotation by supplier order quotation identification
     */
    @PutMapping("/accept-customer-accepted-pr/{supplierOrderQuotationId}")
    public ResponseEntity acceptCustomerAcceptedPurchaseRequisition(@PathVariable long supplierOrderQuotationId) {
        try {
            boolean response = supplierOrderQuotationService.acceptCustomerAcceptedOrderQuotation(supplierOrderQuotationId);

            if (response) {
                return new ResponseEntity(HttpStatus.ACCEPTED);
            } else {
                return new ResponseEntity(HttpStatus.UNPROCESSABLE_ENTITY);
            }
        } catch (Exception e) {
            log.error("{}", e.getMessage());
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /*
        Get all supplier order quotation list from employee user identification
     */
    @GetMapping("/all-customer-approval-pending-soq/{employeeUserId}")
    public ResponseEntity<List<SupplierOrderQuotation>> getAllCustomerApprovalPendingSoq(@PathVariable long employeeUserId) {
        try {
            List<SupplierOrderQuotation> orderItems = supplierOrderQuotationService.getAllCustomerApprovalPendingSoq(employeeUserId);

            if (orderItems != null) {
                return new ResponseEntity<>(orderItems, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            log.error("{}", e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /*
        Accept or reject supplier order quotation
     */
    @PutMapping("/accept-or-reject-approval-pending-soq")
    public ResponseEntity acceptOrRejectCustomerApprovalPendingSoq(@RequestBody AcceptOrRejectPendingPr command) {
        try {
            boolean response = supplierOrderQuotationService.acceptOrRejectCustomerApprovalPendingSoq(command);

            if (response) {
                return new ResponseEntity(HttpStatus.ACCEPTED);
            } else {
                return new ResponseEntity(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            log.error("{}", e.getMessage());
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
