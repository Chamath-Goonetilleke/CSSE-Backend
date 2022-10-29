package com.csse.backend.RetrieveDeliveredItems.Services.Abstract;

import com.csse.backend.RetrieveDeliveredItems.DTO.CreditCardDTO;
import com.csse.backend.RetrieveDeliveredItems.Entity.CreditCard;

import java.util.List;

public interface CreditCardService {

    boolean addCard(CreditCardDTO creditCardDTO);

    List<CreditCard> getAllCards(Long userId);

    CreditCard getCardById(Long id);
}
