package com.meida.pay.pojo;

public class ResultTradePay {
	public ResultTradePay()
    {
		errorType = 0;
		messageContent = "操作失败.";
		form = "";
    }
    private int errorType ;
    private String messageContent;
    private String form;
    
	public int getErrorType() {
		return errorType;
	}
	public void setErrorType(int errorType) {
		this.errorType = errorType;
	}
	public String getMessageContent() {
		return messageContent;
	}
	public void setMessageContent(String messageContent) {
		this.messageContent = messageContent;
	}
	public String getForm() {
		return form;
	}
	public void setForm(String form) {
		this.form = form;
	}
}
