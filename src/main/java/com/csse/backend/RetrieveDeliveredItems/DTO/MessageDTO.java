package com.csse.backend.RetrieveDeliveredItems.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessageDTO {
    private Long  id;
    private String  message;
    private Long  sender;
    private Long  receiver;
    private Long  orderReference;
}
