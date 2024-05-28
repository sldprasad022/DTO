package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.DTO.UserLocationDTO;
import com.example.entity.Location;
import com.example.entity.User;
import com.example.service.LocationService;
import com.example.service.UserService;

@RestController
@RequestMapping("/location")
public class LocationController {
	@Autowired
	private LocationService locationService;

	@Autowired
	private UserService userService;

	@PostMapping("/save")
	public ResponseEntity<Location> save(@RequestParam String place, @RequestParam String description,
			@RequestParam double longitude, @RequestParam double latitude) {
		Location savedLocation = locationService.save(place, description, longitude, latitude);
		return new ResponseEntity<>(savedLocation, HttpStatus.CREATED);
	}

	@GetMapping("/get/{locationId}")
	public ResponseEntity<?> getByLocationId(@PathVariable Long locationId) {
		// return ResponseEntity.ok(locationService.getLocationById(locationId));
		ResponseEntity<?> fetched = locationService.getLocationById(locationId);
		return ResponseEntity.ok(fetched);
	}

	@PostMapping("/user/save/{locationId}")
	public ResponseEntity<User> saveUser(@RequestParam String email, @RequestParam String firstName,
			@RequestParam String lastName, @RequestParam String password, @PathVariable long locationId) {
		User savedUser = userService.save(email, firstName, lastName, password, locationId);
		return new ResponseEntity<User>(savedUser, HttpStatus.CREATED);

	}

	@GetMapping("/user/get/{userId}")
	public ResponseEntity<ResponseEntity<UserLocationDTO>> getByUserId(@PathVariable Long userId) {
		ResponseEntity<UserLocationDTO> fetchedDTO = userService.get(userId);
		return ResponseEntity.ok(fetchedDTO);
	}

	@GetMapping("/user/getUserWithOutPassword/{userId}")
	public ResponseEntity<User> getUserWithOutPassword(@PathVariable Long userId) {
		User fetchedUser = userService.getByUserWithoutPassword(userId);
		return ResponseEntity.ok(fetchedUser);
	}

	@GetMapping("/user/get/allDTO")
	public ResponseEntity<ResponseEntity<List<UserLocationDTO>>> all() {
		ResponseEntity<List<UserLocationDTO>> alldto = userService.all();
		return ResponseEntity.ok(alldto);

	}
}
