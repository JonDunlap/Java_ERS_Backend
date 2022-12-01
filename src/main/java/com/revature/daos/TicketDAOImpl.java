package com.revature.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.models.Ticket;
import com.revature.utils.ConnectionUtil;

public class TicketDAOImpl implements TicketDAO {

	@Override
	public boolean addTicket(Ticket ticket, int employeeID) {
		try (Connection connection = ConnectionUtil.getConnection()) {
			String sql = "INSERT INTO tickets (amount, description, employee_id) VALUES" +
					"(?,?,?);";

			PreparedStatement statement = connection.prepareStatement(sql);

			int index = 1;
			statement.setDouble(index++, ticket.getAmount());
			statement.setString(index++, ticket.getDescription());
			statement.setInt(index++, employeeID);

			statement.execute();

			return true;

		} catch (SQLException e) {
			e.getStackTrace();
			return false;
		}
	}

	@Override
	public List<Ticket> getEmployeeTickets(int id) {
		try (Connection connection = ConnectionUtil.getConnection()) {
			String sql = "SELECT  * FROM tickets WHERE employee_id=" + id + ";";

			Statement statement = connection.createStatement();

			ResultSet resultSet = statement.executeQuery(sql);

			List<Ticket> tickets = new ArrayList<>();

			while (resultSet.next()) {
				Ticket ticket = new Ticket();

				ticket.setId(resultSet.getInt("ticket_id"));
				ticket.setAmount(resultSet.getDouble("amount"));
				ticket.setDescription(resultSet.getString("description"));
				ticket.setStatus(resultSet.getString("status"));
				ticket.setEmployeeID(resultSet.getInt("employee_id"));
				ticket.setResolvingManagerID(resultSet.getInt("resolving_manager_id"));

				tickets.add(ticket);
			}

			return tickets;

		} catch (SQLException e) {
			e.getStackTrace();
			return null;
		}
	}

	@Override
	public List<Ticket> getPendingTickets() {
		try (Connection connection = ConnectionUtil.getConnection()) {

			String sql = "SELECT * FROM tickets WHERE status='pending';";

			Statement statement = connection.createStatement();

			ResultSet resultSet = statement.executeQuery(sql);

			List<Ticket> tickets = new ArrayList<>();

			while (resultSet.next()) {
				Ticket ticket = new Ticket();

				ticket.setId(resultSet.getInt("ticket_id"));
				ticket.setAmount(resultSet.getDouble("amount"));
				ticket.setDescription(resultSet.getString("description"));
				ticket.setStatus(resultSet.getString("status"));
				ticket.setEmployeeID(resultSet.getInt("employee_id"));

				tickets.add(ticket);
			}

			return tickets;

		} catch (SQLException e) {
			e.getStackTrace();
			return null;
		}
	}

	// TODO - add method to get tickets with query: approved/denied/pending
	// TODO - add method to get ticket by ID

	/*
	 * Ticket is passed as parameter so that other parts of the ticket can be
	 * updated later (status, description, amount, etc..)
	 */
	@Override
	public boolean updateTicket(Ticket ticket, int managerID) {
		try (Connection connection = ConnectionUtil.getConnection()) {
			String sql = "UPDATE tickets SET status=?, resolving_manager_id=" + managerID + " WHERE ticket_id=?;";

			PreparedStatement statement = connection.prepareStatement(sql);

			int index = 1;
			statement.setString(index++, ticket.getStatus());
			statement.setInt(index++, ticket.getId());

			statement.execute();

			return true;

		} catch (SQLException e) {
			e.getStackTrace();
			return false;
		}
	}
}
