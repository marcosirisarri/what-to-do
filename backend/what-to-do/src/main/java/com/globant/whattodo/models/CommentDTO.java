package com.globant.whattodo.models;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

public class CommentDTO {

	private String content;

	@NotNull
	private String activity;

	@NotNull
	@Email
	private String authorEmail;
}
