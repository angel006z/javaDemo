package com.meida.base.controller;

import org.springframework.web.servlet.ModelAndView;

import com.meida.base.vo.ResultMessage;

public class BaseBackendController extends BaseController {
	
	public ModelAndView NoAccessPageAuth(ResultMessage tempAuth)
    {
		ModelAndView modelAndView = new ModelAndView();		
        String rawUrl = "";
        
		return modelAndView;
		
//        var RedirectUrl = HttpUtility.UrlEncode(rawUrl);
//        if (tempAuth.ErrorType == (int)EErrorType.NoLogin)
//        {
//            if (rawUrl.IndexOf("/Manage/Home/Index") >= 0)
//            {
//                return RedirectPermanent("/Manage/Login/Index?callback=" + RedirectUrl);
//            }
//            else if (rawUrl.IndexOf("/Index/Home/Index") >= 0)
//            {
//                return RedirectPermanent("/Manage/Login/Index?callback=" + RedirectUrl);
//            }
//            else
//            {
//                return RedirectPermanent("/Manage/Login/Index?callback=" +
//                                         HttpUtility.UrlEncode("/Manage/Home/Index?RedirectUrl=" + RedirectUrl));
//            }
//        }
//        return RedirectPermanent(string.Format("/Manage/Error/Index?url={0}&msg={1}&ErrorRank={2}", RedirectUrl, "您没有该页面的访问权限！", EErrorRank.Error));
		
		
    }
	
}
