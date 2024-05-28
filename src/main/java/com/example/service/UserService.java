package com.example.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.example.DTO.UserLocationDTO;
import com.example.entity.User;

public interface UserService {
	User save(String email, String firstName, String lastName, String password, long locationId);

	ResponseEntity<UserLocationDTO> get(Long userId);

	ResponseEntity<List<UserLocationDTO>> all();

	User getByUserWithoutPassword(Long userId);

}