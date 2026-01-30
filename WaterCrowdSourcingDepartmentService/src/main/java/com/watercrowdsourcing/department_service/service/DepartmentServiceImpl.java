package com.watercrowdsourcing.department_service.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.watercrowdsourcing.department_service.dtos.DepartmentRequest;
import com.watercrowdsourcing.department_service.dtos.DepartmentResponse;
import com.watercrowdsourcing.department_service.entities.Department;
import com.watercrowdsourcing.department_service.repository.DepartmentRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;
    private final ModelMapper modelMapper;

    @Override
    public DepartmentResponse createDepartment(DepartmentRequest request) {
        if (departmentRepository.existsByName(request.getName())) {
            throw new RuntimeException("Department already exists");
        }
        Department dept = new Department();
        dept.setName(request.getName());
        dept.setDescription(request.getDescription());
        Department saved = departmentRepository.save(dept);
        return modelMapper.map(saved, DepartmentResponse.class);
    }

    @Override
    public List<DepartmentResponse> getAllDepartments() {
        return departmentRepository.findAll()
                .stream()
                .filter(Department::getIsActive) // only active ones
                .map(d -> modelMapper.map(d, DepartmentResponse.class))
                .collect(Collectors.toList());
    }

    @Override
    public DepartmentResponse getDepartmentById(Long id) {
        Department dept = departmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Department not found"));
        if (!dept.getIsActive()) {
            throw new RuntimeException("Department is inactive");
        }
        return modelMapper.map(dept, DepartmentResponse.class);
    }

    @Override
    public DepartmentResponse updateDepartment(Long id, DepartmentRequest request) {
        Department dept = departmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Department not found"));
        dept.setName(request.getName());
        dept.setDescription(request.getDescription());
        Department updated = departmentRepository.save(dept);
        return modelMapper.map(updated, DepartmentResponse.class);
    }

    @Override
    public void deleteDepartment(Long id) {
        Department dept = departmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Department not found"));
        dept.setIsActive(false); // soft delete
        departmentRepository.save(dept);
    }
}
