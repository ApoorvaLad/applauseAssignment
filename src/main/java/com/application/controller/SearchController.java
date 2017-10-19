package com.application.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.application.service.SearchService;
import com.application.vo.Devices;
import com.application.vo.OutputVo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

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
	public ResponseEntity<String> search(@RequestBody HashMap<Object, Object> requstBody) {
		SearchService searchService = new SearchService();
		List<OutputVo> objectVos = searchService.searchByDevice(requstBody);
		ObjectMapper mapper  = new ObjectMapper();
		String outputJson = null;
		try {
			outputJson = mapper.writeValueAsString(objectVos);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(requstBody);
		return new ResponseEntity<String>(outputJson,HttpStatus.OK);

	}
}
