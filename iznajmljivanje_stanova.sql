/*
SQLyog Community v13.1.6 (64 bit)
MySQL - 10.4.18-MariaDB : Database - database
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`iznajmljivanje_stanova` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci */;

USE `iznajmljivanje_stanova`;



DROP TABLE IF EXISTS `Zaposleni`;

CREATE TABLE `Zaposleni` (
  `ZaposleniID` BIGINT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `Ime` VARCHAR(50) NOT NULL,
  `Prezime` VARCHAR(50) NOT NULL,
  `KorisnickoIme` VARCHAR(30) NOT NULL,
  `Lozinka` VARCHAR(30) NOT NULL,
  PRIMARY KEY (`ZaposleniID`)
) ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;

INSERT INTO Zaposleni (ZaposleniID, Ime, Prezime, KorisnickoIme, Lozinka) VALUES
(1, 'Luka', 'Peric', 'luka', 'luka'),
(2, 'Stefan', 'Stefic', 'stefan', 'stefan');


DROP TABLE IF EXISTS `TerminDezurstva`;

CREATE TABLE `TerminDezurstva` (
  `TerminDezurstvaID` BIGINT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `Smena` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`TerminDezurstvaID`)
) ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;

INSERT INTO TerminDezurstva (TerminDezurstvaID, Smena) VALUES
(1, 'Jutarnja smena'),
(2, 'Popodnevna smena'),
(3, 'Noćna smena');


DROP TABLE IF EXISTS `ZaposleniTermin`;

CREATE TABLE `ZaposleniTermin` (
  `TerminDezurstvaID` BIGINT(10) UNSIGNED NOT NULL,
  `ZaposleniID` BIGINT(10) UNSIGNED NOT NULL,
  `Datum` DATE NOT NULL,
  PRIMARY KEY (`TerminDezurstvaID`, `ZaposleniID`, `Datum`),
  CONSTRAINT `fk_ter_id` FOREIGN KEY (`TerminDezurstvaID`) REFERENCES `TerminDezurstva` (`TerminDezurstvaID`),
  CONSTRAINT `fk_zap_id2` FOREIGN KEY (`ZaposleniID`) REFERENCES `Zaposleni` (`ZaposleniID`)
) ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;

INSERT INTO ZaposleniTermin (TerminDezurstvaID, ZaposleniID, Datum) VALUES
(1, 1, '2025-06-08'),
(2, 2, '2025-06-09'),
(3, 1, '2025-06-10');



DROP TABLE IF EXISTS `Grad`;

CREATE TABLE `Grad` (
  `GradID` BIGINT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `Naziv` VARCHAR(30) NOT NULL,
  PRIMARY KEY (`GradID`)
) ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;


INSERT INTO Grad (GradID, Naziv) VALUES
(1, 'Beograd'),
(2, 'Nis'),
(3, 'Novi Sad');



DROP TABLE IF EXISTS `PoslovniPartner`;

CREATE TABLE `PoslovniPartner` (
  `PoslovniPartnerID` BIGINT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `Ime` VARCHAR(30) NOT NULL,
  `Prezime` VARCHAR(30) NOT NULL,
  `Email` VARCHAR(50) NOT NULL,
  `Telefon` VARCHAR(30) NOT NULL,
  `GradID` BIGINT(10) UNSIGNED NOT NULL,
  PRIMARY KEY (`PoslovniPartnerID`),
  CONSTRAINT `fk_grad_id` FOREIGN KEY (`GradID`) REFERENCES `Grad` (`GradID`)
) ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;



INSERT INTO `PoslovniPartner` (`PoslovniPartnerID`, `Ime`, `Prezime`, `Email`, `Telefon`, `GradID`) VALUES
(1, 'Marko', 'Marković', 'marko@gmail.com', '+38160111222', 1),
(2, 'Jelena', 'Janković', 'jelena@gmail.com', '+38160111333', 2),
(3, 'Ivan', 'Ilić', 'ivan@gmail.com', '+38160111444', 3),
(4, 'Ana', 'Ančić', 'ana@gmail.com', '+38160111555', 4),
(5, 'Petar', 'Petrović', 'petar@gmail.com', '+38160111666', 5);




DROP TABLE IF EXISTS `Stan`;

CREATE TABLE `Stan` (
  `StanID` BIGINT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `Kvadratura` DECIMAL(10,2) NOT NULL,
  `Lokacija` VARCHAR(50) NOT NULL,
  `Opis` VARCHAR(300) NOT NULL,
  `CenaPoDanu` DECIMAL(10,2) NOT NULL,
  PRIMARY KEY (`StanID`)
) ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;


