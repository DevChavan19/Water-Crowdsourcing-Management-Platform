package com.watercrowdsourcing.department_service.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.watercrowdsourcing.department_service.entities.Department;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
    boolean existsByName(String name);
    
    boolean existsByDepartmentIdAndIsActiveTrue(Long departmentId);
}
