package com.example.service;

import org.springframework.http.ResponseEntity;

import com.example.entity.Location;

public interface LocationService {
	Location save(String place, String description, double longitude, double latitude);

	ResponseEntity<?> getLocationById(Long id);
}
