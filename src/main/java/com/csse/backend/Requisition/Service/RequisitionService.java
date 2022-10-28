package com.csse.backend.Requisition.Service;

import com.csse.backend.Requisition.Dto.OrderDto;
import com.csse.backend.Requisition.Model.Order;
import org.springframework.http.ResponseEntity;

public interface RequisitionService {
    ResponseEntity<?> insertNewRequisition(OrderDto orderDto);

    ResponseEntity<?> getPendingOrdersByName(String name);
}
