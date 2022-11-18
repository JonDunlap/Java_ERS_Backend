package com.revature.models;

import java.text.NumberFormat;
import java.util.Locale;

public class Ticket {

	// ticket variables
	double amount;
	String description;
	String status;

	// create variable for formatting currency
	NumberFormat formatter = NumberFormat.getCurrencyInstance(Locale.US);

	// ticket constructor
	public Ticket(
			double amount, String description) {
		super();
		this.amount = amount;
		this.description = description;
		this.status = "pending";
	}

	// override .toString() method
	@Override
	public String toString() {
		return ("Amount " + formatter.format(amount) + ", Description " + description + ", Status " + status);
	}

	// public method to change default status variable
	public void setStatus(String status) {
		// ? possibly move this logic into the business logic class
		if (status.toLowerCase().equals("approved") || status.toLowerCase().equals("denied")) {
			this.status = status.toLowerCase();
		} else {
			throw new Error("Invalid status type, select either approved or denied.");
		}
	}

}
