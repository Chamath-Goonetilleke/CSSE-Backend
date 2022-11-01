package com.csse.backend.services.impl;

import com.csse.backend.domains.Order;
import com.csse.backend.domains.Item;
import com.csse.backend.domains.User;
import com.csse.backend.dtos.AcceptOrRejectPendingPr;
import com.csse.backend.dtos.SupplierAcceptPrDto;
import com.csse.backend.enums.ItemStatus;
import com.csse.backend.enums.OrderStatus;
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
    OrderItemRepository orderItemRepository;

    @Autowired
    UserRepository userRepository;

    /**
     * Create a supplier order quotation
     *
     * @param supplierAcceptPrDto - New Supplier order quotation
     * @return true if new Supplier order quotation created successfully, else false
     */
    @Override
    public boolean acceptCustomerApprovedPurchaseRequisition(SupplierAcceptPrDto supplierAcceptPrDto) {

        Item item = new Item();

        if (supplierAcceptPrDto.getSupplierId() != null) {
            try {
                User user = userRepository.getUserById(supplierAcceptPrDto.getSupplierId());
                if (user != null) {
                    item.setSupplier(user);
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
                Order order = orderItemRepository.getOrderItemById(supplierAcceptPrDto.getOrderItemId());
                if (order != null) {
                    item.setOrder(order);
                } else {
                    return false;
                }
            } catch (IllegalArgumentException e) {
                log.error(e.getMessage());
                return false;
            }
        }

        if (supplierAcceptPrDto.getQuantity() != null) {
            item.setSupplyAmount(supplierAcceptPrDto.getQuantity());
        }

        if (supplierAcceptPrDto.getUnitPrice() != null) {
            item.setSupplyPricePerUnit(supplierAcceptPrDto.getUnitPrice());
        } else {
            return false;
        }

        if (supplierAcceptPrDto.getBrand() != null) {
            item.setSupplyBrand(supplierAcceptPrDto.getBrand());
        } else {
            return false;
        }

        if (supplierAcceptPrDto.getDateCanDeliver() != null) {
            item.setSupplyDate(supplierAcceptPrDto.getDateCanDeliver());
        } else {
            return false;
        }

        item.setItemStatus(ItemStatus.PARTIALLY_APPROVED);

        try {
            supplierOrderQuotationRepository.saveSupplierOrderQuotation(item);
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
    public List<Item> getAllCustomerAndSupplierAcceptedPurchaseRequisitions(long supplierId) {
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
        Item item = supplierOrderQuotationRepository.getSupplierQuotationById(supplierOrderQuotationId);

        if (item != null) {
            item.setAdviceNote(link);
            try {
                supplierOrderQuotationRepository.saveSupplierOrderQuotation(item);
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
        Item item = supplierOrderQuotationRepository.getSupplierQuotationById(supplierOrderQuotationId);

        if (item != null) {
            item.setInvoice(link);
            try {
                supplierOrderQuotationRepository.saveSupplierOrderQuotation(item);
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
    public List<Item> getAllCustomerAcceptedPurchaseRequisitions(long supplierId) {
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
            Item item = supplierOrderQuotationRepository.getSupplierQuotationById(supplierOrderQuotationId);

            if (item != null) {
                item.setItemStatus(ItemStatus.PLACED);
                supplierOrderQuotationRepository.saveSupplierOrderQuotation(item);
                Order order = orderItemRepository.getOrderItemById(item.getOrder().getId());
                order.setOrderStatus(OrderStatus.COMPLETED);
                orderItemRepository.updateOrderItem(order);
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
    public List<Item> getAllCustomerApprovalPendingSoq(long employeeUserId) {
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
            Item item = supplierOrderQuotationRepository.getSupplierQuotationById(command.getSupplierOrderQuotationId());

            if (item != null) {
                if (command.isCommand()) {
                    item.setItemStatus(ItemStatus.APPROVED);
                } else {
                    item.setItemStatus(ItemStatus.DECLINED);
                    item.setOrderRejectedDate(command.getOrderRejectedDate());
                    item.setOrderRejectedReason(command.getRejectedReason());
                }
                supplierOrderQuotationRepository.saveSupplierOrderQuotation(item);
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
