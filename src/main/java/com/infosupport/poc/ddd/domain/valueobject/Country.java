package com.infosupport.poc.ddd.domain.valueobject;

public final class Country {

	private static final String USA = "United States of America";
	
	private String countryName;
	
	private Country(final String countryName) {
		this.countryName = countryName;
	}

	public static Country create(final String country) {
		return new Country(country);
	}

	public String getCountryName() {
		return countryName;
	}

	public boolean isUSA() { return USA.equals(countryName); }
}
