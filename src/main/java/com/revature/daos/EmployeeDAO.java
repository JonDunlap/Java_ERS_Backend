package com.revature.daos;

import java.util.List;

import com.revature.models.Employee;

public interface EmployeeDAO {
	public abstract List<Employee> getEmployees();

	public abstract Employee getEmployee(int id);

	public abstract boolean addEmployee(Employee employee);
}
