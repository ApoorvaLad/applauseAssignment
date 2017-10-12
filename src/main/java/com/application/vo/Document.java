package com.application.vo;

public class Document {
	private String testerId;
	private String country;
	private String deviceId;
	private String deviceName;
	private String bugId;

	public Document(String testerId, String country, String deviceId, String deviceName, String bugId) {
		this.testerId = testerId;
		this.country = country;
		this.deviceId = deviceId;
		this.deviceName = deviceName;
		this.bugId = bugId;
	}

	public String getTesterId() {
		return testerId;
	}

	public void setTesterId(String testerId) {
		this.testerId = testerId;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public String getDeviceName() {
		return deviceName;
	}

	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}

	public String getBugId() {
		return bugId;
	}

	public void setBugId(String bugId) {
		this.bugId = bugId;
	}
}
