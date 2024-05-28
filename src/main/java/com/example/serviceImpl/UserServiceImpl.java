package com.example.serviceImpl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.DTO.UserLocationDTO;
import com.example.entity.Location;
import com.example.entity.User;
import com.example.repository.LocationRepository;
import com.example.repository.UserRepository;
import com.example.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private LocationRepository locationRepository;

	@Autowired
	private UserRepository userRepository;

	@Override
	public User save(String email, String firstName, String lastName, String password, long locationId) {
		Location fetchedLocation = locationRepository.findById(locationId)
				.orElseThrow(() -> new NoSuchElementException("Location is not Present " + locationId));
		if (fetchedLocation != null) {
			User user = new User();
			user.setFirstName(firstName);
			user.setLastName(lastName);
			user.setEmail(email);
			user.setPassword(password);
			user.setLocation(fetchedLocation);
			return userRepository.save(user);
		} else {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Location Id is not Present");
		}
	}

	@Override
	public ResponseEntity<UserLocationDTO> get(Long userId) {
		Optional<User> fetchedUser = userRepository.findById(userId);
		if (fetchedUser.isPresent()) {
			User user1 = fetchedUser.get();
			UserLocationDTO userLocationDTO = new UserLocationDTO();
			userLocationDTO.setUserId(user1.getUserId());
			userLocationDTO.setFirstNAme(user1.getFirstName());
			userLocationDTO.setEmail(user1.getEmail());

			userLocationDTO.setPlace(user1.getLocation().getPlace());
			userLocationDTO.setLongitude(user1.getLocation().getLongitude());
			userLocationDTO.setLatitude(user1.getLocation().getLatitude());
			return ResponseEntity.ok(userLocationDTO);

		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User is not Present with this ID " + userId);
		}
	}

	@Override
	public User getByUserWithoutPassword(Long userId) {
		Optional<User> fetchedUser = userRepository.findById(userId);
		if (fetchedUser != null) {
			User user = fetchedUser.get();
			return user;
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User with this Id is not Present " + userId);
		}
	}

	@Override
	public ResponseEntity<List<UserLocationDTO>> all() {
		List<User> all = userRepository.findAll();
		List<UserLocationDTO> allDTO = new ArrayList<>();
		if (all.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No objects are present ");
		} else {
			for (int i = 0; i < all.size(); i++) {
				UserLocationDTO userLocationDTO = new UserLocationDTO();
				userLocationDTO.setUserId(all.get(i).getUserId());
				userLocationDTO.setFirstNAme(all.get(i).getFirstName());
				userLocationDTO.setEmail(all.get(i).getEmail());

				System.err.println(all.get(i).getLastName());

				userLocationDTO.setPlace(all.get(i).getLocation().getPlace());
				userLocationDTO.setLongitude(all.get(i).getLocation().getLongitude());
				userLocationDTO.setLatitude(all.get(i).getLocation().getLatitude());
				allDTO.add(userLocationDTO);
			}
			return ResponseEntity.ok(allDTO);
		}
	}

}
