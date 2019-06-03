-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: games
-- ------------------------------------------------------
-- Server version	5.7.19-log

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
-- Temporary view structure for view `gamesbyvendor`
--

DROP TABLE IF EXISTS `gamesbyvendor`;
/*!50001 DROP VIEW IF EXISTS `gamesbyvendor`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `gamesbyvendor` AS SELECT 
 1 AS `gameTitle`,
 1 AS `retailName`*/;
SET character_set_client = @saved_cs_client;

--
-- Table structure for table `gameslist`
--

DROP TABLE IF EXISTS `gameslist`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `gameslist` (
  `gameTitle` varchar(50) NOT NULL,
  `developer` varchar(45) NOT NULL,
  `rating` varchar(5) NOT NULL,
  `releaseYear` year(4) NOT NULL,
  PRIMARY KEY (`gameTitle`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `gameslist`
--

LOCK TABLES `gameslist` WRITE;
/*!40000 ALTER TABLE `gameslist` DISABLE KEYS */;
INSERT INTO `gameslist` VALUES ('Age of Mythology','Ensemble','T',2002),('Divinity: Original Sin','Larian','M',2014),('Dragon Age: Origins','BioWare','M',2009),('Fable','Lionhead','M',2004),('Fallout 3','Bethesda','M',2008),('Fallout 4','Bethesda','M',2016),('GTA San Andreas','Rockstar','M',2004),('GTA V','Rockstar','M',2013),('Halo Wars','Bungie','T',2009),('Legend of Zelda: Ocarina of Time','Nintendo','E',1998),('Mass Effect','BioWare','M',2007),('Minecraft','4J Studios','E10',2009),('Morrowind','Bethesda','M',2002),('Oblivion','Bethesda','M',2006),('Saints Row IV','Deep Silver','M',2013),('Sims','Maxis','T',2000),('Sims 2','Maxis','T',2004),('Skyrim','Bethesda','M',2011),('Sly Cooper and the Thievious Racoonus','Sucker Punch','E',2002),('World of Warcraft','Blizzard','T',2004);
/*!40000 ALTER TABLE `gameslist` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `retailers`
--

DROP TABLE IF EXISTS `retailers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `retailers` (
  `retailName` varchar(50) NOT NULL,
  `website` varchar(45) NOT NULL,
  PRIMARY KEY (`retailName`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `retailers`
--

LOCK TABLES `retailers` WRITE;
/*!40000 ALTER TABLE `retailers` DISABLE KEYS */;
INSERT INTO `retailers` VALUES ('Amazon','www.amazon.com'),('BestBuy','www.bestbuy.com'),('GameStop','www.gamestop.com'),('Target','www.target.com'),('Walmart','www.walmart.com');
/*!40000 ALTER TABLE `retailers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Final view structure for view `gamesbyvendor`
--

/*!50001 DROP VIEW IF EXISTS `gamesbyvendor`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `gamesbyvendor` AS select `gameslist`.`gameTitle` AS `gameTitle`,`retailers`.`retailName` AS `retailName` from (`gameslist` join `retailers`) limit 50 */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-03-16 10:37:54
