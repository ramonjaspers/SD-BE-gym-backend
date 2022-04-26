-- Default data initialisation
-- Persons
INSERT INTO `person` (`username`, `address`, `bank_number`, `credit`, `date_of_birth`, `email`, `name`, `password`, `picture`, `sex`, `enabled`)
VALUES('employee', 'Teststraat 13', '1234567890', 99999, '1990-12-12 00:00:00.000000', 'employee@gmail.com', 'Employee1', '$2a$10$CHNzTTTrJ8FTumXkILOgE.YvoUBP7oRQ8S/wJUP/vWqS545BsZIRK', NULL, 2, true);
INSERT INTO `person` (`username`, `address`, `bank_number`, `credit`, `date_of_birth`, `email`, `name`, `password`, `picture`, `sex`, `enabled`)
VALUES('admin', NULL, NULL, NULL, NULL, 'admin@admin.nl', 'admin', '$2a$10$zMTUEW1KySqf21THPQwWLO5cx0l27iR/twLNfoSjjTZ7srps7kijK', NULL, NULL, true);
INSERT INTO `person` (`username`, `address`, `bank_number`, `credit`, `date_of_birth`, `email`, `name`, `password`, `picture`, `sex`, `enabled`)
VALUES('person', 'Teststraat 11', '1234567892', 400, '1996-12-12 00:00:00.000000', 'admin@gmail.com', 'Person1', '$2a$10$ib/KqHIycKOZc4Ac2147Qe118IceHDP.lV4M7D6UEtHXs2CliwcuW', NULL, 1, true);

-- Employee
INSERT INTO `employee` (`date_of_employment`, `date_till_employment`, `func`, `salary`, `work_week_duration`, `username`)
VALUES('2022-03-24 21:42:12.000000', '2055-04-24 21:42:12.000000', 'schoonmaker', 5000, 6, 'employee');

-- Memberships
INSERT INTO `membership` (`weight`, `price`, `name`) VALUES (0, 0, 'none');
INSERT INTO `membership` (`weight`, `price`, `name`) VALUES (1, 25, 'bronze');
INSERT INTO `membership` (`weight`, `price`, `name`) VALUES (2, 50, 'silver');
INSERT INTO `membership` (`weight`, `price`, `name`) VALUES (3, 75, 'gold');

-- Subscriptions
INSERT INTO `subscription` (`username`, `subscription_end_date`, `subscription_start_date`, `membership`)
VALUES ('admin', '2077-04-25 17:36:12.000000', '2022-04-25 17:39:24.000000', 'gold');
INSERT INTO `subscription` (`username`, `subscription_end_date`, `subscription_start_date`, `membership`)
VALUES ('person', '2023-04-25 17:36:12.000000', '2022-04-25 17:39:24.000000', 'bronze');

-- Roles
INSERT INTO authority (username, authority) VALUES ('employee', 'ROLE_EMPLOYEE');
INSERT INTO authority (username, authority) VALUES ('person', 'ROLE_PERSON');
INSERT INTO authority (username, authority) VALUES ('admin', 'ROLE_ADMIN');

-- Facilities
INSERT INTO `facility` (`id`, `name`, `minimum_membership`)
VALUES
    (1, 'squat-rek', 'bronze'),
    (2, 'squash-baan', 'silver'),
    (3, 'gewichten', 'bronze'),
    (4, 'tennis-baan', 'silver'),
    (5, 'zonnenbank', 'gold'),
    (6, 'sauna', 'gold');

-- Activities
INSERT INTO `activity` (`name`, `facility_id`)
VALUES
    ('lunges', 1),
    ('military-press', 1),
    ('squats', 1),
    ('tenissen', 4),
    ('zonnen', 5);

-- exercise muscles
INSERT INTO `exercise_muscle` (`id`, `muscle`, `activity`)
VALUES
    (1, 9, 'lunges'),
    (2, 7, 'lunges'),
    (3, 3, 'lunges'),
    (4, 1, 'military-press'),
    (5, 5, 'military-press'),
    (6, 9 ,'squats'),
    (7, 7, 'squats'),
    (8, 3, 'squats'),
    (9, 12, 'tenissen');




