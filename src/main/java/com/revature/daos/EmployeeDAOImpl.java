package com.revature.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import com.revature.models.Employee;
import com.revature.utils.ConnectionUtil;

public class EmployeeDAOImpl implements EmployeeDAO {

	@Override
	public boolean addEmployee(Employee employee) {
		try (Connection connection = ConnectionUtil.getConnection()) {
			String sql = "INSERT INTO employees (email, PASSWORD, is_manager) VALUES (?,?,?)";

			PreparedStatement statement = connection.prepareStatement(sql);

			int index = 0;
			statement.setString(++index, employee.getEmail());
			statement.setString(++index, employee.getPassword());
			if (employee.isManager()) {
				statement.setBoolean(++index, employee.isManager());
			} else {
				statement.setBoolean(++index, false);
			}

			statement.execute();

			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
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

	// // TODO - move employee validation to EmployeeService
	// // ? Do I need this function at all? or just return all employees to
	// // ? EmployeeService then return the specified employee object from the
	// // ? service
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
