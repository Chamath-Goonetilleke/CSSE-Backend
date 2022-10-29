package com.csse.backend.RetrieveDeliveredItems.Services;

import com.csse.backend.RetrieveDeliveredItems.DTO.CreditCardDTO;
import com.csse.backend.RetrieveDeliveredItems.Entity.CreditCard;
import com.csse.backend.RetrieveDeliveredItems.Respository.CreditCardRepository;
import com.csse.backend.RetrieveDeliveredItems.Services.Abstract.CreditCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
public class CreditCardServiceImpl implements CreditCardService {

    final CreditCardRepository creditCardRepository;

    public CreditCardServiceImpl(CreditCardRepository creditCardRepository) {
        this.creditCardRepository = creditCardRepository;
    }

    @Override
    public boolean addCard(CreditCardDTO creditCardDTO) {

        try {
            CreditCard creditCard = new CreditCard();
            creditCard.setUserId(creditCardDTO.getUserId());
            creditCard.setCardNumber(creditCardDTO.getCardNumber());
            creditCard.setCardHolderName(creditCardDTO.getCardHolderName());
            creditCard.setExpiryDate(creditCardDTO.getExpiryDate());
            creditCard.setCvc(creditCardDTO.getCvc());

            creditCardRepository.addCard(creditCard);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public List<CreditCard> getAllCards(Long userId) {
        try {
            return creditCardRepository.getAllCards(userId);
        }catch (Exception e) {
            return Collections.emptyList();
        }
    }

    @Override
    public CreditCard getCardById(Long id) {
        try {
            return creditCardRepository.getCardById(id);
        }catch (Exception e){
            return null;
        }
    }
}
