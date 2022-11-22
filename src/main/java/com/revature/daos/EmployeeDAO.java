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

	// ! DEBUG - possibly keep, may need all employees to verify email doesn't exist
	public List<Employee> getEmployees() {
		return employees;
	}

	// ? does this go in service or here?
	// TODO - move employee validation to EmployeeService
	public Employee getEmployee(Employee employee) {
		for (Employee emp : employees) {
			if (emp.getEmail().matches(employee.getEmail()) && emp.getPassword().matches(employee.getPassword())) {
				return emp;
			}
		}

		throw new Error("Email and password do not match any employees");
	}

	public void addEmployee(Employee employee) {
		employees.add(employee);
	}
}
