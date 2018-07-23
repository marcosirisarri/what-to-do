package com.globant.whattodo.entities;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Location {

	@Id
	@GeneratedValue
	private Long id;

	private BigDecimal latitude;

	private BigDecimal longitude;

	private String address;

	private Location() {
	} // JPA only

	public Location(BigDecimal latitude, BigDecimal longitude) {
		this.latitude = latitude;
		this.longitude = longitude;
	}
}
