DROP TABLE IF EXISTS `accounts`;

CREATE TABLE `accounts` (
  `id` int NOT NULL,
  `number` int NOT NULL,
  `balance` double NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

DROP TABLE IF EXISTS `contact_details`;

CREATE TABLE `contact_details` (
  `id` int NOT NULL,
  `city` varchar(45) CHARACTER SET utf8mb4 NOT NULL,
  `street` varchar(45) CHARACTER SET utf8mb4 NOT NULL,
  `home` varchar(45) CHARACTER SET utf8mb4 NOT NULL,
  `apartment` varchar(45) CHARACTER SET utf8mb4 NOT NULL,
  `email` varchar(45) CHARACTER SET utf8mb4 NOT NULL,
  `phone` varchar(45) CHARACTER SET utf8mb4 NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

DROP TABLE IF EXISTS `roles`;

CREATE TABLE `roles` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) CHARACTER SET utf8mb4 NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;

DROP TABLE IF EXISTS `services`;

CREATE TABLE `services` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `description` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1;

DROP TABLE IF EXISTS `tariffs`;

CREATE TABLE `tariffs` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `description` varchar(255) NOT NULL,
  `price` double NOT NULL,
  `services_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_tariffs_services1_idx` (`services_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;

DROP TABLE IF EXISTS `users`;

CREATE TABLE `users` (
  `id` int NOT NULL AUTO_INCREMENT,
  `login` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `first_name` varchar(45) NOT NULL,
  `last_name` varchar(45) NOT NULL,
  `surname` varchar(45) NOT NULL,
  `blocked` tinyint NOT NULL,
  `roles_id` int NOT NULL,
  `contact_details_id` int NOT NULL,
  `accounts_id` int NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `login_UNIQUE` (`login`),
  KEY `fk_users_roles_idx` (`roles_id`),
  KEY `fk_users_contact_details1_idx` (`contact_details_id`),
  KEY `fk_users_accounts1_idx` (`accounts_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;

DROP TABLE IF EXISTS `users_has_tariffs`;

CREATE TABLE `users_has_tariffs` (
  `users_id` int NOT NULL,
  `tariffs_id` int NOT NULL,
  PRIMARY KEY (`users_id`,`tariffs_id`),
  KEY `fk_users_has_tariffs_tariffs1_idx` (`tariffs_id`),
  KEY `fk_users_has_tariffs_users1_idx` (`users_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

SET GLOBAL information_schema_stats_expiry=0;