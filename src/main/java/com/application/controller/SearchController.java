package com.application.controller;

import java.util.HashMap;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.application.service.SearchService;
import com.application.vo.Devices;

@RestController
public class SearchController {

	public SearchService searchservice;

	@RequestMapping(value = "/devices", method = RequestMethod.GET)
	public Set<String> getDevices() {
		searchservice = new SearchService();

		return searchservice.getAllDevices();
	}

	@RequestMapping(value = "/countries", method = RequestMethod.GET)
	public Set<String> getCountries() {
		searchservice = new SearchService();

		return searchservice.getCountryNames();
	}

	@RequestMapping(value = "/search", method = RequestMethod.POST, consumes = "application/json")
	public Set<String> search(@RequestBody HashMap<String, String> requstBody) {
		System.out.println(requstBody);
		return null;

	}
}
