package com.application.vo;

public class Bugs {
	private int bugId;
	private String deviceId;
	private int testerId;

	public Bugs(int bugId, String deviceId, int testerId) {
		this.bugId = bugId;
		this.deviceId = deviceId;
		this.testerId = testerId;
	}

	public int getBugId() {
		return bugId;
	}

	public void setBugId(int bugId) {
		this.bugId = bugId;
	}

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public int getTesterId() {
		return testerId;
	}

	public void setTesterId(int testerId) {
		this.testerId = testerId;
	}

}
