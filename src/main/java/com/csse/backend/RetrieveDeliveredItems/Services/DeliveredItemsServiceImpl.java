package com.csse.backend.RetrieveDeliveredItems.Services;

import com.csse.backend.RetrieveDeliveredItems.Common.CommonConstants;
import com.csse.backend.RetrieveDeliveredItems.DTO.DeliveredItemResponseDTO;
import com.csse.backend.RetrieveDeliveredItems.DTO.ReceiptDTO;
import com.csse.backend.RetrieveDeliveredItems.Respository.DeliveredItemsRepository;
import com.csse.backend.RetrieveDeliveredItems.Services.Abstract.DeliveredItemsService;
import com.csse.backend.domains.Item;
import com.csse.backend.domains.Order;
import com.csse.backend.repositories.OrderItemRepository;
import com.csse.backend.repositories.UserRepository;
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

    /**
     * Get all delivered items
     */
    @Override
    public List<DeliveredItemResponseDTO> getAllOrders() {

        try {
            List<Item> deliveredItems = deliveredItemsRepository.getAllDeliveredItems();
            List<DeliveredItemResponseDTO> responseDTOList = new ArrayList<>();

            if (deliveredItems.size() == 0) {
                throw new NullPointerException(CommonConstants.NO_DATA_FOUND);
            }

            for (Item deliveredItem : deliveredItems) {

                Order orderItem = orderItemRepository.getReferenceById(deliveredItem.getOrder().getId());


                float quantity = Float.parseFloat(deliveredItem.getDeliverableAmount().split("\\s+")[0]);

                // Convert data from database to response
                ReceiptDTO receiptDTO = new ReceiptDTO();
                receiptDTO.setReferenceNumber(orderItem.getId());
                receiptDTO.setItem(orderItem.getOrderName());
                receiptDTO.setQuantity(deliveredItem.getDeliverableAmount());
                receiptDTO.setBrand(deliveredItem.getDeliverableBrand());
                receiptDTO.setUnitPrice(deliveredItem.getPricePerUnit());
                receiptDTO.setTotalAmount(quantity * deliveredItem.getPricePerUnit());
                receiptDTO.setDeliveredDate(deliveredItem.getDeliverableDate());
                receiptDTO.setAdviceNote(deliveredItem.getAdviceNote());


                DeliveredItemResponseDTO itemResponseDTO = new DeliveredItemResponseDTO(receiptDTO);

                responseDTOList.add(itemResponseDTO);

            }
            return responseDTOList;
        } catch (Exception e) {
            return null;
        }
    }

}

