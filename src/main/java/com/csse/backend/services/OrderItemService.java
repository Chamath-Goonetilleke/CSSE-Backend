package com.csse.backend.services;

import com.csse.backend.domains.Order;

import java.util.List;

public interface OrderItemService {

    List<Order> getAllCustomerApprovedPurchaseRequisitions();

}
