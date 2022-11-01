package com.csse.backend.RetrieveDeliveredItems.Entity;

import com.csse.backend.RetrieveDeliveredItems.Common.CommonConstants;
import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@Entity
@Getter
@NoArgsConstructor
@Setter
@Table(name = CommonConstants.CREDIT_CARD_DB_NAME)
public class CreditCard {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = CommonConstants.CREDIT_CARD_ID)
    private Long id;

    @Column(name = CommonConstants.CREDIT_CARD_USER_ID)
    private Long userId;

    @Column(name = CommonConstants.CREDIT_CARD_NUMBER)
    private Long cardNumber;

    @Column(name = CommonConstants.CREDIT_CARD_HOLDER_NAME)
    private String cardHolderName;

    @Column(name = CommonConstants.CREDIT_CARD_EXPIRY_DATE)
    private String expiryDate;

    @Column(name = CommonConstants.CREDIT_CARD_CVC)
    private Long cvc;
}

