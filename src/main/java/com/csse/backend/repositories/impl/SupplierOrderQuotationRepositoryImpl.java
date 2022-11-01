package com.csse.backend.repositories.impl;

import com.csse.backend.domains.Item;
import com.csse.backend.repositories.SupplierOrderQuotationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class SupplierOrderQuotationRepositoryImpl implements SupplierOrderQuotationRepository {

    @Autowired
    EntityManager entityManager;

    /**
     * Get a supplier order quotation by its identification
     *
     * @param supplierOrderQuotationId - Supplier order quotation identification
     * @return SupplierOrderQuotation
     */
    @Override
    public Item getSupplierQuotationById(long supplierOrderQuotationId) {
        return entityManager.find(Item.class, supplierOrderQuotationId);
    }

    /**
     * Create a supplier order quotation
     *
     * @param item - New Supplier order quotation
     * @return SupplierOrderQuotation
     */
    @Override
    @Transactional
    public Item saveSupplierOrderQuotation(Item item) {
        if (item.getId() == null) {
            entityManager.persist(item);
        } else {
            item = entityManager.merge(item);
        }

        return item;
    }

    /**
     * Get all ready to deliver items
     *
     * @param supplierId - Supplier identification
     * @return List<SupplierOrderQuotation>
     */
    @Override
    public List<Item> getAllCustomerAndSupplierAcceptedPurchaseRequisitions(long supplierId) {
        TypedQuery<Item> query = entityManager
                .createQuery("SELECT s FROM Item s, Order o WHERE s.supplier.id = :supplierId AND s.itemStatus = 2", Item.class);
        query.setParameter("supplierId", supplierId);
        return query.getResultList();
    }

    /**
     * Get all supervisor accepted supplier order quotations
     *
     * @param supplierId - Supplier identification
     * @return List<SupplierOrderQuotation>
     */
    @Override
    public List<Item> getAllCustomerAcceptedPurchaseRequisitions(long supplierId) {
        TypedQuery<Item> query = entityManager
                .createQuery("SELECT s FROM Item s WHERE s.supplier.id = :supplierId AND s.itemStatus = 1", Item.class);
        query.setParameter("supplierId", supplierId);
        return query.getResultList();
    }

    /**
     * Get all supervisor approval pending supplier order quotations
     *
     * @param employeeUserId - Supervisor identification
     * @return List<SupplierOrderQuotation>t
     */
    @Override
    public List<Item> getAllCustomerApprovalPendingSoq(long employeeUserId) {
        TypedQuery<Item> query = entityManager
                .createQuery("SELECT s FROM Item s WHERE s.order.createdBy.id = :employeeUserId AND s.itemStatus = 0", Item.class);
        query.setParameter("employeeUserId", employeeUserId);
        return query.getResultList();
    }

}
