-- MySQL dump 10.13  Distrib 8.0.27, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: tomolist
-- ------------------------------------------------------
-- Server version	8.0.27

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
-- Table structure for table `amigos`
--

set global innodb_file_format = BARRACUDA;
set global innodb_large_prefix = ON;

DROP TABLE IF EXISTS `amigos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `amigos` (
  `idamistad` int NOT NULL AUTO_INCREMENT,
  `aceptado` bit(1) NOT NULL,
  `idautor` int DEFAULT NULL,
  `idreceptor` int DEFAULT NULL,
  PRIMARY KEY (`idamistad`),
  KEY `FKeue3ijaf5vc1r8h7vqin79oju` (`idautor`),
  KEY `FK32jvyjhq2ydh3jwj2xglltr4x` (`idreceptor`),
  CONSTRAINT `FK32jvyjhq2ydh3jwj2xglltr4x` FOREIGN KEY (`idreceptor`) REFERENCES `usuarios` (`idusuario`) ON DELETE CASCADE,
  CONSTRAINT `FKeue3ijaf5vc1r8h7vqin79oju` FOREIGN KEY (`idautor`) REFERENCES `usuarios` (`idusuario`) ON DELETE CASCADE
) ENGINE=InnoDB ROW_FORMAT=DYNAMIC AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `amigos`
--

LOCK TABLES `amigos` WRITE;
/*!40000 ALTER TABLE `amigos` DISABLE KEYS */;
INSERT INTO `amigos` VALUES (1,_binary '',1,2),(2,_binary '\0',1,3),(3,_binary '',2,3);
/*!40000 ALTER TABLE `amigos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `comentarios`
--

DROP TABLE IF EXISTS `comentarios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `comentarios` (
  `idcomentario` int NOT NULL AUTO_INCREMENT,
  `contenido` varchar(150) NOT NULL,
  `fecha` datetime(6) NOT NULL,
  `idcomentpadre` int DEFAULT NULL,
  `identrada` int DEFAULT NULL,
  `idusuario` int DEFAULT NULL,
  PRIMARY KEY (`idcomentario`),
  KEY `FKn8ppt4l7qgixlnnp40uu4jkc` (`idcomentpadre`),
  KEY `FKs9v5xs7799qd5lr6gura8yuh2` (`identrada`),
  KEY `FK9bfwtu5ra26c6rfl1lq8lirw5` (`idusuario`),
  CONSTRAINT `FK9bfwtu5ra26c6rfl1lq8lirw5` FOREIGN KEY (`idusuario`) REFERENCES `usuarios` (`idusuario`) ON DELETE CASCADE,
  CONSTRAINT `FKn8ppt4l7qgixlnnp40uu4jkc` FOREIGN KEY (`idcomentpadre`) REFERENCES `comentarios` (`idcomentario`) ON DELETE CASCADE,
  CONSTRAINT `FKs9v5xs7799qd5lr6gura8yuh2` FOREIGN KEY (`identrada`) REFERENCES `entradas` (`identrada`) ON DELETE CASCADE
) ENGINE=InnoDB ROW_FORMAT=DYNAMIC AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comentarios`
--

