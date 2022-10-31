package com.csse.backend.RetrieveDeliveredItems.Respository.Impl;

import com.csse.backend.RetrieveDeliveredItems.Entity.DeliveredItem;
//import com.csse.backend.RetrieveDeliveredItems.Entity.User;
import com.csse.backend.RetrieveDeliveredItems.Respository.DeliveredItemsRepository;
import com.csse.backend.domains.SupplierOrderQuotation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.sql.SQLException;
import java.util.List;

@Repository
@Slf4j
public class DeliveredItemsRepositoryImpl implements DeliveredItemsRepository {


    final EntityManager entityManager;

    public DeliveredItemsRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<DeliveredItem> getAllDeliveredItems(){

        try {
            TypedQuery<DeliveredItem> query = entityManager.createQuery("SELECT o FROM DeliveredItem o ",DeliveredItem.class);
            return query.getResultList();

        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }

    @Override
    public SupplierOrderQuotation getDeliveredItemById(Long orderId) {
        return null;
    }

//    @Override
//    public User getUser(Long id) {
//        return entityManager.find(User.class,id);
//    }
}
