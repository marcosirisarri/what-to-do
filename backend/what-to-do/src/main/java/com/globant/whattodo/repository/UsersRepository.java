package com.globant.whattodo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.globant.whattodo.entities.User;

@Repository
public interface UsersRepository extends JpaRepository<User, Long> {
	Optional<User> findByEmail(String email);
}