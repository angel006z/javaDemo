package com.meida.backend.basic.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.meida.base.controller.BaseBackendController;
/**
 * 用户管理
 * @author BING
 */
@Controller
@RequestMapping("/backend/basic/user")
public class UserController extends BaseBackendController{
	
	@RequestMapping(value = "/list")
	public ModelAndView list() {
		ModelAndView modelAndView = new ModelAndView();
		return modelAndView;
	}
	@RequestMapping(value = "/detail")
	public ModelAndView detail() {
		ModelAndView modelAndView = new ModelAndView();
		return modelAndView;
	}
}
