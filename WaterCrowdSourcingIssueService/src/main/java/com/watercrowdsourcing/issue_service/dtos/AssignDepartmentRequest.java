package com.watercrowdsourcing.issue_service.dtos;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AssignDepartmentRequest {
    private Long departmentId;
    private Long issueId;
}
