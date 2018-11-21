package com.meida.common.util;

import com.meida.base.vo.ResultMessage;

public class Utits {

    /**
     * 动态生成查看、修改、增加、删除、等按钮
     * @return
     */
    public static String AuthOperateButton()
    {
        return "";
    }
    
    /**
     * 
     * @return
     */
    public static ResultMessage AccessPageAuth()
    {
    	ResultMessage resultMessage = new ResultMessage();
    	resultMessage.setErrorCode("0");
    	resultMessage.setErrorMessage("未登录.");
    	return resultMessage;
    }
   
    
    
}
