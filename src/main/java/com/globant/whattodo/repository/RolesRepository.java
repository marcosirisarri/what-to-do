package com.globant.whattodo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.globant.whattodo.entities.Role;

@Repository
public interface RolesRepository extends JpaRepository<Role, Long> {
	Optional<Role> findByName(String name);
}
