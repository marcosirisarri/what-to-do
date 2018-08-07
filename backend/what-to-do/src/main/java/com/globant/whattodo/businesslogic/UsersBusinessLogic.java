package com.globant.whattodo.businesslogic;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.globant.whattodo.businesslogic.exceptions.UserEmailAlreadyRegisteredException;
import com.globant.whattodo.businesslogic.exceptions.UserNotFoundException;
import com.globant.whattodo.entities.User;
import com.globant.whattodo.repository.UsersRepository;

@Service
public class UsersBusinessLogic {

	private final UsersRepository usersRepository;

	@Autowired
	public UsersBusinessLogic(UsersRepository usersRepo) {
		this.usersRepository = usersRepo;
	}

	public Collection<User> getAll() {
		return this.usersRepository.findAll();
	}

	public User getByEmail(String email) {
		return this.usersRepository.findByEmail(email).orElseThrow(() -> new UserNotFoundException(email));
	}

	public User addUser(User newUser) {
		validateUser(newUser);
		return this.usersRepository.save(newUser);
	}

	private void validateUser(User user) {
		String email = user.getEmail();
		if (this.usersRepository.findByEmail(email).isPresent()) {
			throw new UserEmailAlreadyRegisteredException(email);
		}
	}
}
