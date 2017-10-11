package com.application.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.application.service.SearchService;

@Controller
public class SearchController {

public SearchService searchservice;
	//@RequestMapping(value = "/devices", method = RequestMethod.GET)
	public void getDevices(/*HttpServletRequest request, HttpServletResponse response*/) {
		searchservice = new SearchService();
		searchservice.searchDevices();
		
	}
	
	public static void main(String[] args) {
		System.out.println("here");
		SearchController controller = new SearchController();
		controller.getDevices();
	}
	

}
