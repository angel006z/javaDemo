package com.meida.base.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.meida.base.vo.ResultMessage;
import com.meida.common.util.constant.EErrorCode;

public class BaseBackendController extends BaseController {

	public ModelAndView noAccessPageAuth(ResultMessage tempAuth) {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getRequest();
		ModelAndView modelAndView = new ModelAndView();
		String rawUrl = request.getRequestURI();
		String redirectUrl = "";
		if (tempAuth.getCode() == EErrorCode.NoLogin) {
			modelAndView.setViewName("/backend/basic/login/index?callback=" + redirectUrl);
		}

		return modelAndView;
	}

}
