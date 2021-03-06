package com.meida.paysdk.alipay.page.pojo;

import com.meida.common.constant.EErrorCode;

public class AlipayPageResultTradePay {
	public AlipayPageResultTradePay()
    {
		code = EErrorCode.Error;
		message = "操作失败.";
		form = "";
    }
    private String code ;
    private String message;
    private String form;
    
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getForm() {
		return form;
	}
	public void setForm(String form) {
		this.form = form;
	}
}
