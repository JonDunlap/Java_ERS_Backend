package com.revature.exceptions;

public class EmployeeExistsException extends Exception {

	public EmployeeExistsException() {
		super();
	}

	public EmployeeExistsException(String message) {
		super(message);
	}

	public EmployeeExistsException(Throwable cause) {
		super(cause);
	}

	public EmployeeExistsException(String message, Throwable cause) {
		super(message, cause);
	}

	public EmployeeExistsException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
