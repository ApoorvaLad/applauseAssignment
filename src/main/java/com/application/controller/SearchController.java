package com.application.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SearchController {

	@RequestMapping(value = "/devices", method = RequestMethod.GET)
	public ModelAndView getDevices(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView modelAndView = new ModelAndView("devices");
		return modelAndView;
		
	}
	
	

}
