package com.csse.backend.services.impl;

import com.csse.backend.domains.Order;
import com.csse.backend.enums.OrderStatus;
import com.csse.backend.repositories.OrderItemRepository;
import com.csse.backend.services.OrderItemService;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class OrderItemServiceImpl implements OrderItemService {

    @Autowired
    private OrderItemRepository orderItemRepository;

    /**
     * Get all supervisor approved purchase requisitions
     *
     * @return - List of approved orders
     */
    @Override
    public List<Order> getAllCustomerApprovedPurchaseRequisitions() {
        try {
            List<Order> orders = orderItemRepository.findAll();
            orders.removeIf(order -> !order.getOrderStatus().equals(OrderStatus.APPROVED));
            return orders;
        } catch (IllegalArgumentException e) {
            log.error(e.getMessage());
            return null;
        }
    }

}
