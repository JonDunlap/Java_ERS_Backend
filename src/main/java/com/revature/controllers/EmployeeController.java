package com.revature.controllers;

import java.util.List;

import com.revature.exceptions.EmployeeExistsException;
import com.revature.models.Employee;
import com.revature.services.EmployeeService;

import io.javalin.Javalin;
import io.javalin.http.Handler;

public class EmployeeController implements Controller {
	private EmployeeService employeeService = new EmployeeService();

	Handler getEmployees = ctx -> {
		List<Employee> list = employeeService.getEmployees();

		ctx.json(list);
		ctx.status(200);
	};

	Handler getEmployee = ctx -> {
		String idString = ctx.pathParam("id");

		int id = 0;

		try {
			id = Integer.parseInt(idString);
		} catch (NumberFormatException e) {
			ctx.status(422);
			return;
		}

		Employee employee = employeeService.getEmployee(id);
		ctx.json(employee);
		ctx.status(200);
	};

	Handler addEmployee = ctx -> {
		Employee employee = ctx.bodyAsClass(Employee.class);

		try {
			if (employeeService.addEmployee(employee)) {
				ctx.status(201);
			} else {
				ctx.status(500);
			}
		} catch (EmployeeExistsException e) {
			ctx.status(400);
		}
	};

	@Override
	public void addRoutes(Javalin app) {
		app.get("/employee", getEmployees);
		app.get("/employee/{id}", getEmployee);
		app.post("/employee", addEmployee);
	}
}
