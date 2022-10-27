package com.csse.backend.services;

import com.csse.backend.domains.OrderItem;

import java.util.List;

public interface OrderItemService {

    List<OrderItem> getAllCustomerApprovedPurchaseRequisitions(long supplierId);

}
