package com.csse.backend.RetrieveDeliveredItems.Services;

import com.csse.backend.RetrieveDeliveredItems.DTO.CreditCardDTO;
import com.csse.backend.RetrieveDeliveredItems.DTO.PaymentDTO;
import com.csse.backend.RetrieveDeliveredItems.Entity.CreditCard;
import com.csse.backend.RetrieveDeliveredItems.Entity.Payment;
import com.csse.backend.RetrieveDeliveredItems.Respository.CreditCardRepository;
import com.csse.backend.RetrieveDeliveredItems.Respository.PaymentRepository;
import com.csse.backend.RetrieveDeliveredItems.Services.Abstract.CreditCardService;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

@Component
public class CreditCardServiceImpl implements CreditCardService {

    final CreditCardRepository creditCardRepository;
    final PaymentRepository paymentRepository;

    public CreditCardServiceImpl(CreditCardRepository creditCardRepository, PaymentRepository paymentRepository) {
        this.creditCardRepository = creditCardRepository;
        this.paymentRepository = paymentRepository;
    }

    @Override
    public long addCard(CreditCardDTO creditCardDTO) {

        try {
            CreditCard creditCard = new CreditCard();
            creditCard.setUserId(creditCardDTO.getUserId());
            creditCard.setCardNumber(creditCardDTO.getCardNumber());
            creditCard.setCardHolderName(creditCardDTO.getCardHolderName());
            creditCard.setExpiryDate(creditCardDTO.getExpiryDate());
            creditCard.setCvc(creditCardDTO.getCvc());

            CreditCard card = creditCardRepository.addCard(creditCard);

            if (card.getId()==null){
                throw new SQLException("Error in card creation");
            }
            return card.getId();

        } catch (Exception e) {
            return 0;
        }
    }

    @Override
    public List<CreditCard> getAllCards(Long userId) {
        try {
            return creditCardRepository.getAllCards(userId);
        } catch (Exception e) {
            return Collections.emptyList();
        }
    }

    @Override
    public CreditCard getCardById(Long id) {
        try {
            return creditCardRepository.getCardById(id);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public boolean savePayment(PaymentDTO paymentDTO) {
        try {
            Payment payment = new Payment();
            payment.setUserId(paymentDTO.getUserId());
            payment.setReferenceNumber(paymentDTO.getReferenceNumber());
            payment.setTotalAmount(paymentDTO.getTotalAmount());
            payment.setCardId(paymentDTO.getCardId());

            paymentRepository.savePayment(payment);

            return true;
        }catch (Exception e){
            return false;
        }
    }
}
