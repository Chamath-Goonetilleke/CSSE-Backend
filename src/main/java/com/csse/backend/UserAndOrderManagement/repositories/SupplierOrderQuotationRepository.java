package com.csse.backend.UserAndOrderManagement.repositories;

import com.csse.backend.UserAndOrderManagement.domains.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SupplierOrderQuotationRepository extends JpaRepository<Item, Long> {

}
