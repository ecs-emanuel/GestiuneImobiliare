-- phpMyAdmin SQL Dump
-- version 5.0.3
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Mar 12, 2021 at 02:23 AM
-- Server version: 10.4.14-MariaDB
-- PHP Version: 7.4.11

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `gestiuneimobiliara`
--

-- --------------------------------------------------------

--
-- Table structure for table `agenti`
--

CREATE TABLE `agenti` (
  `indexAgent` int(11) NOT NULL,
  `userAgent` int(11) DEFAULT NULL,
  `persoanaAgent` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `agenti`
--

INSERT INTO `agenti` (`indexAgent`, `userAgent`, `persoanaAgent`) VALUES
(1, 1, 1),
(2, 2, 2);

-- --------------------------------------------------------

--
-- Table structure for table `apartamente`
--

CREATE TABLE `apartamente` (
  `indexApartament` int(11) NOT NULL,
  `etajApartament` varchar(15) DEFAULT NULL,
  `constructieApartament` int(11) DEFAULT NULL,
  `proprietateApartament` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `apartamente`
--

INSERT INTO `apartamente` (`indexApartament`, `etajApartament`, `constructieApartament`, `proprietateApartament`) VALUES
(5, 'Intermediar', 18, 35),
(6, 'Ultimul', 19, 36),
(7, 'Parter', 20, 37);

-- --------------------------------------------------------

--
-- Table structure for table `cartiere`
--

CREATE TABLE `cartiere` (
  `indexCartier` int(11) NOT NULL,
  `denumireCartier` varchar(30) DEFAULT NULL,
  `orasCartier` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `cartiere`
--

INSERT INTO `cartiere` (`indexCartier`, `denumireCartier`, `orasCartier`) VALUES
(1, 'Blocuri', 1),
(2, 'Centru', 1),
(3, 'Insula', 1),
(4, 'Lut', 1),
(5, 'Sarat', 1),
(6, 'Sancrai', 1),
(7, 'Andrei Muresanu', 2),
(8, 'Aurel Vlaicu', 2),
(9, 'Becas', 2),
(10, 'Bulgaria', 2),
(11, 'Buna Ziua', 2),
(12, 'Centru', 2),
(13, 'Colonia Borhanci', 2),
(14, 'Colonia Sopor', 2),
(15, 'Cordos', 2),
(16, 'Dambul Rotund', 2),
(17, 'Europa', 2),
(18, 'Faget', 2),
(19, 'Fanete', 2),
(20, 'Gheorgheni', 2),
(21, 'Grigorescu', 2),
(22, 'Gruia', 2),
(23, 'Intre Lacuri', 2),
(24, 'Iris', 2),
(25, 'Manastur', 2),
(26, 'Marasti', 2),
(27, 'Plopilor', 2),
(28, 'Someseni', 2),
(29, 'Tetarom', 2),
(30, 'Zorilor', 2),
(31, 'Centru', 3),
(32, 'Dealul Florilor', 3),
(33, 'Mulatau', 3),
(34, 'Ocna Dejului', 3),
(35, 'Peste Somsei', 3),
(36, 'Triaj', 3),
(37, 'Valea Codorului', 3),
(38, 'Viile Dejului', 3),
(39, 'Centru', 4),
(40, 'Centru', 5),
(41, 'Catun Harcana', 6),
(42, 'Centru', 6),
(43, 'Baile Sarate', 6),
(44, 'Oprisan', 6),
(45, 'Poiana', 6),
(46, 'Turda Noua', 6);

-- --------------------------------------------------------

--
-- Table structure for table `casute`
--

CREATE TABLE `casute` (
  `indexCasa` int(11) NOT NULL,
  `constructieCasa` int(11) DEFAULT NULL,
  `proprietateCasa` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `casute`
--

INSERT INTO `casute` (`indexCasa`, `constructieCasa`, `proprietateCasa`) VALUES
(9, 16, 33),
(10, 17, 34);

-- --------------------------------------------------------

--
-- Table structure for table `clienti`
--

CREATE TABLE `clienti` (
  `indexClient` int(11) NOT NULL,
  `persoanaClient` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `clienti`
--

INSERT INTO `clienti` (`indexClient`, `persoanaClient`) VALUES
(23, 29),
(24, 30),
(25, 31),
(26, 32),
(27, 33),
(28, 34),
(29, 35),
(30, 36);

-- --------------------------------------------------------

--
-- Table structure for table `compartimentari`
--

CREATE TABLE `compartimentari` (
  `indexCompartimentare` int(11) NOT NULL,
  `openspace` int(11) DEFAULT NULL,
  `living` int(11) DEFAULT NULL,
  `dormitor` int(11) DEFAULT NULL,
  `dressing` int(11) DEFAULT NULL,
  `bucatarie` int(11) DEFAULT NULL,
  `debara` int(11) DEFAULT NULL,
  `baie` int(11) DEFAULT NULL,
  `hol` int(11) DEFAULT NULL,
  `mansarda` int(11) DEFAULT NULL,
  `balcon` int(11) DEFAULT NULL,
  `terasa` int(11) DEFAULT NULL,
  `gradina` int(11) DEFAULT NULL,
  `parcare` int(11) DEFAULT NULL,
  `garaj` int(11) DEFAULT NULL,
  `boxa` int(11) DEFAULT NULL,
  `pod` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `compartimentari`
--

INSERT INTO `compartimentari` (`indexCompartimentare`, `openspace`, `living`, `dormitor`, `dressing`, `bucatarie`, `debara`, `baie`, `hol`, `mansarda`, `balcon`, `terasa`, `gradina`, `parcare`, `garaj`, `boxa`, `pod`) VALUES
(17, 0, 2, 4, 2, 1, 2, 2, 4, 0, 2, 1, 1, 0, 1, 1, 1),
(18, 0, 1, 3, 0, 1, 2, 2, 2, 0, 0, 1, 1, 0, 1, 1, 1),
(19, 0, 1, 1, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0),
(20, 1, 0, 2, 0, 0, 1, 1, 1, 0, 1, 0, 0, 1, 0, 0, 0),
(21, 0, 1, 3, 1, 1, 1, 2, 1, 0, 2, 0, 0, 0, 1, 1, 0);

-- --------------------------------------------------------

--
-- Table structure for table `comune`
--

CREATE TABLE `comune` (
  `indexComuna` int(11) NOT NULL,
  `denumireComuna` varchar(30) DEFAULT NULL,
  `judetComuna` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `comune`
--

INSERT INTO `comune` (`indexComuna`, `denumireComuna`, `judetComuna`) VALUES
(1, 'Aghiresu', 1),
(2, 'Aiton', 1),
(3, 'Alunis', 1),
(4, 'Apahida', 1),
(5, 'Aschileu', 1),
(6, 'Baciu', 1),
(7, 'Baisoara', 1),
(8, 'Belis', 1),
(9, 'Bobalna', 1),
(10, 'Bontida', 1),
(11, 'Borsa', 1),
(12, 'Buza', 1),
(13, 'Caianu', 1),
(14, 'Calarasi', 1),
(15, 'Calatele', 1),
(16, 'Camarasu', 1),
(17, 'Capusu Mare', 1),
(18, 'Caseiu', 1),
(19, 'Catina', 1),
(20, 'Catcau', 1),
(21, 'Ceanu Mare', 1),
(22, 'Chinteni', 1),
(23, 'Chiuiesti', 1),
(24, 'Ciucea', 1),
(25, 'Ciurila', 1),
(26, 'Cojocna', 1),
(27, 'Cornesti', 1),
(28, 'Cuzdrioada', 1),
(29, 'Dabaca', 1),
(30, 'Feleacu', 1),
(31, 'Fizesu Gherlii', 1),
(32, 'Floresti', 1),
(33, 'Frata', 1),
(34, 'Garbau', 1),
(35, 'Geaca', 1),
(36, 'Gilau', 1),
(37, 'Iara', 1),
(38, 'Iclod', 1),
(39, 'Izvoru Crisului', 1),
(40, 'Jichisu de Jos', 1),
(41, 'Jucu', 1),
(42, 'Luna', 1),
(43, 'Maguri-Racatau', 1),
(44, 'Manastireni', 1),
(45, 'Margau', 1),
(46, 'Marisel', 1),
(47, 'Mica', 1),
(48, 'Mihai Viteazu', 1),
(49, 'Mintiu Gherlii', 1),
(50, 'Mociu', 1),
(51, 'Moldovenesti', 1),
(52, 'Negreni', 1),
(53, 'Panticeu', 1),
(54, 'Palatca', 1),
(55, 'Petrestii de Jos', 1),
(56, 'Ploscos', 1),
(57, 'Ploieni', 1),
(58, 'Rasca', 1),
(59, 'Recea-Cristur', 1),
(60, 'Sacuieu', 1),
(61, 'Sandulesti', 1),
(62, 'Savadisla', 1),
(63, 'Sancraiu', 1),
(64, 'Sanmartin', 1),
(65, 'Sanpaul', 1),
(66, 'Sic', 1),
(67, 'Suatu', 1),
(68, 'Tritenii de Jos', 1),
(69, 'Tureni', 1),
(70, 'Taga', 1),
(71, 'Unguras', 1),
(72, 'Vad', 1),
(73, 'Valea Ierii', 1),
(74, 'Viisoara', 1),
(75, 'Vultureni', 1);

-- --------------------------------------------------------

--
-- Table structure for table `constructii`
--

CREATE TABLE `constructii` (
  `indexConstructie` int(11) NOT NULL,
  `suprafataUtilizabila` int(11) DEFAULT NULL,
  `suprafataConstructie` int(11) DEFAULT NULL,
  `inaltimeConstructie` int(11) DEFAULT NULL,
  `anConstructie` int(11) DEFAULT NULL,
  `structuraConstructie` varchar(15) DEFAULT NULL,
  `dispozitieActuala` varchar(15) DEFAULT NULL,
  `dispozitiePredare` varchar(15) DEFAULT NULL,
  `compartimentareConstructie` int(11) DEFAULT NULL,
  `parcelaConstructie` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `constructii`
--

INSERT INTO `constructii` (`indexConstructie`, `suprafataUtilizabila`, `suprafataConstructie`, `inaltimeConstructie`, `anConstructie`, `structuraConstructie`, `dispozitieActuala`, `dispozitiePredare`, `compartimentareConstructie`, `parcelaConstructie`) VALUES
(16, 120, 160, 1, 1990, 'Lemn', 'Finisat', 'Finisat', 17, 31),
(17, 120, 160, 0, 1950, 'Caramida', 'Renovare', 'Renovare', 18, 32),
(18, 40, 45, 4, 1970, 'FierBeton', 'Finisat', 'Finisat', 19, 33),
(19, 60, 78, 4, 2010, 'FierBeton', 'Semifinisat', 'Mobilat', 20, 34),
(20, 90, 120, 4, 2016, 'FierBeton', 'Semifinisat', 'Mobilat', 21, 35);

-- --------------------------------------------------------

--
-- Table structure for table `judete`
--

CREATE TABLE `judete` (
  `indexJudet` int(11) NOT NULL,
  `denumireJudet` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `judete`
--

INSERT INTO `judete` (`indexJudet`, `denumireJudet`) VALUES
(1, 'Cluj');

-- --------------------------------------------------------

--
-- Table structure for table `locatii`
--

CREATE TABLE `locatii` (
  `indexLocatie` int(11) NOT NULL,
  `judetLocatie` int(11) DEFAULT NULL,
  `orasLocatie` int(11) DEFAULT NULL,
  `cartierLocatie` int(11) DEFAULT NULL,
  `comunaLocatie` int(11) DEFAULT NULL,
  `satLocatie` int(11) DEFAULT NULL,
  `denumireLocatie` varchar(40) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `locatii`
--

INSERT INTO `locatii` (`indexLocatie`, `judetLocatie`, `orasLocatie`, `cartierLocatie`, `comunaLocatie`, `satLocatie`, `denumireLocatie`) VALUES
(1, 1, 1, 1, NULL, NULL, 'Axinte Sever'),
(62, 1, 2, 8, NULL, NULL, 'Str. Ciresilor nr. 1'),
(63, 1, 3, 32, NULL, NULL, 'Str. Vulturului nr 2'),
(64, 1, NULL, NULL, 4, 19, 'Str. Plaiuri nr. 3'),
(65, 1, NULL, NULL, 6, 33, 'Str. Crinzantemelor nr. 44'),
(66, 1, 1, 3, NULL, NULL, 'Str. Paris nr. 5'),
(67, 1, 2, 12, NULL, NULL, 'Str. Eroilor nr. 6'),
(68, 1, 2, 9, NULL, NULL, 'Str. Turnuri nr. 7'),
(69, 1, NULL, NULL, 5, 28, 'Str. Minerilor nr. 8'),
(70, 1, 1, 1, NULL, NULL, 'Str. Ciresilor nr. 33'),
(71, 1, 1, 3, NULL, NULL, 'Str. Merilor'),
(72, 1, 1, 2, NULL, NULL, 'Str. Caisului nr. 44'),
(73, 1, 2, 20, NULL, NULL, 'Str. Frunzisului nr. 11'),
(74, 1, 2, 25, NULL, NULL, 'Str. Mehedinti nr. 11'),
(75, 1, 2, 27, NULL, NULL, 'Str. Decebal nr. 31'),
(76, 1, 6, 44, NULL, NULL, 'Str. Unirii nr. 40');

-- --------------------------------------------------------

--
-- Table structure for table `orase`
--

CREATE TABLE `orase` (
  `indexOras` int(11) NOT NULL,
  `denumireOras` varchar(30) DEFAULT NULL,
  `judetOras` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `orase`
--

INSERT INTO `orase` (`indexOras`, `denumireOras`, `judetOras`) VALUES
(1, 'Campia Turzii', 1),
(2, 'Cluj-Napoca', 1),
(3, 'Dej', 1),
(4, 'Gherla', 1),
(5, 'Huedin', 1),
(6, 'Turda', 1);

-- --------------------------------------------------------

--
-- Table structure for table `parcele`
--

CREATE TABLE `parcele` (
  `indexParcela` int(11) NOT NULL,
  `suprafataParcela` int(11) DEFAULT NULL,
  `hasApa` tinyint(1) DEFAULT NULL,
  `hasGaz` tinyint(1) DEFAULT NULL,
  `hasElectricitate` tinyint(1) DEFAULT NULL,
  `hasCanalizare` tinyint(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `parcele`
--

INSERT INTO `parcele` (`indexParcela`, `suprafataParcela`, `hasApa`, `hasGaz`, `hasElectricitate`, `hasCanalizare`) VALUES
(29, 10000, 1, 1, 1, 1),
(30, 20000, 1, 0, 0, 0),
(31, 500, 1, 1, 1, 1),
(32, 400, 1, 1, 1, 1),
(33, 0, 1, 1, 1, 1),
(34, 0, 1, 1, 1, 1),
(35, 0, 1, 1, 1, 1);

-- --------------------------------------------------------

--
-- Table structure for table `persoane`
--

CREATE TABLE `persoane` (
  `indexPersoana` int(11) NOT NULL,
  `numePersoana` varchar(30) DEFAULT NULL,
  `prenumePersoana` varchar(30) DEFAULT NULL,
  `telefonPersoana` varchar(20) DEFAULT NULL,
  `emailPersoana` varchar(30) DEFAULT NULL,
  `domiciliuPersoana` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `persoane`
--

INSERT INTO `persoane` (`indexPersoana`, `numePersoana`, `prenumePersoana`, `telefonPersoana`, `emailPersoana`, `domiciliuPersoana`) VALUES
(1, 'Popa', 'Andrei', '07', 'pandrei@email', 1),
(2, 'Popa', 'Marian', '07', 'pmarian@email', 1),
(29, 'Vaida ', 'Petrovan', '0711 111 111', 'vaidapetrovan@mail', 62),
(30, 'Chira', 'Sabina', '0722 222 222', 'chirasabina@mail', 63),
(31, 'Toma', 'Codrin', '0733 333 333', 'tomacodrin@mail', 64),
(32, 'Nastase', 'Radu', '0744 444 444', 'nastaseradu@email', 65),
(33, 'Nemes', 'Alin', '0755 555 555', 'nemesalin@email', 66),
(34, 'Kovacs', 'Mariana', '0766 666 666', 'kovacsmariana@email', 67),
(35, 'Chira', 'Sergiu', '0777 777 777', 'chirasergiu@email', 68),
(36, 'Mesesan', 'Claudiu', '0788 888 888', 'mesesanclaudiu@email', 69);

-- --------------------------------------------------------

--
-- Table structure for table `programari`
--

CREATE TABLE `programari` (
  `indexProgramare` int(11) NOT NULL,
  `dataProgramare` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `agentProgramare` int(11) DEFAULT NULL,
  `clientProgramare` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `programari`
--

INSERT INTO `programari` (`indexProgramare`, `dataProgramare`, `agentProgramare`, `clientProgramare`) VALUES
(48, '2021-03-12 00:16:00', 2, 24),
(49, '2021-03-13 01:16:00', 2, 24),
(50, '2021-03-14 03:16:00', 2, 29),
(51, '2021-03-16 04:16:00', 2, 28),
(52, '2021-03-17 05:16:00', 2, 30),
(53, '2021-03-18 06:16:00', 2, 26),
(54, '2021-03-18 07:16:00', 2, 27),
(55, '2021-03-18 08:16:00', 2, 25),
(56, '2021-03-19 09:16:00', 2, 23),
(57, '2021-03-06 12:30:00', 2, 23),
(58, '2021-03-05 12:30:00', 2, 25),
(59, '2021-03-04 12:30:00', 2, 27),
(60, '2021-03-03 12:30:00', 2, 30);

-- --------------------------------------------------------

--
-- Table structure for table `proprietati`
--

CREATE TABLE `proprietati` (
  `indexProprietate` int(11) NOT NULL,
  `titluProprietate` varchar(50) DEFAULT NULL,
  `descriereProprietate` varchar(150) DEFAULT NULL,
  `pretProprietate` int(11) DEFAULT NULL,
  `locatieProprietate` int(11) DEFAULT NULL,
  `proprietarProprietate` int(11) DEFAULT NULL,
  `agentProprietate` int(11) DEFAULT NULL,
  `dispozitieProprietate` varchar(15) DEFAULT NULL,
  `dataProprietate` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `proprietati`
--

INSERT INTO `proprietati` (`indexProprietate`, `titluProprietate`, `descriereProprietate`, `pretProprietate`, `locatieProprietate`, `proprietarProprietate`, `agentProprietate`, `dispozitieProprietate`, `dataProprietate`) VALUES
(31, 'Teren intravilan pentru constructii', 'Descriere teren intravilan pentru constructii', 1000, 70, 24, 2, 'Activ', '2021-03-12'),
(32, 'Teren extravilan foarte fertil', 'Teren extravilan fertil', 1000, 71, 28, 2, 'Activ', '2021-03-12'),
(33, 'Casa duplex 6 camere central', 'Casa duplex central', 10000, 72, 29, 2, 'Activ', '2021-03-12'),
(34, 'Casa 4 camere cartier gheorgheni', 'casa 4 camere zona linistita', 20000, 73, 27, 2, 'Activ', '2021-03-12'),
(35, 'Apartament 2 camere Manastur', 'apartament 2 camere manastur', 12000, 74, 23, 2, 'Activ', '2021-03-12'),
(36, 'Apartament 3 camere Plopilor', 'apartament 3 camere plopilor', 32000, 75, 24, 2, 'Activ', '2021-03-12'),
(37, 'Apartament 4 camere Turda/Oprisan', 'apartament 4 camere in turda cartier oprisan', 13000, 76, 26, 1, 'Activ', '2021-03-12');

-- --------------------------------------------------------

--
-- Table structure for table `sate`
--

CREATE TABLE `sate` (
  `indexSat` int(11) NOT NULL,
  `denumireSat` varchar(30) DEFAULT NULL,
  `comunaSat` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `sate`
--

INSERT INTO `sate` (`indexSat`, `denumireSat`, `comunaSat`) VALUES
(1, 'Aghiresu', 1),
(2, 'Aghiresu-Fabrici', 1),
(3, 'Arghisu', 1),
(4, 'Bagara', 1),
(5, 'Dancu', 1),
(6, 'Doroltu', 1),
(7, 'Inucu', 1),
(8, 'Leghia', 1),
(9, 'Macau', 1),
(10, 'Ticu', 1),
(11, 'Ticu-Colonie', 1),
(12, 'Aiton', 2),
(13, 'Rediu', 2),
(14, 'Alunis', 3),
(15, 'Comeni', 3),
(16, 'Ghirolt', 3),
(17, 'Pruneni', 3),
(18, 'Vale', 3),
(19, 'Apahida', 4),
(20, 'Bodrog', 4),
(21, 'Campenesti', 4),
(22, 'Corpadea', 4),
(23, 'Dezmir', 4),
(24, 'Pata', 4),
(25, 'Sanicoara', 4),
(26, 'Sub Coasta', 4),
(27, 'Aschileu Mare', 5),
(28, 'Aschileu Mic', 5),
(29, 'Cristorel', 5),
(30, 'Dorna', 5),
(31, 'Fodora', 5),
(32, 'Baciu', 6),
(33, 'Corusu', 6),
(34, 'Mera', 6),
(35, 'Popesti', 6),
(36, 'Radaia', 6),
(37, 'Salistea Noua', 6),
(38, 'Suceagu', 6),
(39, 'Baisoara', 7),
(40, 'Frasinet', 7),
(41, 'Moara de Padure', 7),
(42, 'Muntele Baisorii', 7),
(43, 'Muntele Bocului', 7),
(44, 'Muntele Cacoviei', 7),
(45, 'Muntele Filii', 7),
(46, 'Muntele Sacelului', 7),
(47, 'Sacel', 7),
(48, 'Balcesti', 8),
(49, 'Belis', 8),
(50, 'Dealu Botii', 8),
(51, 'Giurcuta de Jos', 8),
(52, 'Giurcuta de Sus', 8),
(53, 'Poiana Horea', 8),
(54, 'Smida', 8),
(55, 'Antas', 9),
(56, 'Babdiu', 9),
(57, 'Blidaresti', 9),
(58, 'Bobalna', 9),
(59, 'Cremenea', 9),
(60, 'Maia', 9),
(61, 'Osorhel', 9),
(62, 'Pruni', 9),
(63, 'Razbuneni', 9),
(64, 'Suaras', 9),
(65, 'Valcelele', 9),
(66, 'Bontida', 10),
(67, 'Coasta', 10),
(68, 'Rascruci', 10),
(69, 'Tauseni', 10),
(70, 'Borsa', 11),
(71, 'Borsa-Catun', 11),
(72, 'Borsa-Crestaia', 11),
(73, 'Ciumafaia', 11),
(74, 'Giula', 11),
(75, 'Buza', 12),
(76, 'Rotunda', 12),
(77, 'Barai', 13),
(78, 'Caianu', 13),
(79, 'Caianu Mic', 13),
(80, 'Caianu Vama', 13),
(81, 'Vaida-Camaras', 13),
(82, 'Valeni', 13),
(83, 'Bogata', 14),
(84, 'Calarasi', 14),
(85, 'Calarasi-Gara', 14),
(86, 'Calata', 15),
(87, 'Calatele', 15),
(88, 'Dealu Negru', 15),
(89, 'Finciu', 15),
(90, 'Valeni', 15),
(91, 'Camarasu', 16),
(92, 'Naoiu', 16),
(93, 'Samboleni', 16),
(94, 'Agarbiciu', 17),
(95, 'Balcesti', 17),
(96, 'Capusu Mare', 17),
(97, 'Capusu Mic', 17),
(98, 'Dangau Mare', 17),
(99, 'Dangau Mic', 17),
(100, 'Dumbrava', 17),
(101, 'Paniceni', 17),
(102, 'Straja', 17),
(103, 'Caseiu', 18),
(104, 'Comorata', 18),
(105, 'Coplean', 18),
(106, 'Custura', 18),
(107, 'Garbau Dejului', 18),
(108, 'Guga', 18),
(109, 'Leurda', 18),
(110, 'Rugasesti', 18),
(111, 'Salatruc', 18),
(112, 'Urisor', 18),
(113, 'Catina', 19),
(114, 'Copru', 19),
(115, 'Feldioara', 19),
(116, 'Hagau', 19),
(117, 'Hodaie', 19),
(118, 'Valea Calda', 19),
(119, 'Catcau', 20),
(120, 'Muncel', 20),
(121, 'Salisca', 20),
(122, 'Andici', 21),
(123, 'Boian', 21),
(124, 'Boldut', 21),
(125, 'Ceanu Mare', 21),
(126, 'Ciurgau', 21),
(127, 'Dosu Napului', 21),
(128, 'Fanate', 21),
(129, 'Hodai-Boian', 21),
(130, 'Iacobeni', 21),
(131, 'Mortesti', 21),
(132, 'Starcu', 21),
(133, 'Strucut', 21),
(134, 'Valuea lui Cati', 21),
(135, 'Chinteni', 22),
(136, 'Deusu', 22),
(137, 'Feiurdeni', 22),
(138, 'Macicasu', 22),
(139, 'Padureni', 22),
(140, 'Satu Lung', 22),
(141, 'Salistea Veche', 22),
(142, 'Sanmartin', 22),
(143, 'Vechea', 22),
(144, 'Chiuiesti', 23),
(145, 'Dosu Bricii', 23),
(146, 'Huta', 23),
(147, 'Magoaja', 23),
(148, 'Strambu', 23),
(149, 'Valea Caseielului', 23),
(150, 'Valea lui Opris', 23),
(151, 'Ciucea', 24),
(152, 'Vanatori', 24),
(153, 'Ciurila', 25),
(154, 'Filea de Jos', 25),
(155, 'Filea de Sus', 25),
(156, 'Padureni', 25),
(157, 'Prunis', 25),
(158, 'Salicea', 25),
(159, 'Saliste', 25),
(160, 'Sutu', 25),
(161, 'Boju', 26),
(162, 'Boj-Catun', 26),
(163, 'Cara', 26),
(164, 'Cojocna', 26),
(165, 'Huci', 26),
(166, 'Iuriu de Campie', 26),
(167, 'Moristi', 26),
(168, 'Straja', 26),
(169, 'Barlea', 27),
(170, 'Cornesti', 27),
(171, 'Igritia', 27),
(172, 'Lujerdiu', 27),
(173, 'Morau', 27),
(174, 'Stoiana', 27),
(175, 'Tiocu de Jos', 27),
(176, 'Tiocu de Sus', 27),
(177, 'Tioltiur', 27),
(178, 'Cuzdrioara', 28),
(179, 'Manasturel', 28),
(180, 'Valea Garboului', 28),
(181, 'Dabaca', 29),
(182, 'Luna de Jos', 29),
(183, 'Paglisa', 29),
(184, 'Casele Micesti', 30),
(185, 'Feleacu', 30),
(186, 'Gheorgheni', 30),
(187, 'Saradis', 30),
(188, 'Valcele', 30),
(189, 'Bont', 31),
(190, 'Fizesu Gherlii', 31),
(191, 'Lunca Bontului', 31),
(192, 'Nicula', 31),
(193, 'Sacalaia', 31),
(194, 'Floresti', 32),
(195, 'Luna de Sus', 32),
(196, 'Tauti', 32),
(197, 'Berchiesu', 33),
(198, 'Frata', 33),
(199, 'Oas', 33),
(200, 'Olariu', 33),
(201, 'Padurea Iacobeni', 33),
(202, 'Poiana Fratii', 33),
(203, 'Razoare', 33),
(204, 'Soporu de Campie', 33),
(205, 'Cornesti', 34),
(206, 'Garbau', 34),
(207, 'Nadaselu', 34),
(208, 'Turea', 34),
(209, 'Vistea', 34),
(210, 'Chiris', 35),
(211, 'Geaca', 35),
(212, 'Lacu', 35),
(213, 'Legii', 35),
(214, 'Puini', 35),
(215, 'Sucutard', 35),
(216, 'Gilau', 36),
(217, 'Somesu Cald', 36),
(218, 'Somesu Rece', 36),
(219, 'Agris', 37),
(220, 'Borzesti', 37),
(221, 'Buru', 37),
(222, 'Cacova Ierii', 37),
(223, 'Fagetu Ierii', 37),
(224, 'Iara', 37),
(225, 'Lungesti', 37),
(226, 'Masca', 37),
(227, 'Magura Ierii', 37),
(228, 'Ocolisel', 37),
(229, 'Surduc', 37),
(230, 'Valea Agrisului', 37),
(231, 'Valea Vadului', 37),
(232, 'Fundatura', 38),
(233, 'Iclod', 38),
(234, 'Iclozel', 38),
(235, 'Livada', 38),
(236, 'Orman', 38),
(237, 'Izvoru Crisului', 39),
(238, 'Nadasu', 39),
(239, 'Nearsova', 39),
(240, 'Saula', 39),
(241, 'Codor', 40),
(242, 'Jichisu de Jos', 40),
(243, 'Jichisu de Sus', 40),
(244, 'Sigau', 40),
(245, 'Tarpiu', 40),
(246, 'Gadalin', 41),
(247, 'Jucu de Mijloc', 41),
(248, 'Jucu de Sus', 41),
(249, 'Juc-Herghelie', 41),
(250, 'Visea', 41),
(251, 'Gligoresti', 42),
(252, 'Luna', 42),
(253, 'Luncani', 42),
(254, 'Maguri', 43),
(255, 'Maguri-Racatau', 43),
(256, 'Muntele Rece', 43),
(257, 'Ardeova', 44),
(258, 'Bedeciu', 44),
(259, 'Bica', 44),
(260, 'Dretea', 44),
(261, 'Manastireni', 44),
(262, 'Manasturu Romanesc', 44),
(263, 'Bociu', 45),
(264, 'Buteni', 45),
(265, 'Ciuleni', 45),
(266, 'Margau', 45),
(267, 'Rachitele', 45),
(268, 'Scrind-Frasinet', 45),
(269, 'Marisel', 46),
(270, 'Dambu Mare', 47),
(271, 'Manastirea', 47),
(272, 'Mica', 47),
(273, 'Nires', 47),
(274, 'Sanmarghita', 47),
(275, 'Valea Ciresoii', 47),
(276, 'Valuea Luncii', 47),
(277, 'Cheia', 48),
(278, 'Cornesti', 48),
(279, 'Mihai Viteazu', 48),
(280, 'Bunesti', 49),
(281, 'Mintiu Gherlii', 49),
(282, 'Nima', 49),
(283, 'Padurenii', 49),
(284, 'Petresti', 49),
(285, 'Salatiu', 49),
(286, 'Boteni', 50),
(287, 'Chesau', 50),
(288, 'Criseni', 50),
(289, 'Falca', 50),
(290, 'Ghirisu Roman', 50),
(291, 'Mociu', 50),
(292, 'Rosieni', 50),
(293, 'Turmasi', 50),
(294, 'Zorenii de Vale', 50),
(295, 'Badeni', 51),
(296, 'Moldovenesti', 51),
(297, 'Pietroasa', 51),
(298, 'Plaiesti', 51),
(299, 'Podeni', 51),
(300, 'Stejeris', 51),
(301, 'Bucea', 52),
(302, 'Negreni', 52),
(303, 'Prelucele', 52),
(304, 'Catalina', 53),
(305, 'Cublesu Somesan', 53),
(306, 'Darja', 53),
(307, 'Panticeu', 53),
(308, 'Sarata', 53),
(309, 'Bagaciu', 54),
(310, 'Muresenii de Campie', 54),
(311, 'Palatca', 54),
(312, 'Petea', 54),
(313, 'Sava', 54),
(314, 'Craesti', 55),
(315, 'Deleni', 55),
(316, 'Livada', 55),
(317, 'Petrestii de Jos', 55),
(318, 'Petrestii de Mijloc', 55),
(319, 'Petrestii de Sus', 55),
(320, 'Plaiuri', 55),
(321, 'Crairat', 56),
(322, 'Lobodas', 56),
(323, 'Ploscos', 56),
(324, 'Valea Florilor', 56),
(325, 'Bologa', 57),
(326, 'Cerbesti', 57),
(327, 'Hodisu', 57),
(328, 'Lunca Visagului', 57),
(329, 'Morlaca', 57),
(330, 'Poieni', 57),
(331, 'Tranisu', 57),
(332, 'Valea Draganului', 57),
(333, 'Dealu Mare', 58),
(334, 'Lapustesti', 58),
(335, 'Marcesti', 58),
(336, 'Rasca', 58),
(337, 'Caprioara', 59),
(338, 'Ciubanca', 59),
(339, 'Ciubancuta', 59),
(340, 'Elciu', 59),
(341, 'Escu', 59),
(342, 'Jurca', 59),
(343, 'Osoi', 59),
(344, 'Pustuta', 59),
(345, 'Recea-Cristur', 59),
(346, 'Rogojel', 60),
(347, 'Sacuieu', 60),
(348, 'Visagu', 60),
(349, 'Copaceni', 61),
(350, 'Sandulesti', 61),
(351, 'Finisel', 62),
(352, 'Hasdate', 62),
(353, 'Lita', 62),
(354, 'Liteni', 62),
(355, 'Savadisla', 62),
(356, 'Stolna', 62),
(357, 'Valisoara', 62),
(358, 'Vlaha', 62),
(359, 'Alunisu', 63),
(360, 'Braisoru', 63),
(361, 'Domosu', 63),
(362, 'Horlacea', 63),
(363, 'Sancraiu', 63),
(364, 'Ceaba', 64),
(365, 'Cutca', 64),
(366, 'Diviciorii Mari', 64),
(367, 'Diviciorii Mici', 64),
(368, 'Mahal', 64),
(369, 'Samboieni', 64),
(370, 'Sanmartin', 64),
(371, 'Targusor', 64),
(372, 'Berindu', 65),
(373, 'Mihaiesti', 65),
(374, 'Sanpaul', 65),
(375, 'Sumurducu', 65),
(376, 'Sardu', 65),
(377, 'Topa Mica', 65),
(378, 'Sic', 66),
(379, 'Aruncuta', 67),
(380, 'Damburile', 67),
(381, 'Suatu', 67),
(382, 'Clapa', 68),
(383, 'Colonia', 68),
(384, 'Padureni', 68),
(385, 'Tritenii de Jos', 68),
(386, 'Tritenii de Sus', 68),
(387, 'Tritenii-Hotar', 68),
(388, 'Ceanu Mic', 69),
(389, 'Comsesti', 69),
(390, 'Martinesti', 69),
(391, 'Micesti', 69),
(392, 'Tureni', 69),
(393, 'Nasal', 70),
(394, 'Santejude', 70),
(395, 'Santejude-Vale', 70),
(396, 'Santioana', 70),
(397, 'Taga', 70),
(398, 'Batin', 71),
(399, 'Darot', 71),
(400, 'Sicfa', 71),
(401, 'Unguras', 71),
(402, 'Valea Ungurasului', 71),
(403, 'Bogata de Jos', 72),
(404, 'Bogata de Sus', 72),
(405, 'Calna', 72),
(406, 'Cetan', 72),
(407, 'Curtuiusu Dejului', 72),
(408, 'Vad', 72),
(409, 'Valea Grosilor', 72),
(410, 'Cerc', 73),
(411, 'Plopi', 73),
(412, 'Valea Ierii', 73),
(413, 'Urca', 74),
(414, 'Viisoara', 74),
(415, 'Babutiu', 75),
(416, 'Badesti', 75),
(417, 'Chidea', 75),
(418, 'Faureni', 75),
(419, 'Soimeni', 75),
(420, 'Vultureni', 75);

-- --------------------------------------------------------

--
-- Table structure for table `terenuri`
--

CREATE TABLE `terenuri` (
  `indexTeren` int(11) NOT NULL,
  `dispozitieTeren` varchar(15) DEFAULT NULL,
  `parcelaTeren` int(11) DEFAULT NULL,
  `proprietateTeren` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `terenuri`
--

INSERT INTO `terenuri` (`indexTeren`, `dispozitieTeren`, `parcelaTeren`, `proprietateTeren`) VALUES
(12, 'Intravilan', 29, 31),
(13, 'Extravilan', 30, 32);

-- --------------------------------------------------------

--
-- Table structure for table `useri`
--

CREATE TABLE `useri` (
  `indexUser` int(11) NOT NULL,
  `nameUser` varchar(20) DEFAULT NULL,
  `passUser` varchar(20) DEFAULT NULL,
  `isAdminUser` tinyint(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `useri`
--

INSERT INTO `useri` (`indexUser`, `nameUser`, `passUser`, `isAdminUser`) VALUES
(1, 'pandrei', 'test', 1),
(2, 'pmarian', 'test', 1);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `agenti`
--
ALTER TABLE `agenti`
  ADD PRIMARY KEY (`indexAgent`),
  ADD KEY `userAgent` (`userAgent`),
  ADD KEY `persoanaAgent` (`persoanaAgent`);

--
-- Indexes for table `apartamente`
--
ALTER TABLE `apartamente`
  ADD PRIMARY KEY (`indexApartament`),
  ADD KEY `constructieApartament` (`constructieApartament`),
  ADD KEY `proprietateApartament` (`proprietateApartament`);

--
-- Indexes for table `cartiere`
--
ALTER TABLE `cartiere`
  ADD PRIMARY KEY (`indexCartier`),
  ADD KEY `orasCartier` (`orasCartier`);

--
-- Indexes for table `casute`
--
ALTER TABLE `casute`
  ADD PRIMARY KEY (`indexCasa`),
  ADD KEY `constructieCasa` (`constructieCasa`),
  ADD KEY `proprietateCasa` (`proprietateCasa`);

--
-- Indexes for table `clienti`
--
ALTER TABLE `clienti`
  ADD PRIMARY KEY (`indexClient`),
  ADD KEY `persoanaClient` (`persoanaClient`);

--
-- Indexes for table `compartimentari`
--
ALTER TABLE `compartimentari`
  ADD PRIMARY KEY (`indexCompartimentare`);

--
-- Indexes for table `comune`
--
ALTER TABLE `comune`
  ADD PRIMARY KEY (`indexComuna`),
  ADD KEY `judetComuna` (`judetComuna`);

--
-- Indexes for table `constructii`
--
ALTER TABLE `constructii`
  ADD PRIMARY KEY (`indexConstructie`),
  ADD KEY `parcelaConstructie` (`parcelaConstructie`),
  ADD KEY `compartimentareConstructie` (`compartimentareConstructie`);

--
-- Indexes for table `judete`
--
ALTER TABLE `judete`
  ADD PRIMARY KEY (`indexJudet`);

--
-- Indexes for table `locatii`
--
ALTER TABLE `locatii`
  ADD PRIMARY KEY (`indexLocatie`),
  ADD KEY `cartierLocatie` (`cartierLocatie`),
  ADD KEY `satLocatie` (`satLocatie`),
  ADD KEY `judetLocatie` (`judetLocatie`),
  ADD KEY `orasLocatie` (`orasLocatie`),
  ADD KEY `comunaLocatie` (`comunaLocatie`);

--
-- Indexes for table `orase`
--
ALTER TABLE `orase`
  ADD PRIMARY KEY (`indexOras`),
  ADD KEY `judetOras` (`judetOras`);

--
-- Indexes for table `parcele`
--
ALTER TABLE `parcele`
  ADD PRIMARY KEY (`indexParcela`);

--
-- Indexes for table `persoane`
--
ALTER TABLE `persoane`
  ADD PRIMARY KEY (`indexPersoana`),
  ADD KEY `domiciliuPersoana` (`domiciliuPersoana`);

--
-- Indexes for table `programari`
--
ALTER TABLE `programari`
  ADD PRIMARY KEY (`indexProgramare`),
  ADD KEY `agentProgramare` (`agentProgramare`),
  ADD KEY `clientProgramare` (`clientProgramare`);

--
-- Indexes for table `proprietati`
--
ALTER TABLE `proprietati`
  ADD PRIMARY KEY (`indexProprietate`),
  ADD KEY `proprietarProprietate` (`proprietarProprietate`),
  ADD KEY `agentProprietate` (`agentProprietate`),
  ADD KEY `locatieProprietate` (`locatieProprietate`);

--
-- Indexes for table `sate`
--
ALTER TABLE `sate`
  ADD PRIMARY KEY (`indexSat`),
  ADD KEY `comunaSat` (`comunaSat`);

--
-- Indexes for table `terenuri`
--
ALTER TABLE `terenuri`
  ADD PRIMARY KEY (`indexTeren`),
  ADD KEY `parcelaTeren` (`parcelaTeren`),
  ADD KEY `proprietateTeren` (`proprietateTeren`);

--
-- Indexes for table `useri`
--
ALTER TABLE `useri`
  ADD PRIMARY KEY (`indexUser`),
  ADD UNIQUE KEY `nameUser` (`nameUser`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `agenti`
--
ALTER TABLE `agenti`
  MODIFY `indexAgent` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `apartamente`
--
ALTER TABLE `apartamente`
  MODIFY `indexApartament` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `cartiere`
--
ALTER TABLE `cartiere`
  MODIFY `indexCartier` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=47;

--
-- AUTO_INCREMENT for table `casute`
--
ALTER TABLE `casute`
  MODIFY `indexCasa` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT for table `clienti`
--
ALTER TABLE `clienti`
  MODIFY `indexClient` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=31;

--
-- AUTO_INCREMENT for table `compartimentari`
--
ALTER TABLE `compartimentari`
  MODIFY `indexCompartimentare` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=22;

--
-- AUTO_INCREMENT for table `comune`
--
ALTER TABLE `comune`
  MODIFY `indexComuna` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=76;

--
-- AUTO_INCREMENT for table `constructii`
--
ALTER TABLE `constructii`
  MODIFY `indexConstructie` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;

--
-- AUTO_INCREMENT for table `judete`
--
ALTER TABLE `judete`
  MODIFY `indexJudet` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=42;

--
-- AUTO_INCREMENT for table `locatii`
--
ALTER TABLE `locatii`
  MODIFY `indexLocatie` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=77;

--
-- AUTO_INCREMENT for table `orase`
--
ALTER TABLE `orase`
  MODIFY `indexOras` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=45;

--
-- AUTO_INCREMENT for table `parcele`
--
ALTER TABLE `parcele`
  MODIFY `indexParcela` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=36;

--
-- AUTO_INCREMENT for table `persoane`
--
ALTER TABLE `persoane`
  MODIFY `indexPersoana` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=37;

--
-- AUTO_INCREMENT for table `programari`
--
ALTER TABLE `programari`
  MODIFY `indexProgramare` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=61;

--
-- AUTO_INCREMENT for table `proprietati`
--
ALTER TABLE `proprietati`
  MODIFY `indexProprietate` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=38;

--
-- AUTO_INCREMENT for table `sate`
--
ALTER TABLE `sate`
  MODIFY `indexSat` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=421;

--
-- AUTO_INCREMENT for table `terenuri`
--
ALTER TABLE `terenuri`
  MODIFY `indexTeren` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT for table `useri`
--
ALTER TABLE `useri`
  MODIFY `indexUser` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `agenti`
--
ALTER TABLE `agenti`
  ADD CONSTRAINT `agenti_ibfk_1` FOREIGN KEY (`userAgent`) REFERENCES `useri` (`indexUser`),
  ADD CONSTRAINT `agenti_ibfk_2` FOREIGN KEY (`persoanaAgent`) REFERENCES `persoane` (`indexPersoana`);

--
-- Constraints for table `apartamente`
--
ALTER TABLE `apartamente`
  ADD CONSTRAINT `apartamente_ibfk_2` FOREIGN KEY (`constructieApartament`) REFERENCES `constructii` (`indexConstructie`),
  ADD CONSTRAINT `apartamente_ibfk_3` FOREIGN KEY (`proprietateApartament`) REFERENCES `proprietati` (`indexProprietate`);

--
-- Constraints for table `cartiere`
--
ALTER TABLE `cartiere`
  ADD CONSTRAINT `cartiere_ibfk_1` FOREIGN KEY (`orasCartier`) REFERENCES `orase` (`indexOras`);

--
-- Constraints for table `casute`
--
ALTER TABLE `casute`
  ADD CONSTRAINT `casute_ibfk_2` FOREIGN KEY (`constructieCasa`) REFERENCES `constructii` (`indexConstructie`),
  ADD CONSTRAINT `casute_ibfk_3` FOREIGN KEY (`proprietateCasa`) REFERENCES `proprietati` (`indexProprietate`);

--
-- Constraints for table `clienti`
--
ALTER TABLE `clienti`
  ADD CONSTRAINT `clienti_ibfk_1` FOREIGN KEY (`persoanaClient`) REFERENCES `persoane` (`indexPersoana`);

--
-- Constraints for table `comune`
--
ALTER TABLE `comune`
  ADD CONSTRAINT `comune_ibfk_1` FOREIGN KEY (`judetComuna`) REFERENCES `judete` (`indexJudet`);

--
-- Constraints for table `constructii`
--
ALTER TABLE `constructii`
  ADD CONSTRAINT `constructii_ibfk_1` FOREIGN KEY (`parcelaConstructie`) REFERENCES `parcele` (`indexParcela`),
  ADD CONSTRAINT `constructii_ibfk_2` FOREIGN KEY (`compartimentareConstructie`) REFERENCES `compartimentari` (`indexCompartimentare`);

--
-- Constraints for table `locatii`
--
ALTER TABLE `locatii`
  ADD CONSTRAINT `locatii_ibfk_1` FOREIGN KEY (`cartierLocatie`) REFERENCES `cartiere` (`indexCartier`),
  ADD CONSTRAINT `locatii_ibfk_2` FOREIGN KEY (`satLocatie`) REFERENCES `sate` (`indexSat`),
  ADD CONSTRAINT `locatii_ibfk_3` FOREIGN KEY (`judetLocatie`) REFERENCES `judete` (`indexJudet`),
  ADD CONSTRAINT `locatii_ibfk_4` FOREIGN KEY (`orasLocatie`) REFERENCES `orase` (`indexOras`),
  ADD CONSTRAINT `locatii_ibfk_5` FOREIGN KEY (`comunaLocatie`) REFERENCES `comune` (`indexComuna`);

--
-- Constraints for table `orase`
--
ALTER TABLE `orase`
  ADD CONSTRAINT `orase_ibfk_1` FOREIGN KEY (`judetOras`) REFERENCES `judete` (`indexJudet`);

--
-- Constraints for table `persoane`
--
ALTER TABLE `persoane`
  ADD CONSTRAINT `persoane_ibfk_1` FOREIGN KEY (`domiciliuPersoana`) REFERENCES `locatii` (`indexLocatie`);

--
-- Constraints for table `programari`
--
ALTER TABLE `programari`
  ADD CONSTRAINT `programari_ibfk_1` FOREIGN KEY (`agentProgramare`) REFERENCES `agenti` (`indexAgent`),
  ADD CONSTRAINT `programari_ibfk_2` FOREIGN KEY (`clientProgramare`) REFERENCES `clienti` (`indexClient`);

--
-- Constraints for table `proprietati`
--
ALTER TABLE `proprietati`
  ADD CONSTRAINT `proprietati_ibfk_1` FOREIGN KEY (`proprietarProprietate`) REFERENCES `clienti` (`indexClient`),
  ADD CONSTRAINT `proprietati_ibfk_2` FOREIGN KEY (`agentProprietate`) REFERENCES `agenti` (`indexAgent`),
  ADD CONSTRAINT `proprietati_ibfk_3` FOREIGN KEY (`locatieProprietate`) REFERENCES `locatii` (`indexLocatie`);

--
-- Constraints for table `sate`
--
ALTER TABLE `sate`
  ADD CONSTRAINT `sate_ibfk_1` FOREIGN KEY (`comunaSat`) REFERENCES `comune` (`indexComuna`);

--
-- Constraints for table `terenuri`
--
ALTER TABLE `terenuri`
  ADD CONSTRAINT `terenuri_ibfk_1` FOREIGN KEY (`parcelaTeren`) REFERENCES `parcele` (`indexParcela`),
  ADD CONSTRAINT `terenuri_ibfk_2` FOREIGN KEY (`proprietateTeren`) REFERENCES `proprietati` (`indexProprietate`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
