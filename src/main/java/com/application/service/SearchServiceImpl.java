package com.application.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import com.application.vo.Devices;

public interface SearchServiceImpl {
	Set<String> getAllDevices();
	void searchByDevice(HashMap<String, Object> deviceNames);
	Set<String> getCountryNames();
}
