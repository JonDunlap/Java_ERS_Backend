package com.revature;

import com.revature.controllers.Controller;
import com.revature.controllers.EmployeeController;
import com.revature.controllers.LoginController;
import com.revature.controllers.TicketController;

import io.javalin.Javalin;

public class Main {
	// Create Javalin variable for use in main method and configure method
	private static Javalin app;

	public static void main(String[] args) {

		// ======== Javalin Server
		// Create Javalin server
		app = Javalin.create();
		// add Javalin route controllers
		configure(new TicketController(), new EmployeeController(), new LoginController());
		// start Javalin server on port 4000
		app.start(4000);
	}

	public static void configure(Controller... controllers) {
		for (Controller controller : controllers) {
			controller.addRoutes(app);
		}
	}
}