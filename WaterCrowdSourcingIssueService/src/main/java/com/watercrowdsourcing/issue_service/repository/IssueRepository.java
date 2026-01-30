package com.watercrowdsourcing.issue_service.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.watercrowdsourcing.issue_service.entities.Issue;
import com.watercrowdsourcing.issue_service.entities.IssueStatus;

public interface IssueRepository extends JpaRepository<Issue, Long> {
	
    List<Issue> findByDepartmentId(Long departmentId);
    
    List<Issue> findByStatus(IssueStatus status);
}

