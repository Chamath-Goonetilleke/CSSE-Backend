package com.csse.backend.RetrieveDeliveredItems.Respository;

import com.csse.backend.UserAndOrderManagement.domains.OrderItem;

public interface OrderRepository {
    OrderItem getOrderById(Long id);
}
