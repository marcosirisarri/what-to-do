package com.globant.whattodo.entities;

import java.sql.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Activity {

	@Id
	@GeneratedValue
	private Long id;

	private String name;

	private String description;

	private Date dateAndTime;

	private Integer maximumParticipants;

	private String base64Image;

	@OneToOne
	private Location location;

	@ManyToOne
	private User creator;

	@ManyToMany
	private List<User> participants;

	@OneToMany
	private List<Comment> comments;

	private Activity() { // JPA only
	}

	public Activity(String name, Date dateAndTime) {
		this.name = name;
		this.dateAndTime = dateAndTime;
	}
}
