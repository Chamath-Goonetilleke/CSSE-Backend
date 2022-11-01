package com.csse.backend.RetrieveDeliveredItems.Services.Abstract;

import com.csse.backend.RetrieveDeliveredItems.DTO.DeliveredItemResponseDTO;
import com.csse.backend.RetrieveDeliveredItems.Entity.DeliveredItem;
import com.csse.backend.domains.SupplierOrderQuotation;

import java.util.List;

public interface DeliveredItemsService {

    List<DeliveredItemResponseDTO> getAllOrders();
    SupplierOrderQuotation getOrderById(Long orderId);

}