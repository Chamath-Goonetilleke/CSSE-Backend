package com.csse.backend.RetrieveDeliveredItems.Respository.Impl;

import com.csse.backend.RetrieveDeliveredItems.Entity.Message;
import com.csse.backend.RetrieveDeliveredItems.Respository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@Repository
public class MessageRepositoryImpl implements MessageRepository {

    final EntityManager entityManager;

    public MessageRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public Message addMessage(Message message) {
        entityManager.persist(message);
        return message;
    }
}
