package com.globant.whattodo.models;

import java.math.BigDecimal;

import javax.validation.constraints.NotNull;

public class LocationDTO {

	@NotNull
	private BigDecimal latitude;

	@NotNull
	private BigDecimal longitude;

	private String address;

	public BigDecimal getLatitude() {
		return latitude;
	}

	public void setLatitude(BigDecimal latitude) {
		this.latitude = latitude;
	}

	public BigDecimal getLongitude() {
		return longitude;
	}

	public void setLongitude(BigDecimal longitude) {
		this.longitude = longitude;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

}
