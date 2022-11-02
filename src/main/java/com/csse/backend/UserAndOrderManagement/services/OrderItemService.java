package com.csse.backend.UserAndOrderManagement.services;

import com.csse.backend.UserAndOrderManagement.domains.Order;

import java.util.List;

public interface OrderItemService {

    List<Order> getAllCustomerApprovedPurchaseRequisitions();

}
