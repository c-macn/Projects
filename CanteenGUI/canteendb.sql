-- phpMyAdmin SQL Dump
-- version 3.4.9
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Apr 20, 2012 at 01:12 PM
-- Server version: 5.5.20
-- PHP Version: 5.3.9

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `canteendb`
--

-- --------------------------------------------------------

--
-- Table structure for table `basket`
--

CREATE TABLE IF NOT EXISTS `basket` (
  `staffID` varchar(45) NOT NULL DEFAULT '0',
  `orderID` bigint(255) NOT NULL AUTO_INCREMENT,
  `userID` varchar(45) NOT NULL,
  `itemID` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`orderID`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=6 ;

-- --------------------------------------------------------

--
-- Table structure for table `item`
--

CREATE TABLE IF NOT EXISTS `item` (
  `itemID` int(11) NOT NULL AUTO_INCREMENT,
  `itemName` varchar(45) DEFAULT NULL,
  `itemDescription` varchar(500) DEFAULT NULL,
  `itemPrice` decimal(3,2) DEFAULT NULL,
  PRIMARY KEY (`itemID`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=10 ;

--
-- Dumping data for table `item`
--

INSERT INTO `item` (`itemID`, `itemName`, `itemDescription`, `itemPrice`) VALUES
(1, 'Chicken Baguette', 'Southern baked with crisp lettuce,Tomatoe and mayo', 4.69),
(2, 'Light Baguette', 'Wholegrain Baguette filled with chargrilled chicken,crisp leaves,tomatoe salsa,drizzled with basil pesto', 4.69),
(3, 'Sausage & chips', 'Two Pork sausages with a side of chips and beans', 3.99),
(4, 'Chicken Stir fry', 'Mixed peppers,onions,carrots and corn mixed in with soy sauce ', 5.99),
(5, 'Beef Stir fry', 'Mixed peppers,onions,carrots and corn mixed in with oyster sauce ', 5.99),
(6, 'Classic burger', 'Filled with Onions,cheese,lettuce and ITB secret sauce ', 4.50),
(7, 'Bacon burger', 'Filled with Onions,bacon,cheese,lettuce and ITB secret sauce ', 4.50),
(8, 'Triple Decker burger', 'Three pattys all with lettuce,tomatoe,cheese,bacon and ITB secret sauce', 5.50);

-- --------------------------------------------------------

--
-- Table structure for table `payment_info`
--

CREATE TABLE IF NOT EXISTS `payment_info` (
  `userID` varchar(9) NOT NULL,
  `cardNumber` bigint(255) DEFAULT NULL,
  `expDate` year(4) DEFAULT NULL,
  `type` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`userID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `payment_info`
--

INSERT INTO `payment_info` (`userID`, `cardNumber`, `expDate`, `type`) VALUES
('B00012345', 123, 2089, 'MasterCard');

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE IF NOT EXISTS `user` (
  `username` varchar(9) NOT NULL,
  `password` varchar(45) DEFAULT NULL,
  `privillage` int(1) NOT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`username`, `password`, `privillage`) VALUES
('B00012345', 'pass', 3),
('manager', 'man', 1),
('S00012345', 'burger', 2),
('S00012346', 'chips', 2);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
