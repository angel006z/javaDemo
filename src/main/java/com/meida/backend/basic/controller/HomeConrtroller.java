package com.meida.backend.basic.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/backend/basic/home")
public class HomeConrtroller {
	@RequestMapping(value = "/index")
	public ModelAndView index() {	
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("title", "");
		return modelAndView;
	}
}
