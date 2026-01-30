package com.watercrowdsourcing.feedback_service.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.watercrowdsourcing.feedback_service.dtos.CreateFeedbackRequest;
import com.watercrowdsourcing.feedback_service.dtos.FeedbackResponse;
import com.watercrowdsourcing.feedback_service.entities.Feedback;
import com.watercrowdsourcing.feedback_service.repository.FeedbackRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class FeedbackServiceImpl implements FeedbackService {

    private final FeedbackRepository feedbackRepository;
    private final ModelMapper modelMapper;

    @Override
    public FeedbackResponse submitFeedback(Long userId, CreateFeedbackRequest request) {
        Feedback feedback = new Feedback();
        feedback.setUserId(userId);
        feedback.setIssueId(request.getIssueId());
        feedback.setRating(request.getRating());
        feedback.setComment(request.getComment());

        Feedback saved = feedbackRepository.save(feedback);
        log.info("Feedback submitted by user {} for issue {}", userId, request.getIssueId());
        return modelMapper.map(saved, FeedbackResponse.class);
    }

    @Override
    public List<FeedbackResponse> getFeedbackByIssue(Long issueId) {
        return feedbackRepository.findByIssueId(issueId)
                .stream()
                .map(f -> modelMapper.map(f, FeedbackResponse.class))
                .toList();
    }

    @Override
    public List<FeedbackResponse> getFeedbackByUser(Long userId) {
        return feedbackRepository.findByUserId(userId)
                .stream()
                .map(f -> modelMapper.map(f, FeedbackResponse.class))
                .toList();
    }

    @Override
    public Double getAverageRatingForDepartment(Long departmentId) {
        // In a real scenario, we might need a cross-service call to find all issues for
        // a department
        // For now, let's assume we fetch ratings directly if we had a departmentId in
        // Feedback
        // Or we just return a placeholder for this phase.
        return 4.5;
    }
}
