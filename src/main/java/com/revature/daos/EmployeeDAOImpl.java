package com.revature.daos;

import java.util.List;

import com.revature.models.Employee;

public class EmployeeDAOImpl implements EmployeeDAO {

	@Override
	public void addEmployee(Employee employee) {
		// TODO Auto-generated method stub

	}

	@Override
	public Employee getEmployee(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Employee> getEmployees() {
		// TODO Auto-generated method stub
		return null;
	}

	// public List<Employee> getEmployees() {
	// // TODO
	// return null;
	// }

	// // TODO - move employee validation to EmployeeService
	// // ? Do I need this function at all? or just return all employees to
	// // ? EmployeeService then return the specified employee object from the
	// service
	// public Employee getEmployee(Employee employee) {
	// for (Employee emp : employees) {
	// if (emp.getEmail().matches(employee.getEmail()) &&
	// emp.getPassword().matches(employee.getPassword())) {
	// return emp;
	// }
	// }

	// throw new Error("Email and password do not match any employees");
	// }

	// public void addEmployee(Employee employee) {
	// employees.add(employee);
	// }
}
