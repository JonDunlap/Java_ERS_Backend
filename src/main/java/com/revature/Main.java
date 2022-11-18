package com.revature;

import com.revature.models.Employee;
import com.revature.models.Manager;
import com.revature.models.Ticket;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// ! DEBUG Classes
		Employee testEmployee = new Employee("test@email.com", "abc123");
		Manager testManager = new Manager("test2@email.com", "abc123", true);
		Ticket testTicket = new Ticket(10.00, "Test description");

		System.out.println(testEmployee);
		System.out.println(testManager);
		System.out.println(testTicket);

	}

}