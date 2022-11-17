# Project 1 Repository

> Here is the repository for committing your Project 1 code. Please see the announcements channel in Teams for the project requirements.

> The provided skeleton is for a Maven project. Please import as such to your IDE. Your source code should go in packages under src/main/java.

## Models

**Employees/Managers**

- username/email
- password
- role (**employee** (default) | manager)

**Tickets**

- amount
- description
- status (**pending** (default) | approved | denied)

## API Routes (**WIP**)

| Method   | Path         | Description                                                        |
| -------- | ------------ | ------------------------------------------------------------------ |
|          | **Auth**     |                                                                    |
| **POST** | /auth/signup | Create a new user using email, pwd, and role (default to employee) |
| **POST** | /auth/login  | Login a user using email and password                              |
|          | **Tickets**  |                                                                    |
| **GET**  | /tickets     |
