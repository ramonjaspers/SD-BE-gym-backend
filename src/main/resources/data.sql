INSERT INTO `subscriptions` (`id`, `price`, `name`) VALUES (0, 0, 'none');
INSERT INTO `subscriptions` (`id`, `price`, `name`) VALUES (1, 50, 'bronze');
INSERT INTO `persons` (`username`, `address`, `api_key`, `bank_number`, `credit`, `date_of_birth`, `email`, `name`, `password`, `picture`, `sex`, `time_stamp`, `subscription_id`)
VALUES('employee', 'a', 'a', '1', 200, '2002-12-12 00:00:00.000000', 'employee@gmail.com', 'Henk', '1234567890', NULL, NULL, NULL, 0);


INSERT INTO authorities (username, authority) VALUES ('employee', 'ROLE_EMPLOYEE');
INSERT INTO authorities (username, authority) VALUES ('user', 'ROLE_USER');
INSERT INTO authorities (username, authority) VALUES ('admin', 'ROLE_ADMIN');
