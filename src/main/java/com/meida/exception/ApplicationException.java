package com.meida.exception;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 框架基类异常，所有与应用相关异常都应该继续此异常，以使用后期扩展
 */
public class ApplicationException extends RuntimeException {
	
	private static final long serialVersionUID = 3551524761519231413L;
	
	protected Log log = LogFactory.getLog(this.getClass());
	
	//message可以传国际化key值进来，在显示的时候取国际化值
	private String messageKey;
	
	public ApplicationException() {
		super();
	}

	public ApplicationException(Throwable throwable){
		super(throwable);
		setMessageKey(throwable.getMessage());
	}
	
	public ApplicationException(String message) {
		super(message);
		setMessageKey(message);
	}

	public ApplicationException(String message,Throwable cause) {
		super(message,cause);
		setMessageKey(message);
	}

	public String getMessageKey() {
		return messageKey;
	}

	public void setMessageKey(String messageKey) {
		this.messageKey = messageKey;
	}
	
}
