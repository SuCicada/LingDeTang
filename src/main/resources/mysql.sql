-- MySQL dump 10.13  Distrib 5.7.27, for Linux (x86_64)
--
-- Host: 127.0.0.1    Database: lingdetang
-- ------------------------------------------------------
-- Server version	5.7.27-0ubuntu0.16.04.1

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
-- Table structure for table `ldt_admins`
--

DROP TABLE IF EXISTS `ldt_admins`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ldt_admins` (
  `admin_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `role_id` int(10) unsigned NOT NULL COMMENT '角色id',
  `admin_username` varchar(45) NOT NULL,
  `admin_password` varchar(45) NOT NULL,
  PRIMARY KEY (`admin_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ldt_admins`
--

LOCK TABLES `ldt_admins` WRITE;
/*!40000 ALTER TABLE `ldt_admins` DISABLE KEYS */;
/*!40000 ALTER TABLE `ldt_admins` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ldt_forums`
--

DROP TABLE IF EXISTS `ldt_forums`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ldt_forums` (
  `forum_id` int(11) NOT NULL AUTO_INCREMENT,
  `forum_name` varchar(45) NOT NULL COMMENT '板块名',
  `forum_desc` varchar(45) DEFAULT NULL COMMENT '板块描述',
  PRIMARY KEY (`forum_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ldt_forums`
--

LOCK TABLES `ldt_forums` WRITE;
/*!40000 ALTER TABLE `ldt_forums` DISABLE KEYS */;
/*!40000 ALTER TABLE `ldt_forums` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ldt_posts`
--

DROP TABLE IF EXISTS `ldt_posts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ldt_posts` (
  `post_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `topic_id` int(10) unsigned NOT NULL,
  `user_id` int(10) unsigned NOT NULL,
  `post_index` int(11) NOT NULL COMMENT '帖子楼层，从2开始，第一层是楼主。',
  `post_text` mediumtext NOT NULL,
  `post_heart` int(11) NOT NULL COMMENT '赞数',
  `post_create_date` datetime NOT NULL,
  PRIMARY KEY (`post_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ldt_posts`
--

LOCK TABLES `ldt_posts` WRITE;
/*!40000 ALTER TABLE `ldt_posts` DISABLE KEYS */;
/*!40000 ALTER TABLE `ldt_posts` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ldt_topics`
--

DROP TABLE IF EXISTS `ldt_topics`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ldt_topics` (
  `topic_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `forum_id` int(10) unsigned NOT NULL,
  `user_id` int(10) unsigned NOT NULL,
  `topic_title` varchar(45) NOT NULL,
  `topic_text` mediumtext NOT NULL COMMENT '正文第一层',
  `topic_view_count` int(11) NOT NULL COMMENT '观看量',
  `topic_post_count` int(11) NOT NULL COMMENT '帖子数',
  `topic_update_date` datetime NOT NULL,
  `topic_create_date` datetime NOT NULL,
  PRIMARY KEY (`topic_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ldt_topics`
--

LOCK TABLES `ldt_topics` WRITE;
/*!40000 ALTER TABLE `ldt_topics` DISABLE KEYS */;
/*!40000 ALTER TABLE `ldt_topics` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ldt_users`
--

DROP TABLE IF EXISTS `ldt_users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ldt_users` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(20) NOT NULL,
  `user_password` varchar(20) NOT NULL,
  `user_nickname` varchar(20) DEFAULT NULL COMMENT '昵称',
  `user_sex` varchar(20) NOT NULL DEFAULT '0' COMMENT '0 未知 1 男 2 female',
  `user_email` varchar(20) DEFAULT NULL,
  `user_signature` varchar(200) DEFAULT NULL,
  `user_photo` blob,
  `user_phone` int(13) DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `ldt_users_user_id_uindex` (`user_id`),
  UNIQUE KEY `ldt_users_username_uindex` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ldt_users`
--

LOCK TABLES `ldt_users` WRITE;
/*!40000 ALTER TABLE `ldt_users` DISABLE KEYS */;
INSERT INTO `ldt_users` VALUES (1,'tom','123',NULL,'0','1212',NULL,NULL,NULL);
/*!40000 ALTER TABLE `ldt_users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-10-21 15:47:54
