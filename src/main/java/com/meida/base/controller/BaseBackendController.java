package com.meida.base.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.servlet.ModelAndView;

import com.meida.base.vo.ResultMessage;
import com.meida.common.util.constant.EErrorCode;

public class BaseBackendController extends BaseController {

	public ModelAndView noAccessPageAuth(ResultMessage tempAuth,HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView();
		String rawUrl =request.getRequestURI();
		String redirectUrl = "";
		if (tempAuth.getErrorCode() == EErrorCode.NoLogin) {
			modelAndView.setViewName("/backend/basic/login/index?callback=" + redirectUrl);
		}
		
		return modelAndView;
	}

}
