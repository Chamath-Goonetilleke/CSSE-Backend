package com.csse.backend.UserAndOrderManagement.repositories;

import com.csse.backend.UserAndOrderManagement.domains.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<Order, Long> {

}
