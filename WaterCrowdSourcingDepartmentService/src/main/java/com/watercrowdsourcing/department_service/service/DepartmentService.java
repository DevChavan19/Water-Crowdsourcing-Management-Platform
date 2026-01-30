package com.watercrowdsourcing.department_service.service;


import java.util.List;

import com.watercrowdsourcing.department_service.dtos.DepartmentRequest;
import com.watercrowdsourcing.department_service.dtos.DepartmentResponse;

public interface DepartmentService {

    DepartmentResponse createDepartment(DepartmentRequest request);

    List<DepartmentResponse> getAllDepartments();

    DepartmentResponse getDepartmentById(Long id);

    DepartmentResponse updateDepartment(Long id, DepartmentRequest request);

    void deleteDepartment(Long id);
}


