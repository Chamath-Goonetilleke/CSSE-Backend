package com.csse.backend.repositories;

import com.csse.backend.domains.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SupplierOrderQuotationRepository extends JpaRepository<Item, Long> {

}
