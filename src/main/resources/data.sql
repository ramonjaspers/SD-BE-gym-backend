-- Default data initialisation
-- Memberships
INSERT INTO `subscription` (`id`, `price`, `name`) VALUES (0, 0, 'none');
INSERT INTO `subscription` (`id`, `price`, `name`) VALUES (1, 25, 'bronze');
INSERT INTO `subscription` (`id`, `price`, `name`) VALUES (2, 50, 'silver');
INSERT INTO `subscription` (`id`, `price`, `name`) VALUES (3, 75, 'gold');

-- Persons
INSERT INTO `person` (`username`, `address`, `api_key`, `bank_number`, `credit`, `date_of_birth`, `email`, `name`, `password`, `picture`, `sex`, `employee_id`, `subscription_id`)
VALUES('employee', 'Teststraat 13', NULL, '1234567890', 99999, '1990-12-12 00:00:00.000000', 'employee@gmail.com', 'Employee1', 'test', NULL, 0, NULL, 3);
INSERT INTO `person` (`username`, `address`, `api_key`, `bank_number`, `credit`, `date_of_birth`, `email`, `name`, `password`, `picture`, `sex`, `employee_id`, `subscription_id`)
VALUES('admin', 'Teststraat 12', NULL, '1234567891', 99999, '1996-12-12 00:00:00.000000', 'admin@gmail.com', 'Admin1', 'admin', NULL, 0, NULL, 3);
INSERT INTO `person` (`username`, `address`, `api_key`, `bank_number`, `credit`, `date_of_birth`, `email`, `name`, `password`, `picture`, `sex`, `employee_id`, `subscription_id`)
VALUES('person', 'Teststraat 11', NULL, '1234567892', 400, '1996-12-12 00:00:00.000000', 'admin@gmail.com', 'Person1', 'person', NULL, 0, NULL, 1);

-- Roles
INSERT INTO authority (username, authority) VALUES ('employee', 'ROLE_EMPLOYEE');
INSERT INTO authority (username, authority) VALUES ('user', 'ROLE_USER');
INSERT INTO authority (username, authority) VALUES ('admin', 'ROLE_ADMIN');
