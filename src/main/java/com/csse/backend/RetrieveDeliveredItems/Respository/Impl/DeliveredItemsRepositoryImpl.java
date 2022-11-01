package com.csse.backend.RetrieveDeliveredItems.Respository.Impl;

import com.csse.backend.RetrieveDeliveredItems.Common.CommonConstants;
import com.csse.backend.RetrieveDeliveredItems.Respository.DeliveredItemsRepository;
import com.csse.backend.domains.Item;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
@Slf4j
public class DeliveredItemsRepositoryImpl implements DeliveredItemsRepository {


    final EntityManager entityManager;

    public DeliveredItemsRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    /**
     * Get all delivered items from database
     */
    @Override
    public List<Item> getAllDeliveredItems() {

        try {
            TypedQuery<Item> query = entityManager.createQuery(CommonConstants.GET_ALL_DELIVERED_ITEMS, Item.class);
            return query.getResultList();

        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }

}
