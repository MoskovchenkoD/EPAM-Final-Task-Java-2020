-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema epam_task
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema epam_task
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `epam_task` DEFAULT CHARACTER SET utf8 ;
USE `epam_task` ;

-- -----------------------------------------------------
-- Table `epam_task`.`status`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `epam_task`.`status` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `statusName` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `idCourseStatus_UNIQUE` (`id` ASC) VISIBLE)
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `epam_task`.`topic`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `epam_task`.`topic` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `topicName` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `idTopic_UNIQUE` (`id` ASC) VISIBLE,
  UNIQUE INDEX `name_UNIQUE` (`topicName` ASC) VISIBLE)
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `epam_task`.`role`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `epam_task`.`role` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `roleName` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `name_UNIQUE` (`roleName` ASC) VISIBLE,
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE)
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `epam_task`.`state`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `epam_task`.`state` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `stateName` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `idState_UNIQUE` (`id` ASC) VISIBLE)
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `epam_task`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `epam_task`.`user` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `firstName` VARCHAR(45) NOT NULL,
  `lastName` VARCHAR(45) NOT NULL,
  `patronymic` VARCHAR(45) NULL DEFAULT NULL,
  `login` VARCHAR(45) NOT NULL,
  `email` VARCHAR(60) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `role_id` INT NOT NULL DEFAULT '1',
  `state_id` INT NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`),
  INDEX `fk_User_Role1_idx` (`role_id` ASC) VISIBLE,
  INDEX `fk_user_state1_idx` (`state_id` ASC) VISIBLE,
  CONSTRAINT `fk_User_Role1`
    FOREIGN KEY (`role_id`)
    REFERENCES `epam_task`.`role` (`id`),
  CONSTRAINT `fk_user_state1`
    FOREIGN KEY (`state_id`)
    REFERENCES `epam_task`.`state` (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `epam_task`.`course`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `epam_task`.`course` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `headline` VARCHAR(60) NOT NULL,
  `description` VARCHAR(500) NOT NULL,
  `length` INT NOT NULL,
  `topic_id` INT UNSIGNED NOT NULL,
  `user_id` INT UNSIGNED NOT NULL,
  `status_id` INT NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`),
  UNIQUE INDEX `idCourse_UNIQUE` (`id` ASC) VISIBLE,
  INDEX `fk_Course_Topic_idx` (`topic_id` ASC) VISIBLE,
  INDEX `fk_Course_User1_idx` (`user_id` ASC) VISIBLE,
  INDEX `fk_Course_CourseStatus1_idx` (`status_id` ASC) VISIBLE,
  CONSTRAINT `fk_Course_CourseStatus1`
    FOREIGN KEY (`status_id`)
    REFERENCES `epam_task`.`status` (`id`),
  CONSTRAINT `fk_Course_Topic`
    FOREIGN KEY (`topic_id`)
    REFERENCES `epam_task`.`topic` (`id`),
  CONSTRAINT `fk_Course_User1`
    FOREIGN KEY (`user_id`)
    REFERENCES `epam_task`.`user` (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `epam_task`.`journal`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `epam_task`.`journal` (
  `course_id` INT UNSIGNED NOT NULL,
  `user_id` INT UNSIGNED NOT NULL,
  `userScore` INT NULL DEFAULT NULL,
  `dateJoin` DATE NOT NULL,
  PRIMARY KEY (`course_id`, `user_id`),
  INDEX `fk_course_has_user_user1_idx` (`user_id` ASC) VISIBLE,
  INDEX `fk_course_has_user_course1_idx` (`course_id` ASC) VISIBLE,
  CONSTRAINT `fk_course_has_user_course1`
    FOREIGN KEY (`course_id`)
    REFERENCES `epam_task`.`course` (`id`),
  CONSTRAINT `fk_course_has_user_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `epam_task`.`user` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- ---------------------- INSERTS --------------------

INSERT into role (roleName) values ('admin'),('student'),('lecturer');
INSERT into topic (topicName) values ('Math'), ('English');  
INSERT into state (stateName) values ('free'), ('blocked');
insert into status (statusName) values ('new'), ('started'), ('finished');

INSERT into user (firstName, lastName, login, email, password, role_id, state_id) VALUES 
('Sf1', 'Sl1', 's1', 'Se1', 's', 2, 1), ('Pf1', 'Pl1', 'p1', 'Pe1', 'p', 3, 1),
('Sf2', 'Sl2', 's2', 'Se2', 's', 2, 1), ('Pf2', 'Pl1', 'p2', 'Pe2', 'p', 3, 1),
('Sf3', 'Sl3', 's3', 'Se3', 's', 2, 1), ('Pf3', 'Pl1', 'p3', 'Pe3', 'p', 3, 1),
('Sf4', 'Sl4', 's4', 'Se4', 's', 2, 1), ('Pf4', 'Pl1', 'p4', 'Pe4', 'p', 3, 1),
('Sf5', 'Sl5', 's5', 'Se5', 's', 2, 1), ('Ad1', 'Ad1', 'a', 'a', 'a', 1, 1);

insert into course (headline, description, length, topic_id, user_id) values 
('Tn1', 'Td1', 2, 2, 2), ('Tn2', 'Td1', 2, 2, 4), ('Tn3', 'Td1', 2, 2, 6), 
('Tn4', 'Td1', 2, 1, 8), ('Tn5', 'Td1', 2, 1, 2), ('Tn6', 'Td1', 2, 1, 4), 
('Tn4', 'Td1', 2, 2, 6), ('Tn5', 'Td1', 2, 1, 8), ('Tn6', 'Td1', 2, 1, 2);