package com.revature.controllers;

import java.util.List;

import com.revature.models.Employee;
import com.revature.models.Ticket;
import com.revature.services.TicketService;

import io.javalin.Javalin;
import io.javalin.http.Handler;
import jakarta.servlet.http.HttpSession;

public class TicketController implements Controller {
	private TicketService ticketService = new TicketService();

	Handler addTicket = ctx -> {
		// get the current session object without creating one if it doesn't already
		// exist
		HttpSession session = ctx.req().getSession(false);

		// check if session is null, if so send 401 status
		if (session == null)
			ctx.status(401);

		Employee employee = (Employee) session.getAttribute("employee");

		int id = 0;

		// check if the employee object has an ID, if not send 403 status
		if (employee.getId() == 0)
			ctx.status(403);

		id = employee.getId();

		Ticket ticket = ctx.bodyAsClass(Ticket.class);

		// if there is an error adding the ticket, send 400 status
		if (!ticketService.addTicket(ticket, id))
			ctx.status(400);

		// otherwise all checks passed and the ticket was added, send 201 status
		ctx.status(201);
	};

	Handler getEmployeeTickets = ctx -> {
		// get the current session object without creating one if it doesn't already
		// exist
		HttpSession session = ctx.req().getSession(false);

		// check if session is null, if so send 401 status
		if (session == null)
			ctx.status(401);

		Employee employee = (Employee) session.getAttribute("employee");

		int id = 0;

		// check if the employee object has an ID, if not send 403 status
		if (employee.getId() == 0)
			ctx.status(403);

		id = employee.getId();

		// get employee tickets with employee's ID, return them as json, with status 200
		List<Ticket> tickets = ticketService.getEmployeeTickets(id);
		ctx.json(tickets);
		ctx.status(200);
	};

	Handler getPendingTickets = ctx -> {
		List<Ticket> tickets = ticketService.getPendingTickets();

		ctx.json(tickets);
		ctx.status(200);
	};

	// TODO - check that the status is either approved/denied
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

	@Override
	public void addRoutes(Javalin app) {
		// ! DEBUG - use logged in user id to get tickets
		app.get("/ticket", getEmployeeTickets);
		// ! DEBUG, change to use SessionStorage to get managerID
		// TODO - prevent managers from seeing/editing their own pending tickets
		// TODO - protect ticket/pending route
		app.get("/ticket/pending/{id}", getPendingTickets);
		app.post("/ticket", addTicket);
		// ! DEBUG, use SessionStorage to get managerID
		// TODO - prevent managers from seeing/editing their own pending tickets
		app.patch("/ticket/{id}", updateTicket);
	}
}
