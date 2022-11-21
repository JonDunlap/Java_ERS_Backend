package com.revature.daos;

import java.util.ArrayList;
import java.util.List;

import com.revature.models.Employee;

public class EmployeeDAO {
	// ! DEBUG - temp. employees
	private static List<Employee> employees = new ArrayList<>();

	public EmployeeDAO() {
		employees.add(new Employee(1, "test@email.com", "abc123", false));
		employees.add(new Employee(2, "test2@email.com", "abc123", true));
	}

	// ? pass email/password or pass Employee?
	public Employee getEmployee(String email, String password) {
		for (Employee employee : employees) {
			if (employee.getEmail().matches(email) && employee.getPassword().matches(password)) {
				return employee;
			}
		}

		throw new Error("Email and password do not match any employees");
	}

	public void addEmployee(Employee employee) {
		employees.add(employee);
	}
}
