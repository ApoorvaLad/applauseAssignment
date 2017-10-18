package com.application.vo;

public class Document {
	private int testerId;
	private String country;
	private String deviceId;
	private String deviceName;
	private int bugId;

	public Document(int testerId, String country, String deviceId, String deviceName, int bugId) {
		this.testerId = testerId;
		this.country = country;
		this.deviceId = deviceId;
		this.deviceName = deviceName;
		this.bugId = bugId;
	}

	public int getTesterId() {
		return testerId;
	}

	public void setTesterId(int testerId) {
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

	public int getBugId() {
		return bugId;
	}

	public void setBugId(int bugId) {
		this.bugId = bugId;
	}
}
