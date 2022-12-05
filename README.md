- [Project 1 Repository](#project-1-repository)
	- [Description:](#description)
	- [Setting up the app](#setting-up-the-app)
	- [Models](#models)
	- [API Routes](#api-routes)
	- [Issues/Challenges](#issueschallenges)

# Project 1 Repository

## Description:

The overall goal of this project was to build a better understanding of the fundamentals of software development. Specifically, the purpose was to build an application that is meant to be an expense reimbursement system for employees allowing them to create tickets for reimbursement that will then either be approved or denied by a manager.

## Setting up the app

- Maven project so all files start in `src/main/java`
- Javalin server is running on `http://localhost:4000`
  - Edit inside of `src/main/java/Main.java`
- JDBC is configured for `jdbc:postgresql://localhost:5433/postgres`
  - If you need to edit connection params they are inside `src/main/java/com/revature/utils/ConnectionUtil.java`

## Models

**Employees/Managers**

- id (default | auto-incrementing)
- username/email (required)
- password (required)
- role (**employee** (default) | manager)

**Tickets**

- id (default | auto-incrementing)
- amount (required)
- description (required)
- status (**pending** (default) | approved | denied)
- employee_id (added when employee creates ticket)
- resolving_manager_id (added when manager resolves ticket)

## API Routes

| Method    | Path            | Description                                                                    |
| --------- | --------------- | ------------------------------------------------------------------------------ |
|           | **Auth**        |                                                                                |
| **POST**  | /register       | Create a new user using email, pwd, and role (default to employee)             |
| **POST**  | /login          | Login a user using email and password                                          |
| **GET**   | /logout         | Logout the current user                                                        |
|           | **Tickets**     |                                                                                |
| **GET**   | /ticket         | Get all of the currently logged in user's tickets                              |
| **GET**   | /ticket/?status | Query tickets by status (pending / approved / denied)                          |
| **GET**   | /ticket/pending | Gets all pending tickets (manager only route)                                  |
| **POST**  | /ticket         | Create a new ticket with an amount, description, and default status of pending |
| **PATCH** | /ticket         | Update the status of a ticket (manager only route)                             |

## Issues/Challenges

- Testing is not yet fully implemented
-
