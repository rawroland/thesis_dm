INSERT INTO `groups` (`id`, `name`) VALUES
(1, 'All Consumers'),
(2, 'All Suppliers');

INSERT INTO `clients_groups` (`id`, `clientId`, `groupId`) VALUES
(1, 2, 1),
(2, 3, 1),
(3, 4, 1),
(4, 5, 1),
(5, 6, 1),
(6, 7, 2),
(7, 8, 2),
(8, 9, 2),
(9, 10, 2),
(10, 11, 2);