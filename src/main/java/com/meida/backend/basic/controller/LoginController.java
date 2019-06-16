package com.meida.backend.basic.controller;

import javax.servlet.http.HttpServletResponse;

import com.meida.common.cookie.CookieUtils;
import com.meida.common.util.security.DesUtils;
import com.meida.common.util.security.HashEncryptUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.meida.backend.basic.domain.po.User;
import com.meida.backend.basic.service.inter.IUserService;
import com.meida.base.domain.vo.ResultMessage;
import com.meida.common.util.JsonUtils;
import com.meida.common.util.RequestParameters;
import com.meida.common.util.SessionHelper;
import com.meida.common.util.constant.EErrorCode;
import com.mysql.cj.util.StringUtils;

@Controller
@RequestMapping(value = "/backend/basic/login")
public class LoginController {
	
	@Autowired
	private IUserService userService;
	
	@RequestMapping(value = "/index")
	public ModelAndView index() {	
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("title", "登录页面");
		return modelAndView;
	}
	
	@RequestMapping(value = "/dindex")
	public ModelAndView dindex() {	
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("title", "弹窗登录页面");
		return modelAndView;
	}
	
	/************** JsonResult Begin ******************/
	/**
	 * 登录系统
	 * 
	 * @return
	 */
	@RequestMapping(value = "/loginSystem")
	@ResponseBody
	public String loginSystem() {
        ResultMessage resultMessage = new ResultMessage();

		int isCookieUp = 1; //1：cookie用户名和密码；2：cookie用户名；3：不要cookie
        boolean isCode = false;//是否有验证码，默认有(true)
        String userName = RequestParameters.getString("userName");
        String password = RequestParameters.getString("password");
        boolean remember = RequestParameters.getString("remember") == "1";//记住密码                    
        String code = RequestParameters.getString("code");

        if (userName.length() <= 0) {
			resultMessage.setCode(EErrorCode.Error);
			resultMessage.setMessage("用户名不能为空.");
			return JsonUtils.toJSONString(resultMessage);
		}

        if (password.length() <= 0) {
        	resultMessage.setCode(EErrorCode.Error);
        	resultMessage.setMessage("密码不能为空.");
        	return JsonUtils.toJSONString(resultMessage);
        }

        if (!isOkValidateCode(isCode, code))
        {
            clearValidateCode(isCode);
        	resultMessage.setCode(EErrorCode.Error);
        	resultMessage.setMessage("验证码错误.");
        	return JsonUtils.toJSONString(resultMessage);
        }

        clearValidateCode(isCode);

        if (userService.isExistUserName(userName)==false)
        {
        	resultMessage.setCode(EErrorCode.Error);
        	resultMessage.setMessage("用户名不存在.");
        	return JsonUtils.toJSONString(resultMessage);
        }

        User item = userService.loginUser(userName, HashEncryptUtils.backendPassword(password));
        if (item != null)
        {
            SessionHelper.setString("USERID", item.getUserId());
            if (remember)
            {
                HttpServletResponse response =  ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getResponse();
                // 记住内容详细
                if (isCookieUp == 1) //记住用户名和密码
                {
                    CookieUtils.addCooke(response,"USERNAME",DesUtils.encrypt(userName) ,60*60*24*30);
                    CookieUtils.addCooke(response,"PASSWORD",DesUtils.encrypt(HashEncryptUtils.backendPassword(password)),60*60*24*30);
                }
                else if (isCookieUp == 2) //记住用户名不记住密码
                {
                    CookieUtils.addCooke(response,"USERNAME",userName,60*60*24*30);
                }
                else //都不用记
                {
                }
            }
        	resultMessage.setCode(EErrorCode.Success);
        	resultMessage.setMessage("登录成功.");
        	return JsonUtils.toJSONString(resultMessage);
        }
        else
        {
        	resultMessage.setCode(EErrorCode.Error);
        	resultMessage.setMessage("密码错误.");
        	return JsonUtils.toJSONString(resultMessage);
        }
	}
	
	 private boolean isOkValidateCode(boolean isCode, String code)
     {
         if (!isCode) { return true; }
         String sessionCheckCode=SessionHelper.getString("CheckCode");
         if (StringUtils.isNullOrEmpty(sessionCheckCode) || StringUtils.isNullOrEmpty(code))
         {
             return false;
         }
         if (code.trim().toLowerCase() != sessionCheckCode.toLowerCase())
         {
             return false;
         }
         return true;
     }

     private void clearValidateCode(boolean isCode)
     {
         if (isCode)
         {
             SessionHelper.setString("CheckCode",null); 
         }
     }
     
	/************** JsonResult End ******************/
}
