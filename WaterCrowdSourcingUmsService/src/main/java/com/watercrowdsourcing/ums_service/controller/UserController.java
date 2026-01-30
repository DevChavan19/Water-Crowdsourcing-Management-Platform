package com.watercrowdsourcing.ums_service.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.watercrowdsourcing.ums_service.dtos.AuthRequest;
import com.watercrowdsourcing.ums_service.dtos.AuthResp;
import com.watercrowdsourcing.ums_service.dtos.UserSignupRequest;
import com.watercrowdsourcing.ums_service.dtos.AdminSignupRequest;
import com.watercrowdsourcing.ums_service.dtos.ApiResponse;
import com.watercrowdsourcing.ums_service.dtos.UserDTO;
import com.watercrowdsourcing.ums_service.service.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserService userService;

    // STEP 1: User signup
    @PostMapping("/signup")
    public ResponseEntity<ApiResponse> signup(@RequestBody @Valid UserSignupRequest dto) {
        log.info("User signup request: {}", dto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(userService.userSignup(dto));
    }

    // STEP 2: Admin signup
    @PreAuthorize("hasRole('SUPER_ADMIN')")
    @PostMapping("/signup-admin")
    public ResponseEntity<ApiResponse> signupAdmin(@RequestBody @Valid AdminSignupRequest dto) {
        log.info("Admin signup request: {}", dto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(userService.adminSignup(dto));
    }

    // STEP 2.5: Department signup
    @PostMapping("/signup-department")
    public ResponseEntity<ApiResponse> signupDepartment(
            @RequestBody @Valid com.watercrowdsourcing.ums_service.dtos.DepartmentSignupRequest dto) {
        log.info("Department signup request: {}", dto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(userService.departmentSignup(dto));
    }

    // STEP 3: User login
    @PostMapping("/login")
    public ResponseEntity<AuthResp> login(@RequestBody @Valid AuthRequest dto) {
        log.info("Login request: {}", dto.getEmail());
        return ResponseEntity.ok(userService.authenticate(dto));
    }

    // STEP 4: Get all users (Admin only)
    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        log.info("Fetching all users");
        return ResponseEntity.ok(userService.getAllUsers());
    }

    // STEP 5: Delete user by id
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable Long id) {
        log.info("Deleting user with id {}", id);
        return ResponseEntity.ok(userService.deleteUserDetails(id));
    }

    // STEP 6: Update user details
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> updateUser(
            @PathVariable Long id,
            @RequestBody @Valid com.watercrowdsourcing.ums_service.entities.User user) {
        log.info("Updating user with id {}", id);
        return ResponseEntity.ok(userService.updateDetails(id, user));
    }

    // STEP 7: Get user details by id
    @GetMapping("/{id}")
    public ResponseEntity<com.watercrowdsourcing.ums_service.entities.User> getUser(@PathVariable Long id) {
        log.info("Fetching user with id {}", id);
        return ResponseEntity.ok(userService.getUserDetails(id));
    }
}
