package com.csse.backend.UserAndOrderManagement.services.impl;

import com.csse.backend.UserAndOrderManagement.domains.Order;
import com.csse.backend.UserAndOrderManagement.domains.Item;
import com.csse.backend.UserAndOrderManagement.domains.User;
import com.csse.backend.UserAndOrderManagement.dtos.AcceptOrRejectPendingPr;
import com.csse.backend.UserAndOrderManagement.dtos.SupplierAcceptPrDto;
import com.csse.backend.UserAndOrderManagement.enums.ItemStatus;
import com.csse.backend.UserAndOrderManagement.enums.OrderStatus;
import com.csse.backend.UserAndOrderManagement.repositories.OrderItemRepository;
import com.csse.backend.UserAndOrderManagement.repositories.SupplierOrderQuotationRepository;
import com.csse.backend.UserAndOrderManagement.repositories.UserRepository;
import com.csse.backend.UserAndOrderManagement.services.SupplierOrderQuotationService;

import java.util.List;
import java.util.Optional;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.OptimisticLockingFailureException;
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
     * @return - true if new Supplier order quotation created successfully, else false
     */
    @Override
    public boolean acceptCustomerApprovedPurchaseRequisition(SupplierAcceptPrDto supplierAcceptPrDto) {
        Item item = new Item();

        if (supplierAcceptPrDto.getSupplierId() != null) {
            try {
                Optional<User> user = userRepository.findById(supplierAcceptPrDto.getSupplierId());
                if (user.isPresent()) {
                    item.setSupplier(user.get());
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
                Optional<Order> order = orderItemRepository.findById(supplierAcceptPrDto.getOrderItemId());
                if (order.isPresent()) {
                    item.setOrder(order.get());
                } else {
                    return false;
                }
            } catch (IllegalArgumentException e) {
                log.error(e.getMessage());
                return false;
            }
        }

        if (supplierAcceptPrDto.getQuantity() != null) {
            item.setDeliverableAmount(supplierAcceptPrDto.getQuantity());
        }

        if (supplierAcceptPrDto.getUnitPrice() != null) {
            item.setPricePerUnit(supplierAcceptPrDto.getUnitPrice());
        } else {
            return false;
        }

        if (supplierAcceptPrDto.getBrand() != null) {
            item.setDeliverableBrand(supplierAcceptPrDto.getBrand());
        } else {
            return false;
        }

        if (supplierAcceptPrDto.getDateCanDeliver() != null) {
            item.setDeliverableDate(supplierAcceptPrDto.getDateCanDeliver());
        } else {
            return false;
        }

        item.setItemStatus(ItemStatus.PARTIALLY_APPROVED);

        try {
            supplierOrderQuotationRepository.save(item);
            return true;
        } catch (IllegalArgumentException | OptimisticLockingFailureException e) {
            log.error("{}", e.getMessage());
            return false;
        }
    }

    /**
     * Get all ready to deliver items
     *
     * @param supplierId - Supplier identification
     * @return - List of ready to deliver supplier order quotations
     */
    @Override
    public List<Item> getAllCustomerAndSupplierAcceptedPurchaseRequisitions(long supplierId) {
        try {
            List<Item> items = supplierOrderQuotationRepository.findAll();
            items.removeIf(item -> !item.getSupplier().getId().equals(supplierId) || !item.getItemStatus().equals(ItemStatus.PLACED));
            return items;
        } catch (NullPointerException | UnsupportedOperationException e) {
            log.error("{}", e.getMessage());
            return null;
        }
    }

    /**
     * Update an advice note notice for an existing supplier order quotation
     *
     * @param supplierOrderQuotationId - Supplier oder quotation identification
     * @param link                     - Advice note document upload cloud storage link
     * @return - true if new Supplier order quotation created successfully, else false
     */
    @Override
    public boolean updateAdviceNote(long supplierOrderQuotationId, String link) {
        try {
            Optional<Item> item = supplierOrderQuotationRepository.findById(supplierOrderQuotationId);
            if (item.isPresent()) {
                item.get().setAdviceNote(link);
                supplierOrderQuotationRepository.save(item.get());
                return true;
            } else {
                return false;
            }
        } catch (IllegalArgumentException e) {
            log.error("{}", e.getMessage());
            return false;
        }
    }

    /**
     * Update an invoice notice for an existing supplier order quotation
     *
     * @param supplierOrderQuotationId - Supplier oder quotation identification
     * @param link                     - Invoice document upload cloud storage link
     * @return - true if new Supplier order quotation created successfully, else false
     */
    @Override
    public boolean updateInvoice(long supplierOrderQuotationId, String link) {
        try {
            Optional<Item> item = supplierOrderQuotationRepository.findById(supplierOrderQuotationId);
            if (item.isPresent()) {
                item.get().setInvoice(link);
                supplierOrderQuotationRepository.save(item.get());
                return true;
            } else {
                return false;
            }
        } catch (IllegalArgumentException e) {
            log.error("{}", e.getMessage());
            return false;
        }
    }

    /**
     * Get all supervisor accepted supplier order quotations
     *
     * @param supplierId - Supplier identification
     * @return - List of customer accepted supplier order quotations
     */
    @Override
    public List<Item> getAllCustomerAcceptedPurchaseRequisitions(long supplierId) {
        try {
            List<Item> items = supplierOrderQuotationRepository.findAll();
            items.removeIf(item -> !item.getSupplier().getId().equals(supplierId) || !item.getItemStatus().equals(ItemStatus.APPROVED));
            return items;
        } catch (NullPointerException | UnsupportedOperationException e) {
            log.error("{}", e.getMessage());
            return null;
        }
    }

    /**
     * Accept a supervisor accepted supplier order quotation by relevant supplier
     *
     * @param supplierOrderQuotationId - Supplier order quotation identification
     * @return - true if update successful, else false
     */
    @Override
    public boolean acceptCustomerAcceptedOrderQuotation(long supplierOrderQuotationId) {
        try {
            Optional<Item> item = supplierOrderQuotationRepository.findById(supplierOrderQuotationId);
            if (item.isPresent()) {
                item.get().setItemStatus(ItemStatus.PLACED);
                supplierOrderQuotationRepository.save(item.get());
                Optional<Order> order = orderItemRepository.findById(item.get().getOrder().getId());
                if (order.isPresent()) {
                    order.get().setOrderStatus(OrderStatus.COMPLETED);
                    orderItemRepository.save(order.get());
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        } catch (IllegalArgumentException | OptimisticLockingFailureException e) {
            log.error("{}", e.getMessage());
            return false;
        }
    }

    /**
     * Get all supervisor approval pending supplier order quotations
     *
     * @param employeeUserId - Supervisor identification
     * @return - List of customer approval pending supplier order quotations
     */
    @Override
    public List<Item> getAllCustomerApprovalPendingSoq(long employeeUserId) {
        try {
            List<Item> items = supplierOrderQuotationRepository.findAll();
            items.removeIf(item -> !item.getOrder().getCreatedBy().getId().equals(employeeUserId) || !item.getItemStatus().equals(ItemStatus.PARTIALLY_APPROVED));
            return items;
        } catch (NullPointerException | UnsupportedOperationException e) {
            log.error("{}", e.getMessage());
            return null;
        }
    }

    /**
     * Approve ar reject supplier order quotation by supervisor
     *
     * @param command - If command inside AcceptOrRejectPendingPr object is true will accept supplier order quotation,
     *                else will it with reason
     * @return - true if update successful, else false
     */
    @Override
    public boolean acceptOrRejectCustomerApprovalPendingSoq(AcceptOrRejectPendingPr command) {
        try {
            Optional<Item> item = supplierOrderQuotationRepository.findById(command.getSupplierOrderQuotationId());
            if (item.isPresent()) {
                if (!command.isCommand()) {
                    item.get().setItemStatus(ItemStatus.DECLINED);
                    item.get().setRejectedReason(command.getRejectedReason());
                    item.get().setRejectedDate(command.getOrderRejectedDate());
                } else {
                    item.get().setItemStatus(ItemStatus.APPROVED);
                }
                supplierOrderQuotationRepository.save(item.get());
                return true;
            } else {
                return false;
            }
        } catch (IllegalArgumentException e) {
            log.error("{}", e.getMessage());
            return false;
        }
    }

}
