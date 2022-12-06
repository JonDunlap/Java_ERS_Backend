package com.revature.daos;

import java.util.List;

import com.revature.models.Ticket;

public interface TicketDAO {
	public abstract List<Ticket> getEmployeeTickets(int id);

	public abstract List<Ticket> getEmployeeTickets(int id, String query);

	public abstract List<Ticket> getPendingTickets();

	public abstract boolean addTicket(Ticket ticket, int employeeID);

	public abstract boolean updateTicket(Ticket ticket, int managerID);

	public abstract Ticket getTicketByID(int id);

}
