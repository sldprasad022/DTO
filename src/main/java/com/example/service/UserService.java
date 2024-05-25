package com.example.service;

import org.springframework.http.ResponseEntity;

import com.example.DTO.UserLocationDTO;
import com.example.entity.User;

public interface UserService {
	User save(String email, String firstName, String lastName, String password, long locationId);

	ResponseEntity<UserLocationDTO> get(Long userId);

	User getByUserWithoutPassword(Long userId);

}