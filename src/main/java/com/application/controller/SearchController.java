package com.application.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.application.service.SearchService;

@Controller
public class SearchController {

	public SearchService searchservice;

	@RequestMapping(value = "/devices", method = RequestMethod.GET)
	public ModelAndView getDevices(HttpServletRequest request, HttpServletResponse response) {
		searchservice = new SearchService();
		ModelAndView modelAndView = new ModelAndView("devicesTobeSearched");
		modelAndView.addObject(searchservice.getAllDevices());
		return modelAndView;
	}

	@RequestMapping(value = "/searchDevices", method = RequestMethod.GET)
	public void searchDevices(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
		
	}

}
