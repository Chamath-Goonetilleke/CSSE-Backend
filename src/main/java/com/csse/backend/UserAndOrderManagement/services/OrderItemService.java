package com.csse.backend.UserAndOrderManagement.services;

import com.csse.backend.UserAndOrderManagement.domains.OrderItem;

import java.util.List;

public interface OrderItemService {

    List<OrderItem> getAllCustomerApprovedPurchaseRequisitions(long supplierId);

}
