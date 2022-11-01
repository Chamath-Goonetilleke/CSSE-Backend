package com.csse.backend.RetrieveDeliveredItems.Respository.Impl;

import com.csse.backend.RetrieveDeliveredItems.Entity.Payment;
import com.csse.backend.RetrieveDeliveredItems.Respository.PaymentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@Repository
@Slf4j
public class PaymentRepositoryImpl implements PaymentRepository {

    final
    EntityManager entityManager;

    public PaymentRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    /**
     * Save payment details in the database
     */
    @Override
    @Transactional
    public void savePayment(Payment payment) {
        try {
            entityManager.persist(payment);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }
}
