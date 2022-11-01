package com.csse.backend.RetrieveDeliveredItems.Respository;

import com.csse.backend.domains.Item;

import java.sql.SQLException;
import java.util.List;

public interface DeliveredItemsRepository {

    List<Item> getAllDeliveredItems() throws SQLException;

}
