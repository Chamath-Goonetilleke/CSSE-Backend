package com.csse.backend.RetrieveDeliveredItems.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "credit_card")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreditCard {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long  id;

    @Column(name = "user_id")
    private Long  userId;

    @Column(name = "card_number")
    private Long  cardNumber;

    @Column(name = "card_holder_name")
    private String  cardHolderName;

    @Column(name = "expiry_date")
    private String  expiryDate;

    @Column(name = "cvc")
    private Long  cvc;
}

