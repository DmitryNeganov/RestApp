CREATE TABLE `my_db`.`role` (
  `role_id` INT NOT NULL,
  `role` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`role_id`));


CREATE TABLE `my_db`.`football_player` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(100) NOT NULL,
  `surname` VARCHAR(100) NOT NULL,
  `role_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
  INDEX `role_id_idx` (`role_id` ASC) VISIBLE,
  CONSTRAINT `role_id`
    FOREIGN KEY (`role_id`)
    REFERENCES `my_db`.`role` (`role_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

INSERT INTO my_db.football_player (name, surname, role_id)
VALUES ("Wayene", "Rooney", 4),
	("Lionel", "Messi", 4);