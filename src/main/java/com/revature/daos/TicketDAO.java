package com.revature.daos;

import java.util.List;

import com.revature.models.Ticket;

public interface TicketDAO {
	public abstract List<Ticket> getEmployeeTickets(int id);

	public abstract List<Ticket> getPendingTickets();

	public abstract boolean addTicket(Ticket ticket);

	public abstract boolean updateTicket(int ticketID, int managerID);
}
