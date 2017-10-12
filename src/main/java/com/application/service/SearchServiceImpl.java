package com.application.service;

import java.util.ArrayList;

import com.application.vo.Devices;

public interface SearchServiceImpl {
	Devices[] getAllDevices();
	void searchByDevice(ArrayList<String> deviceNames);
}
