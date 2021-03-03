-- phpMyAdmin SQL Dump
-- version 5.0.3
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Feb 20, 2021 at 04:05 AM
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
(1, 1, 1);

-- --------------------------------------------------------

--
-- Table structure for table `apartamente`
--

CREATE TABLE `apartamente` (
  `indexApartament` int(11) NOT NULL,
  `etajApartament` varchar(15) DEFAULT NULL,
  `compartimentareApartament` int(11) DEFAULT NULL,
  `constructieApartament` int(11) DEFAULT NULL,
  `proprietateApartament` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

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
(1, 'Micro', 2);

-- --------------------------------------------------------

--
-- Table structure for table `casute`
--

CREATE TABLE `casute` (
  `indexCasa` int(11) NOT NULL,
  `compartimentareCasa` int(11) DEFAULT NULL,
  `constructieCasa` int(11) DEFAULT NULL,
  `proprietateCasa` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `clienti`
--

CREATE TABLE `clienti` (
  `indexClient` int(11) NOT NULL,
  `persoanaClient` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `compartimentari`
--

CREATE TABLE `compartimentari` (
  `indexCompartimentare` int(11) NOT NULL,
  `openspace` int(11) DEFAULT NULL,
  `living` int(11) DEFAULT NULL,
  `dormitor` int(11) DEFAULT NULL,
  `bucatarie` int(11) DEFAULT NULL,
  `baie` int(11) DEFAULT NULL,
  `dressing` int(11) DEFAULT NULL,
  `debara` int(11) DEFAULT NULL,
  `balcon` int(11) DEFAULT NULL,
  `mansarda` int(11) DEFAULT NULL,
  `pod` int(11) DEFAULT NULL,
  `subsol` int(11) DEFAULT NULL,
  `garaj` int(11) DEFAULT NULL,
  `parcare` int(11) DEFAULT NULL,
  `gradina` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `comune`
--

CREATE TABLE `comune` (
  `indexComuna` int(11) NOT NULL,
  `denumireComuna` varchar(30) DEFAULT NULL,
  `judetComuna` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `constructii`
--

CREATE TABLE `constructii` (
  `indexConstructie` int(11) NOT NULL,
  `suprafataConstructie` int(11) DEFAULT NULL,
  `suprafataUtilizabila` int(11) DEFAULT NULL,
  `inaltimeConstructie` int(11) DEFAULT NULL,
  `anConstructie` int(11) DEFAULT NULL,
  `structuraConstructie` varchar(15) DEFAULT NULL,
  `dispozitieActuala` varchar(15) DEFAULT NULL,
  `dispozitiePredare` varchar(15) DEFAULT NULL,
  `parcelaConstructie` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

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
(1, 'Alba'),
(2, 'Arad');

-- --------------------------------------------------------

--
-- Table structure for table `locatii`
--

CREATE TABLE `locatii` (
  `indexLocatie` int(11) NOT NULL,
  `denumireLocatie` varchar(30) DEFAULT NULL,
  `cartierLocatie` int(11) DEFAULT NULL,
  `satLocatie` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `locatii`
--

INSERT INTO `locatii` (`indexLocatie`, `denumireLocatie`, `cartierLocatie`, `satLocatie`) VALUES
(1, 'Axinte Sever', 1, NULL);

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
(1, 'Alba Iulia', 1),
(2, 'Aiud', 1);

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
(1, 'testnume', 'testprenume', 'testtelefon', 'test@email.com', 1);

-- --------------------------------------------------------

--
-- Table structure for table `programari`
--

CREATE TABLE `programari` (
  `indexProgramare` int(11) NOT NULL,
  `dataProgramare` date DEFAULT NULL,
  `agentProgramare` int(11) DEFAULT NULL,
  `clientProgramare` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

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

-- --------------------------------------------------------

--
-- Table structure for table `sate`
--

CREATE TABLE `sate` (
  `indexSat` int(11) NOT NULL,
  `denumireSat` varchar(30) DEFAULT NULL,
  `comunaSat` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

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
(1, 'test', 'test', 1);

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
  ADD KEY `compartimentareApartament` (`compartimentareApartament`),
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
  ADD KEY `compartimentareCasa` (`compartimentareCasa`),
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
  ADD KEY `parcelaConstructie` (`parcelaConstructie`);

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
  ADD KEY `satLocatie` (`satLocatie`);

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
  MODIFY `indexAgent` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `apartamente`
--
ALTER TABLE `apartamente`
  MODIFY `indexApartament` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `cartiere`
--
ALTER TABLE `cartiere`
  MODIFY `indexCartier` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `casute`
--
ALTER TABLE `casute`
  MODIFY `indexCasa` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `clienti`
--
ALTER TABLE `clienti`
  MODIFY `indexClient` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `compartimentari`
--
ALTER TABLE `compartimentari`
  MODIFY `indexCompartimentare` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `comune`
--
ALTER TABLE `comune`
  MODIFY `indexComuna` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `constructii`
--
ALTER TABLE `constructii`
  MODIFY `indexConstructie` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `judete`
--
ALTER TABLE `judete`
  MODIFY `indexJudet` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `locatii`
--
ALTER TABLE `locatii`
  MODIFY `indexLocatie` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `orase`
--
ALTER TABLE `orase`
  MODIFY `indexOras` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `parcele`
--
ALTER TABLE `parcele`
  MODIFY `indexParcela` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `persoane`
--
ALTER TABLE `persoane`
  MODIFY `indexPersoana` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `programari`
--
ALTER TABLE `programari`
  MODIFY `indexProgramare` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `proprietati`
--
ALTER TABLE `proprietati`
  MODIFY `indexProprietate` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `sate`
--
ALTER TABLE `sate`
  MODIFY `indexSat` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `terenuri`
--
ALTER TABLE `terenuri`
  MODIFY `indexTeren` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `useri`
--
ALTER TABLE `useri`
  MODIFY `indexUser` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

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
  ADD CONSTRAINT `apartamente_ibfk_1` FOREIGN KEY (`compartimentareApartament`) REFERENCES `compartimentari` (`indexCompartimentare`),
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
  ADD CONSTRAINT `casute_ibfk_1` FOREIGN KEY (`compartimentareCasa`) REFERENCES `compartimentari` (`indexCompartimentare`),
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
  ADD CONSTRAINT `constructii_ibfk_1` FOREIGN KEY (`parcelaConstructie`) REFERENCES `parcele` (`indexParcela`);

--
-- Constraints for table `locatii`
--
ALTER TABLE `locatii`
  ADD CONSTRAINT `locatii_ibfk_1` FOREIGN KEY (`cartierLocatie`) REFERENCES `cartiere` (`indexCartier`),
  ADD CONSTRAINT `locatii_ibfk_2` FOREIGN KEY (`satLocatie`) REFERENCES `sate` (`indexSat`);

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
