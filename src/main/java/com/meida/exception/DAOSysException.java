package com.meida.exception;

/**
 *
 */
public class DAOSysException extends SystemException {
	public DAOSysException() {
		super();
	}

	public DAOSysException(Throwable throwable){
		super(throwable);
	}
	
	public DAOSysException(String message) {
		super(message);
		messageList.add(message);
	}

	public DAOSysException(String message,Throwable cause) {
		super(message,cause);
		messageList.add(message);
	}
}
