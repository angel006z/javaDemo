package com.meida.exception;

/**
 *邮件异常
 */
public class EmailException extends Exception {
	
	public EmailException(){
		super();
	}
	
	public EmailException(String message){
		super(message);
	}
	
	public EmailException(String message,Throwable cause){
		super(message,cause);
	}
	
	public EmailException(Throwable cause){
		super(cause);
	}
}
