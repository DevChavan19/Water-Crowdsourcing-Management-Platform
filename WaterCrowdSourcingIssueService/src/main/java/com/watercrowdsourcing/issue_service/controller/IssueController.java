package com.watercrowdsourcing.issue_service.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.watercrowdsourcing.issue_service.dtos.CreateIssueRequest;
import com.watercrowdsourcing.issue_service.dtos.IssueResponse;
import com.watercrowdsourcing.issue_service.entities.IssueStatus;
import com.watercrowdsourcing.issue_service.service.IssueService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/issues")
@RequiredArgsConstructor
public class IssueController {

        private final IssueService issueService;

        // ================= ADMIN =================
        // Create Issue (Assigned by Admin)
        @PostMapping
        public ResponseEntity<IssueResponse> createIssue(
                        @RequestHeader("Authorization") String authHeader,
                        @RequestBody CreateIssueRequest request) {

                // remove "Bearer " prefix
                String jwtToken = authHeader.replace("Bearer ", "");

                // assume adminId comes from JWT parsing or request context
                Long adminId = 1L; // for now hardcode or parse from JWT

                IssueResponse response = issueService.createIssue(request, adminId, jwtToken);
                return ResponseEntity.status(HttpStatus.CREATED).body(response);
        }

        // ================= COMMON =================
        // Get Issue by Issue ID
        @GetMapping("/{issueId}")
        public ResponseEntity<IssueResponse> getIssueById(
                        @PathVariable Long issueId) {
                return ResponseEntity.ok(
                                issueService.getIssueById(issueId));
        }

        // ================= DEPARTMENT =================
        // Get Issues by Department ID
        @GetMapping("/department/{departmentId}")
        public ResponseEntity<List<IssueResponse>> getIssuesByDepartment(
                        @PathVariable Long departmentId) {
                return ResponseEntity.ok(
                                issueService.getIssuesByDepartment(departmentId));
        }

        // ================= STATUS UPDATE =================
        // Update Issue Status (OPEN / IN_PROGRESS / RESOLVED)
        @PutMapping("/{issueId}/status")
        public ResponseEntity<IssueResponse> updateIssueStatus(
                        @PathVariable Long issueId,
                        @RequestParam IssueStatus status,
                        @RequestHeader("Authorization") String authHeader,
                        @RequestParam(required = false) Long resolutionImageId) {
                String jwtToken = authHeader.replace("Bearer ", "");
                return ResponseEntity.ok(
                                issueService.updateIssueStatus(issueId, status, jwtToken, resolutionImageId));
        }
}
