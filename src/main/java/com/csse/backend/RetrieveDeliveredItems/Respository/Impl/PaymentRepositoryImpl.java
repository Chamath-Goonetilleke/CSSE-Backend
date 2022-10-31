package com.csse.backend.RetrieveDeliveredItems.Respository.Impl;

import com.csse.backend.RetrieveDeliveredItems.DTO.PaymentDTO;
import com.csse.backend.RetrieveDeliveredItems.Entity.Payment;
import com.csse.backend.RetrieveDeliveredItems.Respository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@Repository
public class PaymentRepositoryImpl implements PaymentRepository {

    final
    EntityManager entityManager;

    public PaymentRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void savePayment(Payment payment) {
        entityManager.persist(payment);

    }
}
