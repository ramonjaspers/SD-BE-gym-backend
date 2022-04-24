-- Default data initialisation
-- Memberships
INSERT INTO `subscription` (`id`, `price`, `name`) VALUES (0, 0, 'none');
INSERT INTO `subscription` (`id`, `price`, `name`) VALUES (1, 25, 'bronze');
INSERT INTO `subscription` (`id`, `price`, `name`) VALUES (2, 50, 'silver');
INSERT INTO `subscription` (`id`, `price`, `name`) VALUES (3, 75, 'gold');

-- Persons
INSERT INTO `person` (`username`, `address`, `api_key`, `bank_number`, `credit`, `date_of_birth`, `email`, `name`, `password`, `picture`, `sex`, `subscription_id`, `enabled`)
VALUES('employee', 'Teststraat 13', NULL, '1234567890', 99999, '1990-12-12 00:00:00.000000', 'employee@gmail.com', 'Employee1', '$2a$10$CHNzTTTrJ8FTumXkILOgE.YvoUBP7oRQ8S/wJUP/vWqS545BsZIRK', NULL, 0, 3, true);
INSERT INTO `person` (`username`, `address`, `api_key`, `bank_number`, `credit`, `date_of_birth`, `email`, `name`, `password`, `picture`, `sex`, `subscription_id`, `enabled`)
VALUES('admin', NULL, NULL, NULL, NULL, NULL, 'admin@admin.nl', 'admin', '$2a$10$zMTUEW1KySqf21THPQwWLO5cx0l27iR/twLNfoSjjTZ7srps7kijK', NULL, NULL, NULL, true);
INSERT INTO `person` (`username`, `address`, `api_key`, `bank_number`, `credit`, `date_of_birth`, `email`, `name`, `password`, `picture`, `sex`, `subscription_id`, `enabled`)
VALUES('person', 'Teststraat 11', NULL, '1234567892', 400, '1996-12-12 00:00:00.000000', 'admin@gmail.com', 'Person1', '$2a$10$ib/KqHIycKOZc4Ac2147Qe118IceHDP.lV4M7D6UEtHXs2CliwcuW', NULL, 0, 1, true);

-- Employee
INSERT INTO `employee` (`id`, `date_of_employment`, `end_date_employment`, `func`, `salary`, `work_week_duration`, `username`)
VALUES
    (0, '2022-03-24 21:42:12.000000', '2055-04-24 21:42:12.000000', 'schoonmaker', 5000, 6, 'employee');


-- Roles
INSERT INTO authority (username, authority) VALUES ('employee', 'ROLE_EMPLOYEE');
INSERT INTO authority (username, authority) VALUES ('user', 'ROLE_USER');
INSERT INTO authority (username, authority) VALUES ('admin', 'ROLE_ADMIN');
