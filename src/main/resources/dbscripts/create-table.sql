-- phpMyAdmin SQL Dump
-- version 4.7.4
-- https://www.phpmyadmin.net/
--
-- Server version: 10.1.29-MariaDB
-- PHP Version: 7.2.0

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `configsource-db`
--

-- --------------------------------------------------------

--
-- Table structure for table `configproperty`
--

CREATE TABLE `configproperty` (
  `id` bigint(20) NOT NULL,
  `application` varchar(255) DEFAULT NULL,
  `configKey` varchar(255) DEFAULT NULL,
  `configValue` varchar(255) DEFAULT NULL,
  `isActive` bit(1) DEFAULT NULL,
  `label` varchar(255) DEFAULT NULL,
  `profile` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `configproperty`
--

INSERT INTO `configproperty` (`id`, `application`, `configKey`, `configValue`, `isActive`, `label`, `profile`) VALUES
(1, 'test-app', 'test.property.key', 'test property value', b'1', NULL, 'QA');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `configproperty`
--
ALTER TABLE `configproperty`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `configproperty`
--
ALTER TABLE `configproperty`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
