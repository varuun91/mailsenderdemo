CREATE DATABASE  IF NOT EXISTS `maildb` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `maildb`;
-- MySQL dump 10.13  Distrib 5.7.12, for Win32 (AMD64)
--
-- Host: localhost    Database: maildb
-- ------------------------------------------------------
-- Server version	5.7.28-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `maildetails`
--

DROP TABLE IF EXISTS `maildetails`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `maildetails` (
  `maildtl_id` int(11) NOT NULL,
  `maildtl_toaddr` varchar(1000) DEFAULT NULL,
  `maildtl_ccaddr` varchar(1000) DEFAULT NULL,
  `maildtl_bccaddr` varchar(1000) DEFAULT NULL,
  `maildtl_subject` varchar(500) DEFAULT NULL,
  `maildtl_body` varchar(1000) DEFAULT NULL,
  `maildtl_attachment` varchar(1000) DEFAULT NULL,
  `maildtl_deliverysts` varchar(1) DEFAULT NULL,
  `maildtl_deliverytime` timestamp(6) NULL DEFAULT NULL,
  `maildtl_batchrefid` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`maildtl_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `maildetails`
--

LOCK TABLES `maildetails` WRITE;
/*!40000 ALTER TABLE `maildetails` DISABLE KEYS */;
/*!40000 ALTER TABLE `maildetails` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-01-26 18:23:55
