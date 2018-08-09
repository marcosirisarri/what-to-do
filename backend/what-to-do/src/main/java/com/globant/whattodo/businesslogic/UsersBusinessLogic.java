package com.globant.whattodo.businesslogic;

import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

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

	public User updateUser(String email, User updatedUser) {
		User originalUser = this.getByEmail(email);
		String updatedPassword = updatedUser.getPassword();
		String updatedFirstName = updatedUser.getFirstName();
		String updatedLastName = updatedUser.getLastName();
		Date updatedBirthDate = updatedUser.getBirthDate();
		String updatedBiography = updatedUser.getBiography();
		String updatedBase64Image = updatedUser.getBase64Image();
		if (!StringUtils.isEmpty(updatedPassword)) {
			originalUser.setPassword(updatedPassword);
		}
		if (!StringUtils.isEmpty(updatedFirstName)) {
			originalUser.setFirstName(updatedFirstName);
		}
		if (!StringUtils.isEmpty(updatedLastName)) {
			originalUser.setLastName(updatedLastName);
		}
		if (updatedBirthDate != null) {
			originalUser.setBirthDate(updatedBirthDate);
		}
		if (!StringUtils.isEmpty(updatedBiography)) {
			originalUser.setBiography(updatedBiography);
		}
		if (!StringUtils.isEmpty(updatedBase64Image)) {
			originalUser.setBase64Image(updatedBase64Image);
		}
		return this.usersRepository.save(updatedUser);
	}

	private void validateUser(User user) {
		String email = user.getEmail();
		if (this.usersRepository.findByEmail(email).isPresent()) {
			throw new UserEmailAlreadyRegisteredException(email);
		}
	}
}
