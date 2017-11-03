-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Dec 05, 2016 at 06:41 PM
-- Server version: 10.1.16-MariaDB
-- PHP Version: 5.6.24

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `elektronik`
--

-- --------------------------------------------------------

--
-- Table structure for table `barang`
--

CREATE TABLE `barang` (
  `KodeBarang` varchar(5) NOT NULL,
  `NamaBarang` varchar(30) NOT NULL,
  `HargaBeli` int(10) NOT NULL,
  `HargaJual` int(10) NOT NULL,
  `Stok` int(5) NOT NULL,
  `KatId` varchar(13) NOT NULL,
  `Satuan` varchar(13) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `barang`
--

INSERT INTO `barang` (`KodeBarang`, `NamaBarang`, `HargaBeli`, `HargaJual`, `Stok`, `KatId`, `Satuan`) VALUES
('KB001', 'ADAPTOR ACER ASLI', 50000, 150000, 9, 'ADAPTOR', 'UNIT'),
('KB002', 'ADAPTOR ACER KW', 35000, 100000, 10, 'ADAPTOR', 'UNIT'),
('KB003', 'ACER ONE Z1401', 3000000, 3800000, 8, 'LAPTOP', 'UNIT'),
('KB004', 'TOSHIBA SATELLITE NB10', 3500000, 4100000, 10, 'LAPTOP', 'UNIT'),
('KB005', 'ASUS Eee PC 1215B', 2500000, 3200000, 10, 'LAPTOP', 'UNIT'),
('KB006', 'Creative A120', 150000, 230000, 15, 'SPEAKER', 'SET'),
('KB007', 'Dazumba DZ 210', 80000, 150000, 10, 'SPEAKER', 'SET'),
('KB008', 'Edifier M3500', 800000, 950000, 10, 'SPEAKER', 'SET'),
('KB009', 'LOGITECH Pure Fi Anywhere 2', 950000, 1100000, 9, 'SPEAKER', 'SET'),
('KB010', 'Simbadda 6600', 170000, 250000, 10, 'SPEAKER', 'SET');

-- --------------------------------------------------------

--
-- Table structure for table `beli`
--

CREATE TABLE `beli` (
  `NoFaktur` varchar(5) NOT NULL,
  `KodeSupplier` varchar(5) NOT NULL,
  `NamaToko` varchar(30) NOT NULL,
  `TglBeli` varchar(30) NOT NULL,
  `Total` int(10) NOT NULL,
  `Bayar` int(10) NOT NULL,
  `Kembali` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `beli`
--

INSERT INTO `beli` (`NoFaktur`, `KodeSupplier`, `NamaToko`, `TglBeli`, `Total`, `Bayar`, `Kembali`) VALUES
('NF001', 'KS001', 'Maju Jaya', '29/November/2016', 90850000, 100000000, 9150000),
('NF002', 'KS002', 'Pointblank', '29/November/2016', 21500000, 21500000, 0),
('NF003', 'KS001', 'Maju Jaya', '30/November/2016', 750000, 750000, 0);

-- --------------------------------------------------------

--
-- Table structure for table `detailbeli`
--

CREATE TABLE `detailbeli` (
  `NoFaktur` varchar(5) NOT NULL,
  `KodeBarang` varchar(5) NOT NULL,
  `NamaBarang` varchar(30) NOT NULL,
  `HargaBeli` int(10) NOT NULL,
  `Qty` int(5) NOT NULL,
  `Total` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `detailbeli`
--

INSERT INTO `detailbeli` (`NoFaktur`, `KodeBarang`, `NamaBarang`, `HargaBeli`, `Qty`, `Total`) VALUES
('NF001', 'KB001', 'ADAPTOR ACER ASLI', 50000, 10, 500000),
('NF001', 'KB002', 'ADAPTOR ACER KW', 35000, 10, 350000),
('NF001', 'KB003', 'ACER ONE Z1401', 3000000, 10, 30000000),
('NF001', 'KB004', 'TOSHIBA SATELLITE NB10', 3500000, 10, 35000000),
('NF001', 'KB005', 'ASUS Eee PC 1215B', 2500000, 10, 25000000),
('NF002', 'KB006', 'Creative A120', 150000, 10, 1500000),
('NF002', 'KB007', 'Dazumba DZ 210', 80000, 10, 800000),
('NF002', 'KB008', 'Edifier M3500', 800000, 10, 8000000),
('NF002', 'KB009', 'LOGITECH Pure Fi Anywhere 2', 950000, 10, 9500000),
('NF002', 'KB010', 'Simbadda 6600', 170000, 10, 1700000),
('NF003', 'KB006', 'Creative A120', 150000, 5, 750000);

--
-- Triggers `detailbeli`
--
DELIMITER $$
CREATE TRIGGER `TG_STOKUPDATE_BELI` AFTER INSERT ON `detailbeli` FOR EACH ROW BEGIN
 UPDATE barang SET stok=stok+NEW.qty
 WHERE KodeBarang=NEW.kodebarang;
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `detailbelitemp`
--

CREATE TABLE `detailbelitemp` (
  `NoFaktur` varchar(5) NOT NULL,
  `KodeBarang` varchar(5) NOT NULL,
  `NamaBarang` varchar(30) NOT NULL,
  `HargaBeli` int(10) NOT NULL,
  `Qty` int(5) NOT NULL,
  `Total` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `detailjual`
--

CREATE TABLE `detailjual` (
  `NoTransaksi` varchar(5) NOT NULL,
  `KodeBarang` varchar(5) NOT NULL,
  `NamaBarang` varchar(30) NOT NULL,
  `HargaJual` int(10) NOT NULL,
  `Qty` int(5) NOT NULL,
  `Total` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `detailjual`
--

INSERT INTO `detailjual` (`NoTransaksi`, `KodeBarang`, `NamaBarang`, `HargaJual`, `Qty`, `Total`) VALUES
('NT001', 'KB003', 'ACER ONE Z1401', 3800000, 1, 3800000),
('NT002', 'KB001', 'ADAPTOR ACER ASLI', 150000, 1, 150000),
('NT002', 'KB003', 'ACER ONE Z1401', 3800000, 1, 3800000),
('NT003', 'KB009', 'LOGITECH Pure Fi Anywhere 2', 1100000, 1, 1100000);

--
-- Triggers `detailjual`
--
DELIMITER $$
CREATE TRIGGER `TG_STOKUPDATE_JUAL` AFTER INSERT ON `detailjual` FOR EACH ROW BEGIN
 UPDATE barang SET stok=stok-NEW.qty
 WHERE KodeBarang=NEW.kodebarang;
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `detailjualtemp`
--

CREATE TABLE `detailjualtemp` (
  `NoTransaksi` varchar(5) NOT NULL,
  `KodeBarang` varchar(5) NOT NULL,
  `NamaBarang` varchar(30) NOT NULL,
  `HargaJual` int(10) NOT NULL,
  `Qty` int(5) NOT NULL,
  `Total` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `jual`
--

CREATE TABLE `jual` (
  `NoTransaksi` varchar(5) NOT NULL,
  `TglJual` varchar(30) NOT NULL,
  `Total` int(10) NOT NULL,
  `Bayar` int(10) NOT NULL,
  `Kembali` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `jual`
--

INSERT INTO `jual` (`NoTransaksi`, `TglJual`, `Total`, `Bayar`, `Kembali`) VALUES
('NT001', '29/November/2016', 3800000, 3800000, 0),
('NT002', '30/November/2016', 3950000, 4000000, 50000),
('NT003', '30/November/2016', 1100000, 1500000, 400000);

-- --------------------------------------------------------

--
-- Table structure for table `login`
--

CREATE TABLE `login` (
  `IdLogin` int(11) NOT NULL,
  `NamaLengkap` varchar(30) NOT NULL,
  `User` varchar(50) NOT NULL,
  `Pass` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `login`
--

INSERT INTO `login` (`IdLogin`, `NamaLengkap`, `User`, `Pass`) VALUES
(1, 'Nur AlamSyah', 'NAS', '007'),
(2, 'Fitri Angraeni', 'fitri', '026'),
(3, 'Jumrianti', 'jum', '022');

-- --------------------------------------------------------

--
-- Table structure for table `supplier`
--

CREATE TABLE `supplier` (
  `KodeSupplier` varchar(5) NOT NULL,
  `NamaToko` varchar(30) NOT NULL,
  `Alamat` varchar(50) NOT NULL,
  `Telp` varchar(15) NOT NULL,
  `Email` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `supplier`
--

INSERT INTO `supplier` (`KodeSupplier`, `NamaToko`, `Alamat`, `Telp`, `Email`) VALUES
('KS001', 'Maju Jaya', 'Jln Abubakar Lambogo', '(0411)223423', 'majujaya01@gmail.com'),
('KS002', 'Pointblank', 'Jln Gunung Latimojong No.101', '(0411)257499', 'pointblank@gmail.com');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `barang`
--
ALTER TABLE `barang`
  ADD PRIMARY KEY (`KodeBarang`),
  ADD KEY `KatId` (`KatId`),
  ADD KEY `KatId_2` (`KatId`);

--
-- Indexes for table `beli`
--
ALTER TABLE `beli`
  ADD PRIMARY KEY (`NoFaktur`),
  ADD KEY `KodeSupplier` (`KodeSupplier`);

--
-- Indexes for table `detailbeli`
--
ALTER TABLE `detailbeli`
  ADD KEY `KodeBarang` (`KodeBarang`);

--
-- Indexes for table `detailjual`
--
ALTER TABLE `detailjual`
  ADD KEY `KodeBarang` (`KodeBarang`);

--
-- Indexes for table `jual`
--
ALTER TABLE `jual`
  ADD PRIMARY KEY (`NoTransaksi`);

--
-- Indexes for table `supplier`
--
ALTER TABLE `supplier`
  ADD PRIMARY KEY (`KodeSupplier`);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `beli`
--
ALTER TABLE `beli`
  ADD CONSTRAINT `beli_ibfk_1` FOREIGN KEY (`KodeSupplier`) REFERENCES `supplier` (`KodeSupplier`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `detailbeli`
--
ALTER TABLE `detailbeli`
  ADD CONSTRAINT `detailbeli_ibfk_1` FOREIGN KEY (`KodeBarang`) REFERENCES `barang` (`KodeBarang`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `detailjual`
--
ALTER TABLE `detailjual`
  ADD CONSTRAINT `detailjual_ibfk_1` FOREIGN KEY (`KodeBarang`) REFERENCES `barang` (`KodeBarang`) ON DELETE CASCADE ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
