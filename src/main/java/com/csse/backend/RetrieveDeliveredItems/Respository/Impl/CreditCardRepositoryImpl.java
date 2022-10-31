package com.csse.backend.RetrieveDeliveredItems.Respository.Impl;

import com.csse.backend.RetrieveDeliveredItems.Entity.CreditCard;
import com.csse.backend.RetrieveDeliveredItems.Respository.CreditCardRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class CreditCardRepositoryImpl implements CreditCardRepository {

    final EntityManager entityManager;

    public CreditCardRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public CreditCard addCard(CreditCard creditCard) {
        entityManager.persist(creditCard);
        return creditCard;

    }

    @Override
    public List<CreditCard> getAllCards(Long userId) {
        TypedQuery<CreditCard> query = entityManager.createQuery(" SELECT c FROM CreditCard c WHERE c.userId = :userId ", CreditCard.class);
        query.setParameter("userId",userId);
        return query.getResultList();
    }

    @Override
    public CreditCard getCardById(Long id) {
        return entityManager.find(CreditCard.class,id);
    }
}
