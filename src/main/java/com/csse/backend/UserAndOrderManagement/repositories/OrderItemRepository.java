package com.csse.backend.UserAndOrderManagement.repositories;

import com.csse.backend.UserAndOrderManagement.domains.OrderItem;

import java.util.List;

public interface OrderItemRepository {

    OrderItem getOrderItemById(Long id);

    List<OrderItem> getAllCustomerApprovedPurchaseRequisitions(long supplierId);

}
