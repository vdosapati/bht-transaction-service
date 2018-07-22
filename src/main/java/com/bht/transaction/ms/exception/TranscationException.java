package com.bht.transaction.ms.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TranscationException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String errorcode;
	private String errorMessage;
	private String errorName;
	
	
	public TranscationException(String errorcode, String errorMessage, String errorName) {
		this.errorcode = errorcode;
		this.errorMessage = errorMessage;
		this.errorName = errorName;
	}
	
	public TranscationException(String errorcode, String errorMessage) {
		this.errorcode = errorcode;
		this.errorMessage = errorMessage;
	}
	
	public TranscationException(String errorMessage) {
		super();
		this.errorMessage = errorMessage;
	}

	
}
