package com.csse.backend.Requisition.Repository;

import com.csse.backend.Requisition.Model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RequisitionRepository  extends CrudRepository<Order, Integer> {

    @Modifying
    @Query(value = "SELECT * FROM orders s WHERE s.site_manager_name = ?1 AND status = 'Pending';", nativeQuery = true)
    List<Order> getPrivileges(String name);
}
