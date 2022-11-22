package com.revature.controllers;

import java.util.List;

import com.revature.models.Ticket;
import com.revature.services.TicketService;

import io.javalin.Javalin;
import io.javalin.http.Handler;

public class TicketController implements Controller {
	private TicketService ticketService = new TicketService();

	private Handler getTicketsHandler = ctx -> {
		List<Ticket> list = ticketService.getTickets();

		ctx.json(list);
		ctx.status(200);
	};

	private Handler addTicketHandler = ctx -> {
		Ticket ticket = ctx.bodyAsClass(Ticket.class);
		ticketService.addTicket(ticket);
		ctx.status(201);
	};

	// TODO - ticket/pending route to see pending tickets as a manager
	// TODO - patch /ticket to update tickets (pending/approved/denied)
	// TODO - protect ticket/pending route
	@Override
	public void addRoutes(Javalin app) {
		app.get("/ticket", getTicketsHandler);
		app.post("/ticket", addTicketHandler);
	}
}
