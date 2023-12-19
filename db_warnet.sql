-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               8.0.30 - MySQL Community Server - GPL
-- Server OS:                    Win64
-- HeidiSQL Version:             12.1.0.6537
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- Dumping database structure for db_warnet
CREATE DATABASE IF NOT EXISTS `db_warnet` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `db_warnet`;

-- Dumping structure for table db_warnet.detil_tarif
CREATE TABLE IF NOT EXISTS `detil_tarif` (
  `kodetarif` varchar(10) DEFAULT NULL,
  `idkomp` varchar(10) DEFAULT NULL,
  `durasi` int DEFAULT NULL,
  KEY `FK_tarif` (`kodetarif`) USING BTREE,
  KEY `FK_komputer_idkomp` (`idkomp`) USING BTREE,
  CONSTRAINT `FK_komputer_idkomp` FOREIGN KEY (`idkomp`) REFERENCES `komputer` (`idkomp`),
  CONSTRAINT `FK_tarif` FOREIGN KEY (`kodetarif`) REFERENCES `tarif` (`kodetarif`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table db_warnet.detil_tarif: ~7 rows (approximately)
INSERT INTO `detil_tarif` (`kodetarif`, `idkomp`, `durasi`) VALUES
	('2023120002', 'K068', 3),
	('2023120001', 'K003', 4),
	('2023120001', 'K068', 1),
	('2023120004', 'K012', 2),
	('2023120005', 'K068', 1),
	('2023120005', 'K070', 2),
	('2023120007', 'K070', 2);

-- Dumping structure for table db_warnet.komputer
CREATE TABLE IF NOT EXISTS `komputer` (
  `idkomp` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `namakomp` varchar(20) DEFAULT NULL,
  `jenis` varchar(20) DEFAULT NULL,
  `harga` int DEFAULT NULL,
  PRIMARY KEY (`idkomp`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table db_warnet.komputer: ~8 rows (approximately)
INSERT INTO `komputer` (`idkomp`, `namakomp`, `jenis`, `harga`) VALUES
	('K001', 'NM-001', 'Normal', 10000),
	('K002', 'NM-002', 'Normal', 10000),
	('K003', 'PR-001', 'Premium', 13000),
	('K012', 'PR-012', 'Premium', 13000),
	('K013', 'PR-013', 'Premium', 13000),
	('K068', 'ES-001', 'Esport', 18000),
	('K069', 'ES-002', 'Esport', 18000),
	('K070', 'ES-003', 'Esport', 18000);

-- Dumping structure for table db_warnet.member
CREATE TABLE IF NOT EXISTS `member` (
  `idmember` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `nama` varchar(50) DEFAULT NULL,
  `userid` varchar(50) DEFAULT NULL,
  `password` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`idmember`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table db_warnet.member: ~5 rows (approximately)
INSERT INTO `member` (`idmember`, `nama`, `userid`, `password`) VALUES
	('M0001', 'Yefta', 'yeftasm', 'yefta123'),
	('M0002', 'Adit', 'ditmirakel', 'dit123'),
	('M0003', 'Joko', 'jokk1', 'joko123'),
	('M0004', 'Vito', 'vto12', 'vito123'),
	('M0005', 'test23', 'testing22', 'test132');

-- Dumping structure for table db_warnet.tarif
CREATE TABLE IF NOT EXISTS `tarif` (
  `kodetarif` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `tanggal` date DEFAULT NULL,
  `idmember` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  PRIMARY KEY (`kodetarif`),
  KEY `FK_tarif_member` (`idmember`),
  CONSTRAINT `FK_tarif_member` FOREIGN KEY (`idmember`) REFERENCES `member` (`idmember`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table db_warnet.tarif: ~5 rows (approximately)
INSERT INTO `tarif` (`kodetarif`, `tanggal`, `idmember`) VALUES
	('2023120001', '2023-12-07', 'M0003'),
	('2023120002', '2023-12-15', 'M0002'),
	('2023120004', '2023-12-19', 'M0002'),
	('2023120005', '2023-12-19', 'M0004'),
	('2023120007', '2023-12-19', 'M0003');

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
