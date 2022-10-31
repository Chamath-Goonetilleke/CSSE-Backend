package com.csse.backend.RetrieveDeliveredItems.Respository;

import com.csse.backend.RetrieveDeliveredItems.Entity.DeliveredItem;
//import com.csse.backend.RetrieveDeliveredItems.Entity.User;
import com.csse.backend.UserAndOrderManagement.domains.SupplierOrderQuotation;

import java.sql.SQLException;
import java.util.List;

public interface DeliveredItemsRepository {

    List<DeliveredItem> getAllDeliveredItems() throws SQLException;
    SupplierOrderQuotation getDeliveredItemById(Long orderId);

//    User getUser(Long id);

}
