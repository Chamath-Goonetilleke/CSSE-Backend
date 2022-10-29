package com.csse.backend.RetrieveDeliveredItems.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "message")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long  id;

    @Column(name = "message")
    private String  message;

    @Column(name = "sender")
    private Long  sender;

    @Column(name = "receiver")
    private Long  receiver;

    @Column(name = "order_reference")
    private Long  orderReference;


}
