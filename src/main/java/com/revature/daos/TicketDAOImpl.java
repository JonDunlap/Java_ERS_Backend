package com.revature.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import com.revature.models.Ticket;
import com.revature.utils.ConnectionUtil;

public class TicketDAOImpl implements TicketDAO {

	@Override
	public boolean addTicket(Ticket ticket) {
		try (Connection connection = ConnectionUtil.getConnection()) {
			String sql = "INSERT INTO tickets (amount, description, employee_id) VALUES" +
					"(?,?,?);";

			PreparedStatement statement = connection.prepareStatement(sql);

			int index = 1;
			statement.setDouble(index++, ticket.getAmount());
			statement.setString(index++, ticket.getDescription());
			// TODO - use employee ID from session storage
			if (ticket.getId() != 0) {
				statement.setInt(index++, ticket.getId());
			} else {
				statement.setInt(index++, 0);
			}

			statement.execute();

			return true;

		} catch (SQLException e) {
			e.getStackTrace();
			return false;
		}
	}

	@Override
	public List<Ticket> getEmployeeTickets(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Ticket> getPendingTickets() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean updateTicket(Ticket ticket) {
		// TODO Auto-generated method stub
		return false;
	}

}
