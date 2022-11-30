package com.revature.services;

import java.util.List;

import com.revature.daos.TicketDAO;
import com.revature.daos.TicketDAOImpl;
import com.revature.models.Ticket;

public class TicketService {
	private TicketDAO ticketDAO = new TicketDAOImpl();

	// TODO - get employeeID from SessionStorage
	public boolean addTicket(Ticket ticket, int employeeID) {
		return ticketDAO.addTicket(ticket, employeeID);
	}

	// TODO - get employeeID from SessionStorage
	public List<Ticket> getEmployeeTickets(int employeeID) {
		return ticketDAO.getEmployeeTickets(employeeID);
	}

	// TODO - verify user is manager from SessionStorage
	// TODO - prevent managers from seeing their own tickets
	public List<Ticket> getPendingTickets() {
		return ticketDAO.getPendingTickets();
	}

	// TODO - get managerID from SessionStorage
	// TODO - check ticket status is either approved/denied
	public boolean updateTicket(Ticket ticket, int managerID) {
		return ticketDAO.updateTicket(ticket, managerID);
	}
}
