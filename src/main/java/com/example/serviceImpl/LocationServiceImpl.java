package com.example.serviceImpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.entity.Location;
import com.example.repository.LocationRepository;
import com.example.service.LocationService;

@Service
public class LocationServiceImpl implements LocationService {
	@Autowired
	private LocationRepository locationRepository;

	@Override
	public Location save(String place, String description, double longitude, double latitude) {
		Location location = new Location();
		location.setPlace(place);
		location.setDescription(description);
		location.setLongitude(longitude);
		location.setLatitude(latitude);
		return locationRepository.save(location);
	}

	@Override
	public ResponseEntity<?> getLocationById(Long id) {
		Optional<Location> getById = locationRepository.findById(id);
		if (getById.isPresent()) {
			return ResponseEntity.ok(getById.get());
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Location Id is not Present " + id);
		}
	}

}
