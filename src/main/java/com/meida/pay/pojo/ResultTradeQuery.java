package com.meida.pay.pojo;

public class ResultTradeQuery {
	 private int errorType ;
	    private String messageContent;
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
}
