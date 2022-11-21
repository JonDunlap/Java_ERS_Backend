package com.revature.models;

// import java.text.NumberFormat;
// import java.util.Locale;

public class Ticket {

	// ticket variables
	private int id;
	private double amount;
	private String description;
	private String status;
	private int employeeID;

	public Ticket(int id, double amount, String description, String status, int employeeID) {
		super();
		this.id = id;
		this.amount = amount;
		this.description = description;
		this.status = status;
		this.employeeID = employeeID;
	}

	public Ticket() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getEmployeeID() {
		return employeeID;
	}

	public void setEmployeeID(int employeeID) {
		this.employeeID = employeeID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		long temp;
		temp = Double.doubleToLongBits(amount);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + employeeID;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ticket other = (Ticket) obj;
		if (id != other.id)
			return false;
		if (Double.doubleToLongBits(amount) != Double.doubleToLongBits(other.amount))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		if (employeeID != other.employeeID)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Ticket [id=" + id + ", amount=" + amount + ", description=" + description + ", status=" + status
				+ ", employeeID=" + employeeID + "]";
	}

	// create variable for formatting currency
	// NumberFormat formatter = NumberFormat.getCurrencyInstance(Locale.US);

	// public method to change default status variable
	// public void setStatus(String status) {
	// // ? possibly move this logic into the business logic class
	// if (status.toLowerCase().equals("approved") ||
	// status.toLowerCase().equals("denied")) {
	// this.status = status.toLowerCase();
	// } else {
	// throw new Error("Invalid status type, select either approved or denied.");
	// }
	// }

}
