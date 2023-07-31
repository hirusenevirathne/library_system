CREATE DATABASE  IF NOT EXISTS `library_system` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `library_system`;
-- MySQL dump 10.13  Distrib 8.0.31, for Win64 (x86_64)
--
-- Host: localhost    Database: library_system
-- ------------------------------------------------------
-- Server version	8.0.31

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `admin`
--

DROP TABLE IF EXISTS `admin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `admin` (
  `admin_ID` int NOT NULL AUTO_INCREMENT,
  `username` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `F_name` varchar(45) NOT NULL,
  `L_name` varchar(45) NOT NULL,
  `role` varchar(45) NOT NULL,
  PRIMARY KEY (`admin_ID`),
  UNIQUE KEY `username_UNIQUE` (`username`),
  UNIQUE KEY `admin_ID_UNIQUE` (`admin_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admin`
--

LOCK TABLES `admin` WRITE;
/*!40000 ALTER TABLE `admin` DISABLE KEYS */;
INSERT INTO `admin` VALUES (1,'admin','admin','hiru','wolf','Admin'),(2,'saman','saman321','saman','kumara','librarian'),(3,'nisha','nisha123','nisha','kumari','librarian'),(4,'pawan','pawan123','pawan','nissanka','librarian');
/*!40000 ALTER TABLE `admin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `author`
--

