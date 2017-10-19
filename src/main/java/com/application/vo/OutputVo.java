package com.application.vo;

public class OutputVo {
	private int testerId;
	private long noOfbugs;
	private String deviceName;

	public int getTesterId() {
		return testerId;
	}

	public void setTesterId(int testerId) {
		this.testerId = testerId;
	}

	public long getNoOfbugs() {
		return noOfbugs;
	}

	public void setNoOfbugs(long l) {
		this.noOfbugs = l;
	}

	public String getDeviceName() {
		return deviceName;
	}

	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}
}
