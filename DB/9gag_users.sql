-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: 9gag
-- ------------------------------------------------------
-- Server version	5.7.17-log

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
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `email` varchar(45) NOT NULL,
  `nsfw` tinyint(4) NOT NULL,
  `profile_pic` varchar(500) NOT NULL,
  `gender` varchar(45) DEFAULT NULL,
  `birthday` date DEFAULT NULL,
  `description` varchar(300) DEFAULT NULL,
  `admin` tinyint(4) NOT NULL,
  `is_verified` tinyint(4) NOT NULL,
  `verification_key` varchar(45) NOT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `user_id_UNIQUE` (`user_id`),
  UNIQUE KEY `username_UNIQUE` (`username`),
  UNIQUE KEY `email_UNIQUE` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'gg','123','gabii2@abv.bg',1,'wwww','female','1994-12-01','dsfsdf',0,1,'123'),(2,'jess','123','jevgenieva@gmail.com',0,'ddsf','female','1991-12-02','jkhjkh',0,1,'2589'),(3,'gabi','123','gabriella.apostolova@gmail.com',0,'..\\WebContent\\siteImages\\defaultProfilePic.jpg','Unspecified','1916-01-01','My funny collection',0,1,'6c3cdc4f-41e7-4f08-a09d-db33718eb1b7'),(4,'sfdsfs','123','zixin_@abv.bg',0,'..\\WebContent\\siteImages\\defaultProfilePic.jpg','Unspecified','1916-01-01','My funny collection',0,1,'21ce2c65-191b-4a4e-8d40-15de31033a24'),(5,'gabrislava','123','ittalents.gj@gmail.com',0,'..\\WebContent\\siteImages\\defaultProfilePic.jpg','Unspecified','1916-01-01','My funny collection',0,1,'63b59d6c-4a88-4584-953c-e6fdf05139fa');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-04-12 16:01:16
