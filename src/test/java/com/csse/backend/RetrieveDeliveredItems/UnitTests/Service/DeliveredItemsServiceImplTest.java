package com.csse.backend.RetrieveDeliveredItems.UnitTests.Service;

import com.csse.backend.RetrieveDeliveredItems.DTO.DeliveredItemResponseDTO;
import com.csse.backend.RetrieveDeliveredItems.Respository.DeliveredItemsRepository;
import com.csse.backend.RetrieveDeliveredItems.Services.DeliveredItemsServiceImpl;
import com.csse.backend.domains.Item;
import com.csse.backend.domains.Order;
import com.csse.backend.repositories.OrderItemRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class DeliveredItemsServiceImplTest {

    @Autowired
    DeliveredItemsServiceImpl deliveredItemsService;

    @MockBean
    DeliveredItemsRepository deliveredItemsRepository;
    @MockBean
    OrderItemRepository orderItemRepository;

    @Test
    @DisplayName("GetAllOrdersSuccess")
    void GetAllOrdersSuccess() throws SQLException {
        List<Item> deliveredItems = new ArrayList<>();
        Item item = new Item();
        item.setId(1L);
        item.setSupplier(null);
        item.setOrder(new Order(1L,null,null,"Cement",null,null,null,null));
        item.setDeliverableAmount("50 bags");
        item.setPricePerUnit(1000F);
        item.setDeliverableBrand("TestBrand");

        deliveredItems.add(item);

        Order order = new Order(1L,null,null,"Cement",null,null,null,null);

        Mockito.when(deliveredItemsRepository.getAllDeliveredItems()).thenReturn(deliveredItems);
        Mockito.when(orderItemRepository.getReferenceById(1L)).thenReturn(order);

        List<DeliveredItemResponseDTO> result = deliveredItemsService.getAllOrders();
        Assertions.assertEquals(1,result.size());

    }
    @Test
    @DisplayName("GetAllOrdersFailure")
    void GetAllOrdersFailure() throws SQLException {

        Order order = new Order(1L,null,null,"Cement",null,null,null,null);

        Mockito.when(deliveredItemsRepository.getAllDeliveredItems()).thenReturn(Collections.emptyList());
        Mockito.when(orderItemRepository.getReferenceById(1L)).thenReturn(order);

        List<DeliveredItemResponseDTO> result = deliveredItemsService.getAllOrders();
        Assertions.assertNull(result);

    }



}
