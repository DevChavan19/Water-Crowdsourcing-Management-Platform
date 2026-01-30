package com.watercrowdsourcing.image_service.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AssignDepartmentRequest {

    @NotNull
    private Long departmentId;

    @NotNull
    private Long issueId;
}
