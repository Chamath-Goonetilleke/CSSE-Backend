package com.csse.backend.repositories.impl;

import com.csse.backend.domains.OrderItem;
import com.csse.backend.repositories.OrderItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
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
        javax.persistence.Query query = entityManager
                .createNativeQuery("select i.* \n" +
                        "from procurement_db.order_item i, procurement_db.order_suppliers s\n" +
                        "where i.order_item_id = s.order_item_id AND i.order_status = 1 AND s.user_id = ?1", OrderItem.class);
        query.setParameter(1, supplierId);
        return query.getResultList();
    }

}
