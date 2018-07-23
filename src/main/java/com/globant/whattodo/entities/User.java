package com.globant.whattodo.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class User {

	@Id
	@GeneratedValue
	private Long id;

	private String email;

	@JsonIgnore
	private String password;

	private String firstName;

	private String lastName;

	private Date birthDate;

	private String biography;

	private String base64Image;

	private User() {
	} // JPA only

	public User(final String email, final String password) {
		this.email = email;
		this.password = password;
	}

	public Long getId() {
		return id;
	}

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public String getBiography() {
		return biography;
	}

	public String getBase64Image() {
		return base64Image;
	}

	@Override
	public String toString() {
		return "User [email=" + email + ", firstName=" + firstName + ", lastName=" + lastName + ", birthDate="
				+ birthDate + ", biography=" + biography + "]";
	}

}