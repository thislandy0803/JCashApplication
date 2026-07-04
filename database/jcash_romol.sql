-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jul 04, 2026 at 08:35 AM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `jcash_romol`
--

-- --------------------------------------------------------

--
-- Table structure for table `transactions_logs`
--

CREATE TABLE `transactions_logs` (
  `id` int(11) NOT NULL,
  `user_mobile` varchar(15) NOT NULL,
  `type` varchar(20) NOT NULL,
  `amount` decimal(12,2) NOT NULL,
  `details` varchar(150) NOT NULL,
  `date_time` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `transactions_logs`
--

INSERT INTO `transactions_logs` (`id`, `user_mobile`, `type`, `amount`, `details`, `date_time`) VALUES
(50, '09213193801', 'CASH-IN', 10000000.00, 'Deposit to your JCash Balance', '2026-06-27 09:28:05'),
(51, '09616235624', 'CASH-IN', 1.00, 'Deposit to your JCash Balance', '2026-06-27 09:41:34'),
(52, '09616235624', 'CASH-IN', 1551110.00, 'Deposit to your JCash Balance', '2026-06-27 09:41:53'),
(53, '09616235624', 'Transfer Out', 1.00, 'Sent to Mr/Ms: Thislandy F. Romol With the mobile number of: 09213193801', '2026-06-27 09:42:02'),
(54, '09213193801', 'Transfer In', 1.00, 'Received from Mr/Ms: Jessie Rose G. Ferrer With the mobile number of: 09616235624', '2026-06-27 09:42:02'),
(55, '09213193801', 'Transfer Out', 159654.00, 'Sent to Mr/Ms: Jessie Rose G. Ferrer With the mobile number of: 09616235624', '2026-06-27 10:40:39'),
(56, '09616235624', 'Transfer In', 159654.00, 'Received from Mr/Ms: Thislandy F. Romol With the mobile number of: 09213193801', '2026-06-27 10:40:39'),
(57, '09213193801', 'Transfer Out', 1.00, 'Sent to Mr/Ms: [Jessie Rose G. Ferrer] With the mobile number of [09616235624 ]', '2026-06-27 10:44:28'),
(58, '09616235624', 'Transfer In', 1.00, 'Received from Mr/Ms: [Thislandy F. Romol] With the mobile number of [09213193801 ]', '2026-06-27 10:44:28'),
(59, '09213193801', 'Transfer Out', 159357.00, 'Sent to Mr/Ms [Jessie Rose G. Ferrer] With the mobile number of [09616235624]', '2026-06-27 10:45:22'),
(60, '09616235624', 'Transfer In', 159357.00, 'Received from Mr/Ms [Thislandy F. Romol] With the mobile number of [09213193801]', '2026-06-27 10:45:22'),
(61, '03216549870', 'CASH-IN', 123456.00, 'Deposit to your JCash Balance', '2026-06-27 11:50:15'),
(62, '03216549870', 'Transfer Out', 1.00, 'Sent to Mr/Ms [Thislandy F. Romol] With the mobile number of [09213193801]', '2026-06-27 11:50:25'),
(63, '09213193801', 'Transfer In', 1.00, 'Received from Mr/Ms [john john] With the mobile number of [03216549870]', '2026-06-27 11:50:25');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id` bigint(20) NOT NULL,
  `mobile_number` varchar(15) NOT NULL,
  `pin` varchar(60) NOT NULL,
  `full_name` varchar(100) NOT NULL,
  `balance` decimal(12,2) NOT NULL DEFAULT 0.00
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `mobile_number`, `pin`, `full_name`, `balance`) VALUES
(1, '12345678901', '$2a$12$XfrVzt/x5RAw4I5L6SJs.ugi6mgXidcdeIt3fnwo5MUgFZaHQBGeW', 'asdasdasdasdas', 0.00),
(2, '98765432109', '$2a$12$0j.IIK4qLhjQT5Eck14vOeze2ZDONMjAStx56LISQ734Nw9cFTp6a', 'ASDASDASDASDASDA', 111110.00),
(3, '12345678900', '$2a$12$Q36RPePLtZEq40.RXahk8.9qowDMDgPdtdM4DDRw3QGYRGM8rrPym', 'ASDASDASDASD', 0.00),
(4, '09213193801', '$2a$12$J8me0peaULR/JyfWHkwgAe864S.tJF1jjrtlEWW5nFEQQA2fGGAvW', 'Thislandy F. Romol', 9680990.00),
(5, '09616235624', '$2a$12$LCdRDZwkZBJAtuwecPoNqePkP1QGluq/yyOfCLYV9JR3zg5b8XVqa', 'Jessie Rose G. Ferrer', 1870122.00),
(6, '03216549870', '$2a$12$IUgjBE0B7z5V0ySYGSFMbeN/7LGyoFcjP8jgxhNF73XP7.S5vhS1y', 'john john', 123455.00);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `transactions_logs`
--
ALTER TABLE `transactions_logs`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `mobile_number` (`mobile_number`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `transactions_logs`
--
ALTER TABLE `transactions_logs`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=64;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
