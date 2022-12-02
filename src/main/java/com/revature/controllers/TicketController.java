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
		if (session == null) {
			ctx.status(401);
			ctx.result("Session is null");
			return;
		}

		Employee employee = (Employee) session.getAttribute("employee");

		// check if the employee object has an ID, if not send 403 status
		if (employee.getId() == 0) {
			ctx.status(403);
			ctx.result("Employee has no ID");
			return;
		}

		Ticket ticket = ctx.bodyAsClass(Ticket.class);

		// check that ticket is not null, if so send 400 status
		if (ticket == null) {
			ctx.status(400);
			ctx.result("Ticket is null");
			return;
		}

		// check that amount and description are included, if not send 400 status
		if (ticket.getAmount() == 0 || ticket.getDescription() == null) {
			ctx.status(400);
			ctx.result("Make sure to include an amount and a description");
			return;
		}

		// if there is an error adding the ticket, send 400 status
		if (!ticketService.addTicket(ticket, employee.getId())) {
			ctx.status(400);
			ctx.result("There was an error adding this ticket");
			return;
		}

		// otherwise all checks passed and the ticket was added, send 201 status
		ctx.status(201);
		ctx.result("Ticket was added successfully");
	};

	Handler getEmployeeTickets = ctx -> {
		// get the current session object without creating one if it doesn't already
		// exist
		HttpSession session = ctx.req().getSession(false);

		// check if session is null, if so send 401 status
		if (session == null) {
			ctx.status(401);
			ctx.result("Session is null");
			return;
		}

		Employee employee = (Employee) session.getAttribute("employee");

		// check if the employee object has an ID, if not send 403 status
		if (employee.getId() == 0) {
			ctx.status(403);
			ctx.result("Employee has no ID");
			return;
		}

		// get employee tickets with employee's ID, return them as json, with status 200
		List<Ticket> tickets = ticketService.getEmployeeTickets(employee.getId());
		ctx.json(tickets);
		ctx.status(200);
	};

	// ! IMPORTANT
	// TODO - add method to get tickets with query: approved/denied/pending

	Handler getPendingTickets = ctx -> {
		// get the current session object without creating one if it doesn't already
		// exist
		HttpSession session = ctx.req().getSession(false);

		// check if session is null, if so send 401 status
		if (session == null) {
			ctx.status(401);
			ctx.result("Session is null");
			return;
		}

		Employee employee = (Employee) session.getAttribute("employee");

		// if the employee is not a manager, send 403 status
		if (!employee.isManager()) {
			ctx.status(403);
			ctx.result("Employee is not a manager");
			return;
		}

		// if they are a manager get the list of all pending tickets
		List<Ticket> tickets = ticketService.getPendingTickets();
		// create a new ArrayList that will hold all of the filtered tickets
		List<Ticket> filteredTickets = new ArrayList<>();
		// filter the tickets list, add only tickets that don't belong to the current
		// manager
		for (Ticket ticket : tickets) {
			if (ticket.getEmployeeID() != employee.getId())
				filteredTickets.add(ticket);
		}
		// send the filtered tickets as json, send 200 status
		ctx.json(filteredTickets);
		ctx.status(200);
	};

	Handler updateTicket = ctx -> {
		// get the current session object without creating one if it doesn't already
		// exist
		HttpSession session = ctx.req().getSession(false);

		// check if session is null, if so send 401 status
		if (session == null) {
			ctx.status(401);
			ctx.result("Session is null");
			return;
		}

		Employee employee = (Employee) session.getAttribute("employee");

		// if the employee is not a manager, send 403 status
		if (!employee.isManager()) {
			ctx.status(403);
			ctx.result("Employee is not a manager");
			return;
		}

		// check if the employee object has an ID, if not send 403 status
		if (employee.getId() == 0) {
			ctx.status(403);
			ctx.result("Employee has no ID");
			return;
		}

		int id = employee.getId();

		Ticket ticket = ctx.bodyAsClass(Ticket.class);

		// check that ticket is not null, if so send 400 status
		if (ticket == null) {
			ctx.status(400);
			ctx.result("Ticket is null");
			return;
		}

		// get the ticket that is intended to be updated
		// this way we can check if it exists
		// and that it is not already approved/denied
		Ticket ticketToUpdate = ticketService.getTicketByID(ticket.getId());

		// make sure that a ticket exists to update, if not send 403 status
		if (ticketToUpdate == null) {
			ctx.status(403);
			ctx.result("There is no ticket with this ID in the database");
			return;
		}
		// check that the ticket they are updating is not already approved/denied
		// if it is approved/denied send 403 status
		if (ticketToUpdate.getStatus().matches("approved") || ticketToUpdate.getStatus().matches("denied")) {
			ctx.status(403);
			ctx.result("The ticket you are trying to change is already approved|denied");
			return;
		}

		// check if the manager is trying to edit their own ticket, if so send 403
		// status
		if (ticket.getEmployeeID() == id) {
			ctx.status(403);
			ctx.result("You are trying to edit your own ticket");
			return;
		}

		// check that the status of the ticket is either approved/denied
		if (ticket.getStatus().matches("approved") || ticket.getStatus().matches("denied")) {

			// if there is an error updating the ticket, send 400 status
			if (!ticketService.updateTicket(ticket, id)) {
				ctx.status(400);
				ctx.result("There was an error updating this ticket");
				return;
			}

			// otherwise all checks passed and the ticket was updated, send 201 status
			ctx.status(201);
			ctx.result("Ticket was updated successfully");

		} else {
			// if ticket status is not approved/denied, send 400 status
			ctx.status(400);
			ctx.result("Ticket can only be approved|denied");
			return;
		}
	};

	@Override
	public void addRoutes(Javalin app) {
		app.get("/ticket", getEmployeeTickets);
		app.get("/ticket/pending", getPendingTickets);
		app.post("/ticket", addTicket);
		app.patch("/ticket", updateTicket);
	}
}
