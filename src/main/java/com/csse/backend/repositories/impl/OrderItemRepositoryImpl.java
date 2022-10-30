package com.csse.backend.repositories.impl;

import com.csse.backend.domains.OrderItem;
import com.csse.backend.repositories.OrderItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
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
    public OrderItem getOrderItemById(Long id) {
        return entityManager.find(OrderItem.class, id);
    }

    /**
     * Get all supervisor approved order items by supplier id
     *
     * @param supplierId - Supplier identification
     * @return List<OrderItem>
     */
    @Override
    @Query
    public List<OrderItem> getAllCustomerApprovedPurchaseRequisitions(long supplierId) {
        TypedQuery<OrderItem> query = entityManager
                .createQuery("SELECT o FROM OrderItem o JOIN FETCH o.suppliers s WHERE s.id = :supplierId AND o.orderStatus = 1", OrderItem.class);
        query.setParameter("supplierId", supplierId);
        return query.getResultList();
    }

}
