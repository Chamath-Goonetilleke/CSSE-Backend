package com.csse.backend.RetrieveDeliveredItems.Entity;

import com.csse.backend.RetrieveDeliveredItems.Common.CommonConstants;
import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@Entity
@Getter
@NoArgsConstructor
@Setter
@Table(name = CommonConstants.MESSAGE_DB_NAME)
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = CommonConstants.MESSAGE_ID)
    private Long id;

    @Column(name = CommonConstants.MESSAGE_BODY)
    private String message;

    @Column(name = CommonConstants.MESSAGE_SENDER)
    private Long sender;

    @Column(name = CommonConstants.MESSAGE_RECEIVER)
    private Long receiver;

    @Column(name = CommonConstants.MESSAGE_ORDER_REFERENCE)
    private Long orderReference;


}
