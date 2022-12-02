package com.revature.services;

import java.util.List;

import com.revature.exceptions.EmployeeExistsException;
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

	public boolean register(Employee employee) {
		try {
			return employeeService.addEmployee(employee);
		} catch (EmployeeExistsException e) {
			e.printStackTrace();
			return false;
		}
	}
}
