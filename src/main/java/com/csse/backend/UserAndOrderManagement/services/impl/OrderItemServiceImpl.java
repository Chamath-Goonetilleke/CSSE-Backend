package com.csse.backend.UserAndOrderManagement.services.impl;

import com.csse.backend.UserAndOrderManagement.domains.OrderItem;
import com.csse.backend.UserAndOrderManagement.domains.User;
import com.csse.backend.UserAndOrderManagement.repositories.OrderItemRepository;
import com.csse.backend.UserAndOrderManagement.repositories.UserRepository;
import com.csse.backend.UserAndOrderManagement.services.OrderItemService;

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

    /**
     * Get all supervisor approved purchase requisitions
     *
     * @param supplierId - Supplier identification
     * @return List<OrderItem></OrderItem>
     */
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
