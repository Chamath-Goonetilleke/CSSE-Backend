package com.csse.backend.RetrieveDeliveredItems.Respository;

import com.csse.backend.RetrieveDeliveredItems.Entity.CreditCard;

import java.util.List;

public interface CreditCardRepository {

    void addCard(CreditCard creditCard);

    List<CreditCard> getAllCards(Long userId);

    CreditCard getCardById(Long id);

}
