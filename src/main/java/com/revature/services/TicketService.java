package com.revature.services;

import java.util.List;

import com.revature.daos.TicketDAO;
import com.revature.models.Ticket;

public class TicketService {
	private TicketDAO ticketDAO = new TicketDAO();

	public List<Ticket> getTickets() {
		return ticketDAO.getAllTickets();
	}

	public void addTicket(Ticket ticket) {
		// ! DEBUG - use salt/hash method for generating employee IDs
		int newID = getTickets().size() + 1;
		ticket.setId(newID);
		ticketDAO.addTicket(ticket);
	}

	// TODO - add function to change status of ticket (pending -> approved/denied)
	// TODO - add resolver to ticket (managerID of who completed the ticket)
}
