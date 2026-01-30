package com.watercrowdsourcing.feedback_service.service;

import java.util.List;
import com.watercrowdsourcing.feedback_service.dtos.CreateFeedbackRequest;
import com.watercrowdsourcing.feedback_service.dtos.FeedbackResponse;

public interface FeedbackService {
    FeedbackResponse submitFeedback(Long userId, CreateFeedbackRequest request);

    List<FeedbackResponse> getFeedbackByIssue(Long issueId);

    List<FeedbackResponse> getFeedbackByUser(Long userId);

    Double getAverageRatingForDepartment(Long departmentId);
}
