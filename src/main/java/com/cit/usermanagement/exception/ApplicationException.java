package com.cit.usermanagement.exception;

public class ApplicationException extends RuntimeException {

	private static final long serialVersionUID = -1501447398005198726L;

	public ApplicationException(String message) {
		super(message);
	}

	public ApplicationException(String message, Throwable e) { super(message, e);}

}
