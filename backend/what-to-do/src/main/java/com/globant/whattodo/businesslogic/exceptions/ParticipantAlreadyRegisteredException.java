package com.globant.whattodo.businesslogic.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class ParticipantAlreadyRegisteredException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public ParticipantAlreadyRegisteredException(String email, Long activityId) {
		super("Already registered user with email '" + email + "' on activity " + activityId + ".");
	}
}