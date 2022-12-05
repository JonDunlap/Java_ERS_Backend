-- ========= Drop statements

DROP TABLE IF EXISTS tickets;
DROP TABLE IF EXISTS employees;

-- ========= Employees

CREATE TABLE employees (
	employee_id SERIAL PRIMARY KEY,
	email varchar(100) UNIQUE NOT NULL,
	PASSWORD varchar(50) NOT NULL,
	is_manager boolean NOT NULL default(false)
);

-- Populate employees table

INSERT INTO employees (email, PASSWORD, is_manager) VALUES
	('test@email.com', 'abc123', false),
	('test2@email.com', 'abc123', false),
	('testmanager@email.com', 'abc123', true);

-- ========= Tickets

CREATE TABLE tickets (
	ticket_id SERIAL PRIMARY KEY,
	amount numeric(6, 2),
	description varchar(150),
	status varchar(8) NOT NULL default('pending') CHECK(status='approved' OR status='denied' OR status='pending'),
	employee_id integer NOT NULL REFERENCES employees(employee_id),
	resolving_manager_id integer REFERENCES employees(employee_id)
);

-- Populate tickets table

INSERT INTO tickets (amount, description, employee_id) VALUES
	(100.50, 'Test description for ticket 1', 1),
	(200.00, 'Test description for managers ticket', 3);

INSERT INTO tickets (amount, description, status, employee_id, resolving_manager_id) VALUES
	(2500.00, 'Test description for approved ticket', 'approved', 1, 3),
	(1234.56, 'Test description for denied ticket', 'denied', 2, 3);

-- Test SQL statements
	
/*
SELECT * FROM employees WHERE email='testmanager@email.com' AND PASSWORD='abc123';

SELECT  * FROM tickets WHERE employee_id=1;

SELECT * FROM tickets WHERE status='pending';

UPDATE tickets SET status='approved', resolving_manager_id=3 WHERE ticket_id=1 AND amount=100.50 AND description='Test description for ticket 1' AND employee_id=1;

SELECT  * FROM tickets WHERE ticket_id=4;
**/


-- test constraint

/*
INSERT INTO tickets (amount, description, status, employee_id) VALUES
	(123, 'testing', 'testing', 1);
		
DELETE FROM tickets WHERE ticket_id = 6;
*/