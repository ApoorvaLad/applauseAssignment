package com.application.vo;

public class Bugs {
	private String bugId;
	private String deviceId;
	private String testerId;

	public Bugs(String bugId, String deviceId, String testerId) {
		this.bugId = bugId;
		this.deviceId = deviceId;
		this.testerId = testerId;
	}

	public String getBugId() {
		return bugId;
	}

	public void setBugId(String bugId) {
		this.bugId = bugId;
	}

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public String getTesterId() {
		return testerId;
	}

	public void setTesterId(String testerId) {
		this.testerId = testerId;
	}

}
