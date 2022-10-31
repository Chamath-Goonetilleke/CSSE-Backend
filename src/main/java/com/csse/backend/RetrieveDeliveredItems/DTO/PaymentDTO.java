package com.csse.backend.RetrieveDeliveredItems.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentDTO {
    private Long id;
    private Long UserId;
    private Long referenceNumber;
    private Float totalAmount;
    private Long cardId;
}
