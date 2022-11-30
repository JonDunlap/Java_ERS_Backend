package com.revature.controllers;

import java.util.List;
import java.util.ArrayList;

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
		// get the current session object without creating one if it doesn't already
		// exist
		HttpSession session = ctx.req().getSession(false);

		// check if session is null, if so send 401 status
		if (session == null)
			ctx.status(401);

		Employee employee = (Employee) session.getAttribute("employee");

		// check that the employee is a manager, if not send 403 status
		if (!employee.isManager())
			ctx.status(403);

		// if they are a manager get the list of all pending tickets
		List<Ticket> tickets = ticketService.getPendingTickets();
		
		// filter the tickets list and add only tickets that don't belong to the current manager
		// send the filtered tickets as json, send 200 status
		List<Ticket> filteredTickets = new ArrayList<>();
		for (Ticket ticket : tickets){
			if(ticket.getEmployeeID() != employee.getId()) 
				filteredTickets.add(ticket);
		}
		ctx.json(filteredTickets);
		ctx.status(200);
	};

	Handler updateTicket = ctx -> {
		// get the current session object without creating one if it doesn't already
		// exist
		HttpSession session = ctx.req().getSession(false);

		// check if session is null, if so send 401 status
		if (session == null)
			ctx.status(401);

		Employee employee = (Employee) session.getAttribute("employee");

		// check that the employee is a manager, if not send 403 status
		if (!employee.isManager())
			ctx.status(403);
		
		int id = 0;

		// check if the employee object has an ID, if not send 403 status
		if (employee.getId() == 0)
			ctx.status(403);

		id = employee.getId();

		Ticket ticket = ctx.bodyAsClass(Ticket.class);

		// check if the manager is trying to edit their own ticket, if so send 403 status
		if(ticket.getEmployeeID() == id)
			ctx.status(403);
		
		// check that the status of the ticket is either approved/denied, if not send 400 status
		if(!ticket.getStatus().matches("approved") || !ticket.getStatus().matches("denied"))
			ctx.status(400);
			
		// if there is an error adding the ticket, send 400 status
		if (ticketService.updateTicket(ticket, id))
			ctx.status(400);
		
		// otherwise all checks passed and the ticket was added, send 201 status
		ctx.status(201);
	};

	@Override
	public void addRoutes(Javalin app) {
		app.get("/ticket", getEmployeeTickets);
		app.get("/ticket/pending", getPendingTickets);
		app.post("/ticket", addTicket);
		app.patch("/ticket", updateTicket);
	}
}
