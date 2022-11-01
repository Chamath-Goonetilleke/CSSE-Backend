package com.csse.backend.RetrieveDeliveredItems.Controller;

import com.csse.backend.RetrieveDeliveredItems.Common.CommonConstants;
import com.csse.backend.RetrieveDeliveredItems.DTO.CreditCardDTO;
import com.csse.backend.RetrieveDeliveredItems.DTO.PaymentDTO;
import com.csse.backend.RetrieveDeliveredItems.Entity.CreditCard;
import com.csse.backend.RetrieveDeliveredItems.Services.Abstract.CreditCardService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(CommonConstants.CREDIT_CARD_BASE_URL)
public class CreditCardController {

    final CreditCardService creditCardService;

    public CreditCardController(CreditCardService creditCardService) {
        this.creditCardService = creditCardService;
    }

    /**
     * Api to add a credit card for a user
     */
    @PostMapping(CommonConstants.ADD_CARD_URL)
    public ResponseEntity<?> addCard(@RequestBody CreditCardDTO creditCardDTO) {
        long id = creditCardService.addCard(creditCardDTO);
        if (id != 0) {
            return new ResponseEntity<>(id, HttpStatus.OK);
        }
        return new ResponseEntity<>(CommonConstants.SOMETHING_WENT_WRONG, HttpStatus.BAD_REQUEST);

    }

    /**
     * Api to get all cards by user id
     */
    @GetMapping(CommonConstants.Get_ALL_USER_CARDS)
    public ResponseEntity<?> getAllCards(@PathVariable Long userId) {
        List<CreditCard> cards = creditCardService.getAllCards(userId);
        if (cards.isEmpty()) {
            return new ResponseEntity<>(CommonConstants.NO_DATA_FOUND, HttpStatus.OK);

        }
        return new ResponseEntity<>(cards, HttpStatus.OK);
    }

    /**
     * Api to get a credit card by card id
     */
    @GetMapping(CommonConstants.GET_CARD_BY_ID)
    public ResponseEntity<?> getCardById(@PathVariable Long id) {
        CreditCard creditCard = creditCardService.getCardById(id);
        if (creditCard == null) {
            return new ResponseEntity<>(CommonConstants.NO_DATA_FOUND, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(creditCard, HttpStatus.OK);
    }

    /**
     * Api to save a payment user done
     */
    @PostMapping(CommonConstants.SAVE_PAYMENT)
    public ResponseEntity<?> savePayment(@RequestBody PaymentDTO paymentDTO) {
        boolean status = creditCardService.savePayment(paymentDTO);
        if (status) {
            return new ResponseEntity<>(CommonConstants.SAVED, HttpStatus.OK);
        }
        return new ResponseEntity<>(CommonConstants.SOMETHING_WENT_WRONG, HttpStatus.BAD_REQUEST);
    }

}
