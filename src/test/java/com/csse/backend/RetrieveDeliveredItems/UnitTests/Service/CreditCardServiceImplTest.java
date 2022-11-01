package com.csse.backend.RetrieveDeliveredItems.UnitTests.Service;

import com.csse.backend.RetrieveDeliveredItems.DTO.CreditCardDTO;
import com.csse.backend.RetrieveDeliveredItems.Entity.CreditCard;
import com.csse.backend.RetrieveDeliveredItems.Respository.CreditCardRepository;
import com.csse.backend.RetrieveDeliveredItems.Respository.PaymentRepository;
import com.csse.backend.RetrieveDeliveredItems.Services.CreditCardServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CreditCardServiceImplTest {

    @Autowired
    CreditCardServiceImpl creditCardService;
    @MockBean
    CreditCardRepository creditCardRepository;
    @MockBean
    PaymentRepository paymentRepository;

    @Test
    @DisplayName("AddCardSuccess")
    void AddCardSuccess(){

        CreditCardDTO creditCardDTO = new CreditCardDTO();
        creditCardDTO.setUserId(1L);
        creditCardDTO.setCardNumber(123456789456L);
        creditCardDTO.setCardHolderName("chamath");
        creditCardDTO.setExpiryDate("03/23");
        creditCardDTO.setCvc(123L);

        CreditCard creditCard = new CreditCard();
        creditCard.setId(1L);

        Mockito.when(creditCardRepository.addCard(Mockito.any())).thenReturn(creditCard);

        Long result= creditCardService.addCard(creditCardDTO);
        Assertions.assertEquals(1L,result);

    }
    @Test
    @DisplayName("AddCardFailure")
    void AddCardFailure(){

        CreditCardDTO creditCardDTO = new CreditCardDTO();
        creditCardDTO.setUserId(1L);
        creditCardDTO.setCardNumber(123456789456L);
        creditCardDTO.setCardHolderName("chamath");
        creditCardDTO.setExpiryDate("03/23");
        creditCardDTO.setCvc(123L);

        CreditCard creditCard = new CreditCard();

        Mockito.when(creditCardRepository.addCard(Mockito.any())).thenReturn(creditCard);

        Long result= creditCardService.addCard(creditCardDTO);
        Assertions.assertEquals(0,result);

    }






}
