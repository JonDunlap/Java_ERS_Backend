package com.revature.models;

public class Employee {
	// class values
	public String email;
	public String password;

	// create constructor method
	public Employee(String email, String password) {
		super();
		this.email = email;
		this.password = password;
	}

	// override .toString() method
	@Override
	public String toString() {
		return ("Email " + email + ", Password " + password);
	}

}
