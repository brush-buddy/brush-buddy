-- MySQL dump 10.13  Distrib 8.0.36, for Win64 (x86_64)
--
-- Host: i10a205.p.ssafy.io    Database: brushBuddy
-- ------------------------------------------------------
-- Server version	8.0.36

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
-- Table structure for table `board`
--

DROP TABLE IF EXISTS `board`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `board` (
  `board_is_deleted` bit(1) NOT NULL DEFAULT b'0',
  `board_like_number` int NOT NULL,
  `board_watch` int NOT NULL,
  `user_id` int NOT NULL,
  `board_id` bigint NOT NULL AUTO_INCREMENT,
  `board_timestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `draft_id` bigint DEFAULT NULL,
  `board_title` varchar(200) NOT NULL,
  `board_content` varchar(1000) NOT NULL,
  `board_thumbnail` varchar(255) NOT NULL,
  PRIMARY KEY (`board_id`),
  KEY `FKdd6ilvex4h2ilmuebushqums0` (`draft_id`),
  KEY `FKfyf1fchnby6hndhlfaidier1r` (`user_id`),
  CONSTRAINT `FKdd6ilvex4h2ilmuebushqums0` FOREIGN KEY (`draft_id`) REFERENCES `draft` (`draft_id`),
  CONSTRAINT `FKfyf1fchnby6hndhlfaidier1r` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `board`
--

LOCK TABLES `board` WRITE;
/*!40000 ALTER TABLE `board` DISABLE KEYS */;
INSERT INTO `board` VALUES (_binary '',0,7,2,19,'2024-02-16 00:31:54',41,'비행기!','비행기를 그릴 도안을 뽑고 그려봤어요',''),(_binary '',0,1,2,20,'2024-02-16 00:31:58',41,'비행기!','비행기를 그릴 도안을 뽑고 그려봤어요',''),(_binary '',0,1,2,21,'2024-02-16 00:33:31',41,'로고','시연그림',''),(_binary '',0,1,3,22,'2024-02-16 01:07:29',41,'','직접 그린 비행기!!','https://brush-buddy-prod.s3.ap-northeast-2.amazonaws.com/board/d840dc55-e3f2-4529-9816-f945e9422fe2image.jpeg'),(_binary '\0',2,6,3,23,'2024-02-16 01:07:31',41,'','직접 그린 비행기!!','https://brush-buddy-prod.s3.ap-northeast-2.amazonaws.com/board/1323d3ed-8bf6-45e9-97d0-9c844730d536image.jpeg'),(_binary '\0',0,3,2,24,'2024-02-16 01:22:59',46,'하트 그리기 짱쉬워','다들 그려봐','https://brush-buddy-prod.s3.ap-northeast-2.amazonaws.com/board/c111646d-9363-42d8-8c05-ca46b8a96752image.png'),(_binary '\0',0,1,3,25,'2024-02-16 01:29:25',45,'개발자들의 모습','카페에서','https://brush-buddy-prod.s3.ap-northeast-2.amazonaws.com/board/a9c47166-e4c6-44e5-9275-986836f2d2b9image.png'),(_binary '\0',0,2,3,26,'2024-02-16 01:30:26',54,'사막여행','낙타가 잘나왔습니다','https://brush-buddy-prod.s3.ap-northeast-2.amazonaws.com/board/d4d2997b-a8f1-49ec-a00e-a9f958b954fbimage.png'),(_binary '\0',0,3,3,27,'2024-02-16 01:31:13',53,'노인과 나무','나뭇잎 색칠이 조금 힘들어요..','https://brush-buddy-prod.s3.ap-northeast-2.amazonaws.com/board/f93e1fca-d75e-42be-a41f-1e890c53286cimage.png'),(_binary '\0',0,3,4,28,'2024-02-16 01:31:31',55,'LA','가고싶다','https://brush-buddy-prod.s3.ap-northeast-2.amazonaws.com/board/569045e1-df37-492f-ba9d-a5166781ce75image.jpeg'),(_binary '\0',0,1,3,29,'2024-02-16 01:33:41',52,'일몰','빛을 표현해 내기가 조금 힘들 것 같아요','https://brush-buddy-prod.s3.ap-northeast-2.amazonaws.com/board/3ea95f67-8965-4796-8901-3f1d20307731image.png'),(_binary '\0',0,0,3,30,'2024-02-16 01:34:29',51,'한옥 투어','다양한 색을 사용할 수 있어요','https://brush-buddy-prod.s3.ap-northeast-2.amazonaws.com/board/43993b10-590e-4265-992a-ad2eb32d81b6image.png'),(_binary '\0',0,0,3,31,'2024-02-16 01:36:26',57,'날으는 할아버지','검은색이 조금 많이 쓰일 것 같습니다..','https://brush-buddy-prod.s3.ap-northeast-2.amazonaws.com/board/17f2f495-4256-4757-a19e-d62a3bbe1116image.png');
/*!40000 ALTER TABLE `board` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bookmark`
--

DROP TABLE IF EXISTS `bookmark`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bookmark` (
  `user_id` int NOT NULL,
  `bookmark_timestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `draft_id` bigint NOT NULL,
  PRIMARY KEY (`user_id`,`draft_id`),
  KEY `FKraxuv2lsmuohu4vp87wrtwoe6` (`draft_id`),
  CONSTRAINT `FK3ogdxsxa4tx6vndyvpk1fk1am` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`),
  CONSTRAINT `FKraxuv2lsmuohu4vp87wrtwoe6` FOREIGN KEY (`draft_id`) REFERENCES `draft` (`draft_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bookmark`
--

LOCK TABLES `bookmark` WRITE;
/*!40000 ALTER TABLE `bookmark` DISABLE KEYS */;
INSERT INTO `bookmark` VALUES (2,'2024-02-16 01:24:40',50);
/*!40000 ALTER TABLE `bookmark` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `category` (
  `category_id` bigint NOT NULL AUTO_INCREMENT,
  `category_content` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`category_id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES (1,'음식'),(2,'애니메이션 캐릭터'),(3,'로봇'),(4,'나무'),(5,'바다'),(6,'눈'),(7,'과일'),(8,'패션'),(9,'봄'),(10,'강변'),(11,'별자리'),(12,'애완동물'),(13,'자연'),(14,'도시'),(15,'여행'),(16,'연인');
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `draft`
--

DROP TABLE IF EXISTS `draft`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `draft` (
  `draft_bookmark` int NOT NULL,
  `draft_download` int NOT NULL,
  `draft_is_ai` bit(1) NOT NULL,
  `draft_is_default` bit(1) NOT NULL DEFAULT b'0',
  `draft_is_deleted` bit(1) NOT NULL DEFAULT b'0',
  `draft_is_public` bit(1) NOT NULL,
  `draft_price` int NOT NULL DEFAULT '0',
  `user_id` int NOT NULL,
  `draft_id` bigint NOT NULL AUTO_INCREMENT,
  `draft_timestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `draft_prompt` varchar(300) DEFAULT NULL,
  `draft_color_code` varchar(255) NOT NULL,
  `draft_file_link` varchar(255) NOT NULL,
  `draft_thumbnail` varchar(255) NOT NULL,
  PRIMARY KEY (`draft_id`),
  KEY `FKqppr9qic2mtrb0g8noe00r58q` (`user_id`),
  CONSTRAINT `FKqppr9qic2mtrb0g8noe00r58q` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=59 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `draft`
--

LOCK TABLES `draft` WRITE;
/*!40000 ALTER TABLE `draft` DISABLE KEYS */;
INSERT INTO `draft` VALUES (0,2,_binary '\0',_binary '\0',_binary '\0',_binary '',0,2,41,'2024-02-16 08:48:05','draft','{\"1\":\"#fbfbfb\",\"2\":\"#c3ecf7\",\"3\":\"#61e0f9\",\"4\":\"#4690a2\",\"5\":\"#064f64\",\"6\":\"#635044\",\"7\":\"#ecc289\",\"8\":\"#eba60f\",\"9\":\"#f8d008\",\"10\":\"#ef4d47\"}','https://brush-buddy-prod.s3.ap-northeast-2.amazonaws.com/draft/numbering/94646f38-cc5c-11ee-9c40-0242ac160002.PNG','https://brush-buddy-prod.s3.ap-northeast-2.amazonaws.com/draft/watermark/9556d5c0-cc5c-11ee-9c40-0242ac160002.png'),(0,0,_binary '\0',_binary '\0',_binary '\0',_binary '',0,2,42,'2024-02-16 09:41:16','draft','{\"1\":\"#fdfdfd\",\"2\":\"#8cecfc\",\"3\":\"#fcb4a4\",\"4\":\"#b4ec54\",\"5\":\"#ec6c6c\",\"6\":\"#444c7c\",\"7\":\"#6ce46c\",\"8\":\"#0cb4c4\",\"9\":\"#34e494\",\"10\":\"#fccc34\"}','https://brush-buddy-prod.s3.ap-northeast-2.amazonaws.com/draft/numbering/0d6fdf1e-cc64-11ee-b770-0242ac160002.PNG','https://brush-buddy-prod.s3.ap-northeast-2.amazonaws.com/draft/watermark/0faf0246-cc64-11ee-b770-0242ac160002.png'),(0,1,_binary '\0',_binary '\0',_binary '\0',_binary '',300,2,43,'2024-02-16 09:45:51','draft','{\"1\":\"#fdfdfb\",\"2\":\"#9bb895\",\"3\":\"#22ceac\",\"4\":\"#048eba\",\"5\":\"#08b0b8\",\"6\":\"#f83661\",\"7\":\"#f3c544\",\"8\":\"#fba20b\",\"9\":\"#fb6425\",\"10\":\"#081d44\"}','https://brush-buddy-prod.s3.ap-northeast-2.amazonaws.com/draft/numbering/6f2ad15a-cc64-11ee-b770-0242ac160002.PNG','https://brush-buddy-prod.s3.ap-northeast-2.amazonaws.com/draft/watermark/7134a4bc-cc64-11ee-b770-0242ac160002.png'),(0,0,_binary '\0',_binary '\0',_binary '',_binary '',0,4,44,'2024-02-16 09:49:18','draft','{\"1\":\"#bed4e8\",\"2\":\"#6fa1d3\",\"3\":\"#ac92c4\",\"4\":\"#8a7494\",\"5\":\"#69546f\",\"6\":\"#afab80\",\"7\":\"#635b40\",\"8\":\"#423536\",\"9\":\"#8a8958\",\"10\":\"#1b1213\"}','https://brush-buddy-prod.s3.ap-northeast-2.amazonaws.com/draft/numbering/2a39c370-cc65-11ee-b770-0242ac160002.PNG','https://brush-buddy-prod.s3.ap-northeast-2.amazonaws.com/draft/watermark/2dcf58ba-cc65-11ee-b770-0242ac160002.png'),(0,0,_binary '\0',_binary '\0',_binary '\0',_binary '',0,3,45,'2024-02-16 10:05:33','draft','{\"1\":\"#f4f7f8\",\"2\":\"#e8eae1\",\"3\":\"#b2c3c5\",\"4\":\"#745749\",\"5\":\"#fadabc\",\"6\":\"#b38a76\",\"7\":\"#441e13\",\"8\":\"#95a9af\",\"9\":\"#d1cfc7\",\"10\":\"#c9b19f\"}','https://brush-buddy-prod.s3.ap-northeast-2.amazonaws.com/draft/numbering/50f64d2e-cc67-11ee-a7fe-0242ac160002.PNG','https://brush-buddy-prod.s3.ap-northeast-2.amazonaws.com/draft/watermark/57ced04e-cc67-11ee-a7fe-0242ac160002.png'),(0,0,_binary '\0',_binary '\0',_binary '\0',_binary '',200,2,46,'2024-02-16 10:12:37','draft','{\"1\":\"#fcfcfc\",\"2\":\"#fc5c94\",\"3\":\"#fc4484\",\"4\":\"#fc3c84\",\"5\":\"#fc448c\",\"6\":\"#fc4c8c\"}','https://brush-buddy-prod.s3.ap-northeast-2.amazonaws.com/draft/numbering/6a93186a-cc68-11ee-a7fe-0242ac160002.PNG','https://brush-buddy-prod.s3.ap-northeast-2.amazonaws.com/draft/watermark/6aed07d0-cc68-11ee-a7fe-0242ac160002.png'),(0,0,_binary '',_binary '\0',_binary '',_binary '',0,4,47,'2024-02-16 10:17:25','간단한 동물','{\"1\":\"#edfefe\",\"2\":\"#fbfbfb\",\"3\":\"#a2dbe6\",\"4\":\"#82b7c2\",\"5\":\"#3f7883\",\"6\":\"#093449\",\"7\":\"#043044\",\"8\":\"#235b66\",\"9\":\"#5e97a2\",\"10\":\"#92d9e4\"}','https://brush-buddy-prod.s3.ap-northeast-2.amazonaws.com/draft/numbering/0bfc6dbe-cc69-11ee-a7fe-0242ac160002.PNG','https://brush-buddy-prod.s3.ap-northeast-2.amazonaws.com/draft/watermark/0f686494-cc69-11ee-a7fe-0242ac160002.png'),(0,0,_binary '',_binary '\0',_binary '\0',_binary '',0,4,48,'2024-02-16 10:17:26','간단한 동물','{\"1\":\"#edfefe\",\"2\":\"#fbfbfb\",\"3\":\"#a2dbe6\",\"4\":\"#82b7c2\",\"5\":\"#3f7883\",\"6\":\"#093449\",\"7\":\"#043044\",\"8\":\"#235b66\",\"9\":\"#5e97a2\",\"10\":\"#92d9e4\"}','https://brush-buddy-prod.s3.ap-northeast-2.amazonaws.com/draft/numbering/0bfc6dbe-cc69-11ee-a7fe-0242ac160002.PNG','https://brush-buddy-prod.s3.ap-northeast-2.amazonaws.com/draft/watermark/0f686494-cc69-11ee-a7fe-0242ac160002.png'),(0,0,_binary '\0',_binary '\0',_binary '\0',_binary '',100,2,49,'2024-02-16 10:19:51','draft','{\"1\":\"#0e62c9\",\"2\":\"#17adf0\",\"3\":\"#8ad2f5\",\"4\":\"#225542\",\"5\":\"#831e1f\",\"6\":\"#d65381\",\"7\":\"#dc851c\",\"8\":\"#ebd36f\",\"9\":\"#eff0ec\",\"10\":\"#74a945\"}','https://brush-buddy-prod.s3.ap-northeast-2.amazonaws.com/draft/numbering/5ba0f574-cc69-11ee-a7fe-0242ac160002.PNG','https://brush-buddy-prod.s3.ap-northeast-2.amazonaws.com/draft/watermark/685afe5e-cc69-11ee-a7fe-0242ac160002.png'),(1,1,_binary '',_binary '\0',_binary '\0',_binary '',0,4,50,'2024-02-16 10:21:53','간단한 강아지','{\"1\":\"#f1d1c1\",\"2\":\"#f3dbc7\",\"3\":\"#c3b4ae\",\"4\":\"#9e928c\",\"5\":\"#77706a\",\"6\":\"#534f49\",\"7\":\"#39382e\",\"8\":\"#191c19\",\"9\":\"#040404\",\"10\":\"#fbebdc\"}','https://brush-buddy-prod.s3.ap-northeast-2.amazonaws.com/draft/numbering/b7d34446-cc69-11ee-a7fe-0242ac160002.PNG','https://brush-buddy-prod.s3.ap-northeast-2.amazonaws.com/draft/watermark/b9780f7a-cc69-11ee-a7fe-0242ac160002.png'),(0,0,_binary '\0',_binary '\0',_binary '\0',_binary '',0,3,51,'2024-02-16 10:25:32','draft','{\"1\":\"#faf6ed\",\"2\":\"#e5cfb3\",\"3\":\"#b0b0af\",\"4\":\"#948e86\",\"5\":\"#6f685f\",\"6\":\"#4b3625\",\"7\":\"#181812\",\"8\":\"#7a4c2e\",\"9\":\"#9f6239\",\"10\":\"#be8050\"}','https://brush-buddy-prod.s3.ap-northeast-2.amazonaws.com/draft/numbering/0743d216-cc6a-11ee-a7fe-0242ac160002.PNG','https://brush-buddy-prod.s3.ap-northeast-2.amazonaws.com/draft/watermark/30560c46-cc6a-11ee-a7fe-0242ac160002.png'),(0,0,_binary '\0',_binary '\0',_binary '\0',_binary '',0,3,52,'2024-02-16 10:26:18','draft','{\"1\":\"#5d5f5e\",\"2\":\"#956d56\",\"3\":\"#6d3827\",\"4\":\"#422421\",\"5\":\"#a5471f\",\"6\":\"#d8854b\",\"7\":\"#b09f8b\",\"8\":\"#f3b26f\",\"9\":\"#1c161f\",\"10\":\"#f5daa4\"}','https://brush-buddy-prod.s3.ap-northeast-2.amazonaws.com/draft/numbering/54c7be6c-cc6a-11ee-a7fe-0242ac160002.PNG','https://brush-buddy-prod.s3.ap-northeast-2.amazonaws.com/draft/watermark/57b0e572-cc6a-11ee-a7fe-0242ac160002.png'),(0,0,_binary '\0',_binary '\0',_binary '\0',_binary '',0,3,53,'2024-02-16 10:27:28','draft','{\"1\":\"#fdf2ed\",\"2\":\"#d1d5c0\",\"3\":\"#afb39a\",\"4\":\"#979b7e\",\"5\":\"#7f876b\",\"6\":\"#41553a\",\"7\":\"#526447\",\"8\":\"#647358\",\"9\":\"#233e2e\",\"10\":\"#163127\"}','https://brush-buddy-prod.s3.ap-northeast-2.amazonaws.com/draft/numbering/6d1d23d0-cc6a-11ee-a7fe-0242ac160002.PNG','https://brush-buddy-prod.s3.ap-northeast-2.amazonaws.com/draft/watermark/7548b0ec-cc6a-11ee-a7fe-0242ac160002.png'),(0,0,_binary '\0',_binary '\0',_binary '\0',_binary '',0,3,54,'2024-02-16 10:28:39','draft','{\"1\":\"#9ca996\",\"2\":\"#8c7f5b\",\"3\":\"#c2cebc\",\"4\":\"#8e6132\",\"5\":\"#eef4df\",\"6\":\"#f2e3ae\",\"7\":\"#e4c382\",\"8\":\"#693a20\",\"9\":\"#c4914e\",\"10\":\"#331015\"}','https://brush-buddy-prod.s3.ap-northeast-2.amazonaws.com/draft/numbering/9dea7a9e-cc6a-11ee-a7fe-0242ac160002.PNG','https://brush-buddy-prod.s3.ap-northeast-2.amazonaws.com/draft/watermark/af48efaa-cc6a-11ee-a7fe-0242ac160002.png'),(0,0,_binary '\0',_binary '\0',_binary '\0',_binary '',0,4,55,'2024-02-16 10:30:31','draft','{\"1\":\"#1a1567\",\"2\":\"#0b0f4c\",\"3\":\"#3a3e72\",\"4\":\"#0c0e24\",\"5\":\"#55629c\",\"6\":\"#a19db7\",\"7\":\"#f3f2f6\",\"8\":\"#301a8d\",\"9\":\"#5428ba\",\"10\":\"#bf972a\"}','https://brush-buddy-prod.s3.ap-northeast-2.amazonaws.com/draft/numbering/ea19724e-cc6a-11ee-a7fe-0242ac160002.PNG','https://brush-buddy-prod.s3.ap-northeast-2.amazonaws.com/draft/watermark/ecf011b2-cc6a-11ee-a7fe-0242ac160002.png'),(0,0,_binary '\0',_binary '\0',_binary '\0',_binary '',0,2,56,'2024-02-16 10:33:44','draft','{\"1\":\"#fefef1\",\"2\":\"#f7d0b2\",\"3\":\"#fbbc7e\",\"4\":\"#b5e3d4\",\"5\":\"#7ecfc1\",\"6\":\"#824332\",\"7\":\"#61948e\",\"8\":\"#321107\",\"9\":\"#fb9457\",\"10\":\"#eb6844\"}','https://brush-buddy-prod.s3.ap-northeast-2.amazonaws.com/draft/numbering/612023a6-cc6b-11ee-a7fe-0242ac160002.PNG','https://brush-buddy-prod.s3.ap-northeast-2.amazonaws.com/draft/watermark/6315ebd2-cc6b-11ee-a7fe-0242ac160002.png'),(0,0,_binary '\0',_binary '\0',_binary '\0',_binary '',0,3,57,'2024-02-16 10:35:58','draft','{\"1\":\"#040b1f\",\"2\":\"#1a3141\",\"3\":\"#0b2031\",\"4\":\"#2c4451\",\"5\":\"#5f777b\",\"6\":\"#809693\",\"7\":\"#435b64\",\"8\":\"#a4b6ac\",\"9\":\"#eef1d8\",\"10\":\"#cad6c4\"}','https://brush-buddy-prod.s3.ap-northeast-2.amazonaws.com/draft/numbering/938b97e4-cc6b-11ee-a7fe-0242ac160002.PNG','https://brush-buddy-prod.s3.ap-northeast-2.amazonaws.com/draft/watermark/b1750e16-cc6b-11ee-a7fe-0242ac160002.png'),(0,0,_binary '',_binary '\0',_binary '\0',_binary '',0,4,58,'2024-02-16 10:56:50','간단한 강아지','{\"1\":\"#fef1f1\",\"2\":\"#fcf3eb\",\"3\":\"#ddd9da\",\"4\":\"#acacae\",\"5\":\"#909292\",\"6\":\"#c6c2c6\",\"7\":\"#6b6f71\",\"8\":\"#202423\",\"9\":\"#040404\",\"10\":\"#44474a\"}','https://brush-buddy-prod.s3.ap-northeast-2.amazonaws.com/draft/numbering/7af9c59a-cc6e-11ee-a7fe-0242ac160002.PNG','https://brush-buddy-prod.s3.ap-northeast-2.amazonaws.com/draft/watermark/95f3a406-cc6e-11ee-a7fe-0242ac160002.png');
/*!40000 ALTER TABLE `draft` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `draft_category`
--

DROP TABLE IF EXISTS `draft_category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `draft_category` (
  `category_id` bigint NOT NULL,
  `draft_id` bigint NOT NULL,
  PRIMARY KEY (`category_id`,`draft_id`),
  KEY `FKkwacjjp0isd5g16j42lbgqwfi` (`draft_id`),
  CONSTRAINT `FKkwacjjp0isd5g16j42lbgqwfi` FOREIGN KEY (`draft_id`) REFERENCES `draft` (`draft_id`),
  CONSTRAINT `FKn1idg0q45vbtmypmfj9gmvlbn` FOREIGN KEY (`category_id`) REFERENCES `category` (`category_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `draft_category`
--

LOCK TABLES `draft_category` WRITE;
/*!40000 ALTER TABLE `draft_category` DISABLE KEYS */;
INSERT INTO `draft_category` VALUES (1,41),(10,42),(13,42),(13,43),(9,44),(14,45),(15,45),(16,46),(12,47),(13,47),(12,48),(13,48),(12,49),(12,50),(13,50),(14,51),(15,51),(13,52),(4,53),(13,53),(13,54),(14,55),(15,55),(16,55),(12,56),(2,57),(12,58),(13,58);
/*!40000 ALTER TABLE `draft_category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hashtag`
--

DROP TABLE IF EXISTS `hashtag`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hashtag` (
  `hashtag_serial_number` int NOT NULL,
  `board_id` bigint NOT NULL,
  `hashtag_content` varchar(50) NOT NULL,
  PRIMARY KEY (`board_id`,`hashtag_content`),
  CONSTRAINT `FK5q40rtdctjrpyqnx0kj19lt29` FOREIGN KEY (`board_id`) REFERENCES `board` (`board_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hashtag`
--

LOCK TABLES `hashtag` WRITE;
/*!40000 ALTER TABLE `hashtag` DISABLE KEYS */;
INSERT INTO `hashtag` VALUES (0,19,' 여행'),(0,19,'비행기'),(0,20,' 여행'),(0,20,'비행기'),(0,21,'그림시연'),(0,24,'하트'),(0,28,'노래좋아');
/*!40000 ALTER TABLE `hashtag` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `heart`
--

DROP TABLE IF EXISTS `heart`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `heart` (
  `user_id` int NOT NULL,
  `board_id` bigint NOT NULL,
  PRIMARY KEY (`user_id`,`board_id`),
  KEY `FK197ewge8jbxp2sfnm7f64bhdo` (`board_id`),
  CONSTRAINT `FK197ewge8jbxp2sfnm7f64bhdo` FOREIGN KEY (`board_id`) REFERENCES `board` (`board_id`),
  CONSTRAINT `FK5pv32bwn1jhofpwouomqupc6u` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `heart`
--

LOCK TABLES `heart` WRITE;
/*!40000 ALTER TABLE `heart` DISABLE KEYS */;
INSERT INTO `heart` VALUES (2,23),(4,23);
/*!40000 ALTER TABLE `heart` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `image`
--

DROP TABLE IF EXISTS `image`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `image` (
  `image_order` int NOT NULL,
  `board_id` bigint NOT NULL,
  `image_id` bigint NOT NULL AUTO_INCREMENT,
  `image_url` varchar(255) NOT NULL,
  PRIMARY KEY (`image_id`),
  KEY `FKil875c0myaxwwf0hty0u1ej2d` (`board_id`),
  CONSTRAINT `FKil875c0myaxwwf0hty0u1ej2d` FOREIGN KEY (`board_id`) REFERENCES `board` (`board_id`)
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `image`
--

LOCK TABLES `image` WRITE;
/*!40000 ALTER TABLE `image` DISABLE KEYS */;
INSERT INTO `image` VALUES (1,22,26,'https://brush-buddy-prod.s3.ap-northeast-2.amazonaws.com/board/d840dc55-e3f2-4529-9816-f945e9422fe2image.jpeg'),(1,23,27,'https://brush-buddy-prod.s3.ap-northeast-2.amazonaws.com/board/9df94980-b795-4f5a-a805-3504dcb9a179image.jpeg'),(1,23,28,'https://brush-buddy-prod.s3.ap-northeast-2.amazonaws.com/board/1323d3ed-8bf6-45e9-97d0-9c844730d536image.jpeg'),(1,24,29,'https://brush-buddy-prod.s3.ap-northeast-2.amazonaws.com/board/c111646d-9363-42d8-8c05-ca46b8a96752image.png'),(1,25,30,'https://brush-buddy-prod.s3.ap-northeast-2.amazonaws.com/board/a9c47166-e4c6-44e5-9275-986836f2d2b9image.png'),(1,26,31,'https://brush-buddy-prod.s3.ap-northeast-2.amazonaws.com/board/d4d2997b-a8f1-49ec-a00e-a9f958b954fbimage.png'),(1,27,32,'https://brush-buddy-prod.s3.ap-northeast-2.amazonaws.com/board/f93e1fca-d75e-42be-a41f-1e890c53286cimage.png'),(1,28,33,'https://brush-buddy-prod.s3.ap-northeast-2.amazonaws.com/board/569045e1-df37-492f-ba9d-a5166781ce75image.jpeg'),(1,29,34,'https://brush-buddy-prod.s3.ap-northeast-2.amazonaws.com/board/3ea95f67-8965-4796-8901-3f1d20307731image.png'),(1,30,35,'https://brush-buddy-prod.s3.ap-northeast-2.amazonaws.com/board/43993b10-590e-4265-992a-ad2eb32d81b6image.png'),(1,31,36,'https://brush-buddy-prod.s3.ap-northeast-2.amazonaws.com/board/17f2f495-4256-4757-a19e-d62a3bbe1116image.png');
/*!40000 ALTER TABLE `image` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `machine`
--

DROP TABLE IF EXISTS `machine`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `machine` (
  `machine_paint_amount` int DEFAULT '0',
  `user_id` int DEFAULT NULL,
  `machine_id` bigint NOT NULL AUTO_INCREMENT,
  `workplace_id` bigint NOT NULL,
  `owner` enum('personal','admin') NOT NULL,
  PRIMARY KEY (`machine_id`),
  KEY `FKf4hvlemijw7wpgms9d2naoro4` (`user_id`),
  KEY `FKrq3047qrnkxbtcsvv0m7dj916` (`workplace_id`),
  CONSTRAINT `FKf4hvlemijw7wpgms9d2naoro4` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`),
  CONSTRAINT `FKrq3047qrnkxbtcsvv0m7dj916` FOREIGN KEY (`workplace_id`) REFERENCES `workplace` (`workplace_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `machine`
--

LOCK TABLES `machine` WRITE;
/*!40000 ALTER TABLE `machine` DISABLE KEYS */;
INSERT INTO `machine` VALUES (0,4,1,1,'personal');
/*!40000 ALTER TABLE `machine` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mileage`
--

DROP TABLE IF EXISTS `mileage`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `mileage` (
  `mileage_after` int NOT NULL,
  `mileage_amount` int NOT NULL,
  `mileage_before` int NOT NULL,
  `user_id` int NOT NULL,
  `workplace_id` int DEFAULT NULL,
  `mileage_id` bigint NOT NULL AUTO_INCREMENT,
  `mileage_timestamp` datetime(6) DEFAULT NULL,
  `mileage_content` varchar(100) NOT NULL,
  PRIMARY KEY (`mileage_id`),
  KEY `FK9bguxpnvgt1gf1bkwvav1comn` (`user_id`),
  CONSTRAINT `FK9bguxpnvgt1gf1bkwvav1comn` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mileage`
--

LOCK TABLES `mileage` WRITE;
/*!40000 ALTER TABLE `mileage` DISABLE KEYS */;
INSERT INTO `mileage` VALUES (500,500,0,2,NULL,1,NULL,'충전'),(5000,5000,0,1,NULL,2,NULL,'충전'),(800,300,500,2,NULL,3,NULL,'충전'),(900,100,800,2,NULL,4,NULL,'충전'),(1400,500,900,2,NULL,5,NULL,'충전'),(300,300,0,4,NULL,6,NULL,'충전'),(2400,1000,1400,2,NULL,7,NULL,'충전'),(2700,300,2400,2,NULL,8,NULL,'충전'),(800,500,300,4,NULL,9,NULL,'충전'),(1300,500,800,4,NULL,10,NULL,'충전');
/*!40000 ALTER TABLE `mileage` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mileage_log`
--

DROP TABLE IF EXISTS `mileage_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `mileage_log` (
  `price` int DEFAULT NULL,
  `user_id` int NOT NULL,
  `mileage_log_id` bigint NOT NULL AUTO_INCREMENT,
  `mileage_log_status` varchar(255) DEFAULT NULL,
  `tid` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`mileage_log_id`),
  KEY `FKxdajy9i49rqx8q9u7mxkrncs` (`user_id`),
  CONSTRAINT `FKxdajy9i49rqx8q9u7mxkrncs` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=45 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mileage_log`
--

LOCK TABLES `mileage_log` WRITE;
/*!40000 ALTER TABLE `mileage_log` DISABLE KEYS */;
INSERT INTO `mileage_log` VALUES (100,2,1,'충전','T5cd58fe1c025b0ff1f1'),(300,2,2,'충전','T5cd59131c025b0ff1f4'),(100,2,3,'충전','T5cd5c28196d5d205f9c'),(100,2,4,'충전','T5cd5d7a196d5d205fa4'),(1000,3,5,'충전','T5cd6c9a1c025b0ff2ae'),(1000,3,6,'충전','T5cd6c9a1c025b0ff2af'),(100,5,7,'충전','T5cd72e01c025b0ff307'),(300,5,8,'충전','T5cd72f21c025b0ff309'),(1000,5,9,'충전','T5cd7317196d5d20608b'),(500,1,10,'충전','T5cdadd8196d5d2062cd'),(300,2,11,'충전','T5cdb714196d5d20633c'),(1000,2,12,'충전','T5cdb85b1c025b0ff5bb'),(3000,4,13,'충전','T5cdcfe9196d5d20649f'),(1000,4,14,'충전','T5cdcfff1c025b0ff724'),(500,2,15,'충전','T5ce1005196d5d2066a3'),(500,2,16,'충전','T5ce100c196d5d2066a4'),(500,2,17,'충전','T5ce100f1c025b0ff93e'),(300,2,18,'충전','T5ce30a3196d5d206796'),(500,2,19,'충전','T5ce3105196d5d20679b'),(500,2,20,'충전','T5ce32891c025b0ffa38'),(500,2,21,'충전완료','T5ce329f196d5d2067a8'),(300,4,22,'충전','T5ce3391196d5d2067b4'),(500,4,23,'충전','T5ce33ba1c025b0ffa41'),(1000,4,24,'충전','T5ce33d21c025b0ffa42'),(500,2,25,'충전','T5ce34e0196d5d2067bc'),(500,1,26,'충전','T5ce371b1c025b0ffa57'),(1000,1,27,'충전','T5ce3730196d5d2067ca'),(5000,1,28,'충전완료','T5ce37711c025b0ffa5a'),(300,2,29,'충전','T5ce549d1c025b0ffaf5'),(300,2,30,'충전','T5ce54af1c025b0ffaf7'),(100,2,31,'충전','T5ce54cd196d5d206861'),(300,2,32,'충전완료','T5ce5583196d5d206865'),(100,2,33,'충전완료','T5ce56a3196d5d20686b'),(500,2,34,'충전완료','T5ce8e151c025b0ffc14'),(300,4,35,'충전완료','T5ce901c196d5d20698a'),(1000,2,36,'충전완료','T5ce9bce196d5d2069c0'),(1000,7,37,'충전','T5ce9fc81c025b0ffc74'),(300,2,38,'충전','T5ceada11c025b0ffd51'),(300,2,39,'충전완료','T5ceadc71c025b0ffd52'),(3000,4,40,'충전','T5ceb3aa1c025b0ffd87'),(500,4,41,'충전완료','T5ceb3df1c025b0ffd89'),(5000,1,42,'충전','T5cebab81c025b0ffdcb'),(1000,3,43,'충전','T5cebb02196d5d206b3d'),(500,4,44,'충전완료','T5cec0fe1c025b0ffe23');
/*!40000 ALTER TABLE `mileage_log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `palette`
--

DROP TABLE IF EXISTS `palette`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `palette` (
  `user_id` int DEFAULT NULL,
  `draft_id` bigint DEFAULT NULL,
  `palette_created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `palette_id` bigint NOT NULL AUTO_INCREMENT,
  `palette_last_modified_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `palette_color_code` json NOT NULL,
  `palette_name` varchar(255) NOT NULL,
  PRIMARY KEY (`palette_id`),
  KEY `FK92e40018skwy2pstn7krxmek4` (`draft_id`),
  KEY `FKlit6q1f34y82yg9xi4q7htt0b` (`user_id`),
  CONSTRAINT `FK92e40018skwy2pstn7krxmek4` FOREIGN KEY (`draft_id`) REFERENCES `draft` (`draft_id`),
  CONSTRAINT `FKlit6q1f34y82yg9xi4q7htt0b` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=97 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `palette`
--

LOCK TABLES `palette` WRITE;
/*!40000 ALTER TABLE `palette` DISABLE KEYS */;
INSERT INTO `palette` VALUES (2,41,'2024-02-15 23:48:04',71,'2024-02-16 09:30:05','{\"1\": \"#fbfbfb\", \"2\": \"#c3ecf7\", \"3\": \"#61e0f9\", \"4\": \"#4690a2\", \"5\": \"#064f64\", \"6\": \"#635044\", \"7\": \"#ecc289\", \"8\": \"#eba60f\", \"9\": \"#f8d008\", \"10\": \"#ef4d47\", \"11\": \"#A64343\"}','비행기'),(3,41,'2024-02-15 23:56:13',72,'2024-02-16 08:56:13','{\"1\": \"#fbfbfb\", \"2\": \"#c3ecf7\", \"3\": \"#61e0f9\", \"4\": \"#4690a2\", \"5\": \"#064f64\", \"6\": \"#635044\", \"7\": \"#ecc289\", \"8\": \"#eba60f\", \"9\": \"#f8d008\", \"10\": \"#ef4d47\"}','구매한 도안의 팔레트'),(2,41,'2024-02-16 00:30:12',73,'2024-02-16 09:30:13','{\"1\": \"#fbfbfb\", \"2\": \"#c3ecf7\", \"3\": \"#61e0f9\", \"4\": \"#4690a2\", \"5\": \"#064f64\", \"6\": \"#635044\", \"7\": \"#ecc289\", \"8\": \"#eba60f\", \"9\": \"#f8d008\", \"10\": \"#ef4d47\", \"11\": \"#A64343\"}','비행기_copy'),(2,42,'2024-02-16 00:41:16',74,'2024-02-16 09:41:16','{\"1\": \"#fdfdfd\", \"2\": \"#8cecfc\", \"3\": \"#fcb4a4\", \"4\": \"#b4ec54\", \"5\": \"#ec6c6c\", \"6\": \"#444c7c\", \"7\": \"#6ce46c\", \"8\": \"#0cb4c4\", \"9\": \"#34e494\", \"10\": \"#fccc34\"}','산책'),(3,42,'2024-02-16 00:42:30',75,'2024-02-16 09:42:31','{\"1\": \"#fdfdfd\", \"2\": \"#8cecfc\", \"3\": \"#fcb4a4\", \"4\": \"#b4ec54\", \"5\": \"#ec6c6c\", \"6\": \"#444c7c\", \"7\": \"#6ce46c\", \"8\": \"#0cb4c4\", \"9\": \"#34e494\", \"10\": \"#fccc34\"}','구매한 도안의 팔레트'),(7,42,'2024-02-16 00:44:24',76,'2024-02-16 09:44:24','{\"1\": \"#fdfdfd\", \"2\": \"#8cecfc\", \"3\": \"#fcb4a4\", \"4\": \"#b4ec54\", \"5\": \"#ec6c6c\", \"6\": \"#444c7c\", \"7\": \"#6ce46c\", \"8\": \"#0cb4c4\", \"9\": \"#34e494\", \"10\": \"#fccc34\"}','구매한 도안의 팔레트'),(2,43,'2024-02-16 00:45:50',77,'2024-02-16 09:45:51','{\"1\": \"#fdfdfb\", \"2\": \"#9bb895\", \"3\": \"#22ceac\", \"4\": \"#048eba\", \"5\": \"#08b0b8\", \"6\": \"#f83661\", \"7\": \"#f3c544\", \"8\": \"#fba20b\", \"9\": \"#fb6425\", \"10\": \"#081d44\"}','석양'),(4,44,'2024-02-16 00:49:18',78,'2024-02-16 09:49:18','{\"1\": \"#bed4e8\", \"2\": \"#6fa1d3\", \"3\": \"#ac92c4\", \"4\": \"#8a7494\", \"5\": \"#69546f\", \"6\": \"#afab80\", \"7\": \"#635b40\", \"8\": \"#423536\", \"9\": \"#8a8958\", \"10\": \"#1b1213\"}','꽃'),(3,41,'2024-02-16 01:04:38',79,'2024-02-16 10:06:58','{\"1\": \"#fbfbfb\", \"2\": \"#c3ecf7\", \"3\": \"#61e0f9\", \"4\": \"#4690a2\", \"5\": \"#064f64\", \"6\": \"#635044\", \"7\": \"#ecc289\", \"8\": \"#eba60f\", \"9\": \"#f8d008\", \"10\": \"#ef4d47\", \"11\": \"#00FF00\"}','구매한 도안의 팔레트'),(3,45,'2024-02-16 01:05:32',80,'2024-02-16 10:05:33','{\"1\": \"#f4f7f8\", \"2\": \"#e8eae1\", \"3\": \"#b2c3c5\", \"4\": \"#745749\", \"5\": \"#fadabc\", \"6\": \"#b38a76\", \"7\": \"#441e13\", \"8\": \"#95a9af\", \"9\": \"#d1cfc7\", \"10\": \"#c9b19f\"}','개발자'),(2,46,'2024-02-16 01:12:36',81,'2024-02-16 10:12:37','{\"1\": \"#fcfcfc\", \"2\": \"#fc5c94\", \"3\": \"#fc4484\", \"4\": \"#fc3c84\", \"5\": \"#fc448c\", \"6\": \"#fc4c8c\"}','하뚜'),(4,47,'2024-02-16 01:17:25',82,'2024-02-16 10:17:25','{\"1\": \"#edfefe\", \"2\": \"#fbfbfb\", \"3\": \"#a2dbe6\", \"4\": \"#82b7c2\", \"5\": \"#3f7883\", \"6\": \"#093449\", \"7\": \"#043044\", \"8\": \"#235b66\", \"9\": \"#5e97a2\", \"10\": \"#92d9e4\"}','코뿔소'),(4,48,'2024-02-16 01:17:26',83,'2024-02-16 10:17:26','{\"1\": \"#edfefe\", \"2\": \"#fbfbfb\", \"3\": \"#a2dbe6\", \"4\": \"#82b7c2\", \"5\": \"#3f7883\", \"6\": \"#093449\", \"7\": \"#043044\", \"8\": \"#235b66\", \"9\": \"#5e97a2\", \"10\": \"#92d9e4\"}','코뿔소'),(2,49,'2024-02-16 01:19:51',84,'2024-02-16 10:19:51','{\"1\": \"#0e62c9\", \"2\": \"#17adf0\", \"3\": \"#8ad2f5\", \"4\": \"#225542\", \"5\": \"#831e1f\", \"6\": \"#d65381\", \"7\": \"#dc851c\", \"8\": \"#ebd36f\", \"9\": \"#eff0ec\", \"10\": \"#74a945\"}','싸피 다니는 강아지'),(4,50,'2024-02-16 01:21:53',85,'2024-02-16 10:21:53','{\"1\": \"#f1d1c1\", \"2\": \"#f3dbc7\", \"3\": \"#c3b4ae\", \"4\": \"#9e928c\", \"5\": \"#77706a\", \"6\": \"#534f49\", \"7\": \"#39382e\", \"8\": \"#191c19\", \"9\": \"#040404\", \"10\": \"#fbebdc\"}','멍멍'),(2,50,'2024-02-16 01:24:32',86,'2024-02-16 10:24:33','{\"1\": \"#f1d1c1\", \"2\": \"#f3dbc7\", \"3\": \"#c3b4ae\", \"4\": \"#9e928c\", \"5\": \"#77706a\", \"6\": \"#534f49\", \"7\": \"#39382e\", \"8\": \"#191c19\", \"9\": \"#040404\", \"10\": \"#fbebdc\"}','구매한 도안의 팔레트'),(3,51,'2024-02-16 01:25:32',87,'2024-02-16 10:25:32','{\"1\": \"#faf6ed\", \"2\": \"#e5cfb3\", \"3\": \"#b0b0af\", \"4\": \"#948e86\", \"5\": \"#6f685f\", \"6\": \"#4b3625\", \"7\": \"#181812\", \"8\": \"#7a4c2e\", \"9\": \"#9f6239\", \"10\": \"#be8050\"}','잘생긴 남자'),(3,52,'2024-02-16 01:26:18',88,'2024-02-16 10:26:18','{\"1\": \"#5d5f5e\", \"2\": \"#956d56\", \"3\": \"#6d3827\", \"4\": \"#422421\", \"5\": \"#a5471f\", \"6\": \"#d8854b\", \"7\": \"#b09f8b\", \"8\": \"#f3b26f\", \"9\": \"#1c161f\", \"10\": \"#f5daa4\"}','일몰 바라보기'),(3,53,'2024-02-16 01:27:27',89,'2024-02-16 10:27:28','{\"1\": \"#fdf2ed\", \"2\": \"#d1d5c0\", \"3\": \"#afb39a\", \"4\": \"#979b7e\", \"5\": \"#7f876b\", \"6\": \"#41553a\", \"7\": \"#526447\", \"8\": \"#647358\", \"9\": \"#233e2e\", \"10\": \"#163127\"}','노인과 나무'),(3,54,'2024-02-16 01:28:38',90,'2024-02-16 10:28:39','{\"1\": \"#9ca996\", \"2\": \"#8c7f5b\", \"3\": \"#c2cebc\", \"4\": \"#8e6132\", \"5\": \"#eef4df\", \"6\": \"#f2e3ae\", \"7\": \"#e4c382\", \"8\": \"#693a20\", \"9\": \"#c4914e\", \"10\": \"#331015\"}','사막여행'),(4,55,'2024-02-16 01:30:30',91,'2024-02-16 10:30:31','{\"1\": \"#1a1567\", \"2\": \"#0b0f4c\", \"3\": \"#3a3e72\", \"4\": \"#0c0e24\", \"5\": \"#55629c\", \"6\": \"#a19db7\", \"7\": \"#f3f2f6\", \"8\": \"#301a8d\", \"9\": \"#5428ba\", \"10\": \"#bf972a\"}','좋아하는 영화'),(2,56,'2024-02-16 01:33:43',92,'2024-02-16 10:33:44','{\"1\": \"#fefef1\", \"2\": \"#f7d0b2\", \"3\": \"#fbbc7e\", \"4\": \"#b5e3d4\", \"5\": \"#7ecfc1\", \"6\": \"#824332\", \"7\": \"#61948e\", \"8\": \"#321107\", \"9\": \"#fb9457\", \"10\": \"#eb6844\"}','강쥐'),(3,57,'2024-02-16 01:35:57',93,'2024-02-16 10:35:58','{\"1\": \"#040b1f\", \"2\": \"#1a3141\", \"3\": \"#0b2031\", \"4\": \"#2c4451\", \"5\": \"#5f777b\", \"6\": \"#809693\", \"7\": \"#435b64\", \"8\": \"#a4b6ac\", \"9\": \"#eef1d8\", \"10\": \"#cad6c4\"}','날으는 할아버지'),(4,57,'2024-02-16 01:51:13',94,'2024-02-16 10:51:39','{\"1\": \"#040b1f\", \"2\": \"#1a3141\", \"3\": \"#0b2031\", \"4\": \"#2c4451\", \"5\": \"#5f777b\", \"6\": \"#809693\", \"7\": \"#435b64\", \"8\": \"#a4b6ac\", \"9\": \"#eef1d8\", \"11\": \"#681C1C\"}','날으는 할아버지_copy'),(4,58,'2024-02-16 01:56:49',95,'2024-02-16 10:56:50','{\"1\": \"#fef1f1\", \"2\": \"#fcf3eb\", \"3\": \"#ddd9da\", \"4\": \"#acacae\", \"5\": \"#909292\", \"6\": \"#c6c2c6\", \"7\": \"#6b6f71\", \"8\": \"#202423\", \"9\": \"#040404\", \"10\": \"#44474a\"}','고양이'),(4,43,'2024-02-16 01:58:16',96,'2024-02-16 10:58:16','{\"1\": \"#fdfdfb\", \"2\": \"#9bb895\", \"3\": \"#22ceac\", \"4\": \"#048eba\", \"5\": \"#08b0b8\", \"6\": \"#f83661\", \"7\": \"#f3c544\", \"8\": \"#fba20b\", \"9\": \"#fb6425\", \"10\": \"#081d44\"}','구매한 도안의 팔레트');
/*!40000 ALTER TABLE `palette` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `purchase`
--

DROP TABLE IF EXISTS `purchase`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `purchase` (
  `purchase_price` int NOT NULL,
  `user_id` int NOT NULL,
  `draft_id` bigint NOT NULL,
  `purchase_timestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`user_id`,`draft_id`),
  KEY `FKbhdighedadjw3xi4elvupykg4` (`draft_id`),
  CONSTRAINT `FK86i0stm7cqsglqptdvjij1k3m` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`),
  CONSTRAINT `FKbhdighedadjw3xi4elvupykg4` FOREIGN KEY (`draft_id`) REFERENCES `draft` (`draft_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `purchase`
--

LOCK TABLES `purchase` WRITE;
/*!40000 ALTER TABLE `purchase` DISABLE KEYS */;
INSERT INTO `purchase` VALUES (0,2,50,'2024-02-16 01:24:32'),(0,3,41,'2024-02-16 01:04:38'),(0,3,42,'2024-02-16 00:42:30'),(300,4,43,'2024-02-16 01:58:16'),(0,7,42,'2024-02-16 00:44:24');
/*!40000 ALTER TABLE `purchase` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reply`
--

DROP TABLE IF EXISTS `reply`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `reply` (
  `reply_is_deleted` bit(1) NOT NULL,
  `user_id` int NOT NULL,
  `board_id` bigint NOT NULL,
  `id` bigint NOT NULL AUTO_INCREMENT,
  `reply_timestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `reply_content` varchar(100) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKapyyxlgntertu5okpkr685ir9` (`user_id`),
  KEY `FKcs9hiip0bv9xxfrgoj0lwv2dt` (`board_id`),
  CONSTRAINT `FKapyyxlgntertu5okpkr685ir9` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`),
  CONSTRAINT `FKcs9hiip0bv9xxfrgoj0lwv2dt` FOREIGN KEY (`board_id`) REFERENCES `board` (`board_id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reply`
--

LOCK TABLES `reply` WRITE;
/*!40000 ALTER TABLE `reply` DISABLE KEYS */;
INSERT INTO `reply` VALUES (_binary '\0',2,23,20,'2024-02-16 01:24:02','종이 꼬깃'),(_binary '\0',3,25,21,'2024-02-16 01:29:35','노트북이 뭔가요'),(_binary '\0',3,26,22,'2024-02-16 01:30:37','꼭 그려보고 싶었어요!'),(_binary '\0',4,24,23,'2024-02-16 01:32:04','우왕~~~'),(_binary '\0',4,26,24,'2024-02-16 01:32:13','다그닥'),(_binary '\0',3,28,25,'2024-02-16 01:33:10','같이 갈래요?');
/*!40000 ALTER TABLE `reply` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `user_birth` varchar(4) NOT NULL,
  `user_id` int NOT NULL AUTO_INCREMENT,
  `user_is_admin` bit(1) NOT NULL DEFAULT b'0',
  `user_is_withdraw` bit(1) NOT NULL DEFAULT b'0',
  `user_mileage` int NOT NULL DEFAULT '0',
  `user_nickname` varchar(50) NOT NULL,
  `user_gender` enum('male','female') NOT NULL,
  `user_profile` varchar(255) DEFAULT NULL,
  `user_refreshtoken` varchar(255) DEFAULT NULL,
  `user_social_id` varchar(255) NOT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES ('1999',1,_binary '\0',_binary '\0',5000,'백승윤','male',NULL,'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzM4NCJ9.eyJ1c2VySWQiOjEsImlhdCI6MTcwODA0NzMzNSwiZXhwIjoxNzA5MjU2OTM1fQ.CsDdCp2wTMYwf9t6DPywr9lvfqIozYHOxkJ-xRoFQ965iHkyN8j7Tk3guh6CBR-f','3327957726'),('1999',2,_binary '\0',_binary '\0',2700,'관리자','female',NULL,'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzM4NCJ9.eyJ1c2VySWQiOjIsImlhdCI6MTcwODA0NjcwMywiZXhwIjoxNzA5MjU2MzAzfQ.hYflvNh_7jzSSlt5y_ggf_aRHhxNl2NU8J-_pmR5PE0Cc1aEAmbhxn1WRgXFOERK','3333089981'),('1994',3,_binary '\0',_binary '\0',0,'이상협','male',NULL,'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzM4NCJ9.eyJ1c2VySWQiOjMsImlhdCI6MTcwODA0NzE1MiwiZXhwIjoxNzA5MjU2NzUyfQ.HIyfwgu1iSbyXBjxTSxpaJKzhNLGr6oLg_fsxyBwBqQN5IR17nbGXK_KzgWGTKMa','3343497531'),('1993',4,_binary '\0',_binary '\0',1000,'브러시내','female',NULL,'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzM4NCJ9.eyJ1c2VySWQiOjQsImlhdCI6MTcwODA0ODY2MSwiZXhwIjoxNzA5MjU4MjYxfQ.WzUwoI_wswrdMEn14ls_Or8pvpzUxRpX9LPnw2OAoUgyBx_O9OJ27kXVAEzh5nXM','3334168659'),('1994',5,_binary '\0',_binary '\0',0,'yjKim','male',NULL,'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzM4NCJ9.eyJ1c2VySWQiOjUsImlhdCI6MTcwODA0NTU0MCwiZXhwIjoxNzA5MjU1MTQwfQ.Be9PNBGIO6kDm9vfa_BjfdlewLHPuRZIpSqfEbmRYn9Gx5dtL6Tb6ErVqu_DvR2H','3341395110'),('1996',6,_binary '\0',_binary '\0',0,'소민','female',NULL,'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzM4NCJ9.eyJ1c2VySWQiOjYsImlhdCI6MTcwODAzNTE3NiwiZXhwIjoxNzA5MjQ0Nzc2fQ.9uSX1uTOMv8MpSUN5KZCa1KFCy_mmXOj-VTFLoAdToOyn60LMnKEW2Dh4Bdew8wB','3291603579'),('1994',7,_binary '\0',_binary '\0',0,'이상협','male',NULL,'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzM4NCJ9.eyJ1c2VySWQiOjcsImlhdCI6MTcwODA0NDIxOCwiZXhwIjoxNzA5MjUzODE4fQ.eFGVlXB1rop_fD_oa2JYYjsNRoXxv0j6I2fcasC0rt0KtY_EMbIaW4UsbxAePdbZ','3344921778');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `workplace`
--

DROP TABLE IF EXISTS `workplace`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `workplace` (
  `workplace_paint_price` smallint NOT NULL,
  `workplace_id` bigint NOT NULL AUTO_INCREMENT,
  `workplace_revenue` bigint DEFAULT '0',
  `workplace_name` varchar(30) DEFAULT NULL,
  `workplace_address` varchar(1000) DEFAULT NULL,
  PRIMARY KEY (`workplace_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `workplace`
--

LOCK TABLES `workplace` WRITE;
/*!40000 ALTER TABLE `workplace` DISABLE KEYS */;
INSERT INTO `workplace` VALUES (0,1,0,'멀티캠퍼스','서울 강남구 테헤란로 212');
/*!40000 ALTER TABLE `workplace` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-02-16 11:09:31
