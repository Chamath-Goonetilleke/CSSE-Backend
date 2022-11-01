package com.csse.backend.RetrieveDeliveredItems.Respository.Impl;

import com.csse.backend.RetrieveDeliveredItems.Entity.DeliveredItem;
import com.csse.backend.RetrieveDeliveredItems.Respository.DeliveredItemsRepository;
import com.csse.backend.domains.Item;
import com.csse.backend.domains.Order;
import com.csse.backend.domains.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

@Repository
@Slf4j
public class DeliveredItemsRepositoryImpl implements DeliveredItemsRepository {


    final EntityManager entityManager;

    public DeliveredItemsRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Item> getAllDeliveredItems(){

        try {
            TypedQuery<Item> query = entityManager.createQuery("SELECT o FROM Item o ",Item.class);
            return query.getResultList();

        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
//        User user= new User();
//        user.setId(12L);
//        user.setUserFirstAndLastName("P.K Withanage");
//
//        Order order = new Order();
//        order.setId(789456123L);
//        order.setOrderName("Cement");
//
//        List<DeliveredItem> orderList=new ArrayList<>();
//        DeliveredItem deliveredItem= new DeliveredItem();
//        deliveredItem.setId(1L);
//        deliveredItem.setSupplier(user);

//
//return orderList;
    }

    @Override
    public Item getDeliveredItemById(Long orderId) {
        return null;
    }

//    @Override
//    public User getUser(Long id) {
//        return entityManager.find(User.class,id);
//    }
}
