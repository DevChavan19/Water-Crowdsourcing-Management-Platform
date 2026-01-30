package com.watercrowdsourcing.report_service.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.watercrowdsourcing.report_service.entities.Report;
import com.watercrowdsourcing.report_service.entities.ReportStatus;

public interface ReportRepository extends JpaRepository<Report, Long> {

    // USER
    List<Report> findByUserId(Long userId);

    // ADMIN
    List<Report> findByStatus(ReportStatus status);

    // DEPARTMENT
    List<Report> findByDepartmentId(Long departmentId);

    // SAFETY CHECKS
    boolean existsByReportIdAndUserId(Long reportId, Long userId);
}
