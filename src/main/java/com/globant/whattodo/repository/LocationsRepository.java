package com.globant.whattodo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.globant.whattodo.entities.Location;

@Repository
public interface LocationsRepository extends JpaRepository<Location, Long> {

}