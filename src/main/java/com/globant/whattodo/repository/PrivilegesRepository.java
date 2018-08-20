package com.globant.whattodo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.globant.whattodo.entities.Privilege;

public interface PrivilegesRepository extends JpaRepository<Privilege, Long> {
	Optional<Privilege> findByName(String name);
}
