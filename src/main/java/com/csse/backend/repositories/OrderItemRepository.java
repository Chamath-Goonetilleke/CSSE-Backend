package com.csse.backend.repositories;

import com.csse.backend.domains.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<Order, Long> {

}
