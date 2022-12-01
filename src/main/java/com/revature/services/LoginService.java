package com.revature.services;

import java.util.List;

import com.revature.models.Employee;

public class LoginService {
	EmployeeService employeeService = new EmployeeService();

	public Employee login(String email, String password) {
		List<Employee> employees = employeeService.getEmployees();

		try {
			for (Employee emp : employees) {
				if (emp.getEmail().matches(email) && emp.getPassword().matches(password)) {
					return emp;
				}
			}
			return null;
		} catch (NullPointerException e) {
			return null;
		}
	}

	// TODO - add method to register user
	// TODO - make sure that user doesn't exist already
}
