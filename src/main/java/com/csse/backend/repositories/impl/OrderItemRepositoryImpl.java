package com.csse.backend.repositories.impl;

import com.csse.backend.domains.Order;
import com.csse.backend.repositories.OrderItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class OrderItemRepositoryImpl implements OrderItemRepository {

    @Autowired
    private EntityManager entityManager;

    /**
     * Get a site manager created order item by its identification
     *
     * @param id - Order item identification
     * @return OrderItem
     */
    @Override
    public Order getOrderItemById(Long id) {
        return entityManager.find(Order.class, id);
    }

    /**
     * Get all supervisor approved order items by supplier id
     *
     * @return List<OrderItem>
     */
    @Override
    @Query
    public List<Order> getAllCustomerApprovedPurchaseRequisitions() {
        javax.persistence.Query query = entityManager
                .createNativeQuery("select i.* from procurement_db.order_item i where i.order_status = 1", Order.class);
        return query.getResultList();
    }

    /**
     * Update order item
     *
     * @param order - Order object needs to be updated
     */
    @Override
    @Transactional
    public void updateOrderItem(Order order) {
        entityManager.merge(order);
    }

}
