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
-- Table structure for table `gags`
--

DROP TABLE IF EXISTS `gags`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `gags` (
  `gag_id` int(11) NOT NULL AUTO_INCREMENT,
  `content` varchar(45) NOT NULL,
  `nsfw` tinyint(4) NOT NULL,
  `title` varchar(45) NOT NULL,
  `points` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `public` tinyint(4) NOT NULL,
  `type` varchar(45) NOT NULL,
  PRIMARY KEY (`gag_id`),
  UNIQUE KEY `gag_id_UNIQUE` (`gag_id`),
  UNIQUE KEY `content_UNIQUE` (`content`),
  KEY `fk_gags_users1_idx` (`user_id`),
  CONSTRAINT `fk_gags_users1` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `gags`
--

LOCK TABLES `gags` WRITE;
/*!40000 ALTER TABLE `gags` DISABLE KEYS */;
INSERT INTO `gags` VALUES (1,'Cats',0,'Cats',6,1,1,'jpg'),(2,'Dogs',0,'Dogs',5,1,0,'gif'),(3,'Frog',1,'Frogs',150,5,1,'jpg'),(4,'Monkey',0,'Monkeys',200,5,1,'gif'),(5,'Some gag',1,'Da IMA',0,4,1,'gpg'),(6,'SEGA',1,'Dano da stane',0,4,1,'gpg'),(7,'HoT',1,'Chilli',3000,4,1,'gpg'),(8,'TRENDING',1,'so Fine',600,4,1,'gpg'),(9,'hrana',1,'Food',600,5,1,'jpg'),(10,'smeshnichko',1,'GHA hA',600,5,1,'jpg');
/*!40000 ALTER TABLE `gags` ENABLE KEYS */;
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
