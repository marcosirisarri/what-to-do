package com.globant.whattodo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.globant.whattodo.entities.User;

@Repository
public interface UsersRepository extends JpaRepository<User, Long> {
	List<User> findAll();

	Optional<User> findById(Long userId);

	Optional<User> findByEmail(String email);

}