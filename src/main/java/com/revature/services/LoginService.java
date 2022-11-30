package com.revature.services;

import java.util.List;

import com.revature.models.Employee;

public class LoginService {
	EmployeeService employeeService = new EmployeeService();

	public Employee login(String email, String password) {
		List<Employee> employees = employeeService.getEmployees();

		for (Employee emp : employees) {
			if (emp.getEmail().matches(email)) {
				return emp;
			}
		}
		return null;
	}
}
