package com.example.demo.utils;

import java.util.List;

public class CustomValidationException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<String> exceptionMessages;
	
	public CustomValidationException(List<String> exceptionMessages) {
		super();
		this.exceptionMessages = exceptionMessages;
	}

	public List<String> getExceptionMessages() {
		return exceptionMessages;
	}
}
