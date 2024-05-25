package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entity.Location;

public interface LocationRepository extends JpaRepository<Location, Long> {

}
