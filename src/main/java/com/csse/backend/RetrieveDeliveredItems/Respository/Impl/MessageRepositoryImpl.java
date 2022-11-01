package com.csse.backend.RetrieveDeliveredItems.Respository.Impl;

import com.csse.backend.RetrieveDeliveredItems.Entity.Message;
import com.csse.backend.RetrieveDeliveredItems.Respository.MessageRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@Repository
@Slf4j
public class MessageRepositoryImpl implements MessageRepository {

    final EntityManager entityManager;

    public MessageRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    /**
     * Save a message in database
     */
    @Override
    @Transactional
    public Message addMessage(Message message) {
        try {
            entityManager.persist(message);
            return message;
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }
}
