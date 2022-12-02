package com.revature.services;

import java.util.List;

import com.revature.daos.TicketDAO;
import com.revature.daos.TicketDAOImpl;
import com.revature.models.Ticket;

public class TicketService {
	private TicketDAO ticketDAO = new TicketDAOImpl();

	public boolean addTicket(Ticket ticket, int employeeID) {
		return ticketDAO.addTicket(ticket, employeeID);
	}

	public List<Ticket> getEmployeeTickets(int employeeID) {
		return ticketDAO.getEmployeeTickets(employeeID);
	}

	// TODO - add method to get tickets with query: approved/denied/pending
	public List<Ticket> getEmployeeTicketByQuery(int employeeID, String query) {
		return ticketDAO.getEmployeeTicketByQuery(employeeID, query);
	}

	public List<Ticket> getPendingTickets() {
		return ticketDAO.getPendingTickets();
	}

	public Ticket getTicketByID(int id) {
		return ticketDAO.getTicketByID(id);
	}

	public boolean updateTicket(Ticket ticket, int managerID) {
		return ticketDAO.updateTicket(ticket, managerID);
	}
}
