package com.application.vo;

public class Testers {
	private String testerId;
	private String firstName;
	private String country;
	private String lastLogin;

	public Testers(String testerId, String firstName, String country, String lastLogin) {
		this.testerId = testerId;
		this.firstName = firstName;
		this.country = country;
		this.lastLogin = lastLogin;
	}

	public String getTesterId() {
		return testerId;
	}

	public void setTesterId(String testerId) {
		this.testerId = testerId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getLastLogin() {
		return lastLogin;
	}

	public void setLastLogin(String lastLogin) {
		this.lastLogin = lastLogin;
	}

}
