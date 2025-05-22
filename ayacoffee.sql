-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Apr 20, 2025 at 05:02 PM
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
-- Database: `ayacoffee`
--

-- --------------------------------------------------------

--
-- Table structure for table `bill`
--

CREATE TABLE `bill` (
  `id` int(11) NOT NULL,
  `time` varchar(100) NOT NULL,
  `date` varchar(20) NOT NULL,
  `total` int(11) NOT NULL,
  `status` varchar(50) NOT NULL DEFAULT 'Shipping'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `bill`
--

INSERT INTO `bill` (`id`, `time`, `date`, `total`, `status`) VALUES
(1, '17:29', '2025-04-17', 97000, 'Delivered'),
(2, '18:40', '2025-04-17', 108000, 'Delivered'),
(3, '13:40', '2025-04-18', 79000, 'Delivered'),
(4, '17:41', '2025-04-18', 75000, 'Delivered'),
(5, '11:41', '2025-04-19', 114000, 'Delivered'),
(6, '14:43', '2025-04-19', 88000, 'Delivered'),
(7, '10:43', '2025-04-20', 200000, 'Delivered'),
(8, '17:54', '2025-04-20', 105000, 'Shipping');

-- --------------------------------------------------------

--
-- Table structure for table `category`
--

CREATE TABLE `category` (
  `id` int(11) NOT NULL,
  `name` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `category`
--

INSERT INTO `category` (`id`, `name`) VALUES
(1, 'Food'),
(2, 'Drink');

-- --------------------------------------------------------

--
-- Table structure for table `detailsbill`
--

CREATE TABLE `detailsbill` (
  `id` int(11) NOT NULL,
  `product` int(11) NOT NULL,
  `bill` int(11) NOT NULL,
  `quantity` int(11) NOT NULL,
  `price` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `detailsbill`
--

INSERT INTO `detailsbill` (`id`, `product`, `bill`, `quantity`, `price`) VALUES
(1, 1, 1, 1, 20000),
(2, 2, 1, 1, 40000),
(3, 14, 1, 1, 37000),
(4, 6, 2, 1, 35000),
(5, 11, 2, 1, 18000),
(6, 8, 2, 1, 25000),
(7, 10, 2, 1, 30000),
(8, 13, 3, 2, 64000),
(9, 5, 3, 1, 15000),
(10, 7, 4, 1, 20000),
(11, 8, 4, 1, 25000),
(12, 10, 4, 1, 30000),
(13, 13, 5, 1, 32000),
(14, 3, 5, 1, 25000),
(15, 9, 5, 1, 27000),
(16, 10, 5, 1, 30000),
(17, 3, 6, 1, 25000),
(18, 11, 6, 1, 18000),
(19, 15, 6, 1, 45000),
(20, 7, 7, 1, 20000),
(21, 8, 7, 1, 25000),
(22, 10, 7, 1, 30000),
(23, 13, 7, 1, 32000),
(24, 6, 7, 1, 35000),
(25, 2, 7, 1, 40000),
(26, 11, 7, 1, 18000),
(27, 6, 8, 1, 35000),
(28, 2, 8, 1, 40000),
(29, 10, 8, 1, 30000);

-- --------------------------------------------------------

--
-- Table structure for table `product`
--

CREATE TABLE `product` (
  `id` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `categoryID` int(11) NOT NULL,
  `quantity` int(11) NOT NULL,
  `price` varchar(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `product`
--

INSERT INTO `product` (`id`, `name`, `categoryID`, `quantity`, `price`) VALUES
(1, 'Donut', 1, 203, '20000'),
(2, 'Tiramisu', 1, 177, '40000'),
(3, 'Cupcake', 1, 51, '25000'),
(4, 'Bánh da lợn', 1, 50, '12000'),
(5, 'Bánh flan', 1, 69, '15000'),
(6, 'Bánh su kem', 1, 146, '35000'),
(7, 'Trà Đào', 2, 42, '20000'),
(8, 'Trà dâu', 2, 30, '25000'),
(9, 'Bánh bao', 1, 66, '27000'),
(10, 'Matcha Latte', 2, 94, '30000'),
(11, 'Hotdog', 1, 99, '18000'),
(12, 'Mochi', 1, 78, '28000'),
(13, 'Muffin', 1, 174, '32000'),
(14, 'Espresso', 2, 69, '37000'),
(15, 'Trà sữa lài', 2, 67, '45000');

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `id` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `sex` varchar(10) NOT NULL,
  `role` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id`, `name`, `username`, `password`, `sex`, `role`) VALUES
(1, 'Thế Phong', 'admin', 'c4ca4238a0b923820dcc509a6f75849b', 'Nam', 2),
(2, 'Ngọc Trâm', 'Tram', '202cb962ac59075b964b07152d234b70', 'Nữ', 1),
(3, 'Gia Bảo', 'Bao', '202cb962ac59075b964b07152d234b70', 'Nam', 1),
(4, 'Trọng Hiếu', 'Hieu', '202cb962ac59075b964b07152d234b70', 'Nam', 1);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `bill`
--
ALTER TABLE `bill`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `category`
--
ALTER TABLE `category`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `detailsbill`
--
ALTER TABLE `detailsbill`
  ADD PRIMARY KEY (`id`),
  ADD KEY `bill` (`bill`);

--
-- Indexes for table `product`
--
ALTER TABLE `product`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_cat` (`categoryID`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `bill`
--
ALTER TABLE `bill`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `category`
--
ALTER TABLE `category`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `detailsbill`
--
ALTER TABLE `detailsbill`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=30;

--
-- AUTO_INCREMENT for table `product`
--
ALTER TABLE `product`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `product`
--
ALTER TABLE `product`
  ADD CONSTRAINT `fk_cat` FOREIGN KEY (`categoryID`) REFERENCES `category` (`id`),
  ADD CONSTRAINT `product_ibfk_1` FOREIGN KEY (`id`) REFERENCES `detailsbill` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
