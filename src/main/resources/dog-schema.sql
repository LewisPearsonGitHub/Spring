DROP TABLE IF EXISTS `dog` CASCADE;
create table `dog` (
		`id` INTEGER UNIQUE PRIMARY KEY AUTO_INCREMENT,
        `name` VARCHAR(255) NOT NULL,
        `breed` VARCHAR(255),
        `age` INTEGER
);
        