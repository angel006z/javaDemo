package com.meida.backend.basic.controller;

import java.util.UUID;

import com.meida.common.cookie.CookieUtils;
import com.meida.common.util.SessionHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.meida.backend.basic.domain.po.User;
import com.meida.backend.basic.service.inter.IUserService;
import com.meida.base.domain.vo.ResultMessage;
import com.meida.common.util.JsonUtils;
import com.meida.common.util.RequestParameters;
import com.meida.common.util.Utits;
import com.meida.common.util.constant.EErrorCode;
import com.meida.common.util.security.MD5Utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping(value = "/backend/basic/home")
public class HomeController {
	@Autowired
	private IUserService userService;

	@RequestMapping(value = "/index")
	public ModelAndView index() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("title", "");
		return modelAndView;
	}

	@RequestMapping(value = "/modifyPassword")
	@ResponseBody
	public String modifyPassword() {
		ResultMessage resultMessage = new ResultMessage();
		if (!Utits.isLogin()) {
			resultMessage.setErrorCode(EErrorCode.NoLogin);
			resultMessage.setErrorMessage("未登录.");
			return JsonUtils.toJSONString(resultMessage);
		}
		String oldPassword = RequestParameters.getString("OldPassword");
		if (oldPassword.length() <= 0) {
			resultMessage.setErrorCode(EErrorCode.Error);
			resultMessage.setErrorMessage("旧密码不能为空.");
			return JsonUtils.toJSONString(resultMessage);
		}
		String newPassword = RequestParameters.getString("NewPassword");
		if (newPassword.length() <= 0) {
			resultMessage.setErrorCode(EErrorCode.Error);
			resultMessage.setErrorMessage("新密码不能为空.");
			return JsonUtils.toJSONString(resultMessage);
		}
		
		UUID currentUserId = Utits.getCurrentUserId();
		User item = userService.getObjectById(currentUserId.toString());
		if (item == null) {
			resultMessage.setErrorCode(EErrorCode.Error);
			resultMessage.setErrorMessage("当前用户不存在.");
			return JsonUtils.toJSONString(resultMessage);
		}
		if (!oldPassword.equals(item.getPassword())) {
			resultMessage.setErrorCode(EErrorCode.Error);
			resultMessage.setErrorMessage("旧密码有误.");
			return JsonUtils.toJSONString(resultMessage);
		}

		boolean isFlag = userService.changePassword(item.getUserId(), newPassword);
		if (isFlag) {
			resultMessage.setErrorCode(EErrorCode.Success);
			resultMessage.setErrorMessage("操作成功.");
			return JsonUtils.toJSONString(resultMessage);
		} else {
			resultMessage.setErrorCode(EErrorCode.Error);
			resultMessage.setErrorMessage("操作失败.");
			return JsonUtils.toJSONString(resultMessage);
		}
	}

	@RequestMapping(value = "/quitLogin")
	@ResponseBody
	public String quitLogin(HttpServletRequest request, HttpServletResponse response) {

		SessionHelper.removeString("USERID");

		CookieUtils.deleteCookie(request, response, "USERNAME");
		CookieUtils.deleteCookie(request, response, "PASSWORD");
		
		ResultMessage resultMessage = new ResultMessage();
		resultMessage.setErrorCode(EErrorCode.Success);
		resultMessage.setErrorMessage("退出系统成功.");
		return JsonUtils.toJSONString(resultMessage);
	}
}
