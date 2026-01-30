package com.watercrowdsourcing.issue_service.dtos;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentResponse {
    private Long departmentId;
    private String name;
    private String description;
    private Boolean isActive;
    private String createdAt;
}
