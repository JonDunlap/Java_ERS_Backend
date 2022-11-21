package com.revature.models;

// import java.text.NumberFormat;
// import java.util.Locale;

public class Ticket {

	// ticket variables
	double amount;
	String description;
	String status;
	Employee employee;

	public Ticket(double amount, String description, String status, Employee employee) {
		super();
		this.amount = amount;
		this.description = description;
		this.status = status;
		this.employee = employee;
	}

	public Ticket() {
		super();
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

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(amount);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((employee == null) ? 0 : employee.hashCode());
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
		if (employee == null) {
			if (other.employee != null)
				return false;
		} else if (!employee.equals(other.employee))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Ticket [amount=" + amount + ", description=" + description + ", status=" + status + ", employee=" + employee
				+ "]";
	}

	// create variable for formatting currency
	// NumberFormat formatter = NumberFormat.getCurrencyInstance(Locale.US);

	// ticket constructor
	// public Ticket(
	// double amount, String description) {
	// super();
	// this.amount = amount;
	// this.description = description;
	// this.status = "pending";
	// }

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
