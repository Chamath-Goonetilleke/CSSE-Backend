package com.csse.backend.RetrieveDeliveredItems.Respository.Impl;

import com.csse.backend.RetrieveDeliveredItems.Common.CommonConstants;
import com.csse.backend.RetrieveDeliveredItems.Entity.CreditCard;
import com.csse.backend.RetrieveDeliveredItems.Respository.CreditCardRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Slf4j
public class CreditCardRepositoryImpl implements CreditCardRepository {

    final EntityManager entityManager;

    public CreditCardRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    /**
     * Save card details in database
     */
    @Override
    @Transactional
    public CreditCard addCard(CreditCard creditCard) {
        try {
            entityManager.persist(creditCard);
            return creditCard;
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }

    /**
     * Get all credit cards from database
     */
    @Override
    public List<CreditCard> getAllCards(Long userId) {
        try {
            TypedQuery<CreditCard> query = entityManager.createQuery(CommonConstants.GET_ALL_CARDS_QUERY, CreditCard.class);
            query.setParameter(CommonConstants.USER_ID_PARAM, userId);
            return query.getResultList();
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }

    /**
     * Get card by card id
     */
    @Override
    public CreditCard getCardById(Long id) {
        try {
            return entityManager.find(CreditCard.class, id);
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }
}
