-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: gifthorse
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
-- Table structure for table `basketcontents`
--

DROP TABLE IF EXISTS `basketcontents`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `basketcontents` (
  `prodID` varchar(5) NOT NULL,
  `ItemID` int(11) NOT NULL,
  `ItemQty` int(3) NOT NULL,
  PRIMARY KEY (`prodID`,`ItemID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `basketcontents`
--

LOCK TABLES `basketcontents` WRITE;
/*!40000 ALTER TABLE `basketcontents` DISABLE KEYS */;
INSERT INTO `basketcontents` VALUES ('f01',4,1),('f01',11,1),('f01',12,1),('f01',13,1),('f01',14,2),('f01',15,1),('f02',2,1),('f02',11,2),('f02',1000,1),('g01',3,1),('g01',4,1),('g01',2000,1),('g01',2100,1),('g01',2300,1),('g01',2400,1),('g01',2500,1),('g01',2600,1),('g02',2,1),('g02',3,1),('g02',2150,1),('g02',2300,2),('g02',2400,1),('g02',2600,1),('g02',2700,1),('g03',2,1),('g03',1000,1),('g03',2300,2),('g03',2400,2),('g03',2500,2),('m01',2,1),('m01',7,1),('m01',56,6),('m01',57,2),('m01',500,1),('m02',6,1),('m02',70,1),('m02',510,1),('m02',1400,1),('m02',5200,1),('s01',5,1),('s01',8,1),('s01',13,1),('s01',51,1),('s02',9,1),('s02',52,1),('s02',70,1),('s02',1200,1),('s03',9,1),('s03',53,1),('s03',54,1),('s03',55,1),('s03',1300,1),('s04',2,1),('s04',5,1),('s04',13,1),('s04',15,1),('s04',55,1),('s04',60,1),('w01',6,1),('w01',16,1),('w01',1100,1),('w01',5000,2),('w02',6,1),('w02',16,1),('w02',1100,1),('w02',5100,2);
/*!40000 ALTER TABLE `basketcontents` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customers`
--

DROP TABLE IF EXISTS `customers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `customers` (
  `customerID` int(11) NOT NULL,
  `custCity` varchar(45) NOT NULL,
  `custState` varchar(2) NOT NULL,
  `custLName` varchar(45) NOT NULL,
  `custFName` varchar(45) NOT NULL,
  PRIMARY KEY (`customerID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customers`
--

LOCK TABLES `customers` WRITE;
/*!40000 ALTER TABLE `customers` DISABLE KEYS */;
INSERT INTO `customers` VALUES (1,'Eau Claire','WI','Friedl','Luke'),(2,'Englewood','WI','Feigum','Marge'),(3,'Chilton','WI','Manion','Thom'),(4,'Wales','WI','Gee','Alexandra'),(5,'Eau Claire','WI','Johnson','Chaz');
/*!40000 ALTER TABLE `customers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `items`
--

DROP TABLE IF EXISTS `items`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `items` (
  `itemID` int(5) NOT NULL,
  `vendID` int(4) NOT NULL,
  `itemDesc` varchar(45) NOT NULL,
  `itemPrice` decimal(8,2) NOT NULL,
  PRIMARY KEY (`vendID`,`itemID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `items`
--

LOCK TABLES `items` WRITE;
/*!40000 ALTER TABLE `items` DISABLE KEYS */;
INSERT INTO `items` VALUES (51,2,'12-pack chip pack',4.99),(52,2,'5lb bag of Doves chocolates',15.00),(53,2,'1 lb. dark chocolate fudge',10.00),(54,2,'1/4 lb espresso roast coffee',2.00),(55,2,'1/2 lb almonds',3.00),(56,2,'Jiffy Pop',1.00),(57,2,'Brownie Mix',1.20),(60,2,'16 oz. organic yogurt',6.00),(2,3,'Medium wicker basket',4.50),(4,3,'Large wicker basket',6.15),(6,3,'fancy wicker basket',2.00),(8,3,'Plastic basket',2.25),(9,3,'Small wicker basket',3.00),(2000,7,'Dominoes',2.50),(2100,7,'Uno',1.50),(2150,7,'Uno Attack',5.00),(2300,7,'Card deck',0.99),(2400,7,'Poker chip set',3.49),(2600,7,'Pictionary',6.99),(2700,7,'Taboo',4.50),(11,10,'pineapple',1.50),(12,10,'purple grapes',0.60),(13,10,'6 bananas',2.00),(14,10,'mango',1.60),(15,10,'1/2 lb blueberries',4.00),(16,10,'8 oz. wheel colby cheese',8.00),(5000,10,'red wine',12.00),(5100,10,'white wine',11.00),(5200,10,'Blush wine',10.00),(2500,14,'Cribbage board',3.65),(3,16,'Large wicker basket cover',1.50),(5,16,'plastic seal',2.99),(7,16,'Medium Wicker basket cover',1.25),(1000,16,'Medium fabric liner',1.39),(1100,16,'red/blue plaid linen liner',2.50),(1200,16,'purple satin basket liner',3.50),(1300,16,'green satin basket liner',3.50),(1400,16,'fall-colored basket liner',3.00),(70,20,'16 oz. lavender-scented candle',5.00),(500,25,'Music CD: Led Zeppelin - Greatest Hits',5.00),(510,25,'Music CD: George Winston - Autumn',5.00);
/*!40000 ALTER TABLE `items` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orderline`
--

DROP TABLE IF EXISTS `orderline`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `orderline` (
  `OrdID` int(11) NOT NULL,
  `ProdID` varchar(4) NOT NULL,
  `ProdQty` int(5) DEFAULT NULL,
  `UnitPrice` decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (`OrdID`,`ProdID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orderline`
--

LOCK TABLES `orderline` WRITE;
/*!40000 ALTER TABLE `orderline` DISABLE KEYS */;
INSERT INTO `orderline` VALUES (100399,'g03',1,65.00),(100399,'w01',1,46.99),(100400,'s04',1,40.00),(100400,'w01',1,45.99),(100422,'g01',1,75.25),(100422,'w02',1,45.99),(100426,'s04',3,40.00),(100500,'s03',1,39.99),(100500,'s04',2,40.00),(100555,'s03',2,39.99);
/*!40000 ALTER TABLE `orderline` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `orders` (
  `OrdID` int(11) NOT NULL,
  `CustID` int(11) NOT NULL,
  `ordDate` date DEFAULT NULL,
  `paidDate` date DEFAULT NULL,
  `shipDate` date DEFAULT NULL,
  `shipAddr` varchar(100) DEFAULT NULL,
  `shipCity` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`OrdID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT INTO `orders` VALUES (100399,2,'2013-12-06','2013-12-06','2013-12-07','15 East Main st.','Baltimore'),(100400,3,'2013-12-06','2013-12-06','2013-12-07','2000 Ironway Rd.','St. Paul'),(100422,2,'2013-12-11','2013-12-11','2013-12-12','13556 Shady Pine Drive','Platteville'),(100426,4,'2013-02-16','2013-02-16','2013-02-17','20 Winsor Woods Dr','St. Paul'),(100500,1,'2012-04-15','2012-04-15','2012-04-16','1010 Milam St','Houston'),(100555,1,'2017-01-28','2017-01-28',NULL,'151 Rum River Road','Planter');
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `products`
--

DROP TABLE IF EXISTS `products`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `products` (
  `prodID` varchar(5) NOT NULL,
  `prodPrice` double NOT NULL,
  `prodDesc` varchar(500) NOT NULL,
  PRIMARY KEY (`prodID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `products`
--

LOCK TABLES `products` WRITE;
/*!40000 ALTER TABLE `products` DISABLE KEYS */;
INSERT INTO `products` VALUES ('f01',24.75,'Pleasing mixture of five fresh fruits – one whole pineapple, purple grapes, 6 bananas, two mangos, and a half- pound of fresh blueberries in a large wicker basket'),('f02',7.25,'Two fresh, sweet whole pineapples in a medium plastic basket with fabric liner'),('g01',75.25,'Pictionary, Card deck, Poker Chip Set, Dominos, Cribbage Board, and Uno in a large covered wicker basket'),('g02',72.25,'Pictionary, 2 Card decks, Poker Chips, Uno Attack, Taboo in a large covered wicker basket'),('g03',67,'2 Card decks, 2 Poker Chip Sets, and 2 Cribbage Boards in a medium fabric- lined wicker basket'),('m01',39.99,'Music CD: Led Zeppelin –Greatest Hits, 6 bags Jiffy Pop, 2 boxes Gourmet Brownie Mix in a medium covered wicker basket'),('m02',49.99,'Music CD: George Winston – Autumn, one bottle blush wine, 16 oz. lavender-scented candle in a fancy wicker basket lined in fall-colored fabric'),('s01',24.99,'12-pack snack chip variety, 6 bananas in a plastic basket with contents sealed in plastic'),('s02',38.99,'Variety of packaged mini-candies from Dove. 5 lbs, 16 oz. lavender-scented candle in a small wicker basket lined in purple satin'),('s03',39.99,'1 lb. dark chocolate fudge, ¼ lb. espresso roast coffee, ½ lb. almonds in a small wicker basket lined in green satin'),('s04',40,'½ lb almonds, 6 bananas, ½ pound of fresh blueberries, and 16 oz organic yogurt in a medium wicker basket with contents sealed in plastic'),('w01',45.99,'Two bottles of red wine with an 8 oz. wheel of Colby cheese in a fancy wicker basket lined in red/blue plaid linen'),('w02',45.99,'Two bottles of white wine with an 8 oz. wheel of Colby cheese in a fancy wicker basket lined in red/blue plaid linen');
/*!40000 ALTER TABLE `products` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vendors`
--

DROP TABLE IF EXISTS `vendors`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `vendors` (
  `vendID` int(11) NOT NULL,
  `vendCompany` varchar(45) NOT NULL,
  `vendCity` varchar(15) NOT NULL,
  PRIMARY KEY (`vendID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vendors`
--

LOCK TABLES `vendors` WRITE;
/*!40000 ALTER TABLE `vendors` DISABLE KEYS */;
INSERT INTO `vendors` VALUES (2,'Carmen Grocers','Greenville'),(3,'Kateman Basketry','Alma'),(7,'Games Galore LLC','Greenville'),(10,'Fruit Factory','Sarasota'),(14,'Boards N More','Eau Claire'),(16,'LinersCovers and Seals','Eau Claire'),(20,'Yankee Candle','Eau Claire'),(25,'Records \'R Us','Chicago');
/*!40000 ALTER TABLE `vendors` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-10-19 19:46:10
