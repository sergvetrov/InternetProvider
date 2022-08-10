INSERT INTO `internet_provider`.`roles` (`name`) VALUES ('admim');
INSERT INTO `internet_provider`.`roles` (`name`) VALUES ('client');

INSERT INTO `internet_provider`.`services` (`name`, `description`) VALUES ('Интернет', 'Безлимитные тарифные планы «Интернет»');
INSERT INTO `internet_provider`.`services` (`name`, `description`) VALUES ('Цифровое ТВ (IPTV)', 'Просмотр каналов в цифровом формате IPTV ');
INSERT INTO `internet_provider`.`services` (`name`, `description`) VALUES ('Телефонная связь', 'Городская телефонная связь');

INSERT INTO `internet_provider`.`tariffs` (`name`, `description`, `price`, `services_id`) VALUES ('«Комфорт 50»', '50 до, Мбит/с Безлимитные тарифные планы «Интернет»', '140', '1');
INSERT INTO `internet_provider`.`tariffs` (`name`, `description`, `price`, `services_id`) VALUES ('Расширенный', 'Цифровое ТВ (IPTV)', '20', '2');
INSERT INTO `internet_provider`.`tariffs` (`name`, `description`, `price`, `services_id`) VALUES ('Сотовая связь', 'Городская телефонная связь', '1', '3');

INSERT INTO `internet_provider`.`contact_details` (`id`, `city`, `street`, `home`, `apartment`, `email`, `phone`) VALUES (1,'Kharkiv','Pushkinskaya','23a','12','admin@gmail.com','+380661112899');
INSERT INTO `internet_provider`.`accounts` (`id`, `number`, `balance`) VALUES (1,1,0);
INSERT INTO `internet_provider`.`users` (`login`, `password`, `first_name`, `last_name`, `surname`, `blocked`, `roles_id`, `contact_details_id`, `accounts_id`) VALUES ('admin12345678','admin1',' Ivan ','Ivanovich','Ivanov',0,1,1,1);