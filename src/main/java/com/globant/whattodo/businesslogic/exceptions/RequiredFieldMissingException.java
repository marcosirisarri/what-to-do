package com.globant.whattodo.businesslogic.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class RequiredFieldMissingException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public RequiredFieldMissingException(String fieldName) {
		super("Required field missing: '" + fieldName + "'.");
	}
}