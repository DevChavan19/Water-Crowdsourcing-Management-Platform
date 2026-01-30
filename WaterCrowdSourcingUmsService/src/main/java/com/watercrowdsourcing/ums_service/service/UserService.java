package com.watercrowdsourcing.ums_service.service;

import java.util.List;

import com.watercrowdsourcing.ums_service.dtos.AdminSignupRequest;
import com.watercrowdsourcing.ums_service.dtos.ApiResponse;
import com.watercrowdsourcing.ums_service.dtos.AuthRequest;
import com.watercrowdsourcing.ums_service.dtos.AuthResp;
import com.watercrowdsourcing.ums_service.dtos.UserDTO;
import com.watercrowdsourcing.ums_service.dtos.UserSignupRequest;
import com.watercrowdsourcing.ums_service.entities.User;

public interface UserService {
	// get all users
	List<UserDTO> getAllUsers();

	String addUser(User user);

	ApiResponse deleteUserDetails(Long userId);

	User getUserDetails(Long userId);

	ApiResponse updateDetails(Long id, User user);

	AuthResp authenticate(AuthRequest request);

	ApiResponse encryptPasswords();

	ApiResponse userSignup(UserSignupRequest request);

	ApiResponse adminSignup(AdminSignupRequest request);

	ApiResponse departmentSignup(com.watercrowdsourcing.ums_service.dtos.DepartmentSignupRequest request);
}
