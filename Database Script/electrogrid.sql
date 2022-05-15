-- phpMyAdmin SQL Dump
-- version 5.1.3
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 15, 2022 at 10:13 AM
-- Server version: 10.4.24-MariaDB
-- PHP Version: 7.4.28

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `electrogrid`
--

-- --------------------------------------------------------

--
-- Table structure for table `billing`
--

CREATE TABLE `billing` (
  `BillID` int(11) NOT NULL,
  `electricity_account-number` int(20) NOT NULL,
  `name` varchar(30) NOT NULL,
  `address` varchar(50) NOT NULL,
  `from_date` date NOT NULL,
  `previous_meter_reading` varchar(20) NOT NULL,
  `to_date` date NOT NULL,
  `current_meter_reading` varchar(20) NOT NULL,
  `NoOfUnits` int(20) NOT NULL,
  `charge_for_elec_consumed` double NOT NULL,
  `dues_pre_amount` double NOT NULL,
  `Amount` float NOT NULL,
  `UserID` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `billing`
--

INSERT INTO `billing` (`BillID`, `electricity_account-number`, `name`, `address`, `from_date`, `previous_meter_reading`, `to_date`, `current_meter_reading`, `NoOfUnits`, `charge_for_elec_consumed`, `dues_pre_amount`, `Amount`, `UserID`) VALUES
(1000, 2342344, 'perara', '2/3,jabugahawaththa', '2022-01-03', '34', '2023-02-03', '56', 75, 2000, 2300, 10000, 1),
(1001, 2123123, 'karuna', '12/2', '2022-01-03', '23', '2023-03-04', '24', 150, 2000, 2344, 20000, 2),
(1002, 2123126, 'karuna1', '12/6', '2022-01-03', '23', '2023-03-04', '24', 250, 2000, 2344, 15000, 3),
(1003, 7123123, 'karuna1', '12/2', '2022-01-03', '23', '2023-03-04', '24', 40, 2000, 2344, 20000, 1);

-- --------------------------------------------------------

--
-- Table structure for table `payment`
--

CREATE TABLE `payment` (
  `PaymentID` int(11) NOT NULL,
  `CardType` varchar(20) NOT NULL,
  `CardNumber` varchar(30) NOT NULL,
  `CardHolderName` varchar(50) NOT NULL,
  `CVC` varchar(20) NOT NULL,
  `CardExpireDate` varchar(30) NOT NULL,
  `Status` varchar(50) NOT NULL,
  `TaxAmount` float NOT NULL,
  `TotalAmount` double NOT NULL,
  `PaymentDate` varchar(30) NOT NULL,
  `BillID` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `payment`
--

INSERT INTO `payment` (`PaymentID`, `CardType`, `CardNumber`, `CardHolderName`, `CVC`, `CardExpireDate`, `Status`, `TaxAmount`, `TotalAmount`, `PaymentDate`, `BillID`) VALUES
(141, 'visa', '1234123412341234', 'Chamath', '234', '2/1/2022', 'pending', 225, 10225, '2/2/2022', 1000),
(142, 'master', '1234565434343333', 'Ruwan', '665', '2/3/2025', 'pending', 500, 20500, '3/4/2022', 1001),
(143, 'visa', '6565666677778765', 'Pawan', '323', '3/9/2025', 'pending', 950, 15950, '2/3/2022', 1002),
(144, 'visa', '1265555545654444', 'Kamal', '567', '5/5/2025', 'pending', 120, 20120, '4/7/2022', 1003);

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `UserID` int(11) NOT NULL,
  `account_number` int(20) NOT NULL,
  `name` varchar(30) NOT NULL,
  `address` varchar(50) NOT NULL,
  `NIC` varchar(20) NOT NULL,
  `email` varchar(20) NOT NULL,
  `phone` int(10) NOT NULL,
  `user_type` varchar(20) NOT NULL,
  `username` varchar(20) NOT NULL,
  `password` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`UserID`, `account_number`, `name`, `address`, `NIC`, `email`, `phone`, `user_type`, `username`, `password`) VALUES
(1, 2147483647, 'J.K.PERERA', '2/1,RAJAYANA', '12323432V', 'Chama@gmail.com', 765656565, 'user', 'Chama', 'chama123'),
(2, 2147483647, 'E.K.PERERA', '3/1,RAJAYANA', '72323432V', 'Chama1@gmail.com', 765656569, 'user', 'Chama4', 'chama12'),
(3, 2147483647, 'A.K.PERERA', '3/1,RAJAYANA', '92323432V', 'Chama1@gmail.com', 765656588, 'user', 'Chama4', 'chama');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `billing`
--
ALTER TABLE `billing`
  ADD PRIMARY KEY (`BillID`),
  ADD KEY `fk_userid` (`UserID`);

--
-- Indexes for table `payment`
--
ALTER TABLE `payment`
  ADD PRIMARY KEY (`PaymentID`),
  ADD KEY `fk_billid` (`BillID`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`UserID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `billing`
--
ALTER TABLE `billing`
  MODIFY `BillID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=1004;

--
-- AUTO_INCREMENT for table `payment`
--
ALTER TABLE `payment`
  MODIFY `PaymentID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=145;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `UserID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `billing`
--
ALTER TABLE `billing`
  ADD CONSTRAINT `fk_userid` FOREIGN KEY (`UserID`) REFERENCES `user` (`UserID`);

--
-- Constraints for table `payment`
--
ALTER TABLE `payment`
  ADD CONSTRAINT `fk_billid` FOREIGN KEY (`BillID`) REFERENCES `billing` (`BillID`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
