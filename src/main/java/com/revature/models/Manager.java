package com.revature.models;

public class Manager extends Employee {

	// manager variables
	public boolean isManager;

	// ? may not need this model, just add isManager variable to Employee model
	// manager constructor
	public Manager(String email, String password, boolean isManager) {
		// call super for employee constructor variables
		super(email, password);
		this.isManager = isManager;
	}

	// override .toString() method
	@Override
	public String toString() {
		return ("Email " + email + ", Password " + password + ", Manager " + isManager);
	}

}
