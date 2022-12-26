- [Expense Reimbursement System - Java Backend](#expense-reimbursement-system---java-backend)
	- [Description:](#description)
	- [Setting up the app](#setting-up-the-app)
	- [Models](#models)
	- [API Routes](#api-routes)
	- [Notes:](#notes)
	- [Issues/Challenges](#issueschallenges)

# Expense Reimbursement System - Java Backend

## Description:

The overall goal of this project was to build a better understanding of the fundamentals of software development. Specifically, the purpose was to build an application that is meant to be an expense reimbursement system for employees allowing them to create tickets for reimbursement that will then either be approved or denied by a manager. Some of the technologies and packages that were used for this project were: [Maven](https://maven.apache.org) for project and dependency management, [Javalin](https://javalin.io) for creating the REST API along with [SLF4J](https://www.slf4j.org) for logging and [Jackson](https://github.com/FasterXML/jackson) for parsing JSON. It also makes use of [Docker](https://www.docker.com) for the containerization of the [PostgreSQL database](https://www.postgresql.org) and uses the JDBC along with the [PostgreSQL JDBC Driver](https://mvnrepository.com/artifact/org.postgresql/postgresql) to create connections between the Java application and the database. Testing has been added using [JUnit](https://junit.org/junit5/) and [Mockito](https://site.mockito.org) for testing and mocking unit tests, although no tests are currently implemented I have included these packages so that it can be added at a later time.

## Setting up the app

- Database SQL commands located in `/Resources/project_1_script.sql`
- Maven project files start in `src/main/java`
- Javalin server is running on `http://localhost:4000`
  - Edit inside of `src/main/java/Main.java`
- JDBC is configured for `jdbc:postgresql://localhost:5433/postgres`
  - Edit DB connection params inside `src/main/java/com/revature/utils/ConnectionUtil.java`

## Models

**Employees/Managers**

- id (primary key | auto-incrementing)
- email (required)
- password (required)
- role (**employee** (default) | manager)

**Tickets**

- id (primary key | auto-incrementing)
- amount (required)
- description (required)
- status (**pending** (default) | approved | denied)
- employee_id (foreign key | added when employee creates ticket)
- resolving_manager_id (foreign key | added when manager resolves ticket)

## API Routes

| Method    | Path            | Description                                                                    |
| --------- | --------------- | ------------------------------------------------------------------------------ |
|           | **Auth**        |                                                                                |
| **POST**  | /register       | Create a new user using email, pwd, and role (**default to employee**)         |
| **POST**  | /login          | Login a user using email and password                                          |
| **GET**   | /logout         | Logout the current user                                                        |
|           | **Tickets**     |                                                                                |
| **GET**   | /ticket         | Get all of the currently logged in user's tickets                              |
| **GET**   | /ticket/?status | Query tickets by status (pending / approved / denied)                          |
| **GET**   | /ticket/pending | Gets all pending tickets (**manager only route**)                              |
| **POST**  | /ticket         | Create a new ticket with an amount, description, and default status of pending |
| **PATCH** | /ticket         | Update the status of a ticket (**manager only route**)                         |

## Notes:

- EmployeeController currently has no implementation as there are no web routes for employee functions currently
  - I am leaving the file in as some of the stretch goals have functionality that would require employee routes and it was useful for testing the EmployeeDAO was working properly
- Testing is not implemented although I have created a file for testing the login service
- I would like to add redirects for some of the routes, e.g. protected routes send the user back to the "home" or after registering either login the employee or redirect to the login page
- I am using `ctx.result("{result_message}")` in several spots just so that there is some kind of user feedback other than just the HTTP status codes. I would probably replace this with either JSON or actual errors back to the user so that they would be more useful in an actual production API, but I felt like, in this instance, it was a good way of giving feedback and it was also helpful during development to see where I was having issues.

## Issues/Challenges

- Some small logic errors were resulting in unexpected/unwanted behavior, one example was an if statement I was trying to use:

  ```
  if (!ticket.getStatus().matches("approved") || !ticket.getStatus().matches("denied")) {
  		ctx.status(400);
  		return;
  }
  ```

  this was causing an issue since I was "flipping" the boolean so I was in fact giving an error when the status was either "approved" or "denied" which was not what I wanted.

- Another issue I ran into was while trying to reformat my code to use less if..else statements, I was misunderstanding the fact that setting `ctx.status()` didn't actually exit the method it just sent a status and kept running the code so when I was trying to use:

  ```
  if(ticket == null)
  	ctx.status(400);
  ```

  my program would keep running and I would end up with a `NullPointerException` on the next line since my code would continue running and then try to access a null value. I was able to fix this issue by explicitly calling `return();` inside of my if statements which allowed me to still clean up my code and if there was an issue with user input I could return a status and prevent the method from continuing to run when there would be an issue.

- If a post request is made with no content in the body it is causing a `MismatchedInputException` inside of Jackson databind, tried using a try..catch inside of the methods that require the `ctx.bodyAsClass()`, but this does not resolve the issue.
