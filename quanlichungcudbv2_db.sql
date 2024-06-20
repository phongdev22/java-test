-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               8.2.0 - MySQL Community Server - GPL
-- Server OS:                    Win64
-- HeidiSQL Version:             12.6.0.6765
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

-- Dumping database structure for quanlichungcuv2
-- CREATE DATABASE IF NOT EXISTS `quanlichungcuv2` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
-- USE `quanlichungcuv2`;

-- Dumping structure for table quanlichungcuv2.room
CREATE TABLE IF NOT EXISTS `room` (
  `id` int NOT NULL AUTO_INCREMENT,
  `code` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `description` text,
  `price` decimal(10,2) DEFAULT NULL,
  `rating` int DEFAULT NULL,
  `capacity` int DEFAULT NULL,
  `room_type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `status` bit(1) DEFAULT NULL,
  `created_date` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping structure for table quanlichungcuv2.user_entity
CREATE TABLE IF NOT EXISTS `user_entity` (
  `id` int NOT NULL AUTO_INCREMENT,
  `firstname` varchar(255) DEFAULT NULL,
  `lastname` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `password` varchar(999) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `phone` varchar(20) DEFAULT NULL,
  `role_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `status` varchar(50) DEFAULT NULL,
  `avatar` varchar(999) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `is_lock` tinyint(1) DEFAULT NULL,
  `is_first_login` bit(1) DEFAULT NULL,
  `room_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_user_room` (`room_id`),
  CONSTRAINT `fk_user_room` FOREIGN KEY (`room_id`) REFERENCES `room` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- Dumping structure for table quanlichungcuv2.feedback
CREATE TABLE IF NOT EXISTS `feedback` (
  `id` int NOT NULL AUTO_INCREMENT,
  `title` varchar(255) DEFAULT NULL,
  `content` text,
  `created_at` date DEFAULT NULL,
  `updated_at` date DEFAULT NULL,
  `userId` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_feedback_user` (`userId`),
  CONSTRAINT `fk_feedback_user` FOREIGN KEY (`userId`) REFERENCES `user_entity` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table quanlichungcuv2.feedback: ~0 rows (approximately)
-- Dumping structure for table quanlichungcuv2.invoice
CREATE TABLE IF NOT EXISTS `invoice` (
  `id` int NOT NULL AUTO_INCREMENT,
  `payment_code` varchar(255) DEFAULT NULL,
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `amount` decimal(10,2) DEFAULT NULL,
  `status` varchar(50) DEFAULT NULL,
  `due_date` date DEFAULT NULL,
  `room_id` int DEFAULT NULL,
  `service_type` varchar(50) DEFAULT NULL,
  `is_paid` bit(1) DEFAULT 0,
  `user_id` int DEFAULT NULL,
  `created_at` date DEFAULT NULL,
  `payment_date` date DEFAULT NULL,
  `updated_at` date DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_invoice_user` (`user_id`),
  KEY `fk_invoice_room` (`room_id`),
  CONSTRAINT `fk_invoice_room` FOREIGN KEY (`room_id`) REFERENCES `room` (`id`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `fk_invoice_user` FOREIGN KEY (`user_id`) REFERENCES `user_entity` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table quanlichungcuv2.invoice: ~2 rows (approximately)
INSERT INTO `invoice` (`id`, `payment_code`, `description`, `amount`, `status`, `due_date`, `room_id`, `service_type`, `is_paid`, `user_id`, `created_at`, `payment_date`, `updated_at`) VALUES
	(25, 'yF4sZL', '4213', 23145123.00, NULL, '2024-06-22', 30, 'electric', b'0', NULL, '2024-06-20', NULL, '2024-06-20'),
	(26, 'ISxH9Z', 'dãv', 2341234.00, NULL, '2024-06-28', 30, 'electric', b'0', NULL, '2024-06-20', NULL, '2024-06-20');

-- Dumping structure for table quanlichungcuv2.item
CREATE TABLE IF NOT EXISTS `item` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int DEFAULT NULL,
  `status` tinyint(1) DEFAULT NULL,
  `created_at` date DEFAULT NULL,
  `updated_at` date DEFAULT NULL,
  `image` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_item_user` (`user_id`),
  CONSTRAINT `fk_item_user` FOREIGN KEY (`user_id`) REFERENCES `user_entity` (`id`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table quanlichungcuv2.item: ~1 rows (approximately)
INSERT INTO `item` (`id`, `user_id`, `status`, `created_at`, `updated_at`, `image`) VALUES
	(12, 8, 0, NULL, NULL, NULL);

-- Dumping structure for table quanlichungcuv2.media
CREATE TABLE IF NOT EXISTS `media` (
  `id` int NOT NULL AUTO_INCREMENT,
  `type` varchar(50) DEFAULT NULL,
  `source` varchar(255) DEFAULT NULL,
  `mapping_id` int DEFAULT NULL,
  `table_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table quanlichungcuv2.media: ~0 rows (approximately)

-- Dumping structure for table quanlichungcuv2.relative_registration
CREATE TABLE IF NOT EXISTS `relative_registration` (
  `id` int NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) DEFAULT NULL,
  `expiry_date` datetime(6) DEFAULT NULL,
  `registration_date` datetime(6) DEFAULT NULL,
  `relationship` varchar(255) DEFAULT NULL,
  `relative_name` varchar(255) DEFAULT NULL,
  `relative_phone` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `user_id` int DEFAULT NULL,
  `vehicle_registration_number` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_relative_registration_user` (`user_id`),
  CONSTRAINT `fk_relative_registration_user` FOREIGN KEY (`user_id`) REFERENCES `user_entity` (`id`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table quanlichungcuv2.relative_registration: ~1 rows (approximately)
INSERT INTO `relative_registration` (`id`, `created_at`, `expiry_date`, `registration_date`, `relationship`, `relative_name`, `relative_phone`, `status`, `updated_at`, `user_id`, `vehicle_registration_number`) VALUES
	(12, '2024-06-20 00:32:40.275000', '2024-06-14 00:00:00.000000', '2024-06-20 00:32:40.275000', 'Spouse', 'Phong Van', '3213213124', NULL, '2024-06-20 00:32:40.275000', 8, '85A 231451');

-- Dumping data for table quanlichungcuv2.room: ~1 rows (approximately)
INSERT INTO `room` (`id`, `code`, `name`, `description`, `price`, `rating`, `capacity`, `room_type`, `status`, `created_date`) VALUES
	(30, 'ewqewq', '123214', 'eqweqw', 213123.00, NULL, 3214, 'LUXURY', b'1', NULL);

-- Dumping structure for table quanlichungcuv2.survey
CREATE TABLE IF NOT EXISTS `survey` (
  `id` int NOT NULL AUTO_INCREMENT,
  `title` varchar(255) DEFAULT NULL,
  `description` text,
  `created_date` date DEFAULT NULL,
  `start_date` date DEFAULT NULL,
  `end_date` date DEFAULT NULL,
  `status` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table quanlichungcuv2.survey: ~1 rows (approximately)
INSERT INTO `survey` (`id`, `title`, `description`, `created_date`, `start_date`, `end_date`, `status`) VALUES
	(29, '2313', '3213213', '2024-06-20', '2024-06-06', '2024-06-28', '0');

-- Dumping structure for table quanlichungcuv2.survey_answer
CREATE TABLE IF NOT EXISTS `survey_answer` (
  `id` int NOT NULL AUTO_INCREMENT,
  `question_id` int DEFAULT NULL,
  `text_answer` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_survey_answer_question` (`question_id`),
  CONSTRAINT `fk_survey_answer_question` FOREIGN KEY (`question_id`) REFERENCES `survey_question` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table quanlichungcuv2.survey_answer: ~0 rows (approximately)

-- Dumping structure for table quanlichungcuv2.survey_question
CREATE TABLE IF NOT EXISTS `survey_question` (
  `id` int NOT NULL AUTO_INCREMENT,
  `survey_id` int DEFAULT NULL,
  `question_text` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci,
  `question_type` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `question_order` int DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `fk_survey_question_survey` (`survey_id`),
  CONSTRAINT `fk_survey_question_survey` FOREIGN KEY (`survey_id`) REFERENCES `survey` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table quanlichungcuv2.survey_question: ~1 rows (approximately)
INSERT INTO `survey_question` (`id`, `survey_id`, `question_text`, `question_type`, `question_order`) VALUES
	(28, 29, '123214', 'text', 0);

-- Dumping structure for table quanlichungcuv2.survey_response
CREATE TABLE IF NOT EXISTS `survey_response` (
  `id` int NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) DEFAULT NULL,
  `survey_id` int DEFAULT NULL,
  `user_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_survey_response_survey` (`survey_id`),
  KEY `fk_survey_response_user` (`user_id`),
  CONSTRAINT `fk_survey_response_survey` FOREIGN KEY (`survey_id`) REFERENCES `survey` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_survey_response_user` FOREIGN KEY (`user_id`) REFERENCES `user_entity` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table quanlichungcuv2.survey_response: ~0 rows (approximately)

-- Dumping data for table quanlichungcuv2.user_entity: ~4 rows (approximately)
INSERT INTO `user_entity` (`id`, `firstname`, `lastname`, `username`, `password`, `email`, `phone`, `role_name`, `status`, `avatar`, `is_lock`, `is_first_login`, `room_id`) VALUES
	(7, NULL, NULL, 'admin', '$2a$10$tqb5QBvH9RcVZrZPoDNo5uxSs1.7pZjE1WeYCEWvjxFXRh2VpOc6K', NULL, NULL, 'ADMIN', NULL, NULL, 0, b'0', NULL),
	(8, 'Thanh', 'Hà', 'XXXXXXXXXX', '$2a$10$L2bcboEuP6kuWlKMX.eldu7ZBMAsb.c1Jb4623LhA6XMvU9fO.owq', 'curivailoz@gmail.com', 'XXXXXXXXXX', 'USER', NULL, NULL, 0, b'1', 30),
	(12, 'Văn ', 'Phong', '0813041374', '$2a$10$cNFZJBKfeK/nkx60bSzh.OLroqoCyOzIx1DXhUdr1hv.2olsjrooC', 'curivailoz@gmail.com', '0813041374', 'USER', NULL, NULL, 0, b'0', 30),
	(14, NULL, NULL, 'user', '$2a$10$72BQbgQGjKeAcMAEBNCw5eZAa1u9PXO7urlGXi4ukka2hAivwKdFa', NULL, NULL, 'USER', NULL, NULL, 0, b'0', NULL);

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
