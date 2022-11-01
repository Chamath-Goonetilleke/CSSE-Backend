package com.csse.backend.RetrieveDeliveredItems.Respository;

import com.csse.backend.RetrieveDeliveredItems.Entity.DeliveredItem;
import com.csse.backend.domains.Item;

import java.sql.SQLException;
import java.util.List;

public interface DeliveredItemsRepository {

    List<Item> getAllDeliveredItems() throws SQLException;
    Item getDeliveredItemById(Long orderId);

//    User getUser(Long id);

}
