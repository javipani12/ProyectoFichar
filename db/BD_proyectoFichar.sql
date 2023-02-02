-- MySQL dump 10.13  Distrib 8.0.31, for Win64 (x86_64)
--
-- Host: localhost    Database: empresa_v2
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
-- Table structure for table `departamentos`
--

DROP TABLE IF EXISTS `departamentos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `departamentos` (
  `idDepartamento` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`idDepartamento`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `departamentos`
--

LOCK TABLES `departamentos` WRITE;
/*!40000 ALTER TABLE `departamentos` DISABLE KEYS */;
INSERT INTO `departamentos` (`idDepartamento`, `nombre`) VALUES (1,'Administración'),(2,'Dirección'),(3,'Matemáticas'),(4,'Ciencias'),(5,'Informática');
/*!40000 ALTER TABLE `departamentos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `empleados`
--

DROP TABLE IF EXISTS `empleados`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `empleados` (
  `idEmpleado` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(30) NOT NULL,
  `apellidos` varchar(60) NOT NULL,
  `salario` float NOT NULL DEFAULT '1011.57',
  `email` varchar(100) DEFAULT NULL,
  `idDepartamento` int NOT NULL,
  `codigoEmp` decimal(4,0) DEFAULT NULL,
  PRIMARY KEY (`idEmpleado`),
  KEY `fk_emp_dep` (`idDepartamento`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `empleados`
--

LOCK TABLES `empleados` WRITE;
/*!40000 ALTER TABLE `empleados` DISABLE KEYS */;
INSERT INTO `empleados` (`idEmpleado`, `nombre`, `apellidos`, `salario`, `email`, `idDepartamento`, `codigoEmp`) VALUES (1,'admin','admin',5678.23,'admin@a.com',1,1234),(2,'Maria','Farias',1345.69,'lolalolitalola@lola.com',1,4321),(3,'Javier','Fernandez Paniagua',1485,'javier@javier.com',2,5896),(5,'Ana Maria','Herrera Flores',6231,'ana@ana.com',1,1912),(6,'Pedro','Truncado Moreno',1017,'p@p.com',4,1456),(8,'Amanda','Cintero Llano',1520,'a@cl.com',3,3246),(9,'Ramón','Fraile Curado',1017,'rfc@rfc.com',5,7806);
/*!40000 ALTER TABLE `empleados` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fichar`
--

DROP TABLE IF EXISTS `fichar`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `fichar` (
  `idFicha` int NOT NULL AUTO_INCREMENT,
  `idEmpleado` int DEFAULT NULL,
  `entrada` tinyint DEFAULT NULL,
  `salida` tinyint DEFAULT NULL,
  `fechaFicha` datetime DEFAULT NULL,
  PRIMARY KEY (`idFicha`),
  KEY `fk_fichar_empleados` (`idEmpleado`),
  CONSTRAINT `fk_fichar_empleados` FOREIGN KEY (`idEmpleado`) REFERENCES `empleados` (`idEmpleado`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fichar`
--

LOCK TABLES `fichar` WRITE;
/*!40000 ALTER TABLE `fichar` DISABLE KEYS */;
INSERT INTO `fichar` (`idFicha`, `idEmpleado`, `entrada`, `salida`, `fechaFicha`) VALUES (2,1,1,0,'2023-03-02 15:45:22'),(4,2,1,0,'2023-01-31 21:12:58'),(5,2,0,1,'2023-01-31 21:13:12'),(6,2,1,0,'2023-02-01 23:53:00'),(7,2,0,1,'2023-02-01 23:53:02'),(8,2,1,0,'2023-02-02 00:00:26'),(9,2,0,1,'2023-02-02 00:00:28');
/*!40000 ALTER TABLE `fichar` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-02-02  1:20:44
