package com.revature;

import com.revature.models.Employee;
import com.revature.models.Manager;
import com.revature.models.Ticket;

import io.javalin.Javalin;

public class Main {

	public static void main(String[] args) {

		// ======== Server variables
		// Port variable
		// TODO import environment variables from .env file
		// int port = Dotenv.get("PORT") ? Dotenv.get("PORT") : 4000;
		int port = 4000; // * Temporary variable */

		// Create Javalin server
		Javalin app = Javalin.create();
		// Javalin config
		//
		// Javalin routes
		app.get("/", ctx -> ctx.result("Hello World"));
		// start Javalin server using port variable
		app.start(port);

		// ! ============ DEBUG Classes
		Employee testEmployee = new Employee("test@email.com", "abc123");
		Manager testManager = new Manager("test2@email.com", "abc123", true);
		Ticket testTicket = new Ticket(10.00, "Test description");

		System.out.println(testEmployee);
		System.out.println(testManager);
		System.out.println(testTicket);

		// test that status only works if it is "approved" or "denied"
		// testTicket.setStatus("approved");
		testTicket.setStatus("APPROVED");
		// testTicket.setStatus("denied");
		// testTicket.setStatus("DENIED");
		// testTicket.setStatus("test");
		System.out.println(testTicket);

	}

}