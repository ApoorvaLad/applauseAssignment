package com.application.vo;

public class Devices {
	private String deviceId;
	private String description;

	public Devices(String deviceId, String description) {
		this.deviceId = deviceId;
		this.description = description;
	}

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