LOCK TABLES `comentarios` WRITE;
/*!40000 ALTER TABLE `comentarios` DISABLE KEYS */;
INSERT INTO `comentarios` VALUES (1,'Buenaaas','2021-05-20 12:54:34.000000',NULL,1,1),(2,'QUe tal tio','2021-05-20 12:56:34.000000',1,1,2),(3,'Eyy','2021-05-20 12:57:34.000000',1,1,3),(4,'Buenaaas','2021-05-20 12:54:34.000000',NULL,1,1),(5,'QUe tal tio','2021-05-20 12:56:34.000000',1,25,2),(6,'assdsad tio','2021-05-24 12:56:34.000000',1,25,2),(7,'Eyy','2021-05-20 12:57:34.000000',NULL,26,3),(8,'Buenaaas','2021-05-20 12:54:34.000000',NULL,1,1),(9,'Pepeeee','2021-05-20 12:52:34.000000',1,25,3),(10,'Eyy','2021-05-20 12:57:34.000000',1,26,3),(11,'Buenaaas','2021-05-20 12:54:34.000000',NULL,26,1),(12,'QUe tal tio','2021-05-20 12:56:34.000000',1,26,2),(13,'Eyy','2021-05-20 12:57:34.000000',1,26,3);
/*!40000 ALTER TABLE `comentarios` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `entradas`
--

DROP TABLE IF EXISTS `entradas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `entradas` (
  `identrada` int NOT NULL AUTO_INCREMENT,
  `contenido` varchar(250) DEFAULT NULL,
  `fecha` datetime(6) NOT NULL,
  `imagen` varchar(250) DEFAULT NULL,
  `titulo` varchar(70) NOT NULL,
  `idautor` int DEFAULT NULL,
  PRIMARY KEY (`identrada`),
  KEY `FKk6vgw5y6weua7p7h75ejhyb26` (`idautor`),
  CONSTRAINT `FKk6vgw5y6weua7p7h75ejhyb26` FOREIGN KEY (`idautor`) REFERENCES `usuarios` (`idusuario`) ON DELETE CASCADE
) ENGINE=InnoDB ROW_FORMAT=DYNAMIC AUTO_INCREMENT=32 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `entradas`
--

LOCK TABLES `entradas` WRITE;
/*!40000 ALTER TABLE `entradas` DISABLE KEYS */;
INSERT INTO `entradas` VALUES (1,'Lores sit amet eros. Quisque sapien turpis, tristique et ornare facilisis, tempor ut nunc. Aliquam erat volutpat. ','2021-03-25 11:50:34.000000','https://images.pexels.com/photos/5767607/pexels-photo-5767607.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940','Entrada1',1),(2,'Sed ultricies, felis ut laoreet tincidunt, ipsum quam facilisis nibh, sed fringilla arcu diam sit amet eros. Quisque sapien turpis, tristique et ornare facilisis, tempor ut nunc. Aliquam erat volutpat. ','2021-05-20 12:22:34.000000','https://images.pexels.com/photos/7047538/pexels-photo-7047538.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940','Entrada2',1),(3,'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nam eget lobortis quam. Nullam gravida tempus ultricies. Maecevolutpat. ','2021-05-20 12:30:34.000000','https://images.pexels.com/photos/7196477/pexels-photo-7196477.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940','Entrada3',1),(4,'Lorem ipsum dolor sit amet, consectetur a. Aliquam erat volutpat. ','2021-05-20 05:23:34.000000','https://images.pexels.com/photos/735911/pexels-photo-735911.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940','Titular',2),(5,'Lorem ipsum dolor sit quam facilisis nibh, sed fringilla arcu diam sit amet eros. Quisque sapien turpis, tristique et ornare facilisis, tempor ut nunc. Aliquam erat volutpat. ','2021-05-11 15:58:34.000000','https://images.pexels.com/photos/6156972/pexels-photo-6156972.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940','Ejemplisimo',3),(6,'Lorem ipsum dolor sit amet, consectetur a. Aliquam erat volutpat. ','2021-05-13 03:14:34.000000','https://images.pexels.com/photos/533446/pexels-photo-533446.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940','Titulardo',2),(7,'Lorem ipsum dolor sit quam facilisis nibh, sed fringilla arcu diam sit amet eros. Quisque sapien turpis, tristique et ornare facilisis, tempor ut nunc. Aliquam erat volutpat. ','2021-05-16 14:58:34.000000','https://images.pexels.com/photos/2694434/pexels-photo-2694434.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940','Fotito',3),(8,'Lorem ipsum dolor sit amet, consectetur a. Aliquam erat volutpat. ','2021-05-20 15:12:34.000000','https://images.pexels.com/photos/9183256/pexels-photo-9183256.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940','Titulador',2),(9,'Lorem ipsum dolor sit quam facilisis nibh, sed fringilla arcu diam sit amet eros. Quisque sapien turpis, tristique et ornare facilisis, tempor ut nunc. Aliquam erat volutpat. ','2021-05-15 08:58:34.000000','https://images.pexels.com/photos/10095818/pexels-photo-10095818.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940','Hola Mundo',3),(10,'Lores sit amet eros. Quisque sapien turpis, tristique et ornare facilisis, tempor ut nunc. Aliquam erat volutpat. ','2021-08-13 04:45:34.000000','https://images.pexels.com/photos/1161547/pexels-photo-1161547.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940','Entrada1',1),(11,'Sed ultricies, felis ut laoreet tincidunt, ipsum quam facilisis nibh, sed fringilla arcu diam sit amet eros. Quisque sapien turpis, tristique et ornare facilisis, tempor ut nunc. Aliquam erat volutpat. ','2021-03-25 20:15:34.000000','https://images.pexels.com/photos/3812048/pexels-photo-3812048.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940','Adivinamelo',1),(12,'Lores sit amet eros. Quisque sapien turpis, tristique et ornare facilisis, tempor ut nunc. Aliquam erat volutpat. ','2021-06-25 01:10:34.000000','https://images.pexels.com/photos/973506/pexels-photo-973506.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940','Ay no que',1),(13,'Sed ultricies, felis ut laoreet tincidunt, ipsum quam facilisis nibh, sed fringilla arcu diam sit amet eros. Quisque sapien turpis, tristique et ornare facilisis, tempor ut nunc. Aliquam erat volutpat. ','2021-03-09 23:11:34.000000','https://images.pexels.com/photos/97077/pexels-photo-97077.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940','Pepe fulanito',1),(14,'Lores sit amet eros. Quisque sapien turpis, tristique et ornare facilisis, tempor ut nunc. Aliquam erat volutpat. ','2021-08-23 04:10:34.000000','https://images.pexels.com/photos/1918290/pexels-photo-1918290.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940','En plan',1),(15,'Sed ultricies, felis ut laoreet tincidunt, ipsum quam facilisis nibh, sed fringilla arcu diam sit amet eros. Quisque sapien turpis, tristique et ornare facilisis, tempor ut nunc. Aliquam erat volutpat. ','2021-02-03 18:14:34.000000','https://images.pexels.com/photos/165236/pexels-photo-165236.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940','Titulazo',1),(16,'Lores sit amet eros. Quisque sapien turpis, tristique et ornare facilisis, tempor ut nunc. Aliquam erat volutpat. ','2021-07-26 15:10:34.000000','https://images.pexels.com/photos/247676/pexels-photo-247676.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500','Casa',1),(17,'Sed ultricies, felis ut laoreet tincidunt, ipsum quam facilisis nibh, sed fringilla arcu diam sit amet eros. Quisque sapien turpis, tristique et ornare facilisis, tempor ut nunc. Aliquam erat volutpat. ','2021-05-14 11:43:34.000000','https://images.pexels.com/photos/34225/spider-web-with-water-beads-network-dewdrop.jpg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940','Titulo entrada largo',1),(18,'Lores sit amet eros. Quisque sapien turpis, tristique et ornare facilisis, tempor ut nunc. Aliquam erat volutpat. ','2021-01-14 12:45:34.000000','https://images.pexels.com/photos/2002719/pexels-photo-2002719.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940','Limpa baño',1),(19,'Sed ultricies, felis ut laoreet tincidunt, ipsum quam facilisis nibh, sed fringilla arcu diam sit amet eros. Quisque sapien turpis, tristique et ornare facilisis, tempor ut nunc. Aliquam erat volutpat. ','2021-02-20 08:22:34.000000','https://images.pexels.com/photos/270700/pexels-photo-270700.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940','Titulo entrada',1),(20,'Sed ultricies, felis ut laoreet tincidunt, ipsum quam facilisis nibh, sed fringilla arcu diam sit amet eros. Quisque sapien turpis, tristique et ornare facilisis, tempor ut nunc. Aliquam erat volutpat. ','2021-05-14 11:43:34.000000','https://images.pexels.com/photos/3819818/pexels-photo-3819818.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940','XXFFFFFA',1),(21,'Lores sit amet eros. Quisque sapien turpis, tristique et ornare facilisis, tempor ut nunc. Aliquam erat volutpat. ','2021-06-25 01:10:34.000000','https://images.pexels.com/photos/2603464/pexels-photo-2603464.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940','XADSA',1),(22,'Lores sit amet eros. Quisque sapien turpis, tristique et ornare facilisis, tempor ut nunc. Aliquam erat volutpat. ','2021-06-20 15:30:34.000000','https://images.pexels.com/photos/2570139/pexels-photo-2570139.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940','Lava los platos',2),(23,'Sed ultricies, felis ut laoreet tincidunt, ipsum quam facilisis nibh, sed fringilla arcu diam sit amet eros. Quisque sapien turpis, tristique et ornare facilisis, tempor ut nunc. Aliquam erat volutpat. ','2021-01-13 16:30:34.000000','https://images.pexels.com/photos/3571065/pexels-photo-3571065.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940','DataEjemplo',2),(24,'Lores sit amet eros. Quisque sapien turpis, tristique et ornare facilisis, tempor ut nunc. Aliquam erat volutpat. ','2021-02-20 23:45:34.000000','https://images.pexels.com/photos/4394349/pexels-photo-4394349.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940','Botellon',3),(25,'Sed ultricies, felis ut laoreet tincidunt, ipsum quam facilisis nibh, sed fringilla arcu diam sit amet eros. Quisque sapien turpis, tristique et ornare facilisis, tempor ut nunc. Aliquam erat volutpat. ','2021-10-12 14:14:34.000000','https://images.pexels.com/photos/46798/the-ball-stadion-football-the-pitch-46798.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940','Practica',3),(26,'Lores sit amet eros. Quisque sapien turpis, tristique et ornare facilisis, tempor ut nunc. Aliquam erat volutpat. ','2021-07-20 21:22:34.000000','https://images.pexels.com/photos/1308713/pexels-photo-1308713.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940','MoonToGo',2),(27,'Sed ultricies, felis ut laoreet tincidunt, ipsum quam facilisis nibh, sed fringilla arcu diam sit amet eros. Quisque sapien turpis, tristique et ornare facilisis, tempor ut nunc. Aliquam erat volutpat. ','2021-01-20 02:15:34.000000','https://images.pexels.com/photos/177809/pexels-photo-177809.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940','Posteao',2),(28,'Lores sit amet eros. Quisque sapien turpis, tristique et ornare facilisis, tempor ut nunc. Aliquam erat volutpat. ','2021-06-13 14:10:34.000000','https://images.pexels.com/photos/1108099/pexels-photo-1108099.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940','Entrada titulo ejemplo',3),(29,'Sed ultricies, felis ut laoreet tincidunt, ipsum quam facilisis nibh, sed fringilla arcu diam sit amet eros. Quisque sapien turpis, tristique et ornare facilisis, tempor ut nunc. Aliquam erat volutpat. ','2021-05-10 12:30:34.000000','https://images.pexels.com/photos/850602/pexels-photo-850602.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940','EjemploPost',3),(30,'Lores sit amet eros. Quisque sapien turpis, tristique et ornare facilisis, tempor ut nunc. Aliquam erat volutpat. ','2021-03-10 04:45:34.000000','https://images.pexels.com/photos/189349/pexels-photo-189349.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940','Estaagauy',2),(31,'Sed ultricies, felis ut laoreet tincidunt, ipsum quam facilisis nibh, sed fringilla arcu diam sit amet eros. Quisque sapien turpis, tristique et ornare facilisis, tempor ut nunc. Aliquam erat volutpat. ','2021-05-20 15:22:34.000000','https://images.pexels.com/photos/1831236/pexels-photo-1831236.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940','ADivinas',2);
/*!40000 ALTER TABLE `entradas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `megustas`
--

DROP TABLE IF EXISTS `megustas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `megustas` (
  `idmegusta` int NOT NULL AUTO_INCREMENT,
  `identrada` int DEFAULT NULL,
  `idusuario` int DEFAULT NULL,
  PRIMARY KEY (`idmegusta`),
  KEY `FK30aaek17ead0mdmlq6ysq8pet` (`identrada`),
  KEY `FKoqelf67v701plrxvsfaci22ou` (`idusuario`),
  CONSTRAINT `FK30aaek17ead0mdmlq6ysq8pet` FOREIGN KEY (`identrada`) REFERENCES `entradas` (`identrada`) ON DELETE CASCADE,
  CONSTRAINT `FKoqelf67v701plrxvsfaci22ou` FOREIGN KEY (`idusuario`) REFERENCES `usuarios` (`idusuario`)
) ENGINE=InnoDB ROW_FORMAT=DYNAMIC AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `megustas`
--

LOCK TABLES `megustas` WRITE;
/*!40000 ALTER TABLE `megustas` DISABLE KEYS */;
INSERT INTO `megustas` VALUES (1,1,1),(2,2,1),(3,3,1),(4,26,2),(5,26,3),(6,25,1),(7,25,2),(8,25,3),(9,6,2),(10,6,2),(11,7,2),(12,7,1),(13,8,3),(14,8,2),(15,9,3),(16,10,2),(17,10,3);
/*!40000 ALTER TABLE `megustas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mensajes`
--

DROP TABLE IF EXISTS `mensajes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `mensajes` (
  `idmensaje` int NOT NULL AUTO_INCREMENT,
  `contenido` varchar(250) NOT NULL,
  `fecha` datetime(6) NOT NULL,
  `idautor` int DEFAULT NULL,
  `idreceptor` int DEFAULT NULL,
  PRIMARY KEY (`idmensaje`),
  KEY `FKktrcn8vx7c423aqprc9whbwbf` (`idautor`),
  KEY `FKn57x1hl2pqmwmtkkaiqbhwudi` (`idreceptor`),
  CONSTRAINT `FKktrcn8vx7c423aqprc9whbwbf` FOREIGN KEY (`idautor`) REFERENCES `usuarios` (`idusuario`) ON DELETE CASCADE,
  CONSTRAINT `FKn57x1hl2pqmwmtkkaiqbhwudi` FOREIGN KEY (`idreceptor`) REFERENCES `usuarios` (`idusuario`) ON DELETE CASCADE
) ENGINE=InnoDB ROW_FORMAT=DYNAMIC AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mensajes`
--

LOCK TABLES `mensajes` WRITE;
/*!40000 ALTER TABLE `mensajes` DISABLE KEYS */;
INSERT INTO `mensajes` VALUES (1,'Prueba','2021-05-20 12:50:30.000000',1,2),(2,'Prueba 2','2021-05-20 12:51:24.000000',2,1),(3,'Prueba 3','2021-05-20 12:52:54.000000',1,2),(4,'Prueba 4','2021-05-20 12:53:15.000000',1,2),(5,'Prueba 5','2021-05-20 12:54:34.000000',2,1);
/*!40000 ALTER TABLE `mensajes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `roles` (
  `idrol` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) NOT NULL,
  PRIMARY KEY (`idrol`)
) ENGINE=InnoDB ROW_FORMAT=DYNAMIC AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles`
--

LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` VALUES (1,'ROLE_ADMIN'),(2,'ROLE_ESTANDAR');
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuarios`
--

DROP TABLE IF EXISTS `usuarios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuarios` (
  `idusuario` int NOT NULL AUTO_INCREMENT,
  `biografia` varchar(300) DEFAULT NULL,
  `direccion` varchar(45) DEFAULT NULL,
  `edad` int NOT NULL,
  `email` varchar(320) NOT NULL,
  `fecha` date NOT NULL,
  `foto` varchar(250) DEFAULT NULL,
  `genero` varchar(1) DEFAULT NULL,
  `nombre` varchar(50) NOT NULL,
  `password` varchar(150) NOT NULL,
  `rawpass` varchar(150) NOT NULL,
  `telefono` varchar(40) DEFAULT NULL,
  `username` varchar(14) NOT NULL,
  `idrol` int DEFAULT NULL,
  PRIMARY KEY (`idusuario`),
  UNIQUE KEY `UK_kfsp0s1tflm1cwlj8idhqsad0` (`email`),
  UNIQUE KEY `UK_m2dvbwfge291euvmk6vkkocao` (`username`),
  KEY `FKa9re6cim5apoodf980gsxwgh7` (`idrol`),
  CONSTRAINT `FKa9re6cim5apoodf980gsxwgh7` FOREIGN KEY (`idrol`) REFERENCES `roles` (`idrol`)
) ENGINE=InnoDB ROW_FORMAT=DYNAMIC AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuarios`
--

LOCK TABLES `usuarios` WRITE;
/*!40000 ALTER TABLE `usuarios` DISABLE KEYS */;
INSERT INTO `usuarios` VALUES (1,'No sólo sobrevivió 500 con software de autoedición, como por ejemplo Aldus PageMaker, el cual incluye versiones de Lorem Ipsum.','Calle 13',22,'u@email.com','2021-05-20','https://images-na.ssl-images-amazon.com/images/I/610FlSOYxiL._AC_SX522_.jpg','H','Unai Gonzalez','$2y$10$8zHvivHClP.zLVJB0kwBLe4/XmbfR.HVZx2US.fF8X/Uf318cYOEi','123','666','usu1',2),(2,'Adivinas','Calle 13',22,'r@email.com','2021-05-20','https://statics.proyectoclubes.com/images/sporting/opengraph_image.png?2','H','Roberto Rodriguez','$2y$10$8zHvivHClP.zLVJB0kwBLe4/XmbfR.HVZx2US.fF8X/Uf318cYOEi','123','662','usu2',2),(3,'Adivinas','Calle 13',22,'x@email.com','2021-05-20','https://estaticos.muyinteresante.es/media/cache/1140x_thumb/uploads/images/gallery/594a1ced5bafe85dfd3c9869/gato-romano_0.jpg','M','Xenia Rodriguez','$2y$10$8zHvivHClP.zLVJB0kwBLe4/XmbfR.HVZx2US.fF8X/Uf318cYOEi','123','661','usu3',2),(4,'','',16,'admin@email.com','2021-05-20','','H','Administrador','$2a$10$vk1o5JMQqAIF1EV7L2j3kePhOOlRI84FK51XhyBRt5ITv85UIuOde','admin','','admin',1);
/*!40000 ALTER TABLE `usuarios` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-12-03 20:07:50
