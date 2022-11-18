package com.revature.models;

public class Ticket {

	// ticket variables
	double amount;
	String description;
	String status;

	// ticket constructor
	public Ticket(double amount, String description) {
		super();
		this.amount = amount;
		this.description = description;
		this.status = "pending";
	}

	// override .toString() method
	@Override
	public String toString() {
		return ("Amount " + amount + ", Description " + description + ", Status " + status);
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
