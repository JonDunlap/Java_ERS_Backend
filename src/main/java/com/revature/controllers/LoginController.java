package com.revature.controllers;

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
		if (loginService.login(attempt.email, attempt.password) != null) {
			Employee employee = loginService.login(attempt.email, attempt.password);

			HttpSession session = ctx.req().getSession();

			session.setAttribute("employee", employee);
			ctx.status(200);
		} else {
			// if no user was found, send 401 status
			ctx.status(401);
			ctx.result("There was no user found with that email and password, try again or register for access.");
		}
	};

	Handler logout = ctx -> {
		HttpSession session = ctx.req().getSession(false);

		if (session != null) {
			session.invalidate();
			ctx.status(200);
			ctx.result("Successfully logged out");
		} else {
			ctx.status(400);
			ctx.result("No user is logged in");
		}
	};

	// TODO - add method to register user
	// TODO - make sure that user has email & password
	// TODO - default to employee unless manager is selected
	// TODO - make sure that user doesn't exist already

	@Override
	public void addRoutes(Javalin app) {
		app.post("/login", login);
		app.get("/logout", logout);
	}
}
