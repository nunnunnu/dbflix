-- MySQL dump 10.13  Distrib 8.0.33, for macos13 (x86_64)
--
-- Host: localhost    Database: dbflix
-- ------------------------------------------------------
-- Server version	8.0.33

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `award_info`
--

DROP TABLE IF EXISTS `award_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `award_info` (
  `ai_seq` bigint NOT NULL AUTO_INCREMENT,
  `ai_name` varchar(255) DEFAULT NULL,
  `ai_year` int DEFAULT NULL,
  `ai_cate` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ai_seq`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `award_info`
--

LOCK TABLES `award_info` WRITE;
/*!40000 ALTER TABLE `award_info` DISABLE KEYS */;
INSERT INTO `award_info` VALUES (1,'최우수상',2023,'배우'),(2,'올해의영화',2023,'영화');
/*!40000 ALTER TABLE `award_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `company_info`
--

DROP TABLE IF EXISTS `company_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `company_info` (
  `com_seq` bigint NOT NULL AUTO_INCREMENT,
  `com_business_num` varchar(20) DEFAULT NULL,
  `com_name` varchar(255) DEFAULT NULL,
  `com_adress` text,
  PRIMARY KEY (`com_seq`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `company_info`
--

LOCK TABLES `company_info` WRITE;
/*!40000 ALTER TABLE `company_info` DISABLE KEYS */;
INSERT INTO `company_info` VALUES (1,'222-22-22222','제작사1','서울특별시 언주로'),(2,'222-11-11111','회사이름2','서울특별시');
/*!40000 ALTER TABLE `company_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `creator_award_connect`
--

DROP TABLE IF EXISTS `creator_award_connect`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `creator_award_connect` (
  `cac_seq` bigint NOT NULL AUTO_INCREMENT,
  `cac_ci_seq` bigint NOT NULL,
  `cac_ai_seq` bigint NOT NULL,
  PRIMARY KEY (`cac_seq`),
  KEY `cac_ci_seq` (`cac_ci_seq`),
  KEY `cac_ai_seq` (`cac_ai_seq`),
  CONSTRAINT `creator_award_connect_ibfk_1` FOREIGN KEY (`cac_ci_seq`) REFERENCES `creator_info` (`ci_seq`),
  CONSTRAINT `creator_award_connect_ibfk_2` FOREIGN KEY (`cac_ai_seq`) REFERENCES `award_info` (`ai_seq`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `creator_award_connect`
--

LOCK TABLES `creator_award_connect` WRITE;
/*!40000 ALTER TABLE `creator_award_connect` DISABLE KEYS */;
INSERT INTO `creator_award_connect` VALUES (1,1,1),(2,4,1);
/*!40000 ALTER TABLE `creator_award_connect` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `creator_info`
--

DROP TABLE IF EXISTS `creator_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `creator_info` (
  `ci_seq` bigint NOT NULL AUTO_INCREMENT,
  `ci_contry` varchar(255) NOT NULL,
  `ci_age` int NOT NULL,
  `ci_role` varchar(10) NOT NULL,
  `ci_gen` varchar(10) NOT NULL,
  `ci_name` varchar(255) NOT NULL,
  PRIMARY KEY (`ci_seq`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `creator_info`
--

LOCK TABLES `creator_info` WRITE;
/*!40000 ALTER TABLE `creator_info` DISABLE KEYS */;
INSERT INTO `creator_info` VALUES (1,'한국',19,'배우','여','변경'),(2,'한국',40,'감독','남','박감독'),(4,'한국',20,'배우','남','배우2'),(5,'한국',20,'배우','남','이름');
/*!40000 ALTER TABLE `creator_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `creator_movie_connection`
--

DROP TABLE IF EXISTS `creator_movie_connection`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `creator_movie_connection` (
  `cmc_seq` bigint NOT NULL AUTO_INCREMENT,
  `cmc_ci_seq` bigint NOT NULL,
  `cmc_mi_seq` bigint NOT NULL,
  `cmc_role` varchar(10) NOT NULL,
  PRIMARY KEY (`cmc_seq`),
  KEY `cmc_ci_seq` (`cmc_ci_seq`),
  KEY `cmc_mi_seq` (`cmc_mi_seq`),
  CONSTRAINT `creator_movie_connection_ibfk_1` FOREIGN KEY (`cmc_ci_seq`) REFERENCES `creator_info` (`ci_seq`),
  CONSTRAINT `creator_movie_connection_ibfk_2` FOREIGN KEY (`cmc_mi_seq`) REFERENCES `movie_info` (`mi_seq`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `creator_movie_connection`
--

LOCK TABLES `creator_movie_connection` WRITE;
/*!40000 ALTER TABLE `creator_movie_connection` DISABLE KEYS */;
INSERT INTO `creator_movie_connection` VALUES (1,1,1,'주연'),(2,2,1,'감독'),(4,2,2,'감독'),(7,4,2,'주연');
/*!40000 ALTER TABLE `creator_movie_connection` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `movie_award_connection`
--

DROP TABLE IF EXISTS `movie_award_connection`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `movie_award_connection` (
  `mac_seq` bigint NOT NULL AUTO_INCREMENT,
  `mac_mi_seq` bigint NOT NULL,
  `mac_ai_seq` bigint NOT NULL,
  PRIMARY KEY (`mac_seq`),
  KEY `mac_ai_seq` (`mac_ai_seq`),
  KEY `mac_mi_seq` (`mac_mi_seq`),
  CONSTRAINT `movie_award_connection_ibfk_1` FOREIGN KEY (`mac_ai_seq`) REFERENCES `award_info` (`ai_seq`),
  CONSTRAINT `movie_award_connection_ibfk_2` FOREIGN KEY (`mac_mi_seq`) REFERENCES `movie_info` (`mi_seq`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `movie_award_connection`
--

LOCK TABLES `movie_award_connection` WRITE;
/*!40000 ALTER TABLE `movie_award_connection` DISABLE KEYS */;
INSERT INTO `movie_award_connection` VALUES (1,1,2);
/*!40000 ALTER TABLE `movie_award_connection` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `movie_info`
--

DROP TABLE IF EXISTS `movie_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `movie_info` (
  `mi_seq` bigint NOT NULL AUTO_INCREMENT,
  `mi_attendance` int NOT NULL,
  `mi_reg_dt` date NOT NULL,
  `mi_title` text NOT NULL,
  `mi_price` int NOT NULL,
  `mi_contry` varchar(255) NOT NULL,
  `mi_genre` varchar(50) NOT NULL,
  `mi_com_seq` bigint NOT NULL,
  PRIMARY KEY (`mi_seq`),
  KEY `mi_com_seq` (`mi_com_seq`),
  CONSTRAINT `movie_info_ibfk_1` FOREIGN KEY (`mi_com_seq`) REFERENCES `company_info` (`com_seq`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `movie_info`
--

LOCK TABLES `movie_info` WRITE;
/*!40000 ALTER TABLE `movie_info` DISABLE KEYS */;
INSERT INTO `movie_info` VALUES (1,5300000,'2023-04-25','이름변경',50000000,'한국','로맨스',1),(2,8000000,'2023-04-26','영화2',100000000,'한국','스릴러',2),(7,100000,'2023-04-01','영화이름3',20000000,'한국','액션',1),(8,5000000,'2023-04-01','영화이름4',10000000,'한국','범죄',2),(9,3200000,'2023-04-01','영화이름5',80000000,'한국','스릴러',1);
/*!40000 ALTER TABLE `movie_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `review_info`
--

DROP TABLE IF EXISTS `review_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `review_info` (
  `ri_seq` bigint NOT NULL AUTO_INCREMENT,
  `ri_created` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `ri_coment` text NOT NULL,
  `ri_rate` int NOT NULL,
  `ri_ui_seq` bigint NOT NULL,
  `ri_mi_seq` bigint NOT NULL,
  PRIMARY KEY (`ri_seq`),
  KEY `ri_ui_seq` (`ri_ui_seq`),
  KEY `ri_mi_seq` (`ri_mi_seq`),
  CONSTRAINT `review_info_ibfk_1` FOREIGN KEY (`ri_ui_seq`) REFERENCES `user_info` (`ui_seq`),
  CONSTRAINT `review_info_ibfk_2` FOREIGN KEY (`ri_mi_seq`) REFERENCES `movie_info` (`mi_seq`),
  CONSTRAINT `review_info_ibfk_3` FOREIGN KEY (`ri_mi_seq`) REFERENCES `movie_info` (`mi_seq`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `review_info`
--

LOCK TABLES `review_info` WRITE;
/*!40000 ALTER TABLE `review_info` DISABLE KEYS */;
INSERT INTO `review_info` VALUES (1,'2023-04-25 11:55:46','재밌어요',10,1,1),(5,'2023-04-26 14:54:24','그저그래요',5,2,1),(6,'2023-04-27 09:38:01','리뷰3',4,2,2),(7,'2023-04-27 09:38:01','리뷰4',7,2,7),(8,'2023-04-27 09:38:01','리뷰5',8,2,8),(9,'2023-04-27 09:38:01','리뷰6',9,2,9);
/*!40000 ALTER TABLE `review_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_info`
--

DROP TABLE IF EXISTS `user_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_info` (
  `ui_seq` bigint NOT NULL AUTO_INCREMENT,
  `ui_id` varchar(255) NOT NULL,
  `ui_name` varchar(255) NOT NULL,
  `ui_email` varchar(255) NOT NULL,
  `ui_file` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci,
  `ui_uri` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci,
  `ui_birth` date DEFAULT NULL,
  `ui_gen` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '선택안함',
  `ui_status` int DEFAULT '1',
  `ui_pwd` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`ui_seq`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_info`
--

LOCK TABLES `user_info` WRITE;
/*!40000 ALTER TABLE `user_info` DISABLE KEYS */;
INSERT INTO `user_info` VALUES (1,'user001','김회원1','user01@email.com','profile_1682409862912.png','스크린샷 2023-04-25 오후 5.03.54.png','2000-01-01','남',0,'123456'),(2,'user002','회원2','user02@emaiil.com','profile_1682559089399.png','스크린샷 2023-04-26 오후 4.03.48.png',NULL,'선택안함',1,'111111'),(3,'user003','회원3','user003@email.com',NULL,NULL,NULL,'선택안함',0,'123456'),(4,'user004','회원4','user004@email.com',NULL,NULL,NULL,'선택안함',1,'123456'),(14,'user005','회원5','user005@email.com','profile_1682406296754.png','스크린샷 2023-04-25 오후 3.42.51.png','1999-01-01','남',1,'123456'),(15,'user006','사용자6','user006@email.com',NULL,NULL,NULL,'선택안함',1,'123456');
/*!40000 ALTER TABLE `user_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'dbflix'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-04-27 11:35:17
