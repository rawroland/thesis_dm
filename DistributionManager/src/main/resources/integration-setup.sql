-- phpMyAdmin SQL Dump
-- version 4.0.4.1
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Erstellungszeit: 02. Nov 2014 um 21:24
-- Server Version: 5.5.32-log
-- PHP-Version: 5.4.17

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Datenbank: `thesis_dm_test`
--
CREATE DATABASE IF NOT EXISTS `thesis_dm_test` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE `thesis_dm_test`;

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `accounts`
--

DROP TABLE `accounts`;
CREATE TABLE IF NOT EXISTS `accounts` (
  `id` int(5) NOT NULL AUTO_INCREMENT,
  `clientId` int(5) NOT NULL,
  `amount` int(9) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `clients`
--

DROP TABLE `clients`;
CREATE TABLE IF NOT EXISTS `clients` (
  `id` int(5) NOT NULL AUTO_INCREMENT,
  `prefix` enum('','Mr','Mrs') NOT NULL DEFAULT '',
  `firstName` varchar(100) NOT NULL,
  `lastName` varchar(50) NOT NULL,
  `company` varchar(50) NOT NULL,
  `type` enum('company','consumer','supplier') NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `firstName` (`firstName`,`lastName`,`company`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `employees`
--

DROP TABLE `employees`;
CREATE TABLE IF NOT EXISTS `employees` (
  `id` int(5) NOT NULL AUTO_INCREMENT,
  `givenname` varchar(100) NOT NULL,
  `surname` varchar(100) NOT NULL,
  `username` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `role` enum('general manager','cashier') NOT NULL DEFAULT 'cashier',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `products`
--

DROP TABLE `products`;
CREATE TABLE IF NOT EXISTS `products` (
  `id` int(5) NOT NULL AUTO_INCREMENT,
  `clientId` int(5) NOT NULL,
  `name` varchar(50) NOT NULL,
  `quantity` int(11) DEFAULT NULL,
  `price` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `clientId` (`clientId`,`name`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `transactions`
--

DROP TABLE `transactions`;
CREATE TABLE IF NOT EXISTS `transactions` (
  `id` int(5) NOT NULL AUTO_INCREMENT,
  `accountId` int(5) NOT NULL,
  `quantity` int(11) NOT NULL,
  `productId` int(5) NOT NULL,
  `cost` int(11) NOT NULL,
  `date` date NOT NULL,
  `type` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
