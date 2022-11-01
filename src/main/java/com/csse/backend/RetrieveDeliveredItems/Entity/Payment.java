package com.csse.backend.RetrieveDeliveredItems.Entity;

import com.csse.backend.RetrieveDeliveredItems.Common.CommonConstants;
import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@Entity
@Getter
@NoArgsConstructor
@Setter
@Table(name = CommonConstants.PAYMENT_DB_NAME)
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = CommonConstants.PAYMENT_ID)
    private Long id;

    @Column(name = CommonConstants.PAYMENT_USER_ID)
    private Long UserId;

    @Column(name = CommonConstants.PAYMENT_REFERENCE_NUMBER)
    private Long referenceNumber;

    @Column(name = CommonConstants.PAYMENT_TOTAL_AMOUNT)
    private Float totalAmount;

    @Column(name = CommonConstants.PAYMENT_CARD_ID)
    private Long cardId;


}
