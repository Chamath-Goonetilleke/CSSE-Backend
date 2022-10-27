package com.csse.backend.repositories.impl;

import com.csse.backend.domains.OrderItem;
import com.csse.backend.repositories.OrderItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class OrderItemRepositoryImpl implements OrderItemRepository {

    @Autowired
    private EntityManager entityManager;

    @Override
    public OrderItem getOrderItemById(Long id) {
        return entityManager.find(OrderItem.class, id);
    }

    @Override
    public List<OrderItem> getAllCustomerApprovedPurchaseRequisitions(long supplierId) {
        TypedQuery<OrderItem> query = entityManager
                .createQuery("SELECT o FROM OrderItem o, o.suppliers s WHERE s.id = :supplierId AND o.orderStatus = 1", OrderItem.class);
        query.setParameter("supplierId", supplierId);
        return query.getResultList();
    }

}
