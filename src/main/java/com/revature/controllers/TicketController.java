package com.revature.controllers;

import java.util.List;

import com.revature.models.Ticket;
import com.revature.services.TicketService;

import io.javalin.Javalin;
import io.javalin.http.Handler;

public class TicketController implements Controller {
	private TicketService ticketService = new TicketService();

	Handler addTicket = ctx -> {
		String idString = ctx.pathParam("id");
		Ticket ticket = ctx.bodyAsClass(Ticket.class);

		int id = 0;

		try {
			id = Integer.parseInt(idString);
		} catch (NumberFormatException e) {
			ctx.status(422);
			return;
		}

		if (ticketService.addTicket(ticket, id)) {
			ctx.status(201);
		} else {
			ctx.status(400);
		}
	};

	Handler getEmployeeTickets = ctx -> {
		String idString = ctx.pathParam("id");

		int id = 0;

		try {
			id = Integer.parseInt(idString);
		} catch (NumberFormatException e) {
			ctx.status(422);
			return;
		}

		List<Ticket> tickets = ticketService.getEmployeeTickets(id);

		ctx.json(tickets);
		ctx.status(200);
	};

	Handler getPendingTickets = ctx -> {
		List<Ticket> tickets = ticketService.getPendingTickets();

		ctx.json(tickets);
		ctx.status(200);
	};

	Handler updateTicket = ctx -> {
		String idString = ctx.pathParam("id");
		Ticket ticket = ctx.bodyAsClass(Ticket.class);

		int id = 0;

		try {
			id = Integer.parseInt(idString);
		} catch (NumberFormatException e) {
			ctx.status(422);
			return;
		}

		if (ticketService.updateTicket(ticket, id)) {
			ctx.status(201);
		} else {
			ctx.status(400);
		}
	};

	// TODO - protect ticket/pending route
	@Override
	public void addRoutes(Javalin app) {
		// app.get("/ticket", getTicketsHandler);
		// app.post("/ticket", addTicketHandler);
		app.get("/ticket/{id}", getEmployeeTickets);
		app.get("/ticket/pending", getPendingTickets);
		app.post("/ticket", addTicket);
		app.patch("/ticket", updateTicket);
	}
}
