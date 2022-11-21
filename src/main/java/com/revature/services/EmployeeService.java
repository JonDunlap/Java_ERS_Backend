package com.revature.services;

import java.util.List;

import com.revature.daos.EmployeeDAO;
import com.revature.models.Employee;

public class EmployeeService {
	private EmployeeDAO employeeDAO = new EmployeeDAO();

	public List<Employee> getEmployees() {
		return employeeDAO.getEmployees();
	}

	public Employee getEmployee(Employee employee) {
		return employeeDAO.getEmployee(employee);
	}

	// TODO - add validation that email doesn't exist already
	public void addEmployee(Employee employee) {
		// ! DEBUG - use salt/hash method for generating employee IDs
		int newID = getEmployees().size() + 1;
		employee.setId(newID);
		employeeDAO.addEmployee(employee);
	}
}
