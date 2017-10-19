package com.application.service;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

import com.application.vo.OutputVo;

public interface SearchServiceImpl {
	Set<String> getAllDevices();
	List<OutputVo> searchByDevice(HashMap<Object, Object> deviceNames);
	Set<String> getCountryNames();
}
