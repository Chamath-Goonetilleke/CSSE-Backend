package com.csse.backend.RetrieveDeliveredItems.Controller;

import com.csse.backend.RetrieveDeliveredItems.DTO.CreditCardDTO;
import com.csse.backend.RetrieveDeliveredItems.DTO.PaymentDTO;
import com.csse.backend.RetrieveDeliveredItems.Entity.CreditCard;
import com.csse.backend.RetrieveDeliveredItems.Services.Abstract.CreditCardService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/payment")
public class CreditCardController {

    final CreditCardService creditCardService;

    public CreditCardController(CreditCardService creditCardService) {
        this.creditCardService = creditCardService;
    }

    @PostMapping("/addCard")
    public ResponseEntity<?> addCard(@RequestBody CreditCardDTO creditCardDTO){
        long id = creditCardService.addCard(creditCardDTO);
        if(id !=0){
            return new ResponseEntity<>(id, HttpStatus.OK);
        }
        return new ResponseEntity<>("Something went wrong", HttpStatus.BAD_REQUEST);

    }

    @GetMapping("/getAllCards/{userId}")
    public ResponseEntity<?> getAllCards(@PathVariable Long userId){
        List<CreditCard> cards = creditCardService.getAllCards(userId);
        if(cards.isEmpty()){
            return new ResponseEntity<>("No Cards", HttpStatus.OK);

        }
        return new ResponseEntity<>(cards, HttpStatus.OK);
    }

    @GetMapping("/getCardById/{id}")
    public ResponseEntity<?> getCardById(@PathVariable Long id){
        CreditCard creditCard = creditCardService.getCardById(id);
        if(creditCard==null){
            return new ResponseEntity<>("Can't find the card", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(creditCard, HttpStatus.OK);
    }

    @PostMapping("/savePayment")
    public ResponseEntity<?> savePayment(@RequestBody PaymentDTO paymentDTO){
        boolean status = creditCardService.savePayment(paymentDTO);
        if(status){
            return new ResponseEntity<>("Saved", HttpStatus.OK);
        }
        return new ResponseEntity<>("Something went wrong", HttpStatus.BAD_REQUEST);
    }

}
