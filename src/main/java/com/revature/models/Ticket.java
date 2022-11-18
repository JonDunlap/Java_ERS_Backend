package com.revature.models;

public class Ticket {

	// ticket variables
	double amount;
	String description;
	String status = "pending";

	// ticket constructor
	public Ticket() {
		super();
	}

	// public method to change default status variable
	public void setStatus(String status) {
		// TODO: possibly move this logic into the business logic class
		if (status.toLowerCase() == "approved" || status.toLowerCase() == "denied") {
			this.status = status;
		} else {
			throw new Error("Invalid status type, select either approved or denied.");
		}
	}

}
