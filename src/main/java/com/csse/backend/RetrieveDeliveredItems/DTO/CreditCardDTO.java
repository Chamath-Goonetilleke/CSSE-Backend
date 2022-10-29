package com.csse.backend.RetrieveDeliveredItems.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreditCardDTO {
    private Long  id;
    private Long  userId;
    private Long  cardNumber;
    private String  cardHolderName;
    private String  expiryDate;
    private Long  cvc;
}
