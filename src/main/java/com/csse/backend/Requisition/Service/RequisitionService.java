package com.csse.backend.Requisition.Service;

import com.csse.backend.Requisition.Dto.OrderDto;

import org.json.JSONException;
import org.springframework.http.ResponseEntity;

public interface RequisitionService {
    ResponseEntity<?> insertNewRequisition(OrderDto orderDto);

    ResponseEntity<?> getPendingOrdersByName();
    ResponseEntity<?> getApprovedOrdersByName();
    ResponseEntity<?> getList() throws JSONException;
}
