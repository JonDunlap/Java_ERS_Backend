package com.revature.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
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
		try (Connection connection = ConnectionUtil.getConnection()) {
			String sql = "SELECT * FROM employees WHERE id=" + id + ";";

			Statement statement = connection.createStatement();

			ResultSet resultSet = statement.executeQuery(sql);

			Employee employee = new Employee();

			if (resultSet.next()) {
				employee.setId(resultSet.getInt("employee_id"));
				employee.setEmail(resultSet.getString("email"));
				employee.setPassword(resultSet.getString("password"));
				employee.setManager(resultSet.getBoolean("is_manager"));
			}

			return employee;

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Employee> getEmployees() {
		try (Connection connection = ConnectionUtil.getConnection()) {
			String sql = "SELECT * FROM employees";

			Statement statement = connection.createStatement();

			ResultSet resultSet = statement.executeQuery(sql);

			List<Employee> employees = new ArrayList<>();

			while (resultSet.next()) {
				Employee employee = new Employee();
				employee.setId(resultSet.getInt("employee_id"));
				employee.setEmail(resultSet.getString("email"));
				employee.setPassword(resultSet.getString("password"));
				employee.setManager(resultSet.getBoolean("is_manager"));
				employees.add(employee);
			}

			return employees;

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
}
