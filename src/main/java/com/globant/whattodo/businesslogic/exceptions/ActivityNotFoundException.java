package com.globant.whattodo.businesslogic.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ActivityNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public ActivityNotFoundException(Long id) {
		super("could not find activity with id '" + id + "'.");
	}
}
