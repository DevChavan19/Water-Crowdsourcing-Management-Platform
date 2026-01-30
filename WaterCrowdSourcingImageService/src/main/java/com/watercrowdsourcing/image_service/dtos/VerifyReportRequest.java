package com.watercrowdsourcing.image_service.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class VerifyReportRequest {

    @NotNull
    private Boolean approved;
}
