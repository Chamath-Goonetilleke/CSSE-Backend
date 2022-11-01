package com.csse.backend.repositories;

import com.csse.backend.domains.Order;

import java.util.List;

public interface OrderItemRepository {

    Order getOrderItemById(Long id);

    List<Order> getAllCustomerApprovedPurchaseRequisitions();

    void updateOrderItem(Order order);

}