INSERT INTO `Stan` (`StanID`, `Kvadratura`, `Lokacija`, `Opis`, `CenaPoDanu`) VALUES
(1, 55.50, 'Beograd - Vračar', 'Stan u centru, renoviran', 35.00),
(2, 72.00, 'Novi Sad - Liman', 'Stan blizu fakulteta', 30.00),
(3, 80.00, 'Niš - Centar', 'Veliki stan sa dve spavaće', 28.00),
(4, 60.00, 'Kragujevac - Aerodrom', 'Moderan jednosoban stan', 25.00),
(5, 45.00, 'Subotica - Prozivka', 'Povoljno i funkcionalno', 20.00);




DROP TABLE IF EXISTS `Iznajmljivanje`;

CREATE TABLE `Iznajmljivanje` (
  `IznajmljivanjeID` BIGINT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `Opis` VARCHAR(200) NOT NULL,
  `UkupanIznos` DECIMAL(10,2) NOT NULL,
  `PoslovniPartnerID` BIGINT(10) UNSIGNED NOT NULL,
  `ZaposleniID` BIGINT(10) UNSIGNED NOT NULL,
  PRIMARY KEY (`IznajmljivanjeID`),
  CONSTRAINT `fk_pp_id` FOREIGN KEY (`PoslovniPartnerID`) REFERENCES `PoslovniPartner` (`PoslovniPartnerID`),
  CONSTRAINT `fk_zap_id` FOREIGN KEY (`ZaposleniID`) REFERENCES `Zaposleni` (`ZaposleniID`)
) ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;


INSERT INTO `Iznajmljivanje` (`IznajmljivanjeID`, `Opis`, `UkupanIznos`, `PoslovniPartnerID`, `ZaposleniID`) VALUES
(1, 'Iznajmljivanje za seminar', 210.00, 1, 1),
(2, 'Smeštaj za goste', 160.00, 2, 2),
(3, 'Iznajmljivanje za poslovni put', 230.00, 3, 3);




DROP TABLE IF EXISTS `StavkaIznajmljivanja`;

CREATE TABLE `StavkaIznajmljivanja` (
  `IznajmljivanjeID` BIGINT(10) UNSIGNED NOT NULL,
  `Rb` INT(7) NOT NULL,
  `DatumOd` DATE NOT NULL,
  `DatumDo` DATE NOT NULL,
  `BrojDana` INT(7) NOT NULL,
  `CenaPoDanu` DECIMAL(10,2) NOT NULL,
  `Iznos` DECIMAL(10,2) NOT NULL,
  `StanID` BIGINT(10) UNSIGNED NOT NULL,
  PRIMARY KEY (`IznajmljivanjeID`,`Rb`),
  CONSTRAINT `fk_izn_id` FOREIGN KEY (`IznajmljivanjeID`) REFERENCES `Iznajmljivanje` (`IznajmljivanjeID`) ON DELETE CASCADE,
  CONSTRAINT `fk_stan_id` FOREIGN KEY (`StanID`) REFERENCES `Stan` (`StanID`)
) ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;


INSERT INTO `StavkaIznajmljivanja` (`IznajmljivanjeID`, `Rb`, `DatumOd`, `DatumDo`, `BrojDana`, `CenaPoDanu`, `Iznos`, `StanID`) VALUES
(1, 1, '2025-08-01', '2025-08-03', 2, 35.00, 70.00, 1),
(1, 2, '2025-08-01', '2025-08-04', 3, 30.00, 90.00, 2),
(1, 3, '2025-08-01', '2025-08-02', 1, 50.00, 50.00, 3);


INSERT INTO `StavkaIznajmljivanja` (`IznajmljivanjeID`, `Rb`, `DatumOd`, `DatumDo`, `BrojDana`, `CenaPoDanu`, `Iznos`, `StanID`) VALUES
(2, 1, '2025-08-05', '2025-08-06', 1, 30.00, 30.00, 2),
(2, 2, '2025-08-05', '2025-08-08', 3, 25.00, 75.00, 4),
(2, 3, '2025-08-05', '2025-08-06', 1, 20.00, 20.00, 5),
(2, 4, '2025-08-05', '2025-08-06', 1, 35.00, 35.00, 1);


INSERT INTO `StavkaIznajmljivanja` (`IznajmljivanjeID`, `Rb`, `DatumOd`, `DatumDo`, `BrojDana`, `CenaPoDanu`, `Iznos`, `StanID`) VALUES
(3, 1, '2025-08-10', '2025-08-12', 2, 35.00, 70.00, 1),
(3, 2, '2025-08-10', '2025-08-13', 3, 40.00, 120.00, 3),
(3, 3, '2025-08-10', '2025-08-11', 1, 40.00, 40.00, 2);




/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
