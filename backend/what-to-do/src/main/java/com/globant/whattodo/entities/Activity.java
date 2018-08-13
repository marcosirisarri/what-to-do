package com.globant.whattodo.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;

@Entity
public class Activity {

	@Id
	@GeneratedValue
	private Long id;

	@NotNull
	private String name;

	@NotNull
	private String description;

	@NotNull
	@FutureOrPresent
	private Date dateAndTime;

	private Integer maximumParticipants;

	@Column(length = Integer.MAX_VALUE)
	private String base64Image;

	@OneToOne(fetch = FetchType.EAGER)
	@NotNull
	private Location location;

	@ManyToOne
	@NotNull
	private User creator;

	@ManyToMany
	private List<User> participants;

	@OneToMany(mappedBy = "activity", fetch = FetchType.LAZY)
	private List<Comment> comments;

	private Activity() { // JPA only
	}

	public Activity(String name, Date dateAndTime) {
		this.name = name;
		this.dateAndTime = dateAndTime;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getDateAndTime() {
		return dateAndTime;
	}

	public void setDateAndTime(Date dateAndTime) {
		this.dateAndTime = dateAndTime;
	}

	public Integer getMaximumParticipants() {
		return maximumParticipants;
	}

	public void setMaximumParticipants(Integer maximumParticipants) {
		this.maximumParticipants = maximumParticipants;
	}

	public String getBase64Image() {
		return base64Image;
	}

	public void setBase64Image(String base64Image) {
		this.base64Image = base64Image;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public User getCreator() {
		return creator;
	}

	public void setCreator(User creator) {
		this.creator = creator;
	}

	public List<User> getParticipants() {
		return participants;
	}

	public List<Comment> getComments() {
		return comments;
	}
}
