package com.meida.exception;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
/**
 * message可以传国际化key值进来，在显示的时候会国际化
   * 框架基类异常，所有系统异常都应该继续此异常，以使用后期扩展
 */
public class SystemException extends RuntimeException {
	
	private static final long serialVersionUID = 3551524761519231413L;
	protected List<String> messageList=new ArrayList<String>();
	protected Log log=LogFactory.getLog(this.getClass());
	
	public SystemException() {
		super();
	}

	public SystemException(Throwable throwable){
		super(throwable);
	}
	
	public SystemException(String message) {
		super(message);
		messageList.add(message);
	}

	public SystemException(String message,Throwable cause) {
		super(message,cause);
		messageList.add(message);
	}
	
	public List<String> getMessageList() {
		if(messageList.size()==0){
			messageList.add(super.getMessage());
		}
		return messageList;
	}
	
	public void addMessage(String message){
		messageList.add(message);
	}

}
