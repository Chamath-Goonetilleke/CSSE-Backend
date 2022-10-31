package com.csse.backend.RetrieveDeliveredItems.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeliveredItemResponseDTO {

    private ReceiptDTO details;

}
