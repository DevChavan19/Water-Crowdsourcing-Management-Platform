package com.watercrowdsourcing.report_service.dtos;

import com.watercrowdsourcing.report_service.entities.ReportStatus;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateReportStatusRequest {

    @NotNull
    private String status; // Accept as String for cross-service compatibility

    private Long resolutionImageId;

    // Helper method to convert String to enum
    public ReportStatus getStatusEnum() {
        return ReportStatus.valueOf(this.status);
    }
}
