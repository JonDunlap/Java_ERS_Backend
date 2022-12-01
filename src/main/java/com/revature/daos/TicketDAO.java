package com.revature.daos;

import java.util.List;

import com.revature.models.Ticket;

public interface TicketDAO {
	public abstract List<Ticket> getEmployeeTickets(int id);

	public abstract List<Ticket> getPendingTickets();

	public abstract boolean addTicket(Ticket ticket, int employeeID);

	public abstract boolean updateTicket(Ticket ticket, int managerID);

	// TODO - add method to get tickets with query: approved/denied/pending
	// TODO - add method to get ticket by ID

}
