package com.meida.exception;

/**
 *	数据库应用异常
 */
public class DAOAppExcepton extends ApplicationException {
	public DAOAppExcepton() {
		super();
	}

	public DAOAppExcepton(Throwable throwable){
		super(throwable);
	}
	
	public DAOAppExcepton(String message) {
		super(message);
	}

	public DAOAppExcepton(String message,Throwable cause) {
		super(message,cause);
	}
}
