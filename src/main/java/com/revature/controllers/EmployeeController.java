package com.revature.controllers;

import com.revature.models.Employee;
import com.revature.services.EmployeeService;

import io.javalin.Javalin;
import io.javalin.http.Handler;

public class EmployeeController implements Controller {
	private EmployeeService employeeService = new EmployeeService();

	private Handler getEmployee = ctx -> {
		Employee employee = employeeService.getEmployee(ctx.bodyAsClass(Employee.class));

		ctx.json(employee);
		ctx.status(200);
	};

	private Handler addEmployee = ctx -> {
		Employee employee = ctx.bodyAsClass(Employee.class);
		employeeService.addEmployee(employee);
		ctx.status(201);
	};

	@Override
	public void addRoutes(Javalin app) {
		app.get("/employee", getEmployee);
		app.post("/employee", addEmployee);
	}
}
