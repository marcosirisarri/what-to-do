package com.globant.whattodo.controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

	@GetMapping(produces = "application/json")
	public Collection<User> getAllUsers() {
		return this.usersBusinessLogic.getAllUsers();
	}

	@PostMapping(consumes = "application/json")
	public User addUser(@RequestBody User newUser) {
		System.out.println(newUser);
		this.usersBusinessLogic.addUser(newUser);
		return newUser;
	}
}
