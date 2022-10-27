package com.csse.backend.repositories;

import com.csse.backend.domains.OrderItem;

import java.util.List;

public interface OrderItemRepository {

    OrderItem getOrderItemById(Long id);

    List<OrderItem> getAllCustomerApprovedPurchaseRequisitions(long supplierId);

}
