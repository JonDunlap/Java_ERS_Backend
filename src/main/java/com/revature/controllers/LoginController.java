package com.revature.controllers;

import com.revature.exceptions.EmployeeExistsException;
import com.revature.models.Employee;
import com.revature.models.LoginDTO;
import com.revature.services.LoginService;

import io.javalin.Javalin;
import io.javalin.http.Handler;
import jakarta.servlet.http.HttpSession;

public class LoginController implements Controller {
	private LoginService loginService = new LoginService();

	Handler login = ctx -> {
		LoginDTO attempt = ctx.bodyAsClass(LoginDTO.class);

		// check that the attempted login has a username and password
		if (attempt.email == null || attempt.password == null) {
			ctx.status(400);
			ctx.result("Email or password is empty");
			return;
		}

		// attempt to login with email and password
		if (loginService.login(attempt.email, attempt.password) == null) {
			// if no user was found, send 401 status
			ctx.status(401);
			ctx.result("There was no user found with that email and password, try again or register for access.");
			return;
		}

		// create employee object with employee returned from service
		Employee employee = loginService.login(attempt.email, attempt.password);

		HttpSession session = ctx.req().getSession();

		// add employee to the session, return 200 status
		session.setAttribute("employee", employee);
		ctx.status(200);

	};

	Handler logout = ctx -> {
		HttpSession session = ctx.req().getSession(false);

		// if session is null, return 400 status
		if (session == null) {
			ctx.status(400);
			ctx.result("No user is logged in");
			return;
		}

		// invalidate the session, send 200 status
		session.invalidate();
		ctx.status(200);
		ctx.result("Successfully logged out");
	};

	Handler register = ctx -> {
		LoginDTO attempt = ctx.bodyAsClass(LoginDTO.class);

		// check that the attempted login has a username and password
		if (attempt.email == null || attempt.password == null) {
			ctx.status(400);
			ctx.result("Email or password is empty");
			return;
		}

		Employee employee = new Employee(attempt.email, attempt.password, attempt.isManager);

		// attempt to register the employee
		try {
			if (loginService.register(employee)) {
				ctx.status(200);
				ctx.result("A new employee has been created with that email and password, proceed to login");
			} else {
				ctx.status(401);
				ctx.result("Their was an unknown issue creating this employee");
			}
		} catch (EmployeeExistsException e) {
			// if the employee exists already, send a 400 status
			// and the exception toString
			ctx.status(400);
			ctx.result(e.toString());
		}
	};

	@Override
	public void addRoutes(Javalin app) {
		app.post("/login", login);
		app.post("/register", register);
		app.get("/logout", logout);
	}
}
