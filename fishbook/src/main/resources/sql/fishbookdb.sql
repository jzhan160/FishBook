-- --------------------------------------------------
-- FishbookDB    Version: 1.0              --
-- Thu Oct 18 16:17:57 2018                        --
-- Author: Biao A                                  --
-- MySQL Workbench Forward Engineering             --
-- --------------------------------------------------


SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';



-- -----------------------------------------------------
-- Schema FishbookDB
-- -----------------------------------------------------
DROP schema IF EXISTS `FishbookDB`;
CREATE SCHEMA IF NOT EXISTS `FishbookDB` DEFAULT CHARACTER SET utf8 ;
USE `FishbookDB` ;

-- -----------------------------------------------------
-- Table `FishbookDB`.`User`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `FishbookDB`.`User` (
  `UserId` INT NOT NULL AUTO_INCREMENT,
  `UserName` VARCHAR(45) NULL,
  `Password` VARCHAR(45) NOT NULL,
  `Email` VARCHAR(45) NOT NULL,
  `Gender` VARCHAR(6) NULL,
  `CreateTime` DATETIME NOT NULL DEFAULT NOW(),
  `ImagePath`VARCHAR(45) NULL,
  `IsAdmin` INT NOT NULL DEFAULT 0,
  PRIMARY KEY (`UserId`, `Email`))
ENGINE = InnoDB;

-- Insert Test Data
INSERT INTO `user` (`UserId`,`UserName`,`Password`,`Email`,`Gender`,`ImagePath`) VALUES
(1,'Biao','123456','Biao@syr.edu','Male','/images'),
(2,'Zhang','123456','Zhang@syr.edu','Male','/images'),
(3,'Doggy','123456','Doggy@syr.edu','Male','/images'),
(4,'Catty','123456','Catty@syr.edu','Female','/images');

-- -----------------------------------------------------
-- Table `FishbookDB`.`Post`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `FishbookDB`.`Post` (
  `PostId` INT NOT NULL AUTO_INCREMENT,
  `Content` VARCHAR(150) NOT NULL,
  `CreateTime` DATETIME NOT NULL DEFAULT NOW(),
  `ImagePath` VARCHAR(45) NULL,
  `AuthorId` INT NOT NULL,
  `LocationLatitude` DOUBLE NULL,
  `LocationLongitude` DOUBLE NULL,
  `LikeCount` INT NOT NULL DEFAULT 0,
  
  PRIMARY KEY (`PostId`),
  INDEX `AuthorId_idx` (`AuthorId` ASC),
  
  CONSTRAINT  FOREIGN KEY (`AuthorId`)
    REFERENCES `FishbookDB`.`User` (`UserId`)
    ON DELETE cascade
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

-- Insert Test Data
INSERT INTO `Post` (`PostId`,`Content`,`ImagePath`,`AuthorId`,`LocationLatitude`,`LocationLongitude`) VALUES
(1,'Good Fish! 1 said','/images',1, 100, 100),
(2,'Good Fish! 2 said','/images',2, 100, 100),
(3,'Good Fish! 3 said','/images',3, 100, 100),
(4,'Good Fish! 4 said','/images',4, 100, 100);


-- -----------------------------------------------------
-- Table `FishbookDB`.`Comment`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `FishbookDB`.`Comment` (
  `CommentId` INT NOT NULL AUTO_INCREMENT,
  `Content` VARCHAR(150) NOT NULL,
  `CreateTime` DATETIME NOT NULL DEFAULT NOW(),
  `AuthorId` INT NOT NULL,
  `PostId` INT NOT NULL,
  
  
  PRIMARY KEY (`CommentId`),
  INDEX `AuthorId_idx` (`AuthorId` ASC),
  INDEX `PostId_idx` (`PostId` ASC),
  
  CONSTRAINT FOREIGN KEY (`AuthorId`)
    REFERENCES `FishbookDB`.`User` (`UserId`)
    ON DELETE cascade,
    
  CONSTRAINT FOREIGN KEY (`PostId`)
    REFERENCES `FishbookDB`.`Post` (`PostId`)
    ON DELETE cascade
    )
ENGINE = InnoDB;

-- Insert Test Data
INSERT INTO `Comment` (`CommentId`,`Content`,`AuthorId`,`PostId`) VALUES
(1,'comment from 1', 1, 2);


-- -----------------------------------------------------
-- Table `FishbookDB`.`UserRelationship`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `FishbookDB`.`UserRelationship` (
  `RelationshipId` INT NOT NULL AUTO_INCREMENT,
  `FollowerId` INT NOT NULL,
  `FolloweeId` INT NOT NULL,
  PRIMARY KEY (`RelationshipId`),
  INDEX `FollowerId_idx` (`FollowerId` ASC),
  INDEX `FolloweeId_idx` (`FolloweeId` ASC),
  
  CONSTRAINT `FollowerId`
    FOREIGN KEY (`FollowerId`)
    REFERENCES `FishbookDB`.`User` (`UserId`)
    ON DELETE cascade
    ON UPDATE NO ACTION,
  
  CONSTRAINT `FolloweeId`
    FOREIGN KEY (`FolloweeId`)
    REFERENCES `FishbookDB`.`User` (`UserId`)
    ON DELETE cascade
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

-- Insert Test Data
INSERT INTO `UserRelationship` (`RelationshipId`,`FollowerId`,`FolloweeId`) VALUES
(1, 1, 2),
(2, 2, 1),
(3, 1, 4);
-- -----------------------------------------------------
-- Table `FishbookDB`.`Notification`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `FishbookDB`.`Notification` (
  `NotificationId` INT NOT NULL,
  `ReceiverId` INT NOT NULL,
  `TriggerId` INT NOT NULL,
  `Event` VARCHAR(150),
  `Viewed` TINYINT NOT NULL DEFAULT 0,
  
  PRIMARY KEY (`NotificationId`),
  INDEX `ReceiverId_idx` (`ReceiverId` ASC),
  
  CONSTRAINT `ReceiverId`
    FOREIGN KEY (`ReceiverId`)
    REFERENCES `FishbookDB`.`User` (`UserId`)
    ON DELETE cascade
    ON UPDATE NO ACTION,
    
  CONSTRAINT `TriggerId`
    FOREIGN KEY (`TriggerId`)
    REFERENCES `FishbookDB`.`User` (`UserId`)
	ON DELETE cascade
    ON UPDATE NO ACTION
    
    )
ENGINE = InnoDB;

-- Insert Test Data
INSERT INTO `Notification` (`NotificationId`,`ReceiverId`,`TriggerId`) VALUES
(1, 2, 1);


USE `FishbookDB`;

-- DELIMITER $$
-- USE `FishbookDB`$$
-- CREATE DEFINER = CURRENT_USER TRIGGER `FishbookDB`.`Post_BEFORE_DELETE` AFTER DELETE ON `Post` FOR EACH ROW
-- BEGIN
-- 	declare id INT; 
-- 	Set id  = (Select PostId from I);
-- 	DELETE FROM Comment WHERE Comment.PostId = @id;
-- END$$


DELIMITER ;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
