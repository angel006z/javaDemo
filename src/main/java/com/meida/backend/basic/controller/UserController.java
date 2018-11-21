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
@RequestMapping(value = "/backend/basic/user")
public class UserController extends BaseBackendController{
	//http://localhost:9090/demo/backend/basic/user/list
	@RequestMapping(value = "/list")
	public ModelAndView list() {
		ModelAndView modelAndView = new ModelAndView();
		return modelAndView;
	}
	//http://localhost:9090/demo/backend/basic/user/detail
	@RequestMapping(value = "/detail")
	public ModelAndView detail() {
		ModelAndView modelAndView = new ModelAndView();
		return modelAndView;
	}
	
	
}
