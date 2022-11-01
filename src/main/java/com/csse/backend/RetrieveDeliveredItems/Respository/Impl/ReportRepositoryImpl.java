package com.csse.backend.RetrieveDeliveredItems.Respository.Impl;

import com.csse.backend.RetrieveDeliveredItems.Entity.Report;
import com.csse.backend.RetrieveDeliveredItems.Respository.ReportRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@Repository
@Slf4j
public class ReportRepositoryImpl implements ReportRepository {

    final
    EntityManager entityManager;

    public ReportRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    /**
     * Save test report data in the database
     */
    @Override
    @Transactional
    public Report saveReport(Report report) {
        try {
            entityManager.persist(report);
            return report;
        }catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }
}
