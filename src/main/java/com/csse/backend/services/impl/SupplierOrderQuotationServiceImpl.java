package com.csse.backend.services.impl;

import com.csse.backend.domains.OrderItem;
import com.csse.backend.domains.SupplierOrderQuotation;
import com.csse.backend.domains.User;
import com.csse.backend.dtos.AcceptOrRejectPendingPr;
import com.csse.backend.dtos.SupplierAcceptPrDto;
import com.csse.backend.enums.SupplierOrderQuotationStatus;
import com.csse.backend.repositories.OrderItemRepository;
import com.csse.backend.repositories.SupplierOrderQuotationRepository;
import com.csse.backend.repositories.UserRepository;
import com.csse.backend.services.SupplierOrderQuotationService;

import java.util.List;

import javax.persistence.EntityExistsException;
import javax.persistence.PersistenceException;
import javax.persistence.TransactionRequiredException;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class SupplierOrderQuotationServiceImpl implements SupplierOrderQuotationService {

    @Autowired
    SupplierOrderQuotationRepository supplierOrderQuotationRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    OrderItemRepository orderItemRepository;

    /**
     * Create a supplier order quotation
     *
     * @param supplierAcceptPrDto - New Supplier order quotation
     * @return true if new Supplier order quotation created successfully, else false
     */
    @Override
    public boolean acceptCustomerApprovedPurchaseRequisition(SupplierAcceptPrDto supplierAcceptPrDto) {

        SupplierOrderQuotation supplierOrderQuotation = new SupplierOrderQuotation();

        if (supplierAcceptPrDto.getSupplierId() != null) {
            try {
                User user = userRepository.getUserById(supplierAcceptPrDto.getSupplierId());
                if (user != null) {
                    supplierOrderQuotation.setSupplier(user);
                } else {
                    return false;
                }
            } catch (IllegalArgumentException e) {
                log.error(e.getMessage());
                return false;
            }
        }

        if (supplierAcceptPrDto.getOrderItemId() != null) {
            try {
                OrderItem orderItem = orderItemRepository.getOrderItemById(supplierAcceptPrDto.getOrderItemId());
                if (orderItem != null) {
                    supplierOrderQuotation.setOrderItem(orderItem);
                } else {
                    return false;
                }
            } catch (IllegalArgumentException e) {
                log.error(e.getMessage());
                return false;
            }
        }

        if (supplierAcceptPrDto.getQuantity() != null) {
            supplierOrderQuotation.setSupplyAmount(supplierAcceptPrDto.getQuantity());
        }

        if (supplierAcceptPrDto.getUnitPrice() != null) {
            supplierOrderQuotation.setSupplyPricePerUnit(supplierAcceptPrDto.getUnitPrice());
        } else {
            return false;
        }

        if (supplierAcceptPrDto.getBrand() != null) {
            supplierOrderQuotation.setSupplyBrand(supplierAcceptPrDto.getBrand());
        } else {
            return false;
        }

        if (supplierAcceptPrDto.getDateCanDeliver() != null) {
            supplierOrderQuotation.setSupplyDate(supplierAcceptPrDto.getDateCanDeliver());
        } else {
            return false;
        }

        supplierOrderQuotation.setSupplierOrderQuotationStatus(SupplierOrderQuotationStatus.PARTIALLY_APPROVED);

        try {
            supplierOrderQuotationRepository.saveSupplierOrderQuotation(supplierOrderQuotation);
            return true;
        } catch (EntityExistsException | TransactionRequiredException e) {
            log.error("{}", e.getMessage());
            return false;
        }
    }

    /**
     * Get all ready to deliver items
     *
     * @param supplierId - Supplier identification
     * @return List<SupplierOrderQuotation>
     */
    @Override
    public List<SupplierOrderQuotation> getAllCustomerAndSupplierAcceptedPurchaseRequisitions(long supplierId) {
        User user = userRepository.getUserById(supplierId);

        if (user != null) {
            try {
                return supplierOrderQuotationRepository.getAllCustomerAndSupplierAcceptedPurchaseRequisitions(supplierId);
            } catch (IllegalArgumentException e) {
                log.error(e.getMessage());
                return null;
            }
        } else {
            return null;
        }
    }

    /**
     * Update an advice note notice for an existing supplier order quotation
     *
     * @param supplierOrderQuotationId - Supplier oder quotation identification
     * @param link                     - Advice note document upload cloud storage link
     * @return
     */
    @Override
    public boolean updateAdviceNote(long supplierOrderQuotationId, String link) {
        SupplierOrderQuotation supplierOrderQuotation = supplierOrderQuotationRepository.getSupplierQuotationById(supplierOrderQuotationId);

        if (supplierOrderQuotation != null) {
            supplierOrderQuotation.setAdviceNote(link);
            try {
                supplierOrderQuotationRepository.saveSupplierOrderQuotation(supplierOrderQuotation);
            } catch (EntityExistsException | TransactionRequiredException e) {
                log.error("{}", e.getMessage());
                return false;
            }
            return true;
        } else {
            return false;
        }
    }

    /**
     * Update an invoice notice for an existing supplier order quotation
     *
     * @param supplierOrderQuotationId - Supplier oder quotation identification
     * @param link                     - Invoice document upload cloud storage link
     * @return
     */
    @Override
    public boolean updateInvoice(long supplierOrderQuotationId, String link) {
        SupplierOrderQuotation supplierOrderQuotation = supplierOrderQuotationRepository.getSupplierQuotationById(supplierOrderQuotationId);

        if (supplierOrderQuotation != null) {
            supplierOrderQuotation.setInvoice(link);
            try {
                supplierOrderQuotationRepository.saveSupplierOrderQuotation(supplierOrderQuotation);
            } catch (EntityExistsException | TransactionRequiredException e) {
                log.error("{}", e.getMessage());
                return false;
            }
            return true;
        } else {
            return false;
        }
    }

    /**
     * Get all supervisor accepted supplier order quotations
     *
     * @param supplierId - Supplier identification
     * @return List<SupplierOrderQuotation>
     */
    @Override
    public List<SupplierOrderQuotation> getAllCustomerAcceptedPurchaseRequisitions(long supplierId) {
        try {
            User user = userRepository.getUserById(supplierId);

            if (user != null) {
                return supplierOrderQuotationRepository.getAllCustomerAcceptedPurchaseRequisitions(supplierId);
            } else {
                return null;
            }
        } catch (IllegalArgumentException | PersistenceException e) {
            log.error(e.getMessage());
            return null;
        }
    }

    /**
     * Accept a supervisor accepted supplier order quotation by relevant supplier
     *
     * @param supplierOrderQuotationId - Supplier order quotation identification
     * @return true if update successful, else false
     */
    @Override
    public boolean acceptCustomerAcceptedOrderQuotation(long supplierOrderQuotationId) {
        try {
            SupplierOrderQuotation supplierOrderQuotation = supplierOrderQuotationRepository.getSupplierQuotationById(supplierOrderQuotationId);

            if (supplierOrderQuotation != null) {
                supplierOrderQuotation.setSupplierOrderQuotationStatus(SupplierOrderQuotationStatus.PLACED);
                supplierOrderQuotationRepository.saveSupplierOrderQuotation(supplierOrderQuotation);
                return true;
            } else {
                return false;
            }
        } catch (IllegalArgumentException | EntityExistsException | TransactionRequiredException e) {
            log.error("{}", e.getMessage());
            return false;
        }
    }

    /**
     * Get all supervisor approval pending supplier order quotations
     *
     * @param employeeUserId - Supervisor identification
     * @return List<SupplierOrderQuotation>t
     */
    @Override
    public List<SupplierOrderQuotation> getAllCustomerApprovalPendingSoq(long employeeUserId) {
        try {
            User user = userRepository.getUserById(employeeUserId);

            if (user != null) {
                return supplierOrderQuotationRepository.getAllCustomerApprovalPendingSoq(employeeUserId);
            } else {
                return null;
            }
        } catch (IllegalArgumentException e) {
            log.error(e.getMessage());
            return null;
        }
    }

    /**
     * Approve ar reject supplier order quotation by supervisor
     *
     * @param command - If command inside AcceptOrRejectPendingPr object is true will accept supplier order quotation,
     *                else will it with reason
     * @return true if update successful, else false
     */
    @Override
    public boolean acceptOrRejectCustomerApprovalPendingSoq(AcceptOrRejectPendingPr command) {
        try {
            SupplierOrderQuotation supplierOrderQuotation = supplierOrderQuotationRepository.getSupplierQuotationById(command.getSupplierOrderQuotationId());

            if (supplierOrderQuotation != null) {
                if (command.isCommand()) {
                    supplierOrderQuotation.setSupplierOrderQuotationStatus(SupplierOrderQuotationStatus.APPROVED);
                } else {
                    supplierOrderQuotation.setSupplierOrderQuotationStatus(SupplierOrderQuotationStatus.DECLINED);
                    supplierOrderQuotation.setOrderRejectedDate(command.getOrderRejectedDate());
                    supplierOrderQuotation.setOrderRejectedReason(command.getRejectedReason());
                }
                supplierOrderQuotationRepository.saveSupplierOrderQuotation(supplierOrderQuotation);
                return true;
            } else {
                return false;
            }
        } catch (IllegalArgumentException | EntityExistsException | TransactionRequiredException e) {
            log.error("{}", e.getMessage());
            return false;
        }
    }

}
