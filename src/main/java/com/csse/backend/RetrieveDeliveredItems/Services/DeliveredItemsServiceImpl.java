package com.csse.backend.RetrieveDeliveredItems.Services;

import com.csse.backend.RetrieveDeliveredItems.Entity.DeliveredItem;
import com.csse.backend.RetrieveDeliveredItems.Respository.DeliveredItemsRepository;
import com.csse.backend.RetrieveDeliveredItems.Services.Abstract.DeliveredItemsService;
import com.csse.backend.domains.SupplierOrderQuotation;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class DeliveredItemsServiceImpl implements DeliveredItemsService {

    final DeliveredItemsRepository deliveredItemsRepository;

    public DeliveredItemsServiceImpl(DeliveredItemsRepository deliveredItemsRepository) {
        this.deliveredItemsRepository = deliveredItemsRepository;
    }

    @Override
    public List<DeliveredItem> getAllOrders() {

        try {
            return deliveredItemsRepository.getAllDeliveredItems();
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public SupplierOrderQuotation getOrderById(Long orderId) {
        return null;
    }
}
