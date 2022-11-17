package com.revature.models;

public class Manager extends Employee {

	// manager variables
	public boolean isManager;

	// manager constructor
	public Manager(String email, String password, boolean isManager) {
		// call super for employee constructor variables
		super(email, password);
		this.isManager = isManager;
	}

}
