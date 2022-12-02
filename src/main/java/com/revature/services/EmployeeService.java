package com.revature.services;

import java.util.List;

import com.revature.daos.EmployeeDAO;
import com.revature.daos.EmployeeDAOImpl;
import com.revature.exceptions.EmployeeExistsException;
import com.revature.models.Employee;

public class EmployeeService {
	private EmployeeDAO employeeDAO = new EmployeeDAOImpl();

	public List<Employee> getEmployees() {
		return employeeDAO.getEmployees();
	}

	public Employee getEmployee(int id) {
		return employeeDAO.getEmployee(id);
	}

	public boolean addEmployee(Employee employee) throws EmployeeExistsException {
		List<Employee> employees = getEmployees();

		for (Employee emp : employees) {
			if (emp.getEmail().equals(employee.getEmail())) {
				throw new EmployeeExistsException("An employee with that email exists already.");
			}
		}
		return employeeDAO.addEmployee(employee);
	}
}
