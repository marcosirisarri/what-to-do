package com.globant.whattodo.businesslogic;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.globant.whattodo.entities.User;
import com.globant.whattodo.repository.UsersRepository;

@Service
public class UsersBusinessLogic {

	private final UsersRepository usersRepository;

	@Autowired
	public UsersBusinessLogic(UsersRepository usersRepo) {
		this.usersRepository = usersRepo;
	}

	public Collection<User> getAllUsers() {
		return usersRepository.findAll();
	}

	public void addUser(User newUser) {
		usersRepository.save(newUser);
	}
}
