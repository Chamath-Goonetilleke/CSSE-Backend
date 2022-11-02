package com.csse.backend.Requisition.Repository;


import com.csse.backend.Requisition.Model.Orders;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RequisitionRepository  extends CrudRepository<Orders, Integer> {

    @Modifying
    @Query(value = "SELECT * FROM orders o WHERE o.status = 1;", nativeQuery = true)
    List<Orders> getPendingRequisitions(); //get Pending orders

    @Modifying
    @Query(value = "SELECT * FROM orders o WHERE o.status = 0;", nativeQuery = true)
    List<Orders> getApprovedRequisitions(); //get Approved orders

    @Modifying
    @Query(value = "SELECT item_name FROM order_item", nativeQuery = true)
    List<String> getListsItems();

    @Modifying
    @Query(value = "SELECT u.user_first_and_last_name FROM user u WHERE u.user_type = 1", nativeQuery = true)
    List<String> getListsSupplier();
}
