package com.example.DTO;

import lombok.Data;

@Data
public class UserLocationDTO {
	private Long userId;

	private String email;

	private String firstNAme;

	private String place;

	private double longitude;

	private double latitude;

}