DROP TABLE IF EXISTS `author`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `author` (
  `Author_id` int NOT NULL,
  `A_F_name` varchar(45) NOT NULL,
  `A_L_name` varchar(45) NOT NULL,
  PRIMARY KEY (`Author_id`),
  UNIQUE KEY `Author_id_UNIQUE` (`Author_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `author`
--

LOCK TABLES `author` WRITE;
/*!40000 ALTER TABLE `author` DISABLE KEYS */;
INSERT INTO `author` VALUES (1,'sunil','jagath'),(2,'hemantha ','weerasinhe'),(3,'jagath','manolath'),(4,'Priyani','samararathne'),(5,'Saman','Edirimuni');
/*!40000 ALTER TABLE `author` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `books`
--

DROP TABLE IF EXISTS `books`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `books` (
  `Author_ID` int NOT NULL,
  `B_name` varchar(100) NOT NULL,
  `publish_year` int NOT NULL,
  `Other_details` varchar(200) DEFAULT NULL,
  `state` varchar(15) NOT NULL DEFAULT 'AVALIABLE',
  `book_ID` int NOT NULL,
  PRIMARY KEY (`book_ID`),
  KEY `Author_ID_idx` (`Author_ID`),
  CONSTRAINT `Author_ID` FOREIGN KEY (`Author_ID`) REFERENCES `author` (`Author_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `books`
--

LOCK TABLES `books` WRITE;
/*!40000 ALTER TABLE `books` DISABLE KEYS */;
INSERT INTO `books` VALUES (2,'nisha raya',2000,NULL,'NOT_AVAILABLE',1),(1,'Mine',1997,'jifllsabfe','NOT_AVAILABLE',2),(4,'new tech',2020,'good','NOT_AVAILABLE',3),(2,'it',2015,NULL,'AVAILABLE',4),(1,'maths',2000,NULL,'AVAILABLE',5),(2,'phy lab',2005,'djihf','AVAILABLE',6),(1,'maxi',2004,'kdkkkd','AVAILABLE',7),(4,'humen',1995,NULL,'AVAILABLE',8),(4,'sun',1997,NULL,'AVAILABLE',9),(3,'venus',1997,'','NOT_AVAILABLE',10),(2,'my mind',2022,NULL,'NOT_AVAILABLE',11),(3,'IT',2012,NULL,'AVAILABLE',12),(2,'IT with management',2018,'good','AVAILABLE',13),(1,'IT and Maths',2020,NULL,'AVAILABLE',14),(3,'Gaming',2021,'Gamin in Free Time','NOT_AVAILABLE',15),(4,'Life',2020,'','NOT_AVAILABLE',16),(2,'suger',2002,'','AVAILABLE',17),(1,'tree',2012,'nature','NOT_AVAILABLE',18),(5,'1st inning',2000,NULL,'AVAILABLE',19),(5,'2nd inning',2010,NULL,'AVALIABLE',20),(5,'3rd Inning',2020,NULL,'AVALIABLE',21);
/*!40000 ALTER TABLE `books` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `lending`
--

DROP TABLE IF EXISTS `lending`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `lending` (
  `Mem_ID` int NOT NULL,
  `lend_date` date NOT NULL,
  `resubmit_date` date NOT NULL,
  `book_ID` int NOT NULL,
  `Lend_No` int NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`lend_date`,`book_ID`),
  UNIQUE KEY `Lend_No_UNIQUE` (`Lend_No`),
  KEY `Mem_ID` (`Mem_ID`),
  KEY `lending_ibfk_3_idx` (`book_ID`),
  CONSTRAINT `lending_ibfk_2` FOREIGN KEY (`Mem_ID`) REFERENCES `members` (`Mem_ID`),
  CONSTRAINT `lending_ibfk_3` FOREIGN KEY (`book_ID`) REFERENCES `books` (`book_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lending`
--

LOCK TABLES `lending` WRITE;
/*!40000 ALTER TABLE `lending` DISABLE KEYS */;
INSERT INTO `lending` VALUES (2,'2022-04-12','2022-04-26',1,1),(2,'2022-04-12','2022-04-26',3,2),(3,'2022-05-01','2022-05-15',2,3),(1,'2022-05-10','2022-05-24',9,4),(1,'2022-05-10','2022-05-24',10,5),(1,'2022-05-10','2022-05-24',11,6),(1,'2022-10-01','2022-10-22',2,7),(3,'2022-10-01','2022-10-15',4,8),(6,'2022-10-08','2022-10-22',15,9),(6,'2022-10-08','2022-10-22',16,10),(4,'2022-10-20','2022-11-06',17,11),(4,'2022-10-20','2022-11-06',18,12),(6,'2022-10-26','2022-10-31',16,13);
/*!40000 ALTER TABLE `lending` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `members`
--

DROP TABLE IF EXISTS `members`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `members` (
  `Mem_ID` int NOT NULL,
  `F_name` varchar(45) NOT NULL,
  `L_name` varchar(45) NOT NULL,
  `Contact_no` varchar(45) NOT NULL,
  PRIMARY KEY (`Mem_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `members`
--

LOCK TABLES `members` WRITE;
/*!40000 ALTER TABLE `members` DISABLE KEYS */;
INSERT INTO `members` VALUES (1,'Hiru','Senevirathne','0774586952'),(2,'mihiran','saman','0784565983'),(3,'kamal','subasinhe','0784563256'),(4,'nimal','mohan','0704536549'),(5,'gihan','mahinda','0775468795'),(6,'shehan','mihiranga','0758946723'),(7,'kumara','wijerathne','0785469954'),(9,'gihan ','samararathne','0704562153'),(10,'malka','nimali','0714569955');
/*!40000 ALTER TABLE `members` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `resubmit`
--

DROP TABLE IF EXISTS `resubmit`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `resubmit` (
  `Mem_ID` int NOT NULL,
  `Submit_date` date NOT NULL,
  `book_ID` int NOT NULL,
  `Resubmit_No` int NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`Submit_date`,`book_ID`),
  UNIQUE KEY `Resubmit_No_UNIQUE` (`Resubmit_No`),
  KEY `lending_ibfk_4_idx` (`book_ID`),
  KEY `lending_ibfk_5_idx` (`Mem_ID`),
  CONSTRAINT `lending_ibfk_4` FOREIGN KEY (`book_ID`) REFERENCES `books` (`book_ID`),
  CONSTRAINT `lending_ibfk_5` FOREIGN KEY (`Mem_ID`) REFERENCES `members` (`Mem_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `resubmit`
--

LOCK TABLES `resubmit` WRITE;
/*!40000 ALTER TABLE `resubmit` DISABLE KEYS */;
INSERT INTO `resubmit` VALUES (1,'2022-05-20',9,1),(3,'2022-10-10',4,2),(6,'2022-10-25',16,4),(4,'2022-11-05',17,3);
/*!40000 ALTER TABLE `resubmit` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'library_system'
--

--
-- Dumping routines for database 'library_system'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-10-29 11:17:52
