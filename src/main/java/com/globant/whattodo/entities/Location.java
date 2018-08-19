package com.globant.whattodo.entities;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Location {

	@Id
	@GeneratedValue
	private Long id;

	@Column(nullable = false)
	private BigDecimal latitude;

	@Column(nullable = false)
	private BigDecimal longitude;

	private String address;

	private Location() {
	} // JPA only

	public Location(BigDecimal latitude, BigDecimal longitude) {
		this.latitude = latitude;
		this.longitude = longitude;
	}

	public Long getId() {
		return id;
	}

	public BigDecimal getLatitude() {
		return latitude;
	}

	public BigDecimal getLongitude() {
		return longitude;
	}

	public String getAddress() {
		return address;
	}
}
