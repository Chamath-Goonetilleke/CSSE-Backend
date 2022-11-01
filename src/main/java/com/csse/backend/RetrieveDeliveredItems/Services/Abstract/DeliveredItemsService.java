package com.csse.backend.RetrieveDeliveredItems.Services.Abstract;

import com.csse.backend.RetrieveDeliveredItems.DTO.DeliveredItemResponseDTO;

import java.util.List;

public interface DeliveredItemsService {

    List<DeliveredItemResponseDTO> getAllOrders();

}
