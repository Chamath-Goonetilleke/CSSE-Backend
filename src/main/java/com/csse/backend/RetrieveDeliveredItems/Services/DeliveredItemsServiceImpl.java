package com.csse.backend.RetrieveDeliveredItems.Services;

import com.csse.backend.RetrieveDeliveredItems.DTO.DeliveredItemResponseDTO;
import com.csse.backend.RetrieveDeliveredItems.DTO.ReceiptDTO;
import com.csse.backend.RetrieveDeliveredItems.Entity.DeliveredItem;
import com.csse.backend.RetrieveDeliveredItems.Respository.DeliveredItemsRepository;
import com.csse.backend.RetrieveDeliveredItems.Services.Abstract.DeliveredItemsService;
import com.csse.backend.domains.OrderItem;
import com.csse.backend.domains.SupplierOrderQuotation;
import com.csse.backend.domains.User;
import com.csse.backend.repositories.OrderItemRepository;
import com.csse.backend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class DeliveredItemsServiceImpl implements DeliveredItemsService {

    final DeliveredItemsRepository deliveredItemsRepository;
    final OrderItemRepository orderItemRepository;
    final UserRepository userRepository;

    public DeliveredItemsServiceImpl(DeliveredItemsRepository deliveredItemsRepository, OrderItemRepository orderItemRepository, UserRepository userRepository) {
        this.deliveredItemsRepository = deliveredItemsRepository;
        this.orderItemRepository = orderItemRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<DeliveredItemResponseDTO> getAllOrders() {

        try {
            List<DeliveredItem> deliveredItems =deliveredItemsRepository.getAllDeliveredItems();
            List<DeliveredItemResponseDTO> responseDTOList =new ArrayList<>();

            for (DeliveredItem deliveredItem: deliveredItems) {

                OrderItem orderItem = orderItemRepository.getOrderItemById(deliveredItem.getOrderItem().getId());

                float quantity = Float.parseFloat(deliveredItem.getSupplyAmount().split("\\s+")[0]);

               // User supplier = userRepository.getUserById(1L);



                ReceiptDTO receiptDTO = new ReceiptDTO();
                receiptDTO.setReferenceNumber(orderItem.getId());
                receiptDTO.setItem(orderItem.getItemName());
                receiptDTO.setQuantity(deliveredItem.getSupplyAmount());
                receiptDTO.setBrand(deliveredItem.getSupplyBrand());
                receiptDTO.setUnitPrice(deliveredItem.getSupplyPricePerUnit());
                receiptDTO.setTotalAmount(quantity*deliveredItem.getSupplyPricePerUnit());
                receiptDTO.setDeliveredDate(deliveredItem.getSupplyDate());
                receiptDTO.setAdviceNote(deliveredItem.getAdviceNote());
                receiptDTO.setSupplier("supplier");

                DeliveredItemResponseDTO itemResponseDTO= new DeliveredItemResponseDTO(receiptDTO);

                responseDTOList.add(itemResponseDTO);

            }

            return responseDTOList;
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public SupplierOrderQuotation getOrderById(Long orderId) {
        return null;
    }
}
