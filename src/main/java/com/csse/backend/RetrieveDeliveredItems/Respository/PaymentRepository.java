package com.csse.backend.RetrieveDeliveredItems.Respository;

import com.csse.backend.RetrieveDeliveredItems.DTO.PaymentDTO;
import com.csse.backend.RetrieveDeliveredItems.Entity.Payment;

public interface PaymentRepository {

    void savePayment(Payment payment);

}
