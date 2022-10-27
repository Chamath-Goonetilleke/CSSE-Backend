package com.csse.backend.services.impl;

import com.csse.backend.domains.OrderItem;
import com.csse.backend.domains.User;
import com.csse.backend.repositories.OrderItemRepository;
import com.csse.backend.repositories.UserRepository;
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

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<OrderItem> getAllCustomerApprovedPurchaseRequisitions(long supplierId) {
        try {
            User user = userRepository.getUserById(supplierId);

            if (user != null) {
                return orderItemRepository.getAllCustomerApprovedPurchaseRequisitions(supplierId);
            } else {
                return null;
            }
        } catch (IllegalArgumentException e) {
            log.error(e.getMessage());
            return null;
        }
    }

}
