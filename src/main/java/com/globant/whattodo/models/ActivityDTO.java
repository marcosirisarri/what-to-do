package com.globant.whattodo.models;

import java.time.LocalDateTime;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class ActivityDTO {

	@NotNull
	private String name;

	@NotNull
	private String description;

	@NotNull
	@FutureOrPresent
	private LocalDateTime dateAndTime;

	@Positive
	private Integer maximumParticipants;

	private String base64Image;

	@NotNull
	private String locationAddress;

	@NotNull
	private String creatorEmail;
}
