package com.watercrowdsourcing.report_service.dtos;

import java.time.LocalDateTime;
import java.util.List;

import com.watercrowdsourcing.report_service.entities.ReportStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReportResponse {

    private Long reportId;
    private Long userId;
    private String description;

    private Double latitude;
    private Double longitude;

    private ReportStatus status;

    private Long departmentId;
    private Long issueId;
    private List<Long> imageIds;

    private LocalDateTime createdAt;
    private LocalDateTime verifiedAt;
    private LocalDateTime closedAt;
    private Long resolutionImageId;
}
