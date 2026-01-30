package com.watercrowdsourcing.feedback_service.dtos;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FeedbackResponse {
    private Long feedbackId;
    private Long userId;
    private Long issueId;
    private Integer rating;
    private String comment;
    private LocalDateTime createdAt;
}
