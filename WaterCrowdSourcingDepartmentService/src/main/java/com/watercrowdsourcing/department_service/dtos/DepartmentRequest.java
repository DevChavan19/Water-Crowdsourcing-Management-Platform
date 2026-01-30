package com.watercrowdsourcing.department_service.dtos;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentRequest {
    private String name;
    private String description;
}
