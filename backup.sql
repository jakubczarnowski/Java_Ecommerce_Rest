-- MySQL dump 10.13  Distrib 8.0.30, for Linux (aarch64)
--
-- Host: localhost    Database: ecommerce
-- ------------------------------------------------------
-- Server version	8.0.30

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
-- Table structure for table `cart`
--

DROP TABLE IF EXISTS `cart`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cart` (
  `id` int NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) NOT NULL,
  `modified_at` datetime(6) DEFAULT NULL,
  `active` bit(1) DEFAULT NULL,
  `quantity` int NOT NULL,
  `product_id` int DEFAULT NULL,
  `user_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKpu4bcbluhsxagirmbdn7dilm5` (`product_id`),
  KEY `FKg5uhi8vpsuy0lgloxk2h4w5o6` (`user_id`),
  CONSTRAINT `FKg5uhi8vpsuy0lgloxk2h4w5o6` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
  CONSTRAINT `FKpu4bcbluhsxagirmbdn7dilm5` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cart`
--

LOCK TABLES `cart` WRITE;
/*!40000 ALTER TABLE `cart` DISABLE KEYS */;
INSERT INTO `cart` VALUES (1,'2022-10-02 22:19:35.374585','2022-10-02 23:00:43.068412',_binary '\0',3,1,1),(2,'2022-10-02 23:08:46.623022','2022-10-02 23:08:46.710255',_binary '\0',2,2,1),(3,'2022-10-03 08:29:30.128247','2022-10-03 08:29:30.261313',_binary '\0',2,1,1),(4,'2022-10-03 08:33:59.378600','2022-10-03 08:33:59.378605',_binary '\0',1,1,1),(5,'2022-10-03 08:40:28.154895','2022-10-03 08:40:28.402948',_binary '\0',2,1,1),(6,'2022-10-03 09:46:23.318982','2022-10-03 09:46:23.318998',_binary '\0',1,1,1),(7,'2022-10-03 09:46:23.319218','2022-10-03 09:46:23.453312',_binary '\0',2,1,1),(8,'2022-10-03 09:49:18.720559','2022-10-03 09:49:18.720571',_binary '\0',1,1,1),(9,'2022-10-03 09:51:11.542044','2022-10-03 09:51:11.765127',_binary '\0',2,1,1),(10,'2022-10-03 10:06:16.939979','2022-10-03 10:06:17.827223',_binary '\0',3,1,1),(11,'2022-10-03 10:44:34.167505','2022-10-03 10:44:36.779822',_binary '\0',4,1,1),(12,'2022-10-03 12:14:17.599294','2022-10-03 12:14:17.879340',_binary '\0',3,1,1),(13,'2022-10-03 12:20:14.360195','2022-10-03 12:20:15.867113',_binary '\0',2,1,1),(14,'2022-10-03 12:25:20.293221','2022-10-03 12:25:20.686781',_binary '\0',2,1,1),(15,'2022-10-03 12:42:55.735525','2022-10-03 12:42:55.735530',_binary '\0',1,4,1),(16,'2022-10-03 12:44:56.915046','2022-10-03 12:45:03.778713',_binary '\0',3,1,1),(17,'2022-10-03 12:57:09.997735','2022-10-03 12:57:10.213666',_binary '\0',2,1,1),(18,'2022-10-03 13:28:57.456625','2022-10-03 13:28:58.472106',_binary '\0',3,1,1),(19,'2022-10-03 13:37:38.585392','2022-10-03 13:37:38.866563',_binary '\0',2,1,1),(20,'2022-10-03 13:43:09.259427','2022-10-03 13:43:09.625038',_binary '\0',3,1,1);
/*!40000 ALTER TABLE `cart` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `categories`
--

DROP TABLE IF EXISTS `categories`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `categories` (
  `id` int NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) NOT NULL,
  `modified_at` datetime(6) DEFAULT NULL,
  `category_name` varchar(255) NOT NULL,
  `description` varchar(255) NOT NULL,
  `parent_category_id` int NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_41g4n0emuvcm3qyf1f6cn43c0` (`category_name`)
) ENGINE=InnoDB AUTO_INCREMENT=59 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categories`
--

LOCK TABLES `categories` WRITE;
/*!40000 ALTER TABLE `categories` DISABLE KEYS */;
INSERT INTO `categories` VALUES (1,'2022-10-02 20:04:07.077581','2022-10-02 20:04:07.077596','root','rootCategory',1),(25,'2022-10-02 22:35:31.801252','2022-10-02 22:35:31.801256','Elektronika','Elektronika',1),(26,'2022-10-02 22:35:37.555595','2022-10-02 22:35:37.555598','Uroda','Uroda',1),(27,'2022-10-02 22:35:47.799881','2022-10-02 22:35:47.799885','Supermarket','Supermaker',1),(28,'2022-10-02 22:36:00.061245','2022-10-02 22:36:00.061249','Sport i Turystyka','Sporty',1),(29,'2022-10-02 22:36:13.892733','2022-10-02 22:36:13.892737','Telewizory','Telewizory',25),(31,'2022-10-02 22:37:13.741822','2022-10-02 22:37:13.741836','Telefony','Telefony',25),(32,'2022-10-02 22:37:19.516876','2022-10-02 22:37:19.516881','Komputery','Komputery',25),(33,'2022-10-02 22:37:28.291178','2022-10-02 22:37:28.291182','Laptopy','Laptopy',25),(34,'2022-10-02 22:37:58.491623','2022-10-02 22:37:58.491628','Akcesoria Komputerowe','Akcesoria Komputerowe',32),(35,'2022-10-02 22:38:06.436464','2022-10-02 22:38:06.436469','Komponenty','Komponenty',32),(36,'2022-10-02 22:38:11.841262','2022-10-02 22:38:11.841266','Monitory','Monitory',32),(37,'2022-10-02 22:38:23.198673','2022-10-02 22:38:23.198677','Komputery Stacjonarne','Komputery Stacjonarne',32),(38,'2022-10-02 22:38:39.863013','2022-10-02 22:38:39.863018','Samsung','Samsung',31),(39,'2022-10-02 22:38:45.449583','2022-10-02 22:38:45.449588','Apple','Apple',31),(40,'2022-10-02 22:38:51.156242','2022-10-02 22:38:51.156246','Xiaomi','Xiaomi',31),(41,'2022-10-02 22:40:03.848101','2022-10-02 22:40:03.848105','Szampony','Szampony',26),(42,'2022-10-02 22:40:42.140313','2022-10-02 22:40:42.140317','Perfumy','Perfumy',26),(43,'2022-10-02 22:41:05.474917','2022-10-02 22:41:05.474922','Kosmetyki','Kosmetyki',26),(44,'2022-10-02 22:41:36.675309','2022-10-02 22:41:36.675313','Urzadzenia Namierzajace','Urzadzenia Namierzajace',28),(45,'2022-10-02 22:41:43.231637','2022-10-02 22:41:43.231642','Piłki','Piłki',28),(46,'2022-10-02 22:42:08.391169','2022-10-02 22:42:08.391173','Narzędzia treningowe','Narzędzia treningowe',28),(47,'2022-10-02 22:44:26.907683','2022-10-02 22:44:26.907687','Konsole do Gier','Konsole',25);
/*!40000 ALTER TABLE `categories` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `categories_category_children`
--

DROP TABLE IF EXISTS `categories_category_children`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `categories_category_children` (
  `category_id` int NOT NULL,
  `category_children_id` int NOT NULL,
  PRIMARY KEY (`category_id`,`category_children_id`),
  UNIQUE KEY `UK_ci49e4pl48gbdf30upfi6r0ih` (`category_children_id`),
  CONSTRAINT `FKehly2pw1e2kodlkyq9b6xafhg` FOREIGN KEY (`category_id`) REFERENCES `categories` (`id`),
  CONSTRAINT `FKffm8wxfnaqq7vwfl4600uvhf7` FOREIGN KEY (`category_children_id`) REFERENCES `categories` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categories_category_children`
--

LOCK TABLES `categories_category_children` WRITE;
/*!40000 ALTER TABLE `categories_category_children` DISABLE KEYS */;
INSERT INTO `categories_category_children` VALUES (1,25),(1,26),(1,27),(1,28),(25,29),(25,31),(25,32),(25,33),(32,34),(32,35),(32,36),(32,37),(31,38),(31,39),(31,40),(26,41),(26,42),(26,43),(28,44),(28,45),(28,46),(25,47);
/*!40000 ALTER TABLE `categories_category_children` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `delivery_address`
--

DROP TABLE IF EXISTS `delivery_address`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `delivery_address` (
  `id` int NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) NOT NULL,
  `modified_at` datetime(6) DEFAULT NULL,
  `active` bit(1) DEFAULT NULL,
  `city` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `phone_number` varchar(255) NOT NULL,
  `street_line` varchar(255) NOT NULL,
  `zip_code` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `delivery_address`
--

LOCK TABLES `delivery_address` WRITE;
/*!40000 ALTER TABLE `delivery_address` DISABLE KEYS */;
INSERT INTO `delivery_address` VALUES (1,'2022-10-02 23:02:21.965238','2022-10-02 23:02:21.965256',_binary '','Słupsk','Pomorska ','798432888','7, 2','76-200');
/*!40000 ALTER TABLE `delivery_address` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_items`
--

DROP TABLE IF EXISTS `order_items`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `order_items` (
  `order_id` int NOT NULL,
  `cart_item_id` int NOT NULL,
  PRIMARY KEY (`order_id`,`cart_item_id`),
  KEY `FKf5h7k0bmrfkbxjws8nsm7ge29` (`cart_item_id`),
  CONSTRAINT `FKbioxgbv59vetrxe0ejfubep1w` FOREIGN KEY (`order_id`) REFERENCES `orders` (`id`),
  CONSTRAINT `FKf5h7k0bmrfkbxjws8nsm7ge29` FOREIGN KEY (`cart_item_id`) REFERENCES `cart` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_items`
--

LOCK TABLES `order_items` WRITE;
/*!40000 ALTER TABLE `order_items` DISABLE KEYS */;
INSERT INTO `order_items` VALUES (1,1),(2,1),(3,2),(4,3),(5,4),(6,5),(7,6),(7,7),(8,8),(9,9),(10,10),(11,11),(12,12),(13,13),(14,14),(15,15),(16,16),(17,17),(18,18),(19,19),(20,20);
/*!40000 ALTER TABLE `order_items` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orders` (
  `id` int NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) NOT NULL,
  `modified_at` datetime(6) DEFAULT NULL,
  `active` bit(1) DEFAULT NULL,
  `more_info` varchar(255) DEFAULT NULL,
  `payment_status` int NOT NULL,
  `total_cost` double NOT NULL,
  `delivery_id` int NOT NULL,
  `user_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK5kkq7c4x46ta1gls6d7u0xe0k` (`delivery_id`),
  KEY `FK32ql8ubntj5uh44ph9659tiih` (`user_id`),
  CONSTRAINT `FK32ql8ubntj5uh44ph9659tiih` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
  CONSTRAINT `FK5kkq7c4x46ta1gls6d7u0xe0k` FOREIGN KEY (`delivery_id`) REFERENCES `delivery_address` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT INTO `orders` VALUES (1,'2022-10-02 23:02:24.067250','2022-10-02 23:02:24.067254',_binary '','',0,1889.97,1,1),(2,'2022-10-02 23:02:24.071350','2022-10-02 23:02:24.071353',_binary '','',0,1889.97,1,1),(3,'2022-10-02 23:08:50.429920','2022-10-02 23:08:50.429924',_binary '','',0,1199.98,1,1),(4,'2022-10-03 08:29:34.556974','2022-10-03 08:29:34.556978',_binary '','',0,1259.98,1,1),(5,'2022-10-03 08:34:06.313259','2022-10-03 08:34:06.313262',_binary '','',0,629.99,1,1),(6,'2022-10-03 08:40:32.881554','2022-10-03 08:40:32.881560',_binary '','',0,1259.98,1,1),(7,'2022-10-03 09:46:27.492320','2022-10-03 09:46:27.492325',_binary '','',0,1889.97,1,1),(8,'2022-10-03 09:49:24.754218','2022-10-03 09:49:24.754221',_binary '','',0,629.99,1,1),(9,'2022-10-03 09:51:15.629654','2022-10-03 09:51:15.629658',_binary '','',0,1259.98,1,1),(10,'2022-10-03 10:06:25.552157','2022-10-03 10:06:25.552161',_binary '','',0,1889.97,1,1),(11,'2022-10-03 10:44:40.637756','2022-10-03 10:44:40.637761',_binary '','',0,2519.96,1,1),(12,'2022-10-03 12:14:22.289248','2022-10-03 12:14:22.289252',_binary '','',0,1889.97,1,1),(13,'2022-10-03 12:20:24.530359','2022-10-03 12:20:24.530363',_binary '','',0,1259.98,1,1),(14,'2022-10-03 12:25:25.110832','2022-10-03 12:25:25.110837',_binary '','',0,1259.98,1,1),(15,'2022-10-03 12:43:01.294635','2022-10-03 12:43:01.294640',_binary '','',0,109.99,1,1),(16,'2022-10-03 12:45:08.541166','2022-10-03 12:45:08.541171',_binary '','',0,1889.97,1,1),(17,'2022-10-03 13:24:38.328883','2022-10-03 13:24:38.328887',_binary '','',0,1259.98,1,1),(18,'2022-10-03 13:29:02.860637','2022-10-03 13:29:02.860641',_binary '','',0,1889.97,1,1),(19,'2022-10-03 13:37:42.558786','2022-10-03 13:37:42.558792',_binary '','',0,1259.98,1,1),(20,'2022-10-03 13:43:13.395936','2022-10-03 13:43:13.395941',_binary '','',0,1889.97,1,1);
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `products`
--

DROP TABLE IF EXISTS `products`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `products` (
  `id` int NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) NOT NULL,
  `modified_at` datetime(6) DEFAULT NULL,
  `active` bit(1) DEFAULT NULL,
  `description` varchar(255) NOT NULL,
  `images_url` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `price` double NOT NULL,
  `slug` varchar(255) NOT NULL,
  `category_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKog2rp4qthbtt2lfyhfo32lsw9` (`category_id`),
  CONSTRAINT `FKog2rp4qthbtt2lfyhfo32lsw9` FOREIGN KEY (`category_id`) REFERENCES `categories` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `products`
--

LOCK TABLES `products` WRITE;
/*!40000 ALTER TABLE `products` DISABLE KEYS */;
INSERT INTO `products` VALUES (1,'2022-10-02 21:16:31.328329','2022-10-02 22:52:05.413092',_binary '','Karta graficzna wiele zalet','54Hi5uOQcymi9ONXWgkX.webp','Gigabyte GeForce RTX 3060 Ti EAGLE OC LHR 8GB GDDR',629.99,'gigabyte-geforce-rtx-3060-ti-eagle-oc-lhr-8gb-gddr-wjxdfq',35),(2,'2022-10-02 21:30:59.673368','2022-10-02 22:43:04.535927',_binary '','Przekątna ekranu: 55\"\nRozdzielczość: UHD 4K 3840 x 2160\nTyp telewizora: QLED\nKlasa energetyczna: G','wjsV0BhD1d4N1Zv4qC5z.webp;V10OE9PrdIIwJDQKf3Hp.webp','Samsung QE55Q77BA',599.99,'samsung-qe55q77ba-nbdjsu',29),(3,'2022-10-02 21:34:44.578702','2022-10-02 22:51:56.038371',_binary '','Platforma: PC, Xbox One, Xbox Series S, Xbox Series X\nInterfejs: USB\nŁączność: Przewodowa\nPedały: Gaz, Hamulec, Sprzęgło','8myo2bKFQlUwjf9xFFcG.webp;pFNNI0nQGtQIU2Ga4gfd.webp','Logitech G920 + Shifter Xbox Series X|S / Xbox One',159.99,'logitech-g920--shifter-xbox-series-xs--xbox-one-hbdj7p',34),(4,'2022-10-02 21:37:39.561044','2022-10-02 22:51:50.214932',_binary '','Przeznaczenie: Gaming\nPrzełączniki: Membranowe\nŁączność: Przewodowa\nPodświetlenie: Wielokolorowe - RGB','Ds9lYuqmOOsXdsC2xHEF.webp;d4eQWzRr1RYrtYWf8sjh.webp','Corsair K55 RGB Pro + Harpoon RGB Pro',109.99,'corsair-k55-rgb-pro--harpoon-rgb-pro-lsr93n',34),(5,'2022-10-02 21:40:05.090909','2022-10-02 22:51:43.408358',_binary '','Kompatybilność: PC\nRozdzielczość ekranu: 3664 x 1920\nDźwięk: Wbudowany mikrofon, Wbudowane głośniki\nCzujniki: Akcelerometr, Magnetometr, Żyroskop','BfC4tmef8YOdBYUtvTCW.webp;OJHyu11uZqEeG6pJvsr3.webp','Oculus Quest 2 - 256 GB',499.99,'oculus-quest-2---256-gb-hmuiib',47),(6,'2022-10-02 21:42:08.107845','2022-10-02 22:51:39.475513',_binary '','Procesor: Intel Core i5-1135G7\nPamięć: 8 GB\nGrafika: Intel Iris Xe Graphics\nTyp ekranu: Błyszczący, LED','f9kxNrfSLXyxHARc79CU.webp;hsTFWKD4rIKdZKB09Sih.webp','Microsoft Surface Pro 8 i5/8GB/128GB/Win11',1299.99,'microsoft-surface-pro-8-i58gb128gbwin11-z1262w',33),(7,'2022-10-02 21:44:15.863999','2022-10-02 22:51:34.693385',_binary '','Ekran: 6,1\"\nProcesor: Apple A13 Bionic\nPamięć RAM: 4 GB\nPamięć wbudowana: 64 GB','KIqzjVYughXSFERO0t2c.webp;GtoOzuEQDmr3PX1mybgt.webp','Apple iPhone 11 64GB Black',529.99,'apple-iphone-11-64gb-black-7jmjd0',39),(8,'2022-10-02 21:46:28.472036','2022-10-02 22:51:24.790495',_binary '','Typ: Xbox Series S\nDysk: 512 GB SSD NVMe\nW zestawie: Pad bezprzewodowy Microsoft Xbox Series, Kabel zasilający, Kabel HDMI\nŁączność: Wi-Fi 802.11 a/b/g/n/ac, Bluetooth 5.0, LAN 10/100/1000 Mbps','LbQ06gLa7OTmUTqGN1E8.webp;DNYPJpHSWjEVmJRGceKZ.webp','Microsoft Xbox Series S',399.99,'microsoft-xbox-series-s-wcc4ue',47),(9,'2022-10-02 21:48:51.600046','2022-10-02 22:51:19.320814',_binary '','pomadka w płynie do ust 15 peachy, 4,2 ml\nPołysk: Satynowy','l2sc4Zl69IASTpUq3twa.png;wNbbwyJCgmVGwjaHYH34.png','MAYBELLINE NEW YORK VINYL INK',3.99,'maybelline-new-york-vinyl-ink-rbgkfr',43),(10,'2022-10-02 21:50:26.281384','2022-10-02 22:51:15.106346',_binary '','matowa pomadka w płynie do ust strip down, 4 ml\nPołysk: Matowy','tXE1EWlJ0X3CrbF0AAfr.png;4z6GpRseDeWo8sJ7zGkT.png','NYX PROFESSIONAL MAKEUP LINGERIE XXL',5.99,'nyx-professional-makeup-lingerie-xxl-zkejqg',43),(11,'2022-10-02 21:52:12.081525','2022-10-02 22:51:08.085082',_binary '','szampon do włosów z fioletowymi pigmentami przeciw żółknięciu, 250 ml','9eKrd7diIBgVtYwBmwbv.png','JOHN FRIEDA VIOLET CRUSH',12.99,'john-frieda-violet-crush-uxfzcj',41),(12,'2022-10-02 22:46:10.670409','2022-10-02 22:46:10.670413',_binary '','Ekran:6,43\"\nProcesor:MediaTek Helio G95\nPamięć RAM:6 GB','Zjx1I5k9CI7NWKN4k4Mw.webp;hWzdP0vnb7GpdcURA2t1.webp','Xiaomi Redmi Note 10S 6/128GB Onyx Gray',299.99,'xiaomi-redmi-note-10s-6128gb-onyx-gray-tcjxvj',40),(13,'2022-10-02 22:47:31.135753','2022-10-02 22:47:31.135757',_binary '','Ekran:6,6\"\nProcesor:Samsung Exynos 2200\nPamięć RAM:8 GB\nPamięć wbudowana:256 GB','D243oAFbj48Nqy8qMrN7.webp;LcsnEXvnTBrtrwjf4Kmn.webp','Samsung Galaxy S22+ 8/256GB Pink Gold',1329.99,'samsung-galaxy-s22-8256gb-pink-gold-9m1lrg',38),(14,'2022-10-02 22:48:45.588104','2022-10-02 22:48:45.588107',_binary '','System:Brak systemu\nProcesor:Intel Core i5-11400F\nGrafika:NVIDIA GeForce GTX 1660 SUPER','9MTKqIHH5OyMxxuh6Jf6.webp;ycjNn7BhGHzxjV8QyqaL.webp','Acer Nitro 50 i5-11400F/16GB/512 GTX1660S',999.99,'acer-nitro-50-i5-11400f16gb512-gtx1660s-9abrfk',37),(15,'2022-10-02 22:50:00.150092','2022-10-02 22:50:00.150096',_binary '','Mapy:Polska, Europa Wschodnia, Europa Zachodnia\nEkran:7\"\nRozdzielczość:800 x 480 px','UH1L5UopzmKhAMrT95n6.jpg;Wiv1crZgymwwMqX7upTZ.jpg','Navitel E700 7\" Europa Dożywotnia',79.99,'navitel-e700-7-europa-dozywotnia-jwzzdx',44),(16,'2022-10-02 22:59:28.940638','2022-10-02 22:59:28.940642',_binary '','ADIDAS Piłka Nożna Al Rihla League QATAR 2022 r.5\n','RvJCnle7yIN8bG1rOrea.webp','ADIDAS Piłka Nożna Al Rihla League QATAR 2022 r.5',31.29,'adidas-pika-nozna-al-rihla-league-qatar-2022-r5-g8ixud',45),(17,'2022-10-02 23:00:29.604398','2022-10-02 23:00:29.604402',_binary '','HANTLE REGULOWANE 2x10KG, SZTANGA, ZESTAW +GRYFY\n','zllgLi18rSMNVFgmCLvS.webp','HANTLE REGULOWANE 2x10KG, SZTANGA, ZESTAW +GRYFY',59.99,'hantle-regulowane-2x10kg-sztanga-zestaw-gryfy-66s0x1',46),(18,'2022-10-02 23:01:43.806981','2022-10-02 23:01:43.806985',_binary '','Lay\'s Lays Stix Ketchup Chipsy ziemniaczane 140g','dujldOgzhkEHcxzMYVkA.webp','Lay\'s Lays Stix Ketchup Chipsy ziemniaczane 140g',1.29,'lays-lays-stix-ketchup-chipsy-ziemniaczane-140g-fmpx9x',27);
/*!40000 ALTER TABLE `products` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `products_reviews`
--

DROP TABLE IF EXISTS `products_reviews`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `products_reviews` (
  `product_id` int NOT NULL,
  `reviews_id` int NOT NULL,
  UNIQUE KEY `UK_rba1wlsrla9h6pt9v7lrtc1mr` (`reviews_id`),
  KEY `FKp0p3bq7p29sc9hhvix54g5l2g` (`product_id`),
  CONSTRAINT `FKi9i0n1o7cjlnker2o2hgl0ueq` FOREIGN KEY (`reviews_id`) REFERENCES `reviews` (`id`),
  CONSTRAINT `FKp0p3bq7p29sc9hhvix54g5l2g` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `products_reviews`
--

LOCK TABLES `products_reviews` WRITE;
/*!40000 ALTER TABLE `products_reviews` DISABLE KEYS */;
INSERT INTO `products_reviews` VALUES (7,1);
/*!40000 ALTER TABLE `products_reviews` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `refresh_token`
--

DROP TABLE IF EXISTS `refresh_token`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `refresh_token` (
  `id` int NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) NOT NULL,
  `modified_at` datetime(6) DEFAULT NULL,
  `expiry_date` datetime(6) DEFAULT NULL,
  `token` varchar(255) DEFAULT NULL,
  `user_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKjtx87i0jvq2svedphegvdwcuy` (`user_id`),
  CONSTRAINT `FKjtx87i0jvq2svedphegvdwcuy` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `refresh_token`
--

LOCK TABLES `refresh_token` WRITE;
/*!40000 ALTER TABLE `refresh_token` DISABLE KEYS */;
INSERT INTO `refresh_token` VALUES (1,'2022-10-02 20:11:46.860654','2022-10-02 20:11:46.860663','2023-03-31 20:11:46.856701','6e08cf74-d3e7-468c-b3e7-8c2bca5f3a22',1),(2,'2022-10-02 21:25:17.345145','2022-10-02 21:25:17.345165','2023-03-31 21:25:17.341645','dfb4ddc8-f7fe-4b47-b2b7-5c14defeebb3',1),(3,'2022-10-02 21:28:32.188088','2022-10-02 21:28:32.188093','2023-03-31 21:28:32.187547','9b66f3c5-034a-4647-b872-e755fdc1685a',1),(4,'2022-10-03 10:05:12.301489','2022-10-03 10:05:12.301502','2023-04-01 10:05:12.293680','cbe1cf54-658d-4a58-9ce3-ef5b9ee2730e',1);
/*!40000 ALTER TABLE `refresh_token` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reviews`
--

DROP TABLE IF EXISTS `reviews`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `reviews` (
  `id` int NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) NOT NULL,
  `modified_at` datetime(6) DEFAULT NULL,
  `rating` double NOT NULL,
  `review` varchar(255) NOT NULL,
  `user_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKcgy7qjc1r99dp117y9en6lxye` (`user_id`),
  CONSTRAINT `FKcgy7qjc1r99dp117y9en6lxye` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reviews`
--

LOCK TABLES `reviews` WRITE;
/*!40000 ALTER TABLE `reviews` DISABLE KEYS */;
INSERT INTO `reviews` VALUES (1,'2022-10-03 09:04:38.524033','2022-10-03 09:04:38.524038',4,'Nawet fajen',1);
/*!40000 ALTER TABLE `reviews` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `roles` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKofx66keruapi6vyqpv6f2or37` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles`
--

LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` VALUES (2,'ROLE_ADMIN'),(1,'ROLE_USER');
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_favorite`
--

DROP TABLE IF EXISTS `user_favorite`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_favorite` (
  `user_id` int NOT NULL,
  `product_id` int NOT NULL,
  PRIMARY KEY (`user_id`,`product_id`),
  KEY `FKlcaah26ppbc13h94xoax67r7t` (`product_id`),
  CONSTRAINT `FKlcaah26ppbc13h94xoax67r7t` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`),
  CONSTRAINT `FKs45qur86be72xcosxglq7vbia` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_favorite`
--

LOCK TABLES `user_favorite` WRITE;
/*!40000 ALTER TABLE `user_favorite` DISABLE KEYS */;
INSERT INTO `user_favorite` VALUES (1,1),(1,2);
/*!40000 ALTER TABLE `user_favorite` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_roles`
--

DROP TABLE IF EXISTS `user_roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_roles` (
  `user_id` int NOT NULL,
  `role_id` int NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`),
  KEY `FKh8ciramu9cc9q3qcqiv4ue8a6` (`role_id`),
  CONSTRAINT `FKh8ciramu9cc9q3qcqiv4ue8a6` FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`),
  CONSTRAINT `FKhfh9dx7w3ubf1co1vdev94g3f` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_roles`
--

LOCK TABLES `user_roles` WRITE;
/*!40000 ALTER TABLE `user_roles` DISABLE KEYS */;
INSERT INTO `user_roles` VALUES (1,1),(1,2);
/*!40000 ALTER TABLE `user_roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `id` int NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) NOT NULL,
  `modified_at` datetime(6) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `name` varchar(20) DEFAULT NULL,
  `password` varchar(120) DEFAULT NULL,
  `surname` varchar(20) DEFAULT NULL,
  `username` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKr43af9ap4edm43mmtq01oddj6` (`username`),
  UNIQUE KEY `UK6dotkott2kjsp8vw4d0m25fb7` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'2022-10-02 20:04:07.304422','2022-10-02 20:04:07.304432','admin@admin.com','Admin','$2a$10$upKn9bTucb33B.0VqBiZ4.QFPE5hP2MZx9cXlJUXf/u.5iI2pC7Uy','Admin','admin');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users_addresses`
--

DROP TABLE IF EXISTS `users_addresses`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users_addresses` (
  `user_id` int NOT NULL,
  `addresses_id` int NOT NULL,
  PRIMARY KEY (`user_id`,`addresses_id`),
  UNIQUE KEY `UK_fkg2t84ux3d2l2pg8atpsbljx` (`addresses_id`),
  CONSTRAINT `FKrpoauh74gtrrvj9m8skx6vti1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
  CONSTRAINT `FKwmv47j720v7vdsqe3lusklm6` FOREIGN KEY (`addresses_id`) REFERENCES `delivery_address` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users_addresses`
--

LOCK TABLES `users_addresses` WRITE;
/*!40000 ALTER TABLE `users_addresses` DISABLE KEYS */;
INSERT INTO `users_addresses` VALUES (1,1);
/*!40000 ALTER TABLE `users_addresses` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-10-03 13:47:24
