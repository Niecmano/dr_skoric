/*
SQLyog Community v13.3.0 (64 bit)
MySQL - 10.4.32-MariaDB : Database - medilek_tim
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`medilek_tim` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci */;

USE `medilek_tim`;

/*Table structure for table `dostupantermin` */

DROP TABLE IF EXISTS `dostupantermin`;

CREATE TABLE `dostupantermin` (
  `datumVreme` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `sifraLekara` int(11) DEFAULT NULL,
  PRIMARY KEY (`datumVreme`),
  KEY `sifraLekara` (`sifraLekara`),
  CONSTRAINT `dostupantermin_ibfk_2` FOREIGN KEY (`sifraLekara`) REFERENCES `lekar` (`sifraLekara`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `dostupantermin` */

insert  into `dostupantermin`(`datumVreme`,`sifraLekara`) values 
('2026-01-21 14:30:00',12),
('2026-01-21 10:30:00',507),
('2026-01-30 08:30:00',514);

/*Table structure for table `izvestaj` */

DROP TABLE IF EXISTS `izvestaj`;

CREATE TABLE `izvestaj` (
  `sifraPac` int(11) NOT NULL,
  `datumVreme` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `sifraLekara` int(11) NOT NULL,
  `anamneza` varchar(1000) DEFAULT NULL,
  `dg` varchar(500) DEFAULT NULL,
  `terapija` varchar(1000) DEFAULT NULL,
  `nalaz` varchar(1000) DEFAULT NULL,
  `kontrola` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`sifraPac`,`datumVreme`,`sifraLekara`),
  KEY `sifraLekara` (`sifraLekara`),
  KEY `sifraPac` (`sifraPac`,`sifraLekara`),
  CONSTRAINT `izvestaj_ibfk_3` FOREIGN KEY (`sifraPac`, `sifraLekara`) REFERENCES `zakazantermin` (`sifraPac`, `sifraLekara`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `izvestaj` */

insert  into `izvestaj`(`sifraPac`,`datumVreme`,`sifraLekara`,`anamneza`,`dg`,`terapija`,`nalaz`,`kontrola`) values 
(1,'2026-02-24 14:00:00',513,'Konstantovana je septoplastika.','Deviatio nasi septum\nAcuta tonsilis','Panklav 2 puta dnevno na 12h\nLysobact',NULL,NULL),
(3,'2026-01-20 13:00:00',4,'Pacijent se javlja zbog povremenog osećaja pritiska u grudima, koji se javlja pri fizičkom naporu i\npovlači u mirovanju. Tegobe traju nekoliko meseci, bez širenja bola u ruke ili vilicu. Navodi\npovremenu dispneju pri naporu i zamaranje. Negira gubitak svesti i lupanje srca.\nOd ranijih bolesti navodi arterijsku hipertenziju u trajanju od više godina. Porodična anamneza\npozitivna na kardiovaskularne bolesti. Ne puši, alkohol povremeno. Redovno ne uzima terapiju.','I10 – Arterijska hipertenzija\nI20.9 – Angina pectoris, nespecificirana\nI51.7 – Hipertrofija leve komore','Antihipertenzivna terapija (prema važećim smernicama)\nAntianginozna terapija\nPreporuka za redovno merenje krvnog pritiska\nDijetalni režim sa smanjenim unosom soli\nUmerena fizička aktivnost u skladu sa mogućnostima pacijenta',NULL,NULL),
(5,'2026-01-29 12:00:00',12,'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.','Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.','Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.',NULL,NULL),
(7,'2026-01-28 08:30:00',520,'Dete uzrasta 6 godina, roditelji navode povremeni zamor pri fizičkoj aktivnosti. Bez gubitka svesti, bez bolova u grudima. Trudnoća i porođaj uredni, psihomotorni razvoj odgovarajući uzrastu.','Suspektni funkcionalni srčani šum (Innocent heart murmur).','Praćenje od strane dečjeg kardiologa. Planirana dodatna dijagnostika (EHO srca). Za sada bez terapije, razmatra se intervencioni zahvat u doglednom periodu.',NULL,NULL);

/*Table structure for table `lekar` */

DROP TABLE IF EXISTS `lekar`;

CREATE TABLE `lekar` (
  `sifraLekara` int(11) NOT NULL AUTO_INCREMENT,
  `imePrez` varchar(50) NOT NULL,
  `sifraSpec` int(11) NOT NULL,
  `sifraSubspec` int(11) DEFAULT NULL,
  PRIMARY KEY (`sifraLekara`),
  KEY `sifraSpec` (`sifraSpec`),
  KEY `sifraSubspec` (`sifraSubspec`),
  CONSTRAINT `lekar_ibfk_1` FOREIGN KEY (`sifraSpec`) REFERENCES `specijalizacija` (`sifraSpec`),
  CONSTRAINT `lekar_ibfk_2` FOREIGN KEY (`sifraSubspec`) REFERENCES `specijalizacija` (`sifraSpec`)
) ENGINE=InnoDB AUTO_INCREMENT=524 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `lekar` */

insert  into `lekar`(`sifraLekara`,`imePrez`,`sifraSpec`,`sifraSubspec`) values 
(1,'prof. dr Dejan Škorić',4002,1567),
(2,'dr Željko Smoljanić',1964,NULL),
(3,'prof. dr Branislava Milenković',1378,NULL),
(4,'prim. dr Vesna Babić',1017,4003),
(5,'mr. sci. med. dr Lena Martinović',830,NULL),
(6,'prof. dr Marina Nikolić Đurović',1017,2043),
(7,'dr Zvezdana Jemuović',1017,NULL),
(8,'doc. dr Miodrag Vukčević',1378,NULL),
(9,'prof. dr Sanvila Rašković',820,NULL),
(10,'prof. dr Zoran Golubović',4020,NULL),
(11,'prim. mr sci. med. dr Dragan Vukanić',4020,991),
(12,'ass. mr sci. med. dr Branislav Trifunović',810,NULL),
(501,'dr Marko Kostić',800,NULL),
(502,'prim. dr sci. med. Miljan Mihajlović',2289,NULL),
(503,'prof. dr Aleksandar Sretenović',4020,2334),
(504,'prim. dr sci. med. Dragana Vujović',4020,NULL),
(505,'prof. dr sc. med. Radoje Doder',1017,1294),
(506,'spec. dr med. Ivana Petrov',2391,NULL),
(507,'dr Miodrag Stevović',2391,NULL),
(508,'prof. dr Ivana Petronić Marković',5100,990),
(509,'prof. dr Dragana Ćirović',5100,980),
(510,'dr Margita Mijušković',5200,NULL),
(511,'dr Jesenka Grebenarović',1964,NULL),
(512,'prof. dr Dimitrije Nikolić',4002,1791),
(513,'dr Aleksandar Vujović',9147,NULL),
(514,'ass. dr sci. med. Stefan Đorđević',4002,6934),
(515,'prim. dr sci. med. Dejan Stojiljković',2247,NULL),
(516,'prim. dr Gordana Sušić',4002,1189),
(517,'prof. dr sci. med. Miloš Petković',2391,NULL),
(518,'dr sci. med. Nina Ristić',4002,1295),
(519,'dr Borko Stojanović',4020,NULL),
(520,'ass. dr sci. med. Igor Stefanović',4002,5871),
(521,'spec. Snežana Živković',5400,NULL),
(522,'ass. dr sci. med. Vesna Reljić',5200,NULL),
(523,'dr Radule Vukićević',4002,5871);

/*Table structure for table `pacijent` */

DROP TABLE IF EXISTS `pacijent`;

CREATE TABLE `pacijent` (
  `sifraPac` int(11) NOT NULL AUTO_INCREMENT,
  `imePrez` varchar(100) NOT NULL,
  `datumRodj` date NOT NULL,
  `telefon` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`sifraPac`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `pacijent` */

insert  into `pacijent`(`sifraPac`,`imePrez`,`datumRodj`,`telefon`) values 
(1,'Nemanja Planic','2002-09-12','062490093'),
(2,'Mina Kovacevic','2004-06-08','061283754'),
(3,'Marko Miletic','2010-12-15','0607443941'),
(4,'Dunja Petrovic','2015-12-03','0653994002'),
(5,'Katarina Maric','2016-02-10','0676483058'),
(6,'Marko Jokic','2006-02-16','0624030685'),
(7,'Marija Kostic','2012-01-27','643307887'),
(10,'Aleksandra Antonijevic','2018-01-18','634930540');

/*Table structure for table `specijalizacija` */

DROP TABLE IF EXISTS `specijalizacija`;

CREATE TABLE `specijalizacija` (
  `sifraSpec` int(11) NOT NULL,
  `nazivSpec` varchar(100) NOT NULL,
  PRIMARY KEY (`sifraSpec`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `specijalizacija` */

insert  into `specijalizacija`(`sifraSpec`,`nazivSpec`) values 
(800,'Dečija i grudna hirurgija'),
(810,'Dečija, plastična i rekonstruktivna hirurgija'),
(820,'Interna medicina i alergologija'),
(830,'Interna medicina i gastroenterohepatologija'),
(980,'Dečija rehabilitacija'),
(990,'Dečija fizijatrija'),
(991,'Dečija urologija'),
(1017,'Interna medicina'),
(1189,'Reumatologija'),
(1294,'Gastroenterologija'),
(1295,'Gastroenterohepatologija'),
(1378,'Pulmologija'),
(1482,'Nefrologija'),
(1567,'Hematologija'),
(1673,'Onkologija'),
(1791,'Neurologija'),
(1856,'Psihijatrija'),
(1964,'Radiologija'),
(2043,'Endokrinologija'),
(2079,'Patologija'),
(2247,'Grudna hirurgija'),
(2289,'Neurohirurgija'),
(2334,'Vaskularna hirurgija'),
(2391,'Anesteziologija i reanimatologija'),
(2426,'Pedijatrijska anesteziologija'),
(3098,'Neuroendokrinologija'),
(4001,'Hirurgija'),
(4002,'Pedijatrija'),
(4003,'Kardiologija'),
(4010,'Alergologija'),
(4011,'Imunologija'),
(4020,'Dečja hirurgija'),
(5100,'Fizikalna medicina i rehabilitacija'),
(5200,'Dermatovenerologija'),
(5400,'Nutricionizam i dijetetika'),
(5871,'Dečja kardiologija'),
(6934,'Dečja reumatologija'),
(7452,'Anesteziologija'),
(8620,'Pedijatrijska anesteziologija'),
(9147,'Otorinolaringologija');

/*Table structure for table `zakazantermin` */

DROP TABLE IF EXISTS `zakazantermin`;

CREATE TABLE `zakazantermin` (
  `sifraPac` int(11) NOT NULL,
  `datumVreme` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `sifraLekara` int(11) NOT NULL,
  PRIMARY KEY (`sifraPac`,`sifraLekara`),
  KEY `sifraLekara` (`sifraLekara`),
  CONSTRAINT `zakazantermin_ibfk_1` FOREIGN KEY (`sifraPac`) REFERENCES `pacijent` (`sifraPac`),
  CONSTRAINT `zakazantermin_ibfk_2` FOREIGN KEY (`sifraLekara`) REFERENCES `lekar` (`sifraLekara`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `zakazantermin` */

insert  into `zakazantermin`(`sifraPac`,`datumVreme`,`sifraLekara`) values 
(1,'2026-02-24 14:00:00',513),
(2,'2026-02-21 19:55:54',6),
(3,'2026-01-20 13:00:00',4),
(5,'2026-01-29 12:00:00',12),
(7,'2026-02-24 12:00:00',513),
(7,'2026-01-28 08:30:00',520);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
