CREATE TABLE business(
	`id` INT(11) AUTO_INCREMENT NOT NULL,
	`name` VARCHAR(255),
	`latitude` DOUBLE,
	`longitude` DOUBLE,
	`description` VARCHAR(255),
	`address` TEXT,
	PRIMARY KEY (`id`)
);
