-- phpMyAdmin SQL Dump
-- version 4.7.0
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le :  ven. 29 juin 2018 à 15:08
-- Version du serveur :  10.1.24-MariaDB
-- Version de PHP :  7.1.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `pfe_fstgproject`
--

-- --------------------------------------------------------

--
-- Structure de la table `academie`
--

CREATE TABLE `academie` (
  `ID` bigint(20) NOT NULL,
  `TITRE` varchar(255) DEFAULT NULL,
  `REGION_ID` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `academie`
--

INSERT INTO `academie` (`ID`, `TITRE`, `REGION_ID`) VALUES
(1, 'ACADEMIE ETRANGERE', NULL),
(2, 'ACADEMIE OUED EDDHAB-LAGOUIRA', NULL),
(3, 'ACADEMIE LAAYOUNE-BOUJDOUR-SKIA LHAMRAA', NULL),
(4, 'ACADEMIE GUELMIM-ESSMARA', NULL),
(5, 'ACADEMIE AGADIR SOUSS-MASSA-DRAA', NULL),
(6, 'ACADEMIE GHARB-CHRARDA-BENI HSSEN', NULL),
(7, 'ACADEMIE CHAOUIA-OURDIGHA', NULL),
(8, 'ACADEMIE MARRAKECH-TANSIFT-ALHAOUZ', NULL),
(9, 'ACADEMIE GRAND CASABLANCA', NULL),
(10, 'ACADEMIE RABAT- SALE-ZEMMOUR-ZAIR', NULL),
(11, 'ACADEMIE DOUKALA-ABDA', NULL),
(12, 'ACADEMIE TADLA-AZILAL', NULL),
(13, 'ACADEMIE MEKNES-TAFILALT', NULL),
(14, 'ACADEMIE FES-BOULMANE', NULL),
(15, 'ACADEMIE TAZA-AL HOUCEIMA-TAOUNATE', NULL),
(16, 'ACADEMIE TANGER-TETOUAN', NULL),
(17, 'ACADEMIE MILITAIRE', NULL),
(18, 'MAROC', NULL),
(19, 'AUTRE', NULL);

-- --------------------------------------------------------

--
-- Structure de la table `actualite`
--

CREATE TABLE `actualite` (
  `ID` bigint(20) NOT NULL,
  `CONTENUE` varchar(255) DEFAULT NULL,
  `DATECREATION` date DEFAULT NULL,
  `DATEDEBUT` date DEFAULT NULL,
  `DATEEXPIRATION` date DEFAULT NULL,
  `DESCRIPTION` varchar(255) DEFAULT NULL,
  `ETAT` int(11) DEFAULT NULL,
  `FILE` varchar(255) DEFAULT NULL,
  `PATH` varchar(255) DEFAULT NULL,
  `POSITION` int(11) DEFAULT NULL,
  `PRIORITY` int(11) DEFAULT NULL,
  `TITRE` varchar(255) DEFAULT NULL,
  `AUTEUR_CIN` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `annee`
--

CREATE TABLE `annee` (
  `ID` bigint(20) NOT NULL,
  `LIBELLE` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `annee`
--

INSERT INTO `annee` (`ID`, `LIBELLE`) VALUES
(2, 2013),
(3, 2014),
(4, 2015),
(5, 2016),
(6, 2017);

-- --------------------------------------------------------

--
-- Structure de la table `anneeinscription`
--

CREATE TABLE `anneeinscription` (
  `ID` bigint(20) NOT NULL,
  `ANNEE` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `anneuniversitaire`
--

CREATE TABLE `anneuniversitaire` (
  `ID` bigint(20) NOT NULL,
  `ANNEEUNI` varchar(255) DEFAULT NULL,
  `ANNEEMAX_ID` bigint(20) DEFAULT NULL,
  `ANNEEMIN_ID` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `article`
--

CREATE TABLE `article` (
  `ID` bigint(20) NOT NULL,
  `CONTENT` varchar(255) DEFAULT NULL,
  `DATEPUBLICATION` date DEFAULT NULL,
  `IMAGES` longblob,
  `TITLE` varchar(255) DEFAULT NULL,
  `TYPE` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `candidat`
--

CREATE TABLE `candidat` (
  `CNE` varchar(255) NOT NULL,
  `ADRESSE` varchar(255) DEFAULT NULL,
  `ANNEEBAC` varchar(255) DEFAULT NULL,
  `ANNEEINSCRIPTIONENSSUP` int(11) DEFAULT NULL,
  `ANNEEINSCRIPTIONETAB` int(11) DEFAULT NULL,
  `ANNEEINSCRIPTIONUNIV` int(11) DEFAULT NULL,
  `ANNEEOBTLICENCE` varchar(255) DEFAULT NULL,
  `CIN` varchar(255) DEFAULT NULL,
  `DATEINSCRIPTION` date DEFAULT NULL,
  `DATENAISSANCE` date DEFAULT NULL,
  `EMAIL` varchar(255) DEFAULT NULL,
  `ETABLISSEMENTPREINSC` int(11) DEFAULT NULL,
  `ETAT` tinyint(1) DEFAULT '0',
  `EXPORT` tinyint(1) DEFAULT '0',
  `FONCTIONNAIRE` tinyint(1) DEFAULT '0',
  `HANDICAP` tinyint(1) DEFAULT '0',
  `IP` varchar(255) DEFAULT NULL,
  `LASTPDF` varchar(255) DEFAULT NULL,
  `LIEUAR` varchar(255) DEFAULT NULL,
  `LIEUNAISSANCE` varchar(255) DEFAULT NULL,
  `MENTIONBAC` varchar(255) DEFAULT NULL,
  `MENTIONDIPLOME` varchar(255) DEFAULT NULL,
  `MOYCALIBR` float DEFAULT NULL,
  `NOMAR` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `NOMLAT` varchar(255) DEFAULT NULL,
  `NOTES1` float DEFAULT NULL,
  `NOTES2` float DEFAULT NULL,
  `NOTES3` float DEFAULT NULL,
  `NOTES4` float DEFAULT NULL,
  `NOTES5` float DEFAULT NULL,
  `NOTES6` float DEFAULT NULL,
  `OPTIONLICENCE` varchar(255) DEFAULT NULL,
  `PHOTO` varchar(255) DEFAULT NULL,
  `PRENOMAR` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `PRENOMLAT` varchar(255) DEFAULT NULL,
  `SECRET` bigint(20) DEFAULT NULL,
  `SEXE` tinyint(1) DEFAULT '0',
  `SPECIALITE` varchar(255) DEFAULT NULL,
  `TELEPHONE` varchar(255) DEFAULT NULL,
  `TYPEINSCRIPTION` tinyint(1) DEFAULT '0',
  `ACADEMIE_ID` bigint(20) DEFAULT NULL,
  `DERNIERDIPLOME_ID` bigint(20) DEFAULT NULL,
  `ETABLISSEMENT_ID` bigint(20) DEFAULT NULL,
  `LICENCE_ID` bigint(20) DEFAULT NULL,
  `LYCEE_ID` bigint(20) DEFAULT NULL,
  `OPTIONBAC_ID` bigint(20) DEFAULT NULL,
  `PAYS_ID` bigint(20) DEFAULT NULL,
  `PROFESSIONDELAMERE_ID` bigint(20) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `candidat`
--

INSERT INTO `candidat` (`CNE`, `ADRESSE`, `ANNEEBAC`, `ANNEEINSCRIPTIONENSSUP`, `ANNEEINSCRIPTIONETAB`, `ANNEEINSCRIPTIONUNIV`, `ANNEEOBTLICENCE`, `CIN`, `DATEINSCRIPTION`, `DATENAISSANCE`, `EMAIL`, `ETABLISSEMENTPREINSC`, `ETAT`, `EXPORT`, `FONCTIONNAIRE`, `HANDICAP`, `IP`, `LASTPDF`, `LIEUAR`, `LIEUNAISSANCE`, `MENTIONBAC`, `MENTIONDIPLOME`, `MOYCALIBR`, `NOMAR`, `NOMLAT`, `NOTES1`, `NOTES2`, `NOTES3`, `NOTES4`, `NOTES5`, `NOTES6`, `OPTIONLICENCE`, `PHOTO`, `PRENOMAR`, `PRENOMLAT`, `SECRET`, `SEXE`, `SPECIALITE`, `TELEPHONE`, `TYPEINSCRIPTION`, `ACADEMIE_ID`, `DERNIERDIPLOME_ID`, `ETABLISSEMENT_ID`, `LICENCE_ID`, `LYCEE_ID`, `OPTIONBAC_ID`, `PAYS_ID`, `PROFESSIONDELAMERE_ID`, `password`) VALUES
('123123123', 'agadirouffla', '2016', 2010, 2014, 2011, NULL, '25698', NULL, NULL, 'ouss.kopp@gmail.com', 15, 0, 0, 0, 0, NULL, NULL, NULL, 'jahnam', NULL, NULL, 0, '???', 'hansali', 0, 0, 0, 0, 0, 0, NULL, NULL, '??? ???', 'oussama', NULL, 0, NULL, '28', 0, 1, 1, NULL, NULL, NULL, 1, NULL, NULL, NULL),
('123456887', 'imm', '2014', NULL, NULL, NULL, NULL, 'JH1234', NULL, NULL, 'ouss.kopp@gmail.com', NULL, 0, 0, 0, 0, NULL, NULL, NULL, NULL, NULL, NULL, 1.2525, NULL, 'salimi', 12.6, 15.5, 12, 10, NULL, NULL, NULL, NULL, NULL, 'abdallah', NULL, 0, NULL, NULL, 0, NULL, NULL, 0, NULL, NULL, 1, NULL, NULL, NULL),
('1236541', 'sidi abbad', '', 0, 0, 0, NULL, '58965', NULL, '2018-06-06', 'ouss.kopp@gmail.com', 15, 0, 0, 0, 0, NULL, NULL, NULL, 'Marrakech', NULL, NULL, 0, 'hidi', 'hajar', 0, 0, 0, 0, 0, 0, NULL, NULL, 'ouss', 'ouss', NULL, 0, NULL, '063225555', 0, 1, 1, NULL, NULL, NULL, 1, 1, 1, NULL),
('12365411', 'sidi abbad', '', 0, 0, 0, NULL, '58965', NULL, '2018-06-06', 'ouss.kopp@gmail.com', 15, 0, 0, 0, 0, NULL, NULL, NULL, 'Marrakech', NULL, NULL, 0, 'hidi', 'hajar', 0, 0, 0, 0, 0, 0, NULL, NULL, 'ouss', 'ouss', NULL, 0, NULL, '063225555', 0, 1, 1, NULL, NULL, NULL, 1, 1, 1, NULL),
('123655', 'chi lassa', '', 0, 0, 0, NULL, '552222', NULL, '2018-06-27', 'ouss.kopp@gmail.com', 15, 0, 0, 0, 0, NULL, NULL, NULL, 'Agadir', NULL, NULL, 0, 'hidi', 'hajar', 0, 0, 0, 0, 0, 0, NULL, NULL, 'khodri', 'khodri', NULL, 0, NULL, '063526336', 0, 1, 1, NULL, NULL, NULL, 1, 1, 1, NULL),
('1412025000', 'walo walo walo', '2014', 2014, 2014, 2018, NULL, 'JH124567', '2018-06-18', '2018-06-18', 'ouss.kopp@gmail.com', 2018, 0, 0, 0, 0, NULL, '', NULL, 'agadir', NULL, 'très bien', 0, 'هرديك', 'herdik', 15, 15, 14, 17, 18, 19, NULL, NULL, 'عزدين', 'azzedin', NULL, 1, NULL, '0691815151', 0, 5, 2, NULL, NULL, NULL, 1, 1, 1, '6a18d73d343eab873f6117a42681b8957978cffee89630a52a119e6bd37f014f'),
('1412064444', 'Sidi Abbad n1112', '2014', 2014, 2014, 2014, NULL, 'EE895879', '2018-06-18', '1991-06-05', 'hgyyti985_h@gmail.com', 2014, 0, 0, 0, 0, NULL, '/resources/pdfCycle/creationDuconcours-casD.pdf', NULL, 'Agadir', NULL, 'Bien', 3.135, 'لاود', 'loud', 10, 10, 10, 11.8, 12, 15, NULL, NULL, 'مروى', 'Marwa', NULL, 0, NULL, '0634293555', 0, 5, 9, 0, NULL, NULL, 2, 1, 1, 'cfe9d1ff148a0ef7ffde3cbb04e853f33ddb93ec2e49b6f8b0cd14ffdc8ea55e'),
('1412068888', 'Sidi Abbad n96', '2014', 2014, 2014, 2014, NULL, 'EE895879', '2018-06-18', '1996-06-05', 'ouss.kopp@gmail.com', 2014, 0, 0, 0, 0, NULL, '/resources/pdfCycle/consultationDeDossierFinale-CasD.pdf', NULL, 'Agadir', NULL, 'Bien', 5.225, 'حنصالي', 'Hansali', 10, 10, 10, 11.8, 12, 15, NULL, NULL, 'أسامة ', 'Oussama', NULL, 0, NULL, '0634293555', 0, 8, 8, 1, NULL, NULL, 2, 1, 1, 'd85406c9407336575b0c39757d92ad38a305b7747f2632f4588b0ed9866f4dcf'),
('1412069966', 'LOT Tasseltante n88 SYBA Marrakech', '2014', 2014, 2014, 2014, NULL, 'EE895869', '2018-06-18', '1997-06-05', 'hajar.hidi@aiesec.net', 2014, 0, 0, 0, 0, NULL, '/resources/pdfCycle/IRISI1C2018 .pdf', NULL, 'Marrakech', NULL, 'Bien', 3.135, 'حيدي', 'Hidi', 10, 10, 10, 11.8, 12, 15, NULL, NULL, 'هاجر', 'Hajar', NULL, 0, NULL, '0634293599', 0, 8, 8, 0, NULL, NULL, 2, 1, 1, '600fe3524b0ee0b54bdbf1416c7f9bf3bf1b6552597d6c0a75524907fe5f47c9'),
('1412220593', NULL, '2014', NULL, NULL, NULL, NULL, 'JH12344', NULL, '1996-04-22', 'ouss@kopp', NULL, 0, 0, 0, 0, NULL, NULL, NULL, NULL, NULL, NULL, 1.21925, NULL, 'hansali', 12.02, 10, 14.54, 12.21, NULL, NULL, NULL, NULL, 'mouaas', 'mouaad', NULL, NULL, NULL, '0691816222', 0, NULL, 7, 0, NULL, NULL, 2, NULL, NULL, NULL),
('1594873', 'imm al ouaha', NULL, NULL, NULL, NULL, NULL, 'JH125', NULL, NULL, 'ouss@ouss', NULL, 0, 0, 0, 0, NULL, NULL, NULL, NULL, NULL, NULL, 1.325, NULL, 'abdallah', 15, 16, 12, 10, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, 0, NULL, NULL, 0, NULL, NULL, 2, NULL, NULL, NULL),
('1817514678', 'walop ', '0214', 25015, 2545, 0, NULL, 'JH124567', '2018-06-18', '2018-06-18', 'ouss.kopp@gmail.com', 195, 0, 0, 0, 0, NULL, '', NULL, 'KNVK/B', NULL, 'Bien', 0, 'VMKJVKM', 'bvcbvwcbvcbKVMK', 14, 14, 14, 14, 0, 0, NULL, NULL, 'MKNVMKV', 'VKV', NULL, 0, NULL, '0691815151', 0, 1, 2, NULL, NULL, NULL, 1, 1, 1, '48ef3996886c0dce194bf0949d0e8cf1da13ab1be8311de6dc0dc67653feacec'),
('555', 'cazddzec', 'hmar', 2020, 3000, 2050, NULL, '2412211111111111111', NULL, '2018-06-14', 'ouss.kopp@gmail.com', 15, 0, 0, 1, 0, NULL, NULL, NULL, '', NULL, NULL, 0, 'hansz', 'oussama', 0, 0, 0, 0, 0, 0, NULL, NULL, '', 'hansali', NULL, 1, NULL, '', 0, 1, 1, NULL, NULL, NULL, 1, NULL, NULL, NULL),
('788', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 0, NULL, NULL, NULL, NULL, NULL, NULL, 5.5, NULL, 'test2', 11, 11, 11, 11, 11, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, 0, NULL, NULL, 1, NULL, NULL, 2, NULL, NULL, NULL),
('789', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 0, NULL, NULL, NULL, NULL, NULL, NULL, 6, NULL, 'test1', 12, 12, 12, 12, 12, 12, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, 0, NULL, NULL, 1, NULL, NULL, 1, NULL, NULL, NULL),
('893', 'walo walo', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'aitsidi@aitsidi', NULL, 0, 0, 0, 0, NULL, NULL, NULL, NULL, NULL, NULL, 5.125, NULL, 'ait sidi', 12, 15, 4, 10, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, 0, NULL, NULL, 1, NULL, NULL, 2, NULL, NULL, NULL),
('894', 'walo', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'halza@hamza', NULL, 0, 0, 0, 0, NULL, NULL, NULL, NULL, NULL, NULL, 6.5, NULL, 'hamza', 10, 12, 15, 15, 17, 12, NULL, NULL, NULL, NULL, NULL, 0, NULL, NULL, 0, NULL, NULL, 1, NULL, NULL, 1, NULL, NULL, NULL),
('9999991', 'walo', '2014', 2018, 2018, 2018, NULL, '', NULL, NULL, 'ouss.kopp@gmail', 15, 0, 0, 0, 0, NULL, NULL, NULL, '', NULL, NULL, 0, '', 'ouss', 10, 10, 10, 10, 10, 10, NULL, NULL, '', 'hans', NULL, 1, NULL, '', 0, 3, 1, NULL, NULL, NULL, 1, NULL, NULL, NULL),
('9999998', 'walo', '2014', 2018, 2018, 2018, NULL, '', NULL, NULL, 'ouss.kopp@gmail', 15, 0, 0, 0, 0, NULL, NULL, NULL, '', NULL, NULL, 0, '', 'ouss', 10, 10, 10, 10, 10, 10, NULL, NULL, '', 'hans', NULL, 1, NULL, '', 0, 3, 1, NULL, NULL, NULL, 1, NULL, NULL, NULL),
('jh12345', '', '2014', 0, 0, 0, NULL, '1412025123', NULL, '1996-04-22', 'test@test', 15, 0, 0, 0, 0, '', '', '', 'rabat', NULL, NULL, 1.275, '', 'bouhouch', 12, 10, 14, 15, NULL, NULL, NULL, '', '', 'khalid', NULL, 0, NULL, '0691816123', 0, NULL, 7, 0, NULL, NULL, NULL, NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Structure de la table `categorie`
--

CREATE TABLE `categorie` (
  `ID` bigint(20) NOT NULL,
  `LIBELLE` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `categorie`
--

INSERT INTO `categorie` (`ID`, `LIBELLE`) VALUES
(1, 'Equipement informatique'),
(2, 'Equipement reseau'),
(3, 'Equipement impression'),
(4, 'Equipement laboratoir'),
(5, 'Equipement ameublement');

-- --------------------------------------------------------

--
-- Structure de la table `coeffcalibrage`
--

CREATE TABLE `coeffcalibrage` (
  `ID` bigint(20) NOT NULL,
  `ANNEE` varchar(255) DEFAULT NULL,
  `COEFF` float DEFAULT NULL,
  `NBRMAX` int(11) DEFAULT NULL,
  `NBRMIN` int(11) DEFAULT NULL,
  `NOTEMINIMAL` float DEFAULT NULL,
  `ETABLISSEMENT_ID` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `coeffcalibrage`
--

INSERT INTO `coeffcalibrage` (`ID`, `ANNEE`, `COEFF`, `NBRMAX`, `NBRMIN`, `NOTEMINIMAL`, `ETABLISSEMENT_ID`) VALUES
(1451, '2018 ', 0.2, 12, 3, 0, 2),
(2651, '2018 ', 0.3, 2, 1, 0, 0),
(2701, '2018 ', 0.5, 12, 5, 0, 1);

-- --------------------------------------------------------

--
-- Structure de la table `commande`
--

CREATE TABLE `commande` (
  `ID` varchar(255) NOT NULL,
  `DATECOMMANDE` date DEFAULT NULL,
  `LIVREE` int(11) DEFAULT NULL,
  `NOMBRECOMMANDE` int(11) DEFAULT NULL,
  `USER_ID` varchar(255) DEFAULT NULL,
  `USERSTOCK_ID` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `concourexammatiere`
--

CREATE TABLE `concourexammatiere` (
  `ID` bigint(20) NOT NULL,
  `COEFF` float DEFAULT NULL,
  `DATEEXAM` date DEFAULT NULL,
  `CONCOURNIVEAU_ID` bigint(20) DEFAULT NULL,
  `MATIERECONCOUR_ID` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `concourexammatiere`
--

INSERT INTO `concourexammatiere` (`ID`, `COEFF`, `DATEEXAM`, `CONCOURNIVEAU_ID`, `MATIERECONCOUR_ID`) VALUES
(0, 2, '2018-05-17', 101, 52),
(53, 2, '2018-05-16', 101, 51),
(251, 0.5, '2018-02-02', 201, 1453),
(252, 0.5, '2018-02-02', 1, 1454),
(301, 0.8, '2018-07-21', 201, 52),
(351, 0.7, '2018-02-02', 201, 1452),
(352, 0.8, '2018-07-21', 201, 1454),
(1101, 5, '2018-02-02', 1, 901),
(1151, 3, '2018-05-03', 1, 51),
(1152, 3, '2018-05-03', 1, 52),
(1251, 5, '2018-03-02', 1, 851),
(1351, 2, '2018-02-02', 151, 51),
(1352, 3, '2018-02-02', 151, 52);

-- --------------------------------------------------------

--
-- Structure de la table `concourniveau`
--

CREATE TABLE `concourniveau` (
  `ID` bigint(20) NOT NULL,
  `DESCRIPTION` varchar(255) DEFAULT NULL,
  `ANNEE` varchar(255) DEFAULT NULL,
  `NBRDEPLACEECRIT` int(11) DEFAULT NULL,
  `NBRDEPLACEORALE` int(11) DEFAULT NULL,
  `NBRDEPLADEADMIS` int(11) DEFAULT NULL,
  `NIVEAU_ID` bigint(20) DEFAULT NULL,
  `listF` int(25) NOT NULL DEFAULT '0',
  `listO` int(25) NOT NULL DEFAULT '0',
  `listC` int(25) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `concourniveau`
--

INSERT INTO `concourniveau` (`ID`, `DESCRIPTION`, `ANNEE`, `NBRDEPLACEECRIT`, `NBRDEPLACEORALE`, `NBRDEPLADEADMIS`, `NIVEAU_ID`, `listF`, `listO`, `listC`) VALUES
(1, 'Ingénierie en Finance et Actuariat', '2018 ', 40, 30, 20, 1, 0, 0, 0),
(101, 'Ingénierie en Résaux informatique et Système d\'information', '2018', 50, 40, 25, 0, 1, 1, 1),
(151, '\r\nIndustrie et Sécurité des Aliments', '2018 ', 40, 20, 18, 2, 0, 0, 0),
(201, 'Génie des Matériaux et Procédés', '2018 ', 40, 20, 11, 4, 0, 0, 0);

-- --------------------------------------------------------

--
-- Structure de la table `concouroral`
--

CREATE TABLE `concouroral` (
  `ID` bigint(20) NOT NULL,
  `COEFECRIT` float DEFAULT NULL,
  `COEFORAL` float DEFAULT NULL,
  `NOTEGENERAL` float DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `condidature`
--

CREATE TABLE `condidature` (
  `ID` bigint(20) NOT NULL,
  `CONDIDATUREVALIDE` int(11) DEFAULT NULL,
  `MOYENNEECRIT` float DEFAULT NULL,
  `MOYENNEGENERALE` float DEFAULT NULL,
  `MOYENNEORALE` float DEFAULT NULL,
  `PIECEVALIDE` int(11) DEFAULT NULL,
  `REUSSI` tinyint(1) DEFAULT '0',
  `TYPEINSCRIPTION` int(11) DEFAULT NULL,
  `ANNEUNIVERSITAIRE_ID` bigint(20) DEFAULT NULL,
  `FILIERE_ID` bigint(20) DEFAULT NULL,
  `CANDIDAT_CNE` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `condidature`
--

INSERT INTO `condidature` (`ID`, `CONDIDATUREVALIDE`, `MOYENNEECRIT`, `MOYENNEGENERALE`, `MOYENNEORALE`, `PIECEVALIDE`, `REUSSI`, `TYPEINSCRIPTION`, `ANNEUNIVERSITAIRE_ID`, `FILIERE_ID`, `CANDIDAT_CNE`) VALUES
(2, 1, 9.5, 10.75, 12, 0, 1, 2, NULL, NULL, 'jh12345'),
(3, 3, 11.5, 13.25, 15, NULL, 1, NULL, NULL, NULL, '1412220593'),
(4, 1, 4.5, 9.75, 15, NULL, 1, NULL, NULL, 1, '1594873'),
(5, 1, 15.5, 7.75, NULL, NULL, 1, NULL, NULL, 1, '123456887'),
(6, 3, 13.5, 13.25, 13, NULL, 0, NULL, NULL, 1, '788'),
(7, 3, 10.5, 11.25, 12, NULL, 0, NULL, NULL, 1, '789'),
(8, 3, 12.25, 13.625, 15, NULL, 0, NULL, NULL, 2, '893'),
(9, 3, 13.5, 13.75, 14, NULL, 0, NULL, NULL, 2, '894'),
(10, 3, 0, 0, 0, 0, 0, 3, NULL, NULL, '9999998'),
(11, 3, 0, 0, 0, 0, 0, 3, NULL, NULL, '9999991'),
(12, 3, 0, 0, 0, 0, 0, 3, NULL, NULL, '555'),
(13, 3, 0, 0, 0, 0, 0, 3, NULL, NULL, '123123123'),
(107, 0, 0, 0, 2, 0, 0, 0, NULL, NULL, NULL),
(605, 0, 0, 0, 11, 0, 0, 0, NULL, NULL, NULL),
(655, 0, 0, 0, 13, 0, 0, 0, NULL, NULL, NULL),
(656, 0, 0, 0, 13, 0, 0, 0, NULL, NULL, NULL),
(657, 0, 20, 20, 20, 0, 0, 3, NULL, NULL, '12365411'),
(658, 0, 19.9, 19.45, 19, 0, 0, 3, NULL, NULL, '1236541'),
(659, 0, 19.8, 18.9, 18, 0, 0, 3, NULL, NULL, '123655'),
(660, 3, 0, 0, 0, 0, 0, 3, NULL, NULL, '1412025000'),
(661, 3, 0, 0, 0, 0, 0, 3, NULL, NULL, '1817514678'),
(662, 0, 16.5, 18.25, 20, 0, 1, 3, NULL, NULL, '1412069966'),
(663, 0, 17, 18, 19, 0, 1, 3, NULL, NULL, '1412068888'),
(664, 0, 16.5, 14.25, 12, 0, 1, 3, NULL, NULL, '1412064444');

-- --------------------------------------------------------

--
-- Structure de la table `demande`
--

CREATE TABLE `demande` (
  `ID` bigint(20) NOT NULL,
  `DESCRIPTION` varchar(255) DEFAULT NULL,
  `ETAT` int(11) DEFAULT NULL,
  `ETUDIANT_CNE` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `demandeattestaion`
--

CREATE TABLE `demandeattestaion` (
  `ID` bigint(20) NOT NULL,
  `ATTESTATIONDEUST` int(11) DEFAULT NULL,
  `ATTESTATIONLICENCE` int(11) DEFAULT NULL,
  `ETUDIANT_CNE` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `demandederogation`
--

CREATE TABLE `demandederogation` (
  `ID` bigint(20) NOT NULL,
  `ANNE_BACCALAUREAT` int(11) DEFAULT NULL,
  `ANNE_ETUDE` int(11) DEFAULT NULL,
  `DESCRIPTION` varchar(255) DEFAULT NULL,
  `ETAT` int(11) DEFAULT NULL,
  `NOMBREMDULESVALIDE` int(11) DEFAULT NULL,
  `NOMBRESEMESTRESVALIDE` int(11) DEFAULT NULL,
  `ETUDIANT_CNE` bigint(20) DEFAULT NULL,
  `FILIERE_ID` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `demandeitem`
--

CREATE TABLE `demandeitem` (
  `ID` bigint(20) NOT NULL,
  `DEMANDE_ID` bigint(20) DEFAULT NULL,
  `MODULE_ID` bigint(20) DEFAULT NULL,
  `SEMESTRE_ID` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `demandelicence`
--

CREATE TABLE `demandelicence` (
  `ID` bigint(20) NOT NULL,
  `ETUDIANT_CNE` bigint(20) DEFAULT NULL,
  `ANNEUNIVERSITAIRE_ID` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `demandelicenceitem`
--

CREATE TABLE `demandelicenceitem` (
  `ID` bigint(20) NOT NULL,
  `PRIORITE` int(11) DEFAULT NULL,
  `DEMANDELICENCE_ID` bigint(20) DEFAULT NULL,
  `LICENCE_ID` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `demanderelevenote`
--

CREATE TABLE `demanderelevenote` (
  `ID` bigint(20) NOT NULL,
  `ETAT` int(11) DEFAULT NULL,
  `ETUDIANT_CNE` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `demanderelevenoteitem`
--

CREATE TABLE `demanderelevenoteitem` (
  `ID` bigint(20) NOT NULL,
  `DEMANDERELEVENOTE_ID` bigint(20) DEFAULT NULL,
  `SEMESTRE_ID` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `departement`
--

CREATE TABLE `departement` (
  `ID` bigint(20) NOT NULL,
  `DESCRIPTION` varchar(255) DEFAULT NULL,
  `IMG` varchar(255) DEFAULT NULL,
  `INTITULE` varchar(255) DEFAULT NULL,
  `CHEFDEPARTEMENT_CIN` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `departement`
--

INSERT INTO `departement` (`ID`, `DESCRIPTION`, `IMG`, `INTITULE`, `CHEFDEPARTEMENT_CIN`) VALUES
(1, NULL, NULL, 'Biologie', 'EE0001'),
(2, NULL, NULL, 'Mathématiques', 'EE0005'),
(3, NULL, NULL, 'Informatique', 'EE0003'),
(4, NULL, NULL, 'Sciences Chimiques', 'EE0002'),
(5, NULL, NULL, 'Physique Appliquée', 'EE0007');

-- --------------------------------------------------------

--
-- Structure de la table `dernierdiplome`
--

CREATE TABLE `dernierdiplome` (
  `ID` bigint(20) NOT NULL,
  `NUMAPG` varchar(255) DEFAULT NULL,
  `TITRE` varchar(255) DEFAULT NULL,
  `MENTIONDIPLOME_ID` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `dernierdiplome`
--

INSERT INTO `dernierdiplome` (`ID`, `NUMAPG`, `TITRE`, `MENTIONDIPLOME_ID`) VALUES
(1, 'A', 'BaccalaurÃ©at', NULL),
(2, 'B', 'Brevet de Technicien SpÃ©cialisÃ©', NULL),
(3, 'C', 'Attestation dÃ©livrÃ©e Ã  la suite d\'un cursus en CPGE', NULL),
(4, 'D', 'DiplÃ´me Etudes Universitaires GÃ©nÃ©rales ', NULL),
(5, 'E', 'DiplÃ´me Etudes Universitaires Professionnelles ', NULL),
(6, 'F', 'DiplÃ´me Etudes Universitaires Techniques ', NULL),
(7, 'G', 'DiplÃ´me Universitaire de Technologie', NULL),
(8, 'H', 'Licence ', NULL),
(9, 'I', 'Licence Professionnelle', NULL),
(10, 'J', 'MaÃ®trise Ã¨s-Sciences SpÃ©cialisÃ©es ', NULL),
(11, 'K', 'MaÃ®trise Ã¨s-Sciences et Techniques  ', NULL),
(12, 'L', 'DiplÃ´me Ecole Nationale Commerce et Gestion', NULL),
(13, 'M', 'DiplÃ´me Ecole Roi Fahd de la Traduction', NULL),
(14, 'N', 'Master Professionnel', NULL),
(15, 'O', 'Master SpÃ©cialisÃ©', NULL),
(16, 'P', 'DiplÃ´me IngÃ©nieur Etat', NULL),
(17, 'Q', 'DiplÃ´me Etudes SupÃ©rieures SpÃ©cialisÃ©es ', NULL),
(18, 'R', 'DiplÃ´me Etudes SupÃ©rieures Approfondies ', NULL),
(19, 'S', 'DiplÃ´me de Docteur en MÃ©decine', NULL),
(20, 'T', 'DiplÃ´me de Docteur en Pharmacie', NULL),
(21, 'U', 'DiplÃ´me de Docteur en MÃ©decine Dentaire', NULL),
(22, 'V', 'DiplÃ´me d\'ingÃ©nieur Application', NULL),
(23, 'X', 'DiplÃ´me Etablissement Ã©tranger supÃ©rieur ou secondaire', NULL),
(24, 'Y', 'Autre diplÃ´me supÃ©rieur', NULL),
(25, 'Z', 'Aucun diplÃ´me supÃ©rieur', NULL),
(26, '1', 'Doctorat', NULL);

-- --------------------------------------------------------

--
-- Structure de la table `device`
--

CREATE TABLE `device` (
  `ID` bigint(20) NOT NULL,
  `ADRESSEMAC` varchar(255) DEFAULT NULL,
  `BROWSER` varchar(255) DEFAULT NULL,
  `DATECREATION` date DEFAULT NULL,
  `DEVICECATEGORIE` varchar(255) DEFAULT NULL,
  `OPERATINGSYSTEM` varchar(255) DEFAULT NULL,
  `ETUDIANT_CNE` bigint(20) DEFAULT NULL,
  `USER_CIN` varchar(255) DEFAULT NULL,
  `USERSTOCK_ID` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `enseignant`
--

CREATE TABLE `enseignant` (
  `CIN` varchar(255) NOT NULL,
  `ADMINE` tinyint(1) DEFAULT '0',
  `BLOCKED` tinyint(1) DEFAULT '0',
  `DATENAISSANCE` date DEFAULT NULL,
  `EMAIL` varchar(255) DEFAULT NULL,
  `GENDER` varchar(255) DEFAULT NULL,
  `GRADE` varchar(255) DEFAULT NULL,
  `MDPCHANGED` tinyint(1) DEFAULT '0',
  `NBRCNX` int(11) DEFAULT NULL,
  `NOM` varchar(255) DEFAULT NULL,
  `PASSWORD` varchar(255) DEFAULT NULL,
  `PRENOM` varchar(255) DEFAULT NULL,
  `TELEPHONE` varchar(255) DEFAULT NULL,
  `DEPARTEMENT_ID` bigint(20) DEFAULT NULL,
  `FILIERE_ID` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `enseignant`
--

INSERT INTO `enseignant` (`CIN`, `ADMINE`, `BLOCKED`, `DATENAISSANCE`, `EMAIL`, `GENDER`, `GRADE`, `MDPCHANGED`, `NBRCNX`, `NOM`, `PASSWORD`, `PRENOM`, `TELEPHONE`, `DEPARTEMENT_ID`, `FILIERE_ID`) VALUES
('ana', 0, 0, '2018-02-12', 'ana@ana.com', 'm', NULL, 0, 0, 'ana', '24d4b96f58da6d4a8512313bbd02a28ebf0ca95dec6e4c86ef78ce7f01e788ac', 'ana', '654', NULL, NULL),
('EE0001', 0, 0, '2017-06-01', 'zakariaalaoui@test.com', 'm', NULL, 0, 0, 'Zakaria', '8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92', 'El Alaoui-Talibi', '', NULL, NULL),
('EE0002', 0, 0, '2017-06-01', 'lailasaadi@test.com', 'f', NULL, 0, 0, 'Laila', '8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92', 'Saadi', '', NULL, NULL),
('EE0003', 0, 0, '2017-06-01', 'abdelilahabdali@test.com', 'm', NULL, 0, 0, 'Abdelilah', '8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92', 'Abdali', '', NULL, NULL),
('EE0004', 0, 0, '2017-06-01', 'mohmedelkaoua@test.com', 'm', NULL, 0, 0, 'Mohmed', '8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92', 'El Kaoua', '', NULL, NULL),
('EE0005', 0, 0, '2017-06-01', 'abdellahbentbib@test.com', 'm', NULL, 0, 0, 'Abdallah ', '8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92', 'Bentbib', '', NULL, NULL),
('EE0006', 0, 0, '2017-06-01', 'mohmedelghorfi@test.com', 'm', NULL, 0, 0, 'Mohmed', '8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92', 'El Ghorfi', '', NULL, NULL),
('EE0007', 0, 0, '2017-06-01', 'imanesalhi@test.com', 'm', NULL, 0, 0, 'Imane', '8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92', 'Salhi', '', NULL, NULL),
('EE0008', 0, 0, '2017-06-01', 'salahelhasri@test.com', 'm', NULL, 0, 0, 'Salah', '8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92', 'El Hasri', '', NULL, NULL),
('EE0009', 0, 0, '2017-06-01', 'ahmedbachnou@test.com', 'm', NULL, 0, 0, 'Ahmed', '8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92', 'Bachnou', '', NULL, NULL),
('EE0010', 0, 0, '2017-06-01', 'aliellabib@test.com', 'm', NULL, 0, 0, 'Ali', '8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92', 'Ellabib', '', NULL, NULL),
('EE0011', 0, 0, '2017-06-01', 'fouadsefyani@test.com', 'm', NULL, 0, 0, 'Fouad', '8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92', 'Sefyani', '', NULL, NULL);

-- --------------------------------------------------------

--
-- Structure de la table `entiteadministrative`
--

CREATE TABLE `entiteadministrative` (
  `ID` varchar(255) NOT NULL,
  `LIBELLE` varchar(255) DEFAULT NULL,
  `MAGASIN_ID` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `equiperecherche`
--

CREATE TABLE `equiperecherche` (
  `ID` bigint(20) NOT NULL,
  `NOM` varchar(255) DEFAULT NULL,
  `CHEFEQUIPE_CIN` varchar(255) DEFAULT NULL,
  `LABORATOIRE_ID` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `etablissementtype`
--

CREATE TABLE `etablissementtype` (
  `ID` bigint(20) NOT NULL,
  `ABRAPG` varchar(255) DEFAULT NULL,
  `TITRE` varchar(255) DEFAULT NULL,
  `ACADEMIE_ID` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `etablissementtype`
--

INSERT INTO `etablissementtype` (`ID`, `ABRAPG`, `TITRE`, `ACADEMIE_ID`) VALUES
(0, 'BTS', 'Brevet de Technicien Supérieur', NULL),
(1, 'FST', 'La Faculté des Sciences et Techniques', NULL),
(2, 'FS', 'Faculté Des Science', NULL);

-- --------------------------------------------------------

--
-- Structure de la table `etudiant`
--

CREATE TABLE `etudiant` (
  `CNE` bigint(20) NOT NULL,
  `BLOCKED` tinyint(1) DEFAULT '0',
  `DATENAISSANCE` date DEFAULT NULL,
  `EMAIL` varchar(255) DEFAULT NULL,
  `ETATDEUST` int(11) DEFAULT NULL,
  `GENDER` varchar(255) DEFAULT NULL,
  `MDPCHANGED` tinyint(1) DEFAULT '0',
  `NBRCNX` int(11) DEFAULT NULL,
  `NOM` varchar(255) DEFAULT NULL,
  `PASSWORD` varchar(255) DEFAULT NULL,
  `PRENOM` varchar(255) DEFAULT NULL,
  `EQUIPERECHERCHE_ID` bigint(20) DEFAULT NULL,
  `FILIERE_ID` bigint(20) DEFAULT NULL,
  `NIVEAU_ID` bigint(20) DEFAULT NULL,
  `SEMESTREACTUEL_ID` bigint(20) DEFAULT NULL,
  `CANDIDAT_CNE` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `etudiantmaster`
--

CREATE TABLE `etudiantmaster` (
  `CNE` bigint(20) NOT NULL,
  `ADRESSE` varchar(255) DEFAULT NULL,
  `ANNEEBAC` varchar(255) DEFAULT NULL,
  `ANNEEINSCRIPTIONENSSUP` int(11) DEFAULT NULL,
  `ANNEEINSCRIPTIONETAB` int(11) DEFAULT NULL,
  `ANNEEINSCRIPTIONUNIV` int(11) DEFAULT NULL,
  `CIN` varchar(255) DEFAULT NULL,
  `DATEINSCRIPTION` date DEFAULT NULL,
  `DATENAISSANCE` date DEFAULT NULL,
  `EMAIL` varchar(255) DEFAULT NULL,
  `ETABLISSEMENTPREINSC` int(11) DEFAULT NULL,
  `ETAT` tinyint(1) DEFAULT '0',
  `EXPORT` tinyint(1) DEFAULT '0',
  `FONCTIONNAIRE` tinyint(1) DEFAULT '0',
  `HANDICAP` tinyint(1) DEFAULT '0',
  `IP` varchar(255) DEFAULT NULL,
  `LASTPDF` varchar(255) DEFAULT NULL,
  `LIEUAR` varchar(255) DEFAULT NULL,
  `LIEUNAISSANCE` varchar(255) DEFAULT NULL,
  `NOMAR` varchar(255) DEFAULT NULL,
  `NOMLAT` varchar(255) DEFAULT NULL,
  `PHOTO` varchar(255) DEFAULT NULL,
  `PRENOMAR` varchar(255) DEFAULT NULL,
  `PRENOMLAT` varchar(255) DEFAULT NULL,
  `SECRET` bigint(20) DEFAULT NULL,
  `SEXE` int(11) DEFAULT NULL,
  `TELEPHONE` varchar(255) DEFAULT NULL,
  `TYPEINSCRIPTION` tinyint(1) DEFAULT '0',
  `TYPEDERNIERDIPLOME_ID` bigint(20) DEFAULT NULL,
  `ACADEMIE_ID` bigint(20) DEFAULT NULL,
  `FILIEREMASTER_ID` bigint(20) DEFAULT NULL,
  `LYCEE_ID` bigint(20) DEFAULT NULL,
  `MENTIONBAC_ID` bigint(20) DEFAULT NULL,
  `OPTIONBAC_ID` bigint(20) DEFAULT NULL,
  `PAYS_ID` bigint(20) DEFAULT NULL,
  `PROFESSIONMERE_ID` bigint(20) DEFAULT NULL,
  `PROFESSIONPERE_ID` bigint(20) DEFAULT NULL,
  `PROVINCE_ID` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `examcandidat`
--

CREATE TABLE `examcandidat` (
  `ID` bigint(20) NOT NULL,
  `NOTECALC` float DEFAULT NULL,
  `CONDIDATURE_ID` bigint(20) DEFAULT NULL,
  `MATIERECONCOUR_ID` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `examcandidat`
--

INSERT INTO `examcandidat` (`ID`, `NOTECALC`, `CONDIDATURE_ID`, `MATIERECONCOUR_ID`) VALUES
(1, 20, 657, 52),
(2, 20, 657, 51),
(3, 19.9, 658, 52),
(4, 19.9, 658, 51),
(5, 19.8, 659, 52),
(6, 19.8, 659, 51),
(51, 18, 5, 52),
(151, 19, 662, 51),
(152, 14, 662, 52),
(153, 14, 663, 51),
(154, 20, 663, 52),
(155, 18, 664, 52),
(156, 15, 664, 51),
(551, 0, 2, 51),
(552, 19, 2, 52),
(601, 0, 4, 51),
(602, 9, 4, 52),
(603, 12, 12, 51),
(604, 11.5, 12, 52),
(652, 13, 5, 51),
(653, 13, 6, 51),
(654, 14, 6, 52),
(1201, 15, 3, 901),
(1202, 14, 3, 52),
(1203, 2, 3, 51),
(1205, 13, 8, 901),
(1206, 14, 8, 52),
(1207, 5, 8, 51),
(1209, 15, 9, 901),
(1210, 14, 9, 52),
(1211, 13, 9, 51),
(1301, 15, 3, 851),
(1302, 17, 8, 851),
(1303, 12, 9, 851),
(1455, 12, 7, 51),
(1456, 9, 7, 52);

-- --------------------------------------------------------

--
-- Structure de la table `expressionbesoin`
--

CREATE TABLE `expressionbesoin` (
  `ID` varchar(255) NOT NULL,
  `DATEEXPRESSIONBESOIN` date DEFAULT NULL,
  `RECU` int(11) DEFAULT NULL,
  `USER_ID` varchar(255) DEFAULT NULL,
  `USERSTOCK_ID` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `facture`
--

CREATE TABLE `facture` (
  `ID` varchar(255) NOT NULL,
  `DATEFACTURE` date DEFAULT NULL,
  `PRIXTOTALE` double DEFAULT NULL,
  `COMMANDE_ID` varchar(255) DEFAULT NULL,
  `FOURNISSEUR_ID` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `filiere`
--

CREATE TABLE `filiere` (
  `ID` bigint(20) NOT NULL,
  `ABREVIATION` varchar(255) DEFAULT NULL,
  `LIBELLE` varchar(255) DEFAULT NULL,
  `OBJECTIF` varchar(255) DEFAULT NULL,
  `TYPEFILIERE` int(11) DEFAULT NULL,
  `TYPEFORMATION` int(11) DEFAULT NULL,
  `DEPARTEMENT_ID` bigint(20) DEFAULT NULL,
  `SECTION_ID` bigint(20) DEFAULT NULL,
  `RESPONSABLEFILIERE_CIN` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `filiere`
--

INSERT INTO `filiere` (`ID`, `ABREVIATION`, `LIBELLE`, `OBJECTIF`, `TYPEFILIERE`, `TYPEFORMATION`, `DEPARTEMENT_ID`, `SECTION_ID`, `RESPONSABLEFILIERE_CIN`) VALUES
(1, 'IRISI', 'irisi', '', 0, 2, NULL, 55, NULL),
(2, 'IFA', NULL, NULL, NULL, 2, 1, 55, 'ana'),
(3, 'ISA', NULL, NULL, NULL, NULL, NULL, 55, 'EE0001'),
(4, 'GMP', 'gmp', NULL, NULL, NULL, 2, 55, 'ana');

-- --------------------------------------------------------

--
-- Structure de la table `filieremaster`
--

CREATE TABLE `filieremaster` (
  `ID` bigint(20) NOT NULL,
  `ABRAPG` varchar(255) DEFAULT NULL,
  `DIPLOME` varchar(255) DEFAULT NULL,
  `TITRE` varchar(255) DEFAULT NULL,
  `TYPE` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `fournisseur`
--

CREATE TABLE `fournisseur` (
  `ID` varchar(255) NOT NULL,
  `ADRESSE` varchar(255) DEFAULT NULL,
  `NOM` varchar(255) DEFAULT NULL,
  `TELEPHONE` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `laboratoire`
--

CREATE TABLE `laboratoire` (
  `ID` bigint(20) NOT NULL,
  `DESCRIPTION` varchar(255) DEFAULT NULL,
  `NOM` varchar(255) DEFAULT NULL,
  `DIRECTEUR_CIN` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `licence`
--

CREATE TABLE `licence` (
  `ID` bigint(20) NOT NULL,
  `NOM` varchar(255) DEFAULT NULL,
  `FILIERE_ID` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `lieu`
--

CREATE TABLE `lieu` (
  `ID` bigint(20) NOT NULL,
  `NUMAPG` varchar(255) DEFAULT NULL,
  `TITRE` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `ligne`
--

CREATE TABLE `ligne` (
  `ID` bigint(20) NOT NULL,
  `QUANTITE` double DEFAULT NULL,
  `PRODUIT_ID` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `lignecommande`
--

CREATE TABLE `lignecommande` (
  `ID` bigint(20) NOT NULL,
  `QUANTITE` double DEFAULT NULL,
  `COMMANDE_ID` varchar(255) DEFAULT NULL,
  `PRODUIT_ID` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `ligneexpressionbesoin`
--

CREATE TABLE `ligneexpressionbesoin` (
  `ID` bigint(20) NOT NULL,
  `QUANTITE` double DEFAULT NULL,
  `EXPRESSIONBESOIN_ID` varchar(255) DEFAULT NULL,
  `PRODUIT_ID` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `lignefacture`
--

CREATE TABLE `lignefacture` (
  `ID` bigint(20) NOT NULL,
  `QUANTITE` double DEFAULT NULL,
  `FACTURE_ID` varchar(255) DEFAULT NULL,
  `PRODUIT_ID` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `lignelivraison`
--

CREATE TABLE `lignelivraison` (
  `ID` bigint(20) NOT NULL,
  `QUANTITE` double DEFAULT NULL,
  `LIVRAISON_ID` varchar(255) DEFAULT NULL,
  `PRODUIT_ID` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `lignemagasin`
--

CREATE TABLE `lignemagasin` (
  `ID` bigint(20) NOT NULL,
  `QUANTITADEF` double DEFAULT NULL,
  `QUANTITE` double DEFAULT NULL,
  `MAGASIN_ID` varchar(255) DEFAULT NULL,
  `PRODUIT_ID` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `lignereception`
--

CREATE TABLE `lignereception` (
  `ID` bigint(20) NOT NULL,
  `QUANTITE` double DEFAULT NULL,
  `PRODUIT_ID` varchar(255) DEFAULT NULL,
  `RECEPTION_ID` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `livraison`
--

CREATE TABLE `livraison` (
  `ID` varchar(255) NOT NULL,
  `DATELIVRAISON` date DEFAULT NULL,
  `COMMANDE_ID` varchar(255) DEFAULT NULL,
  `USER_ID` varchar(255) DEFAULT NULL,
  `USERSTOCK_ID` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `lycee`
--

CREATE TABLE `lycee` (
  `ID` bigint(20) NOT NULL,
  `NUMAPG` varchar(255) DEFAULT NULL,
  `TITRE` varchar(255) DEFAULT NULL,
  `TYPE` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `magasin`
--

CREATE TABLE `magasin` (
  `ID` varchar(255) NOT NULL,
  `STOCK_ID` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `marque`
--

CREATE TABLE `marque` (
  `ID` bigint(20) NOT NULL,
  `LIBELLE` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `matierebac`
--

CREATE TABLE `matierebac` (
  `ID` bigint(20) NOT NULL,
  `COEFF` float DEFAULT NULL,
  `TITRE` varchar(255) DEFAULT NULL,
  `OPTIONBAC_ID` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `matiereconcour`
--

CREATE TABLE `matiereconcour` (
  `ID` bigint(20) NOT NULL,
  `NUMAPG` varchar(255) DEFAULT NULL,
  `TITRE` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `matiereconcour`
--

INSERT INTO `matiereconcour` (`ID`, `NUMAPG`, `TITRE`) VALUES
(51, 'progc', 'Programmation c'),
(52, 'thermoo', 'Thermodynamique'),
(353, 'adt', 'Audit'),
(851, 'Adonnee', 'Analyse de donnee '),
(901, 'thermoo', 'Thermodynamique'),
(1452, 'Math', 'Mathématique'),
(1453, 'mang', 'Management'),
(1454, 'cdg', 'Controle de gestion');

-- --------------------------------------------------------

--
-- Structure de la table `mentiondiplome`
--

CREATE TABLE `mentiondiplome` (
  `ID` bigint(20) NOT NULL,
  `NUMAPG` varchar(255) DEFAULT NULL,
  `TITRE` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `mentiondiplome`
--

INSERT INTO `mentiondiplome` (`ID`, `NUMAPG`, `TITRE`) VALUES
(1, 'bien', 'Bien'),
(2, 'ab', 'Assez bien'),
(3, 'psble', 'Passable'),
(4, 'Tb', 'très bien');

-- --------------------------------------------------------

--
-- Structure de la table `message`
--

CREATE TABLE `message` (
  `ID` bigint(20) NOT NULL,
  `DATEENVOI` datetime DEFAULT NULL,
  `EMAIL` varchar(255) DEFAULT NULL,
  `LU` tinyint(1) DEFAULT '0',
  `NOM` varchar(255) DEFAULT NULL,
  `PRENOM` varchar(255) DEFAULT NULL,
  `SUJET` varchar(255) DEFAULT NULL,
  `TEXTMESSAGE` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `module`
--

CREATE TABLE `module` (
  `ID` bigint(20) NOT NULL,
  `NOM` varchar(255) DEFAULT NULL,
  `ENSEIGNANT_CIN` varchar(255) DEFAULT NULL,
  `FILIERE_ID` bigint(20) DEFAULT NULL,
  `SEMESTRE_ID` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `niveau`
--

CREATE TABLE `niveau` (
  `ID` bigint(20) NOT NULL,
  `FILIERE_ID` bigint(20) DEFAULT NULL,
  `SEMESTRE_ID` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `niveau`
--

INSERT INTO `niveau` (`ID`, `FILIERE_ID`, `SEMESTRE_ID`) VALUES
(0, 1, 1),
(1, 2, 1),
(2, 3, 1),
(4, 4, 1);

-- --------------------------------------------------------

--
-- Structure de la table `noteannuelle`
--

CREATE TABLE `noteannuelle` (
  `ID` bigint(20) NOT NULL,
  `NBRMODULEVALIDE` int(11) DEFAULT NULL,
  `NOTE` double DEFAULT NULL,
  `ANNEE_ID` bigint(20) DEFAULT NULL,
  `ETUDIANT_CNE` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `notemodulaire`
--

CREATE TABLE `notemodulaire` (
  `ID` bigint(20) NOT NULL,
  `ETAT` int(11) DEFAULT NULL,
  `MENTION` varchar(255) DEFAULT NULL,
  `MENTIONBEFOREJURY` varchar(255) DEFAULT NULL,
  `NOTE` double DEFAULT NULL,
  `NOTEBEFOREJURY` double DEFAULT NULL,
  `PTJURY` double DEFAULT NULL,
  `ETUDIANT_CNE` bigint(20) DEFAULT NULL,
  `MODULE_ID` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `notesemestre`
--

CREATE TABLE `notesemestre` (
  `ID` bigint(20) NOT NULL,
  `ETAT` int(11) DEFAULT NULL,
  `ETATRELEVE` int(11) DEFAULT NULL,
  `MENTION` varchar(255) DEFAULT NULL,
  `NBRMODULEVALIDE` int(11) DEFAULT NULL,
  `NOTE` double DEFAULT NULL,
  `TOTAL` double DEFAULT NULL,
  `ETUDIANT_CNE` bigint(20) DEFAULT NULL,
  `SEMESTRE_ID` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `optionbac`
--

CREATE TABLE `optionbac` (
  `ID` bigint(20) NOT NULL,
  `NUMAPG` varchar(255) DEFAULT NULL,
  `TITRE` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `optionbac`
--

INSERT INTO `optionbac` (`ID`, `NUMAPG`, `TITRE`) VALUES
(1, 'SMA', 'Sience Mathematique A'),
(2, 'SP', 'Sience Physique');

-- --------------------------------------------------------

--
-- Structure de la table `ouvrage`
--

CREATE TABLE `ouvrage` (
  `NUMERO` varchar(255) NOT NULL,
  `AUTEUR` varchar(255) DEFAULT NULL,
  `EDITION` varchar(255) DEFAULT NULL,
  `RUBRIQUE_ID` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `pays`
--

CREATE TABLE `pays` (
  `ID` bigint(20) NOT NULL,
  `NUMAPG` varchar(255) DEFAULT NULL,
  `TITRE` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `pays`
--

INSERT INTO `pays` (`ID`, `NUMAPG`, `TITRE`) VALUES
(1, 'mrc', 'Maroc');

-- --------------------------------------------------------

--
-- Structure de la table `piece`
--

CREATE TABLE `piece` (
  `ID` bigint(20) NOT NULL,
  `FONCTIONNAIRE` int(11) DEFAULT NULL,
  `NATIONALITE` int(11) DEFAULT NULL,
  `NOMBRE` int(11) DEFAULT NULL,
  `TITRE` varchar(255) DEFAULT NULL,
  `TYPEINSCRIPTION` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `piece`
--

INSERT INTO `piece` (`ID`, `FONCTIONNAIRE`, `NATIONALITE`, `NOMBRE`, `TITRE`, `TYPEINSCRIPTION`) VALUES
(1, 0, 0, 1, 'Attestation originale du baccalaurÃ©at', 0),
(2, 0, 0, 1, 'Diplome originale du DEUST ou Ã©quivalent', 0),
(3, 0, 0, 3, 'copie (s) certifiÃ©re conforme du BaccalaurÃ©rat', 0),
(4, 0, 0, 2, 'copie (s) certifiÃ©e conforme du DEUST ou Ã©quivalent', 0),
(5, 0, 0, 2, 'copie (s) certifiÃ©e conforme de la carte d identitÃ© nationale', 0),
(6, 0, 0, 2, 'extrait (s) d acte de naissance ', 0),
(7, 0, 0, 3, 'enveloppe (s) timbrÃ©e portant l adresse du candidat', 0),
(8, 0, 0, 6, 'photo (s) d identitÃ©', 0),
(9, 0, 0, 1, 'Dossier mÃ©dical', 0),
(10, 0, 0, 1, 'Demande de transfert d inscription ', 1),
(11, 0, 0, 1, 'Autorisation ministÃ©rielle d inscription', 0),
(12, 0, 1, 2, 'copie (s) du passeport', 0),
(13, 0, 1, 2, 'copie (s) de la carte de sÃ©jour', 0),
(14, 1, 0, 1, 'Autorisation de l’organisme employeur', 0),
(18, 0, 0, 1, 'Diplome originale du Licence ou Ã©quivalent', 0),
(19, 0, 0, 2, 'copie (s) certifiÃ© conforme de LICENCE ou Ã©quivalent', 0),
(22, 0, 0, 1, 'Déclaration sur l honneur', 0);

-- --------------------------------------------------------

--
-- Structure de la table `pieceetudiant`
--

CREATE TABLE `pieceetudiant` (
  `ID` bigint(20) NOT NULL,
  `NBR` int(11) DEFAULT NULL,
  `CONDIDATURE_ID` bigint(20) DEFAULT NULL,
  `PIECESPARNIVEAU_ID` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `pieceetudiant`
--

INSERT INTO `pieceetudiant` (`ID`, `NBR`, `CONDIDATURE_ID`, `PIECESPARNIVEAU_ID`) VALUES
(0, 1, 2, 0),
(1, 2, 2, 1),
(5, 5, 3, 5),
(6, 1, 4, 1),
(7, 1, 5, 1),
(8, 1, 6, 1),
(9, 1, 7, 1),
(10, 1, 8, 5),
(11, 1, 9, 5),
(301, 0, 10, 1),
(303, 0, 11, 1),
(351, 0, 12, 1),
(401, 0, 13, 1),
(402, 0, 657, 12),
(403, 0, 658, 12),
(404, 0, 659, 12),
(405, 0, 660, 12),
(406, 0, 661, 12),
(407, 0, 662, 0),
(408, 0, 663, 0),
(409, 0, 664, 0);

-- --------------------------------------------------------

--
-- Structure de la table `piecesparniveau`
--

CREATE TABLE `piecesparniveau` (
  `ID` bigint(20) NOT NULL,
  `NOMBRE` int(11) DEFAULT NULL,
  `NIVEAU_ID` bigint(20) DEFAULT NULL,
  `PIECESJOINTE_ID` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `piecesparniveau`
--

INSERT INTO `piecesparniveau` (`ID`, `NOMBRE`, `NIVEAU_ID`, `PIECESJOINTE_ID`) VALUES
(0, 1, 0, 1),
(1, 1, 0, 14),
(2, 2, 0, 11),
(3, 3, 0, 19),
(5, 1, 1, 1),
(12, 2, 2, 1);

-- --------------------------------------------------------

--
-- Structure de la table `produit`
--

CREATE TABLE `produit` (
  `ID` varchar(255) NOT NULL,
  `DESCRIPTION` varchar(255) DEFAULT NULL,
  `LIBELLE` varchar(255) DEFAULT NULL,
  `PRIX` double DEFAULT NULL,
  `SEUILALERT` double DEFAULT NULL,
  `CATEGORIE_ID` bigint(20) DEFAULT NULL,
  `MARQUE_ID` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `profession`
--

CREATE TABLE `profession` (
  `ID` bigint(20) NOT NULL,
  `NUMAPG` varchar(255) DEFAULT NULL,
  `TITRE` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `profession`
--

INSERT INTO `profession` (`ID`, `NUMAPG`, `TITRE`) VALUES
(1, 'doc', 'Docteur');

-- --------------------------------------------------------

--
-- Structure de la table `pv`
--

CREATE TABLE `pv` (
  `ID` bigint(20) NOT NULL,
  `SEMESTRE_ID` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `pvnotemodulaireitem`
--

CREATE TABLE `pvnotemodulaireitem` (
  `ID` bigint(20) NOT NULL,
  `PVNOTESEMESTREITEM_ID` bigint(20) DEFAULT NULL,
  `NOTEMODULAIRE_ID` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `pvnotesemestreitem`
--

CREATE TABLE `pvnotesemestreitem` (
  `ID` bigint(20) NOT NULL,
  `PV_ID` bigint(20) DEFAULT NULL,
  `NOTESEMESTRE_ID` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `reception`
--

CREATE TABLE `reception` (
  `ID` varchar(255) NOT NULL,
  `DATERECEPTION` date DEFAULT NULL,
  `EXPRESSIONBESOIN_ID` varchar(255) DEFAULT NULL,
  `USER_ID` varchar(255) DEFAULT NULL,
  `USERSTOCK_ID` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `region`
--

CREATE TABLE `region` (
  `ID` bigint(20) NOT NULL,
  `NOM` varchar(255) DEFAULT NULL,
  `PAYS_ID` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `region`
--

INSERT INTO `region` (`ID`, `NOM`, `PAYS_ID`) VALUES
(1, 'Tanger-Tétouan-Al Hoceïma', 1),
(2, ' Rabat-Salé-Kénitra', 1),
(4, 'Souss-Massa', 1);

-- --------------------------------------------------------

--
-- Structure de la table `role`
--

CREATE TABLE `role` (
  `ID` bigint(20) NOT NULL,
  `LIBELLE` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `rubrique`
--

CREATE TABLE `rubrique` (
  `ID` bigint(20) NOT NULL,
  `NOM` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `section`
--

CREATE TABLE `section` (
  `ID` bigint(20) NOT NULL,
  `TITRE` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `section`
--

INSERT INTO `section` (`ID`, `TITRE`) VALUES
(54, 'TC'),
(55, 'cycle'),
(56, 'master');

-- --------------------------------------------------------

--
-- Structure de la table `secure`
--

CREATE TABLE `secure` (
  `ID` bigint(20) NOT NULL,
  `CORRECT` tinyint(1) DEFAULT '0',
  `QUEST` varchar(255) DEFAULT NULL,
  `RESP` varchar(255) DEFAULT NULL,
  `ENSEIGNANT_CIN` varchar(255) DEFAULT NULL,
  `ETUDIANT_CNE` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `semestre`
--

CREATE TABLE `semestre` (
  `ID` bigint(20) NOT NULL,
  `ANNEEDEVALIDATION` varchar(255) DEFAULT NULL,
  `LIBELLE` int(11) DEFAULT NULL,
  `MODEDEVALIDATION` varchar(255) DEFAULT NULL,
  `NOMBREDINSCRIPTION` int(11) DEFAULT NULL,
  `NOTES1` float DEFAULT NULL,
  `NOTES2` float DEFAULT NULL,
  `NOTES3` float DEFAULT NULL,
  `NOTES4` float DEFAULT NULL,
  `NOTES5` float DEFAULT NULL,
  `NOTES6` float DEFAULT NULL,
  `VALIDEAPRESRATTRAPAGE` int(11) DEFAULT NULL,
  `ANNEE_ID` bigint(20) DEFAULT NULL,
  `CANDIDAT_CNE` varchar(255) DEFAULT NULL,
  `FILIERE_ID` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `semestre`
--

INSERT INTO `semestre` (`ID`, `ANNEEDEVALIDATION`, `LIBELLE`, `MODEDEVALIDATION`, `NOMBREDINSCRIPTION`, `NOTES1`, `NOTES2`, `NOTES3`, `NOTES4`, `NOTES5`, `NOTES6`, `VALIDEAPRESRATTRAPAGE`, `ANNEE_ID`, `CANDIDAT_CNE`, `FILIERE_ID`) VALUES
(1, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 6, NULL, 1),
(2, NULL, 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 6, NULL, 1);

-- --------------------------------------------------------

--
-- Structure de la table `sequence`
--

CREATE TABLE `sequence` (
  `SEQ_NAME` varchar(50) NOT NULL,
  `SEQ_COUNT` decimal(38,0) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `sequence`
--

INSERT INTO `sequence` (`SEQ_NAME`, `SEQ_COUNT`) VALUES
('SEQ_GEN', '400');

-- --------------------------------------------------------

--
-- Structure de la table `stock`
--

CREATE TABLE `stock` (
  `ID` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `themerecherche`
--

CREATE TABLE `themerecherche` (
  `ID` bigint(20) NOT NULL,
  `NOM` varchar(255) DEFAULT NULL,
  `EQUIPERECHERCHE_ID` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `timeline`
--

CREATE TABLE `timeline` (
  `ID` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `user`
--

CREATE TABLE `user` (
  `ID` varchar(255) NOT NULL,
  `NOM` varchar(255) DEFAULT NULL,
  `PASSWORD` varchar(255) DEFAULT NULL,
  `PRENOM` varchar(255) DEFAULT NULL,
  `TELEPHONE` varchar(255) DEFAULT NULL,
  `ENTITEADMINISTRATIVE_ID` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `usermaster`
--

CREATE TABLE `usermaster` (
  `ID` bigint(20) NOT NULL,
  `LOGIN` varchar(255) DEFAULT NULL,
  `PWD` varchar(255) DEFAULT NULL,
  `TYPE` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `userstock`
--

CREATE TABLE `userstock` (
  `ID` varchar(255) NOT NULL,
  `ADMINE` tinyint(1) DEFAULT '0',
  `BLOCKED` tinyint(1) DEFAULT '0',
  `DATENAISSANCE` date DEFAULT NULL,
  `EMAIL` varchar(255) DEFAULT NULL,
  `GENDER` varchar(255) DEFAULT NULL,
  `GRADE` varchar(255) DEFAULT NULL,
  `MDPCHANGED` tinyint(1) DEFAULT '0',
  `NBRCNX` int(11) DEFAULT NULL,
  `NOM` varchar(255) DEFAULT NULL,
  `PASSWORD` varchar(255) DEFAULT NULL,
  `PRENOM` varchar(255) DEFAULT NULL,
  `TELEPHONE` varchar(255) DEFAULT NULL,
  `ENTITEADMINISTRATIVE_ID` varchar(255) DEFAULT NULL,
  `ROLE_ID` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `userstock`
--

INSERT INTO `userstock` (`ID`, `ADMINE`, `BLOCKED`, `DATENAISSANCE`, `EMAIL`, `GENDER`, `GRADE`, `MDPCHANGED`, `NBRCNX`, `NOM`, `PASSWORD`, `PRENOM`, `TELEPHONE`, `ENTITEADMINISTRATIVE_ID`, `ROLE_ID`) VALUES
('159487', 1, 0, '1980-06-20', 'ouss@ouss', NULL, NULL, 0, 0, 'Hansali', '5058da477ed7c0bd2997d080333baf5970cd62e5db91a7230619afc84d367d8b', 'Oussama', '0691816262', NULL, NULL);

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `academie`
--
ALTER TABLE `academie`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `FK_ACADEMIE_REGION_ID` (`REGION_ID`);

--
-- Index pour la table `actualite`
--
ALTER TABLE `actualite`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `FK_ACTUALITE_AUTEUR_CIN` (`AUTEUR_CIN`);

--
-- Index pour la table `annee`
--
ALTER TABLE `annee`
  ADD PRIMARY KEY (`ID`);

--
-- Index pour la table `anneeinscription`
--
ALTER TABLE `anneeinscription`
  ADD PRIMARY KEY (`ID`);

--
-- Index pour la table `anneuniversitaire`
--
ALTER TABLE `anneuniversitaire`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `FK_ANNEUNIVERSITAIRE_ANNEEMAX_ID` (`ANNEEMAX_ID`),
  ADD KEY `FK_ANNEUNIVERSITAIRE_ANNEEMIN_ID` (`ANNEEMIN_ID`);

--
-- Index pour la table `article`
--
ALTER TABLE `article`
  ADD PRIMARY KEY (`ID`);

--
-- Index pour la table `candidat`
--
ALTER TABLE `candidat`
  ADD PRIMARY KEY (`CNE`),
  ADD KEY `FK_CANDIDAT_ACADEMIE_ID` (`ACADEMIE_ID`),
  ADD KEY `FK_CANDIDAT_PAYS_ID` (`PAYS_ID`),
  ADD KEY `FK_CANDIDAT_PROFESSIONDELAMERE_ID` (`PROFESSIONDELAMERE_ID`),
  ADD KEY `FK_CANDIDAT_LYCEE_ID` (`LYCEE_ID`),
  ADD KEY `FK_CANDIDAT_OPTIONBAC_ID` (`OPTIONBAC_ID`),
  ADD KEY `FK_CANDIDAT_DERNIERDIPLOME_ID` (`DERNIERDIPLOME_ID`),
  ADD KEY `FK_CANDIDAT_ETABLISSEMENT_ID` (`ETABLISSEMENT_ID`),
  ADD KEY `FK_CANDIDAT_LICENCE_ID` (`LICENCE_ID`);

--
-- Index pour la table `categorie`
--
ALTER TABLE `categorie`
  ADD PRIMARY KEY (`ID`);

--
-- Index pour la table `coeffcalibrage`
--
ALTER TABLE `coeffcalibrage`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `FK_COEFFCALIBRAGE_ETABLISSEMENT_ID` (`ETABLISSEMENT_ID`);

--
-- Index pour la table `commande`
--
ALTER TABLE `commande`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `FK_COMMANDE_USERSTOCK_ID` (`USERSTOCK_ID`),
  ADD KEY `FK_COMMANDE_USER_ID` (`USER_ID`);

--
-- Index pour la table `concourexammatiere`
--
ALTER TABLE `concourexammatiere`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `FK_CONCOUREXAMMATIERE_MATIERECONCOUR_ID` (`MATIERECONCOUR_ID`),
  ADD KEY `FK_CONCOUREXAMMATIERE_CONCOURNIVEAU_ID` (`CONCOURNIVEAU_ID`);

--
-- Index pour la table `concourniveau`
--
ALTER TABLE `concourniveau`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `FK_CONCOURNIVEAU_NIVEAU_ID` (`NIVEAU_ID`);

--
-- Index pour la table `concouroral`
--
ALTER TABLE `concouroral`
  ADD PRIMARY KEY (`ID`);

--
-- Index pour la table `condidature`
--
ALTER TABLE `condidature`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `FK_CONDIDATURE_ANNEUNIVERSITAIRE_ID` (`ANNEUNIVERSITAIRE_ID`),
  ADD KEY `FK_CONDIDATURE_CANDIDAT_CNE` (`CANDIDAT_CNE`),
  ADD KEY `FK_CONDIDATURE_FILIERE_ID` (`FILIERE_ID`);

--
-- Index pour la table `demande`
--
ALTER TABLE `demande`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `FK_DEMANDE_ETUDIANT_CNE` (`ETUDIANT_CNE`);

--
-- Index pour la table `demandeattestaion`
--
ALTER TABLE `demandeattestaion`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `FK_DEMANDEATTESTAION_ETUDIANT_CNE` (`ETUDIANT_CNE`);

--
-- Index pour la table `demandederogation`
--
ALTER TABLE `demandederogation`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `FK_DEMANDEDEROGATION_ETUDIANT_CNE` (`ETUDIANT_CNE`),
  ADD KEY `FK_DEMANDEDEROGATION_FILIERE_ID` (`FILIERE_ID`);

--
-- Index pour la table `demandeitem`
--
ALTER TABLE `demandeitem`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `FK_DEMANDEITEM_MODULE_ID` (`MODULE_ID`),
  ADD KEY `FK_DEMANDEITEM_SEMESTRE_ID` (`SEMESTRE_ID`),
  ADD KEY `FK_DEMANDEITEM_DEMANDE_ID` (`DEMANDE_ID`);

--
-- Index pour la table `demandelicence`
--
ALTER TABLE `demandelicence`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `FK_DEMANDELICENCE_ANNEUNIVERSITAIRE_ID` (`ANNEUNIVERSITAIRE_ID`),
  ADD KEY `FK_DEMANDELICENCE_ETUDIANT_CNE` (`ETUDIANT_CNE`);

--
-- Index pour la table `demandelicenceitem`
--
ALTER TABLE `demandelicenceitem`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `FK_DEMANDELICENCEITEM_LICENCE_ID` (`LICENCE_ID`),
  ADD KEY `FK_DEMANDELICENCEITEM_DEMANDELICENCE_ID` (`DEMANDELICENCE_ID`);

--
-- Index pour la table `demanderelevenote`
--
ALTER TABLE `demanderelevenote`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `FK_DEMANDERELEVENOTE_ETUDIANT_CNE` (`ETUDIANT_CNE`);

--
-- Index pour la table `demanderelevenoteitem`
--
ALTER TABLE `demanderelevenoteitem`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `FK_DEMANDERELEVENOTEITEM_SEMESTRE_ID` (`SEMESTRE_ID`),
  ADD KEY `FK_DEMANDERELEVENOTEITEM_DEMANDERELEVENOTE_ID` (`DEMANDERELEVENOTE_ID`);

--
-- Index pour la table `departement`
--
ALTER TABLE `departement`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `FK_DEPARTEMENT_CHEFDEPARTEMENT_CIN` (`CHEFDEPARTEMENT_CIN`);

--
-- Index pour la table `dernierdiplome`
--
ALTER TABLE `dernierdiplome`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `FK_DERNIERDIPLOME_MENTIONDIPLOME_ID` (`MENTIONDIPLOME_ID`);

--
-- Index pour la table `device`
--
ALTER TABLE `device`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `FK_DEVICE_USER_CIN` (`USER_CIN`),
  ADD KEY `FK_DEVICE_USERSTOCK_ID` (`USERSTOCK_ID`),
  ADD KEY `FK_DEVICE_ETUDIANT_CNE` (`ETUDIANT_CNE`);

--
-- Index pour la table `enseignant`
--
ALTER TABLE `enseignant`
  ADD PRIMARY KEY (`CIN`),
  ADD KEY `FK_ENSEIGNANT_FILIERE_ID` (`FILIERE_ID`),
  ADD KEY `FK_ENSEIGNANT_DEPARTEMENT_ID` (`DEPARTEMENT_ID`);

--
-- Index pour la table `entiteadministrative`
--
ALTER TABLE `entiteadministrative`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `FK_ENTITEADMINISTRATIVE_MAGASIN_ID` (`MAGASIN_ID`);

--
-- Index pour la table `equiperecherche`
--
ALTER TABLE `equiperecherche`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `FK_EQUIPERECHERCHE_LABORATOIRE_ID` (`LABORATOIRE_ID`),
  ADD KEY `FK_EQUIPERECHERCHE_CHEFEQUIPE_CIN` (`CHEFEQUIPE_CIN`);

--
-- Index pour la table `etablissementtype`
--
ALTER TABLE `etablissementtype`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `FK_ETABLISSEMENTTYPE_ACADEMIE_ID` (`ACADEMIE_ID`);

--
-- Index pour la table `etudiant`
--
ALTER TABLE `etudiant`
  ADD PRIMARY KEY (`CNE`),
  ADD KEY `FK_ETUDIANT_CANDIDAT_CNE` (`CANDIDAT_CNE`),
  ADD KEY `FK_ETUDIANT_SEMESTREACTUEL_ID` (`SEMESTREACTUEL_ID`),
  ADD KEY `FK_ETUDIANT_EQUIPERECHERCHE_ID` (`EQUIPERECHERCHE_ID`),
  ADD KEY `FK_ETUDIANT_FILIERE_ID` (`FILIERE_ID`),
  ADD KEY `FK_ETUDIANT_NIVEAU_ID` (`NIVEAU_ID`);

--
-- Index pour la table `etudiantmaster`
--
ALTER TABLE `etudiantmaster`
  ADD PRIMARY KEY (`CNE`),
  ADD KEY `FK_ETUDIANTMASTER_PROVINCE_ID` (`PROVINCE_ID`),
  ADD KEY `FK_ETUDIANTMASTER_TYPEDERNIERDIPLOME_ID` (`TYPEDERNIERDIPLOME_ID`),
  ADD KEY `FK_ETUDIANTMASTER_PROFESSIONPERE_ID` (`PROFESSIONPERE_ID`),
  ADD KEY `FK_ETUDIANTMASTER_PROFESSIONMERE_ID` (`PROFESSIONMERE_ID`),
  ADD KEY `FK_ETUDIANTMASTER_ACADEMIE_ID` (`ACADEMIE_ID`),
  ADD KEY `FK_ETUDIANTMASTER_FILIEREMASTER_ID` (`FILIEREMASTER_ID`),
  ADD KEY `FK_ETUDIANTMASTER_MENTIONBAC_ID` (`MENTIONBAC_ID`),
  ADD KEY `FK_ETUDIANTMASTER_OPTIONBAC_ID` (`OPTIONBAC_ID`),
  ADD KEY `FK_ETUDIANTMASTER_LYCEE_ID` (`LYCEE_ID`),
  ADD KEY `FK_ETUDIANTMASTER_PAYS_ID` (`PAYS_ID`);

--
-- Index pour la table `examcandidat`
--
ALTER TABLE `examcandidat`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `FK_EXAMCANDIDAT_MATIERECONCOUR_ID` (`MATIERECONCOUR_ID`),
  ADD KEY `FK_EXAMCANDIDAT_CONDIDATURE_ID` (`CONDIDATURE_ID`);

--
-- Index pour la table `expressionbesoin`
--
ALTER TABLE `expressionbesoin`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `FK_EXPRESSIONBESOIN_USER_ID` (`USER_ID`),
  ADD KEY `FK_EXPRESSIONBESOIN_USERSTOCK_ID` (`USERSTOCK_ID`);

--
-- Index pour la table `facture`
--
ALTER TABLE `facture`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `FK_FACTURE_COMMANDE_ID` (`COMMANDE_ID`),
  ADD KEY `FK_FACTURE_FOURNISSEUR_ID` (`FOURNISSEUR_ID`);

--
-- Index pour la table `filiere`
--
ALTER TABLE `filiere`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `FK_FILIERE_RESPONSABLEFILIERE_CIN` (`RESPONSABLEFILIERE_CIN`),
  ADD KEY `FK_FILIERE_SECTION_ID` (`SECTION_ID`),
  ADD KEY `FK_FILIERE_DEPARTEMENT_ID` (`DEPARTEMENT_ID`);

--
-- Index pour la table `filieremaster`
--
ALTER TABLE `filieremaster`
  ADD PRIMARY KEY (`ID`);

--
-- Index pour la table `fournisseur`
--
ALTER TABLE `fournisseur`
  ADD PRIMARY KEY (`ID`);

--
-- Index pour la table `laboratoire`
--
ALTER TABLE `laboratoire`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `FK_LABORATOIRE_DIRECTEUR_CIN` (`DIRECTEUR_CIN`);

--
-- Index pour la table `licence`
--
ALTER TABLE `licence`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `FK_LICENCE_FILIERE_ID` (`FILIERE_ID`);

--
-- Index pour la table `lieu`
--
ALTER TABLE `lieu`
  ADD PRIMARY KEY (`ID`);

--
-- Index pour la table `ligne`
--
ALTER TABLE `ligne`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `FK_LIGNE_PRODUIT_ID` (`PRODUIT_ID`);

--
-- Index pour la table `lignecommande`
--
ALTER TABLE `lignecommande`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `FK_LigneCommande_PRODUIT_ID` (`PRODUIT_ID`),
  ADD KEY `FK_LigneCommande_COMMANDE_ID` (`COMMANDE_ID`);

--
-- Index pour la table `ligneexpressionbesoin`
--
ALTER TABLE `ligneexpressionbesoin`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `FK_LigneExpressionBesoin_EXPRESSIONBESOIN_ID` (`EXPRESSIONBESOIN_ID`),
  ADD KEY `FK_LigneExpressionBesoin_PRODUIT_ID` (`PRODUIT_ID`);

--
-- Index pour la table `lignefacture`
--
ALTER TABLE `lignefacture`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `FK_LigneFacture_FACTURE_ID` (`FACTURE_ID`),
  ADD KEY `FK_LigneFacture_PRODUIT_ID` (`PRODUIT_ID`);

--
-- Index pour la table `lignelivraison`
--
ALTER TABLE `lignelivraison`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `FK_LigneLivraison_PRODUIT_ID` (`PRODUIT_ID`),
  ADD KEY `FK_LigneLivraison_LIVRAISON_ID` (`LIVRAISON_ID`);

--
-- Index pour la table `lignemagasin`
--
ALTER TABLE `lignemagasin`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `FK_LigneMagasin_PRODUIT_ID` (`PRODUIT_ID`),
  ADD KEY `FK_LigneMagasin_MAGASIN_ID` (`MAGASIN_ID`);

--
-- Index pour la table `lignereception`
--
ALTER TABLE `lignereception`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `FK_LigneReception_PRODUIT_ID` (`PRODUIT_ID`),
  ADD KEY `FK_LigneReception_RECEPTION_ID` (`RECEPTION_ID`);

--
-- Index pour la table `livraison`
--
ALTER TABLE `livraison`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `FK_LIVRAISON_USER_ID` (`USER_ID`),
  ADD KEY `FK_LIVRAISON_COMMANDE_ID` (`COMMANDE_ID`),
  ADD KEY `FK_LIVRAISON_USERSTOCK_ID` (`USERSTOCK_ID`);

--
-- Index pour la table `lycee`
--
ALTER TABLE `lycee`
  ADD PRIMARY KEY (`ID`);

--
-- Index pour la table `magasin`
--
ALTER TABLE `magasin`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `FK_MAGASIN_STOCK_ID` (`STOCK_ID`);

--
-- Index pour la table `marque`
--
ALTER TABLE `marque`
  ADD PRIMARY KEY (`ID`);

--
-- Index pour la table `matierebac`
--
ALTER TABLE `matierebac`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `FK_MATIEREBAC_OPTIONBAC_ID` (`OPTIONBAC_ID`);

--
-- Index pour la table `matiereconcour`
--
ALTER TABLE `matiereconcour`
  ADD PRIMARY KEY (`ID`);

--
-- Index pour la table `mentiondiplome`
--
ALTER TABLE `mentiondiplome`
  ADD PRIMARY KEY (`ID`);

--
-- Index pour la table `message`
--
ALTER TABLE `message`
  ADD PRIMARY KEY (`ID`);

--
-- Index pour la table `module`
--
ALTER TABLE `module`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `FK_MODULE_SEMESTRE_ID` (`SEMESTRE_ID`),
  ADD KEY `FK_MODULE_ENSEIGNANT_CIN` (`ENSEIGNANT_CIN`),
  ADD KEY `FK_MODULE_FILIERE_ID` (`FILIERE_ID`);

--
-- Index pour la table `niveau`
--
ALTER TABLE `niveau`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `FK_NIVEAU_SEMESTRE_ID` (`SEMESTRE_ID`),
  ADD KEY `FK_NIVEAU_FILIERE_ID` (`FILIERE_ID`);

--
-- Index pour la table `noteannuelle`
--
ALTER TABLE `noteannuelle`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `FK_NOTEANNUELLE_ANNEE_ID` (`ANNEE_ID`),
  ADD KEY `FK_NOTEANNUELLE_ETUDIANT_CNE` (`ETUDIANT_CNE`);

--
-- Index pour la table `notemodulaire`
--
ALTER TABLE `notemodulaire`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `FK_NOTEMODULAIRE_ETUDIANT_CNE` (`ETUDIANT_CNE`),
  ADD KEY `FK_NOTEMODULAIRE_MODULE_ID` (`MODULE_ID`);

--
-- Index pour la table `notesemestre`
--
ALTER TABLE `notesemestre`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `FK_NOTESEMESTRE_ETUDIANT_CNE` (`ETUDIANT_CNE`),
  ADD KEY `FK_NOTESEMESTRE_SEMESTRE_ID` (`SEMESTRE_ID`);

--
-- Index pour la table `optionbac`
--
ALTER TABLE `optionbac`
  ADD PRIMARY KEY (`ID`);

--
-- Index pour la table `ouvrage`
--
ALTER TABLE `ouvrage`
  ADD PRIMARY KEY (`NUMERO`),
  ADD KEY `FK_OUVRAGE_RUBRIQUE_ID` (`RUBRIQUE_ID`);

--
-- Index pour la table `pays`
--
ALTER TABLE `pays`
  ADD PRIMARY KEY (`ID`);

--
-- Index pour la table `piece`
--
ALTER TABLE `piece`
  ADD PRIMARY KEY (`ID`);

--
-- Index pour la table `pieceetudiant`
--
ALTER TABLE `pieceetudiant`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `FK_PIECEETUDIANT_PIECESPARNIVEAU_ID` (`PIECESPARNIVEAU_ID`),
  ADD KEY `FK_PIECEETUDIANT_CONDIDATURE_ID` (`CONDIDATURE_ID`);

--
-- Index pour la table `piecesparniveau`
--
ALTER TABLE `piecesparniveau`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `FK_PIECESPARNIVEAU_NIVEAU_ID` (`NIVEAU_ID`),
  ADD KEY `FK_PIECESPARNIVEAU_PIECESJOINTE_ID` (`PIECESJOINTE_ID`);

--
-- Index pour la table `produit`
--
ALTER TABLE `produit`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `FK_PRODUIT_CATEGORIE_ID` (`CATEGORIE_ID`),
  ADD KEY `FK_PRODUIT_MARQUE_ID` (`MARQUE_ID`);

--
-- Index pour la table `profession`
--
ALTER TABLE `profession`
  ADD PRIMARY KEY (`ID`);

--
-- Index pour la table `pv`
--
ALTER TABLE `pv`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `FK_PV_SEMESTRE_ID` (`SEMESTRE_ID`);

--
-- Index pour la table `pvnotemodulaireitem`
--
ALTER TABLE `pvnotemodulaireitem`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `FK_PVNOTEMODULAIREITEM_NOTEMODULAIRE_ID` (`NOTEMODULAIRE_ID`),
  ADD KEY `FK_PVNOTEMODULAIREITEM_PVNOTESEMESTREITEM_ID` (`PVNOTESEMESTREITEM_ID`);

--
-- Index pour la table `pvnotesemestreitem`
--
ALTER TABLE `pvnotesemestreitem`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `FK_PVNOTESEMESTREITEM_NOTESEMESTRE_ID` (`NOTESEMESTRE_ID`),
  ADD KEY `FK_PVNOTESEMESTREITEM_PV_ID` (`PV_ID`);

--
-- Index pour la table `reception`
--
ALTER TABLE `reception`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `FK_RECEPTION_EXPRESSIONBESOIN_ID` (`EXPRESSIONBESOIN_ID`),
  ADD KEY `FK_RECEPTION_USER_ID` (`USER_ID`),
  ADD KEY `FK_RECEPTION_USERSTOCK_ID` (`USERSTOCK_ID`);

--
-- Index pour la table `region`
--
ALTER TABLE `region`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `FK_REGION_PAYS_ID` (`PAYS_ID`);

--
-- Index pour la table `role`
--
ALTER TABLE `role`
  ADD PRIMARY KEY (`ID`);

--
-- Index pour la table `rubrique`
--
ALTER TABLE `rubrique`
  ADD PRIMARY KEY (`ID`);

--
-- Index pour la table `section`
--
ALTER TABLE `section`
  ADD PRIMARY KEY (`ID`);

--
-- Index pour la table `secure`
--
ALTER TABLE `secure`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `FK_SECURE_ENSEIGNANT_CIN` (`ENSEIGNANT_CIN`),
  ADD KEY `FK_SECURE_ETUDIANT_CNE` (`ETUDIANT_CNE`);

--
-- Index pour la table `semestre`
--
ALTER TABLE `semestre`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `FK_SEMESTRE_ANNEE_ID` (`ANNEE_ID`),
  ADD KEY `FK_SEMESTRE_CANDIDAT_CNE` (`CANDIDAT_CNE`),
  ADD KEY `FK_SEMESTRE_FILIERE_ID` (`FILIERE_ID`);

--
-- Index pour la table `sequence`
--
ALTER TABLE `sequence`
  ADD PRIMARY KEY (`SEQ_NAME`);

--
-- Index pour la table `stock`
--
ALTER TABLE `stock`
  ADD PRIMARY KEY (`ID`);

--
-- Index pour la table `themerecherche`
--
ALTER TABLE `themerecherche`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `FK_THEMERECHERCHE_EQUIPERECHERCHE_ID` (`EQUIPERECHERCHE_ID`);

--
-- Index pour la table `timeline`
--
ALTER TABLE `timeline`
  ADD PRIMARY KEY (`ID`);

--
-- Index pour la table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `FK_USER_ENTITEADMINISTRATIVE_ID` (`ENTITEADMINISTRATIVE_ID`);

--
-- Index pour la table `usermaster`
--
ALTER TABLE `usermaster`
  ADD PRIMARY KEY (`ID`);

--
-- Index pour la table `userstock`
--
ALTER TABLE `userstock`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `FK_USERSTOCK_ENTITEADMINISTRATIVE_ID` (`ENTITEADMINISTRATIVE_ID`),
  ADD KEY `FK_USERSTOCK_ROLE_ID` (`ROLE_ID`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `annee`
--
ALTER TABLE `annee`
  MODIFY `ID` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;
--
-- AUTO_INCREMENT pour la table `anneeinscription`
--
ALTER TABLE `anneeinscription`
  MODIFY `ID` bigint(20) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT pour la table `demande`
--
ALTER TABLE `demande`
  MODIFY `ID` bigint(20) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT pour la table `demandeattestaion`
--
ALTER TABLE `demandeattestaion`
  MODIFY `ID` bigint(20) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT pour la table `demandederogation`
--
ALTER TABLE `demandederogation`
  MODIFY `ID` bigint(20) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT pour la table `demandeitem`
--
ALTER TABLE `demandeitem`
  MODIFY `ID` bigint(20) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT pour la table `demandelicence`
--
ALTER TABLE `demandelicence`
  MODIFY `ID` bigint(20) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT pour la table `demandelicenceitem`
--
ALTER TABLE `demandelicenceitem`
  MODIFY `ID` bigint(20) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT pour la table `demanderelevenote`
--
ALTER TABLE `demanderelevenote`
  MODIFY `ID` bigint(20) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT pour la table `demanderelevenoteitem`
--
ALTER TABLE `demanderelevenoteitem`
  MODIFY `ID` bigint(20) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT pour la table `device`
--
ALTER TABLE `device`
  MODIFY `ID` bigint(20) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT pour la table `equiperecherche`
--
ALTER TABLE `equiperecherche`
  MODIFY `ID` bigint(20) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT pour la table `filiere`
--
ALTER TABLE `filiere`
  MODIFY `ID` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT pour la table `laboratoire`
--
ALTER TABLE `laboratoire`
  MODIFY `ID` bigint(20) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT pour la table `licence`
--
ALTER TABLE `licence`
  MODIFY `ID` bigint(20) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT pour la table `message`
--
ALTER TABLE `message`
  MODIFY `ID` bigint(20) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT pour la table `module`
--
ALTER TABLE `module`
  MODIFY `ID` bigint(20) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT pour la table `noteannuelle`
--
ALTER TABLE `noteannuelle`
  MODIFY `ID` bigint(20) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT pour la table `notemodulaire`
--
ALTER TABLE `notemodulaire`
  MODIFY `ID` bigint(20) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT pour la table `notesemestre`
--
ALTER TABLE `notesemestre`
  MODIFY `ID` bigint(20) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT pour la table `pv`
--
ALTER TABLE `pv`
  MODIFY `ID` bigint(20) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT pour la table `pvnotemodulaireitem`
--
ALTER TABLE `pvnotemodulaireitem`
  MODIFY `ID` bigint(20) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT pour la table `pvnotesemestreitem`
--
ALTER TABLE `pvnotesemestreitem`
  MODIFY `ID` bigint(20) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT pour la table `rubrique`
--
ALTER TABLE `rubrique`
  MODIFY `ID` bigint(20) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT pour la table `semestre`
--
ALTER TABLE `semestre`
  MODIFY `ID` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT pour la table `themerecherche`
--
ALTER TABLE `themerecherche`
  MODIFY `ID` bigint(20) NOT NULL AUTO_INCREMENT;
--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `academie`
--
ALTER TABLE `academie`
  ADD CONSTRAINT `FK_ACADEMIE_REGION_ID` FOREIGN KEY (`REGION_ID`) REFERENCES `region` (`ID`);

--
-- Contraintes pour la table `actualite`
--
ALTER TABLE `actualite`
  ADD CONSTRAINT `FK_ACTUALITE_AUTEUR_CIN` FOREIGN KEY (`AUTEUR_CIN`) REFERENCES `enseignant` (`CIN`);

--
-- Contraintes pour la table `anneuniversitaire`
--
ALTER TABLE `anneuniversitaire`
  ADD CONSTRAINT `FK_ANNEUNIVERSITAIRE_ANNEEMAX_ID` FOREIGN KEY (`ANNEEMAX_ID`) REFERENCES `annee` (`ID`),
  ADD CONSTRAINT `FK_ANNEUNIVERSITAIRE_ANNEEMIN_ID` FOREIGN KEY (`ANNEEMIN_ID`) REFERENCES `annee` (`ID`);

--
-- Contraintes pour la table `candidat`
--
ALTER TABLE `candidat`
  ADD CONSTRAINT `FK_CANDIDAT_ACADEMIE_ID` FOREIGN KEY (`ACADEMIE_ID`) REFERENCES `academie` (`ID`),
  ADD CONSTRAINT `FK_CANDIDAT_DERNIERDIPLOME_ID` FOREIGN KEY (`DERNIERDIPLOME_ID`) REFERENCES `dernierdiplome` (`ID`),
  ADD CONSTRAINT `FK_CANDIDAT_ETABLISSEMENT_ID` FOREIGN KEY (`ETABLISSEMENT_ID`) REFERENCES `etablissementtype` (`ID`),
  ADD CONSTRAINT `FK_CANDIDAT_LICENCE_ID` FOREIGN KEY (`LICENCE_ID`) REFERENCES `licence` (`ID`),
  ADD CONSTRAINT `FK_CANDIDAT_LYCEE_ID` FOREIGN KEY (`LYCEE_ID`) REFERENCES `lycee` (`ID`),
  ADD CONSTRAINT `FK_CANDIDAT_OPTIONBAC_ID` FOREIGN KEY (`OPTIONBAC_ID`) REFERENCES `optionbac` (`ID`),
  ADD CONSTRAINT `FK_CANDIDAT_PAYS_ID` FOREIGN KEY (`PAYS_ID`) REFERENCES `pays` (`ID`),
  ADD CONSTRAINT `FK_CANDIDAT_PROFESSIONDELAMERE_ID` FOREIGN KEY (`PROFESSIONDELAMERE_ID`) REFERENCES `profession` (`ID`);

--
-- Contraintes pour la table `coeffcalibrage`
--
ALTER TABLE `coeffcalibrage`
  ADD CONSTRAINT `FK_COEFFCALIBRAGE_ETABLISSEMENT_ID` FOREIGN KEY (`ETABLISSEMENT_ID`) REFERENCES `etablissementtype` (`ID`);

--
-- Contraintes pour la table `commande`
--
ALTER TABLE `commande`
  ADD CONSTRAINT `FK_COMMANDE_USERSTOCK_ID` FOREIGN KEY (`USERSTOCK_ID`) REFERENCES `userstock` (`ID`),
  ADD CONSTRAINT `FK_COMMANDE_USER_ID` FOREIGN KEY (`USER_ID`) REFERENCES `user` (`ID`);

--
-- Contraintes pour la table `concourexammatiere`
--
ALTER TABLE `concourexammatiere`
  ADD CONSTRAINT `FK_CONCOUREXAMMATIERE_CONCOURNIVEAU_ID` FOREIGN KEY (`CONCOURNIVEAU_ID`) REFERENCES `concourniveau` (`ID`),
  ADD CONSTRAINT `FK_CONCOUREXAMMATIERE_MATIERECONCOUR_ID` FOREIGN KEY (`MATIERECONCOUR_ID`) REFERENCES `matiereconcour` (`ID`);

--
-- Contraintes pour la table `concourniveau`
--
ALTER TABLE `concourniveau`
  ADD CONSTRAINT `FK_CONCOURNIVEAU_NIVEAU_ID` FOREIGN KEY (`NIVEAU_ID`) REFERENCES `niveau` (`ID`);

--
-- Contraintes pour la table `condidature`
--
ALTER TABLE `condidature`
  ADD CONSTRAINT `FK_CONDIDATURE_ANNEUNIVERSITAIRE_ID` FOREIGN KEY (`ANNEUNIVERSITAIRE_ID`) REFERENCES `anneuniversitaire` (`ID`),
  ADD CONSTRAINT `FK_CONDIDATURE_CANDIDAT_CNE` FOREIGN KEY (`CANDIDAT_CNE`) REFERENCES `candidat` (`CNE`),
  ADD CONSTRAINT `FK_CONDIDATURE_FILIERE_ID` FOREIGN KEY (`FILIERE_ID`) REFERENCES `filiere` (`ID`);

--
-- Contraintes pour la table `demande`
--
ALTER TABLE `demande`
  ADD CONSTRAINT `FK_DEMANDE_ETUDIANT_CNE` FOREIGN KEY (`ETUDIANT_CNE`) REFERENCES `etudiant` (`CNE`);

--
-- Contraintes pour la table `demandeattestaion`
--
ALTER TABLE `demandeattestaion`
  ADD CONSTRAINT `FK_DEMANDEATTESTAION_ETUDIANT_CNE` FOREIGN KEY (`ETUDIANT_CNE`) REFERENCES `etudiant` (`CNE`);

--
-- Contraintes pour la table `demandederogation`
--
ALTER TABLE `demandederogation`
  ADD CONSTRAINT `FK_DEMANDEDEROGATION_ETUDIANT_CNE` FOREIGN KEY (`ETUDIANT_CNE`) REFERENCES `etudiant` (`CNE`),
  ADD CONSTRAINT `FK_DEMANDEDEROGATION_FILIERE_ID` FOREIGN KEY (`FILIERE_ID`) REFERENCES `filiere` (`ID`);

--
-- Contraintes pour la table `demandeitem`
--
ALTER TABLE `demandeitem`
  ADD CONSTRAINT `FK_DEMANDEITEM_DEMANDE_ID` FOREIGN KEY (`DEMANDE_ID`) REFERENCES `demande` (`ID`),
  ADD CONSTRAINT `FK_DEMANDEITEM_MODULE_ID` FOREIGN KEY (`MODULE_ID`) REFERENCES `module` (`ID`),
  ADD CONSTRAINT `FK_DEMANDEITEM_SEMESTRE_ID` FOREIGN KEY (`SEMESTRE_ID`) REFERENCES `semestre` (`ID`);

--
-- Contraintes pour la table `demandelicence`
--
ALTER TABLE `demandelicence`
  ADD CONSTRAINT `FK_DEMANDELICENCE_ANNEUNIVERSITAIRE_ID` FOREIGN KEY (`ANNEUNIVERSITAIRE_ID`) REFERENCES `anneuniversitaire` (`ID`),
  ADD CONSTRAINT `FK_DEMANDELICENCE_ETUDIANT_CNE` FOREIGN KEY (`ETUDIANT_CNE`) REFERENCES `etudiant` (`CNE`);

--
-- Contraintes pour la table `demandelicenceitem`
--
ALTER TABLE `demandelicenceitem`
  ADD CONSTRAINT `FK_DEMANDELICENCEITEM_DEMANDELICENCE_ID` FOREIGN KEY (`DEMANDELICENCE_ID`) REFERENCES `demandelicence` (`ID`),
  ADD CONSTRAINT `FK_DEMANDELICENCEITEM_LICENCE_ID` FOREIGN KEY (`LICENCE_ID`) REFERENCES `licence` (`ID`);

--
-- Contraintes pour la table `demanderelevenote`
--
ALTER TABLE `demanderelevenote`
  ADD CONSTRAINT `FK_DEMANDERELEVENOTE_ETUDIANT_CNE` FOREIGN KEY (`ETUDIANT_CNE`) REFERENCES `etudiant` (`CNE`);

--
-- Contraintes pour la table `demanderelevenoteitem`
--
ALTER TABLE `demanderelevenoteitem`
  ADD CONSTRAINT `FK_DEMANDERELEVENOTEITEM_DEMANDERELEVENOTE_ID` FOREIGN KEY (`DEMANDERELEVENOTE_ID`) REFERENCES `demanderelevenote` (`ID`),
  ADD CONSTRAINT `FK_DEMANDERELEVENOTEITEM_SEMESTRE_ID` FOREIGN KEY (`SEMESTRE_ID`) REFERENCES `semestre` (`ID`);

--
-- Contraintes pour la table `departement`
--
ALTER TABLE `departement`
  ADD CONSTRAINT `FK_DEPARTEMENT_CHEFDEPARTEMENT_CIN` FOREIGN KEY (`CHEFDEPARTEMENT_CIN`) REFERENCES `enseignant` (`CIN`);

--
-- Contraintes pour la table `dernierdiplome`
--
ALTER TABLE `dernierdiplome`
  ADD CONSTRAINT `FK_DERNIERDIPLOME_MENTIONDIPLOME_ID` FOREIGN KEY (`MENTIONDIPLOME_ID`) REFERENCES `mentiondiplome` (`ID`);

--
-- Contraintes pour la table `device`
--
ALTER TABLE `device`
  ADD CONSTRAINT `FK_DEVICE_ETUDIANT_CNE` FOREIGN KEY (`ETUDIANT_CNE`) REFERENCES `etudiant` (`CNE`),
  ADD CONSTRAINT `FK_DEVICE_USERSTOCK_ID` FOREIGN KEY (`USERSTOCK_ID`) REFERENCES `userstock` (`ID`),
  ADD CONSTRAINT `FK_DEVICE_USER_CIN` FOREIGN KEY (`USER_CIN`) REFERENCES `enseignant` (`CIN`);

--
-- Contraintes pour la table `enseignant`
--
ALTER TABLE `enseignant`
  ADD CONSTRAINT `FK_ENSEIGNANT_DEPARTEMENT_ID` FOREIGN KEY (`DEPARTEMENT_ID`) REFERENCES `departement` (`ID`),
  ADD CONSTRAINT `FK_ENSEIGNANT_FILIERE_ID` FOREIGN KEY (`FILIERE_ID`) REFERENCES `filiere` (`ID`);

--
-- Contraintes pour la table `entiteadministrative`
--
ALTER TABLE `entiteadministrative`
  ADD CONSTRAINT `FK_ENTITEADMINISTRATIVE_MAGASIN_ID` FOREIGN KEY (`MAGASIN_ID`) REFERENCES `magasin` (`ID`);

--
-- Contraintes pour la table `equiperecherche`
--
ALTER TABLE `equiperecherche`
  ADD CONSTRAINT `FK_EQUIPERECHERCHE_CHEFEQUIPE_CIN` FOREIGN KEY (`CHEFEQUIPE_CIN`) REFERENCES `enseignant` (`CIN`),
  ADD CONSTRAINT `FK_EQUIPERECHERCHE_LABORATOIRE_ID` FOREIGN KEY (`LABORATOIRE_ID`) REFERENCES `laboratoire` (`ID`);

--
-- Contraintes pour la table `etablissementtype`
--
ALTER TABLE `etablissementtype`
  ADD CONSTRAINT `FK_ETABLISSEMENTTYPE_ACADEMIE_ID` FOREIGN KEY (`ACADEMIE_ID`) REFERENCES `academie` (`ID`);

--
-- Contraintes pour la table `etudiant`
--
ALTER TABLE `etudiant`
  ADD CONSTRAINT `FK_ETUDIANT_CANDIDAT_CNE` FOREIGN KEY (`CANDIDAT_CNE`) REFERENCES `candidat` (`CNE`),
  ADD CONSTRAINT `FK_ETUDIANT_EQUIPERECHERCHE_ID` FOREIGN KEY (`EQUIPERECHERCHE_ID`) REFERENCES `equiperecherche` (`ID`),
  ADD CONSTRAINT `FK_ETUDIANT_FILIERE_ID` FOREIGN KEY (`FILIERE_ID`) REFERENCES `filiere` (`ID`),
  ADD CONSTRAINT `FK_ETUDIANT_NIVEAU_ID` FOREIGN KEY (`NIVEAU_ID`) REFERENCES `niveau` (`ID`),
  ADD CONSTRAINT `FK_ETUDIANT_SEMESTREACTUEL_ID` FOREIGN KEY (`SEMESTREACTUEL_ID`) REFERENCES `semestre` (`ID`);

--
-- Contraintes pour la table `etudiantmaster`
--
ALTER TABLE `etudiantmaster`
  ADD CONSTRAINT `FK_ETUDIANTMASTER_ACADEMIE_ID` FOREIGN KEY (`ACADEMIE_ID`) REFERENCES `academie` (`ID`),
  ADD CONSTRAINT `FK_ETUDIANTMASTER_FILIEREMASTER_ID` FOREIGN KEY (`FILIEREMASTER_ID`) REFERENCES `filieremaster` (`ID`),
  ADD CONSTRAINT `FK_ETUDIANTMASTER_LYCEE_ID` FOREIGN KEY (`LYCEE_ID`) REFERENCES `lycee` (`ID`),
  ADD CONSTRAINT `FK_ETUDIANTMASTER_MENTIONBAC_ID` FOREIGN KEY (`MENTIONBAC_ID`) REFERENCES `mentiondiplome` (`ID`),
  ADD CONSTRAINT `FK_ETUDIANTMASTER_OPTIONBAC_ID` FOREIGN KEY (`OPTIONBAC_ID`) REFERENCES `optionbac` (`ID`),
  ADD CONSTRAINT `FK_ETUDIANTMASTER_PAYS_ID` FOREIGN KEY (`PAYS_ID`) REFERENCES `pays` (`ID`),
  ADD CONSTRAINT `FK_ETUDIANTMASTER_PROFESSIONMERE_ID` FOREIGN KEY (`PROFESSIONMERE_ID`) REFERENCES `profession` (`ID`),
  ADD CONSTRAINT `FK_ETUDIANTMASTER_PROFESSIONPERE_ID` FOREIGN KEY (`PROFESSIONPERE_ID`) REFERENCES `profession` (`ID`),
  ADD CONSTRAINT `FK_ETUDIANTMASTER_PROVINCE_ID` FOREIGN KEY (`PROVINCE_ID`) REFERENCES `lieu` (`ID`),
  ADD CONSTRAINT `FK_ETUDIANTMASTER_TYPEDERNIERDIPLOME_ID` FOREIGN KEY (`TYPEDERNIERDIPLOME_ID`) REFERENCES `dernierdiplome` (`ID`);

--
-- Contraintes pour la table `examcandidat`
--
ALTER TABLE `examcandidat`
  ADD CONSTRAINT `FK_EXAMCANDIDAT_CONDIDATURE_ID` FOREIGN KEY (`CONDIDATURE_ID`) REFERENCES `condidature` (`ID`),
  ADD CONSTRAINT `FK_EXAMCANDIDAT_MATIERECONCOUR_ID` FOREIGN KEY (`MATIERECONCOUR_ID`) REFERENCES `matiereconcour` (`ID`);

--
-- Contraintes pour la table `expressionbesoin`
--
ALTER TABLE `expressionbesoin`
  ADD CONSTRAINT `FK_EXPRESSIONBESOIN_USERSTOCK_ID` FOREIGN KEY (`USERSTOCK_ID`) REFERENCES `userstock` (`ID`),
  ADD CONSTRAINT `FK_EXPRESSIONBESOIN_USER_ID` FOREIGN KEY (`USER_ID`) REFERENCES `user` (`ID`);

--
-- Contraintes pour la table `facture`
--
ALTER TABLE `facture`
  ADD CONSTRAINT `FK_FACTURE_COMMANDE_ID` FOREIGN KEY (`COMMANDE_ID`) REFERENCES `commande` (`ID`),
  ADD CONSTRAINT `FK_FACTURE_FOURNISSEUR_ID` FOREIGN KEY (`FOURNISSEUR_ID`) REFERENCES `fournisseur` (`ID`);

--
-- Contraintes pour la table `filiere`
--
ALTER TABLE `filiere`
  ADD CONSTRAINT `FK_FILIERE_DEPARTEMENT_ID` FOREIGN KEY (`DEPARTEMENT_ID`) REFERENCES `departement` (`ID`),
  ADD CONSTRAINT `FK_FILIERE_RESPONSABLEFILIERE_CIN` FOREIGN KEY (`RESPONSABLEFILIERE_CIN`) REFERENCES `enseignant` (`CIN`),
  ADD CONSTRAINT `FK_FILIERE_SECTION_ID` FOREIGN KEY (`SECTION_ID`) REFERENCES `section` (`ID`);

--
-- Contraintes pour la table `laboratoire`
--
ALTER TABLE `laboratoire`
  ADD CONSTRAINT `FK_LABORATOIRE_DIRECTEUR_CIN` FOREIGN KEY (`DIRECTEUR_CIN`) REFERENCES `enseignant` (`CIN`);

--
-- Contraintes pour la table `licence`
--
ALTER TABLE `licence`
  ADD CONSTRAINT `FK_LICENCE_FILIERE_ID` FOREIGN KEY (`FILIERE_ID`) REFERENCES `filiere` (`ID`);

--
-- Contraintes pour la table `ligne`
--
ALTER TABLE `ligne`
  ADD CONSTRAINT `FK_LIGNE_PRODUIT_ID` FOREIGN KEY (`PRODUIT_ID`) REFERENCES `produit` (`ID`);

--
-- Contraintes pour la table `lignecommande`
--
ALTER TABLE `lignecommande`
  ADD CONSTRAINT `FK_LigneCommande_COMMANDE_ID` FOREIGN KEY (`COMMANDE_ID`) REFERENCES `commande` (`ID`),
  ADD CONSTRAINT `FK_LigneCommande_PRODUIT_ID` FOREIGN KEY (`PRODUIT_ID`) REFERENCES `produit` (`ID`);

--
-- Contraintes pour la table `ligneexpressionbesoin`
--
ALTER TABLE `ligneexpressionbesoin`
  ADD CONSTRAINT `FK_LigneExpressionBesoin_EXPRESSIONBESOIN_ID` FOREIGN KEY (`EXPRESSIONBESOIN_ID`) REFERENCES `expressionbesoin` (`ID`),
  ADD CONSTRAINT `FK_LigneExpressionBesoin_PRODUIT_ID` FOREIGN KEY (`PRODUIT_ID`) REFERENCES `produit` (`ID`);

--
-- Contraintes pour la table `lignefacture`
--
ALTER TABLE `lignefacture`
  ADD CONSTRAINT `FK_LigneFacture_FACTURE_ID` FOREIGN KEY (`FACTURE_ID`) REFERENCES `facture` (`ID`),
  ADD CONSTRAINT `FK_LigneFacture_PRODUIT_ID` FOREIGN KEY (`PRODUIT_ID`) REFERENCES `produit` (`ID`);

--
-- Contraintes pour la table `lignelivraison`
--
ALTER TABLE `lignelivraison`
  ADD CONSTRAINT `FK_LigneLivraison_LIVRAISON_ID` FOREIGN KEY (`LIVRAISON_ID`) REFERENCES `livraison` (`ID`),
  ADD CONSTRAINT `FK_LigneLivraison_PRODUIT_ID` FOREIGN KEY (`PRODUIT_ID`) REFERENCES `produit` (`ID`);

--
-- Contraintes pour la table `lignemagasin`
--
ALTER TABLE `lignemagasin`
  ADD CONSTRAINT `FK_LigneMagasin_MAGASIN_ID` FOREIGN KEY (`MAGASIN_ID`) REFERENCES `magasin` (`ID`),
  ADD CONSTRAINT `FK_LigneMagasin_PRODUIT_ID` FOREIGN KEY (`PRODUIT_ID`) REFERENCES `produit` (`ID`);

--
-- Contraintes pour la table `lignereception`
--
ALTER TABLE `lignereception`
  ADD CONSTRAINT `FK_LigneReception_PRODUIT_ID` FOREIGN KEY (`PRODUIT_ID`) REFERENCES `produit` (`ID`),
  ADD CONSTRAINT `FK_LigneReception_RECEPTION_ID` FOREIGN KEY (`RECEPTION_ID`) REFERENCES `reception` (`ID`);

--
-- Contraintes pour la table `livraison`
--
ALTER TABLE `livraison`
  ADD CONSTRAINT `FK_LIVRAISON_COMMANDE_ID` FOREIGN KEY (`COMMANDE_ID`) REFERENCES `commande` (`ID`),
  ADD CONSTRAINT `FK_LIVRAISON_USERSTOCK_ID` FOREIGN KEY (`USERSTOCK_ID`) REFERENCES `userstock` (`ID`),
  ADD CONSTRAINT `FK_LIVRAISON_USER_ID` FOREIGN KEY (`USER_ID`) REFERENCES `user` (`ID`);

--
-- Contraintes pour la table `magasin`
--
ALTER TABLE `magasin`
  ADD CONSTRAINT `FK_MAGASIN_STOCK_ID` FOREIGN KEY (`STOCK_ID`) REFERENCES `stock` (`ID`);

--
-- Contraintes pour la table `matierebac`
--
ALTER TABLE `matierebac`
  ADD CONSTRAINT `FK_MATIEREBAC_OPTIONBAC_ID` FOREIGN KEY (`OPTIONBAC_ID`) REFERENCES `optionbac` (`ID`);

--
-- Contraintes pour la table `module`
--
ALTER TABLE `module`
  ADD CONSTRAINT `FK_MODULE_ENSEIGNANT_CIN` FOREIGN KEY (`ENSEIGNANT_CIN`) REFERENCES `enseignant` (`CIN`),
  ADD CONSTRAINT `FK_MODULE_FILIERE_ID` FOREIGN KEY (`FILIERE_ID`) REFERENCES `filiere` (`ID`),
  ADD CONSTRAINT `FK_MODULE_SEMESTRE_ID` FOREIGN KEY (`SEMESTRE_ID`) REFERENCES `semestre` (`ID`);

--
-- Contraintes pour la table `niveau`
--
ALTER TABLE `niveau`
  ADD CONSTRAINT `FK_NIVEAU_FILIERE_ID` FOREIGN KEY (`FILIERE_ID`) REFERENCES `filiere` (`ID`),
  ADD CONSTRAINT `FK_NIVEAU_SEMESTRE_ID` FOREIGN KEY (`SEMESTRE_ID`) REFERENCES `semestre` (`ID`);

--
-- Contraintes pour la table `noteannuelle`
--
ALTER TABLE `noteannuelle`
  ADD CONSTRAINT `FK_NOTEANNUELLE_ANNEE_ID` FOREIGN KEY (`ANNEE_ID`) REFERENCES `annee` (`ID`),
  ADD CONSTRAINT `FK_NOTEANNUELLE_ETUDIANT_CNE` FOREIGN KEY (`ETUDIANT_CNE`) REFERENCES `etudiant` (`CNE`);

--
-- Contraintes pour la table `notemodulaire`
--
ALTER TABLE `notemodulaire`
  ADD CONSTRAINT `FK_NOTEMODULAIRE_ETUDIANT_CNE` FOREIGN KEY (`ETUDIANT_CNE`) REFERENCES `etudiant` (`CNE`),
  ADD CONSTRAINT `FK_NOTEMODULAIRE_MODULE_ID` FOREIGN KEY (`MODULE_ID`) REFERENCES `module` (`ID`);

--
-- Contraintes pour la table `notesemestre`
--
ALTER TABLE `notesemestre`
  ADD CONSTRAINT `FK_NOTESEMESTRE_ETUDIANT_CNE` FOREIGN KEY (`ETUDIANT_CNE`) REFERENCES `etudiant` (`CNE`),
  ADD CONSTRAINT `FK_NOTESEMESTRE_SEMESTRE_ID` FOREIGN KEY (`SEMESTRE_ID`) REFERENCES `semestre` (`ID`);

--
-- Contraintes pour la table `ouvrage`
--
ALTER TABLE `ouvrage`
  ADD CONSTRAINT `FK_OUVRAGE_RUBRIQUE_ID` FOREIGN KEY (`RUBRIQUE_ID`) REFERENCES `rubrique` (`ID`);

--
-- Contraintes pour la table `pieceetudiant`
--
ALTER TABLE `pieceetudiant`
  ADD CONSTRAINT `FK_PIECEETUDIANT_CONDIDATURE_ID` FOREIGN KEY (`CONDIDATURE_ID`) REFERENCES `condidature` (`ID`),
  ADD CONSTRAINT `FK_PIECEETUDIANT_PIECESPARNIVEAU_ID` FOREIGN KEY (`PIECESPARNIVEAU_ID`) REFERENCES `piecesparniveau` (`ID`);

--
-- Contraintes pour la table `piecesparniveau`
--
ALTER TABLE `piecesparniveau`
  ADD CONSTRAINT `FK_PIECESPARNIVEAU_NIVEAU_ID` FOREIGN KEY (`NIVEAU_ID`) REFERENCES `niveau` (`ID`),
  ADD CONSTRAINT `FK_PIECESPARNIVEAU_PIECESJOINTE_ID` FOREIGN KEY (`PIECESJOINTE_ID`) REFERENCES `piece` (`ID`);

--
-- Contraintes pour la table `produit`
--
ALTER TABLE `produit`
  ADD CONSTRAINT `FK_PRODUIT_CATEGORIE_ID` FOREIGN KEY (`CATEGORIE_ID`) REFERENCES `categorie` (`ID`),
  ADD CONSTRAINT `FK_PRODUIT_MARQUE_ID` FOREIGN KEY (`MARQUE_ID`) REFERENCES `marque` (`ID`);

--
-- Contraintes pour la table `pv`
--
ALTER TABLE `pv`
  ADD CONSTRAINT `FK_PV_SEMESTRE_ID` FOREIGN KEY (`SEMESTRE_ID`) REFERENCES `semestre` (`ID`);

--
-- Contraintes pour la table `pvnotemodulaireitem`
--
ALTER TABLE `pvnotemodulaireitem`
  ADD CONSTRAINT `FK_PVNOTEMODULAIREITEM_NOTEMODULAIRE_ID` FOREIGN KEY (`NOTEMODULAIRE_ID`) REFERENCES `notemodulaire` (`ID`),
  ADD CONSTRAINT `FK_PVNOTEMODULAIREITEM_PVNOTESEMESTREITEM_ID` FOREIGN KEY (`PVNOTESEMESTREITEM_ID`) REFERENCES `pvnotesemestreitem` (`ID`);

--
-- Contraintes pour la table `pvnotesemestreitem`
--
ALTER TABLE `pvnotesemestreitem`
  ADD CONSTRAINT `FK_PVNOTESEMESTREITEM_NOTESEMESTRE_ID` FOREIGN KEY (`NOTESEMESTRE_ID`) REFERENCES `notesemestre` (`ID`),
  ADD CONSTRAINT `FK_PVNOTESEMESTREITEM_PV_ID` FOREIGN KEY (`PV_ID`) REFERENCES `pv` (`ID`);

--
-- Contraintes pour la table `reception`
--
ALTER TABLE `reception`
  ADD CONSTRAINT `FK_RECEPTION_EXPRESSIONBESOIN_ID` FOREIGN KEY (`EXPRESSIONBESOIN_ID`) REFERENCES `expressionbesoin` (`ID`),
  ADD CONSTRAINT `FK_RECEPTION_USERSTOCK_ID` FOREIGN KEY (`USERSTOCK_ID`) REFERENCES `userstock` (`ID`),
  ADD CONSTRAINT `FK_RECEPTION_USER_ID` FOREIGN KEY (`USER_ID`) REFERENCES `user` (`ID`);

--
-- Contraintes pour la table `region`
--
ALTER TABLE `region`
  ADD CONSTRAINT `FK_REGION_PAYS_ID` FOREIGN KEY (`PAYS_ID`) REFERENCES `pays` (`ID`);

--
-- Contraintes pour la table `secure`
--
ALTER TABLE `secure`
  ADD CONSTRAINT `FK_SECURE_ENSEIGNANT_CIN` FOREIGN KEY (`ENSEIGNANT_CIN`) REFERENCES `enseignant` (`CIN`),
  ADD CONSTRAINT `FK_SECURE_ETUDIANT_CNE` FOREIGN KEY (`ETUDIANT_CNE`) REFERENCES `etudiant` (`CNE`);

--
-- Contraintes pour la table `semestre`
--
ALTER TABLE `semestre`
  ADD CONSTRAINT `FK_SEMESTRE_ANNEE_ID` FOREIGN KEY (`ANNEE_ID`) REFERENCES `annee` (`ID`),
  ADD CONSTRAINT `FK_SEMESTRE_CANDIDAT_CNE` FOREIGN KEY (`CANDIDAT_CNE`) REFERENCES `candidat` (`CNE`),
  ADD CONSTRAINT `FK_SEMESTRE_FILIERE_ID` FOREIGN KEY (`FILIERE_ID`) REFERENCES `filiere` (`ID`);

--
-- Contraintes pour la table `themerecherche`
--
ALTER TABLE `themerecherche`
  ADD CONSTRAINT `FK_THEMERECHERCHE_EQUIPERECHERCHE_ID` FOREIGN KEY (`EQUIPERECHERCHE_ID`) REFERENCES `equiperecherche` (`ID`);

--
-- Contraintes pour la table `user`
--
ALTER TABLE `user`
  ADD CONSTRAINT `FK_USER_ENTITEADMINISTRATIVE_ID` FOREIGN KEY (`ENTITEADMINISTRATIVE_ID`) REFERENCES `entiteadministrative` (`ID`);

--
-- Contraintes pour la table `userstock`
--
ALTER TABLE `userstock`
  ADD CONSTRAINT `FK_USERSTOCK_ENTITEADMINISTRATIVE_ID` FOREIGN KEY (`ENTITEADMINISTRATIVE_ID`) REFERENCES `entiteadministrative` (`ID`),
  ADD CONSTRAINT `FK_USERSTOCK_ROLE_ID` FOREIGN KEY (`ROLE_ID`) REFERENCES `role` (`ID`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
