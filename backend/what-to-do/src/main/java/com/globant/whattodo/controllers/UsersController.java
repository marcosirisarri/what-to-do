package com.globant.whattodo.controllers;

import java.net.URI;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.globant.whattodo.businesslogic.UsersBusinessLogic;
import com.globant.whattodo.entities.User;

@RestController
@RequestMapping("/users")
public class UsersController {

	private final UsersBusinessLogic usersBusinessLogic;

	@Autowired
	public UsersController(UsersBusinessLogic usersBL) {
		this.usersBusinessLogic = usersBL;
	}

	@GetMapping
	public Collection<User> getAllUsers() {
		return this.usersBusinessLogic.getAll();
	}

	@GetMapping("/{email}")
	public User getUserByEmail(@PathVariable String email) {
		return this.usersBusinessLogic.getByEmail(email);
	}

	@PostMapping
	public ResponseEntity<?> addUser(@RequestBody User newUser) {
		User result = this.usersBusinessLogic.addUser(newUser);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{email}")
				.buildAndExpand(result.getEmail()).toUri();

		return ResponseEntity.created(location).build();
	}

	@PutMapping("/{email}")
	public ResponseEntity<?> updateUser(@PathVariable String email, @RequestBody User updatedUser) {
		User result = this.usersBusinessLogic.updateUser(email, updatedUser);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{email}")
				.buildAndExpand(result.getEmail()).toUri();

		return ResponseEntity.created(location).build();
	}
}
