package com.meida.exception;

/**
 * 文件上传异常
 */
public class FileUploadException extends ApplicationException {
	public FileUploadException() {
		super();
	}

	public FileUploadException(Throwable throwable){
		super(throwable);
	}
	
	public FileUploadException(String message) {
		super(message);
	}

	public FileUploadException(String message,Throwable cause) {
		super(message,cause);
	}
}
