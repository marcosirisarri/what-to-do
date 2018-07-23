package com.globant.whattodo.businesslogic;

import java.util.Arrays;
import java.util.Collection;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import com.globant.whattodo.entities.User;

@Service
public class UsersBusinessLogic {

	@GetMapping
	public Collection<User> getAllUsers() {
		return Arrays.asList(new User("marcos@mail.com", "123"), new User("lucia@mail.com", "321"),
				new User("mario@mail.com", "789"), new User("laura@mail.com", "7890"));
	}
}
