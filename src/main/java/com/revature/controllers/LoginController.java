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

		if (loginService.login(attempt.email, attempt.password) != null) {
			Employee employee = loginService.login(attempt.email, attempt.password);
			HttpSession session = ctx.req().getSession();

			session.setAttribute("employee", employee);
			ctx.status(200);
		} else {
			ctx.status(401);
		}
	};

	Handler logout = ctx -> {
		HttpSession session = ctx.req().getSession(false);

		if (session != null) {
			session.invalidate();
			ctx.status(200);
		} else {
			ctx.status(400);
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
