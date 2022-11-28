package com.revature.daos;

import java.util.ArrayList;
import java.util.List;

import com.revature.models.Ticket;

public class TicketDAO {
	// ! DEBUG - temp list of tickets
	private List<Ticket> tickets = new ArrayList<>();

	public TicketDAO() {
		tickets.add(new Ticket(1, 25.00, "Test pending", "pending", 1, 0));
		tickets.add(new Ticket(2, 50.00, "Test approved", "approved", 1, 2));
		tickets.add(new Ticket(3, 50.00, "Test denied", "denied", 1, 2));
		tickets.add(new Ticket(4, 50.00, "Test pending 2", "pending", 1, 0));
	}

	// ? Functionality to get pending tickets for managers or employee's tickets
	public List<Ticket> getAllTickets() {
		return tickets;
	}

	public void addTicket(Ticket ticket) {
		tickets.add(ticket);
	}

	// TODO - add function to change status of ticket (pending -> approved/denied)
}
