package com.watercrowdsourcing.feedback_service.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.watercrowdsourcing.feedback_service.dtos.CreateFeedbackRequest;
import com.watercrowdsourcing.feedback_service.dtos.FeedbackResponse;
import com.watercrowdsourcing.feedback_service.security.UserPrincipal;
import com.watercrowdsourcing.feedback_service.service.FeedbackService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/feedback")
@RequiredArgsConstructor
public class FeedbackController {

    private final FeedbackService feedbackService;

    @PostMapping
    public ResponseEntity<FeedbackResponse> submitFeedback(@Valid @RequestBody CreateFeedbackRequest request) {
        UserPrincipal principal = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Long userId = principal.getUserId();

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(feedbackService.submitFeedback(userId, request));
    }

    @GetMapping("/issue/{issueId}")
    public ResponseEntity<List<FeedbackResponse>> getFeedbackByIssue(@PathVariable Long issueId) {
        return ResponseEntity.ok(feedbackService.getFeedbackByIssue(issueId));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<FeedbackResponse>> getFeedbackByUser(@PathVariable Long userId) {
        return ResponseEntity.ok(feedbackService.getFeedbackByUser(userId));
    }
}
