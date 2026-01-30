package com.watercrowdsourcing.report_service.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AssignDepartmentRequest {

    @NotNull
    private Long departmentId;

    @NotNull
    private Long issueId;
}
