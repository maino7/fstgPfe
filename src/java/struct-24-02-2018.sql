-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Client :  127.0.0.1
-- Généré le :  Sam 24 Février 2018 à 07:59
-- Version du serveur :  10.1.8-MariaDB
-- Version de PHP :  5.6.14

SET FOREIGN_KEY_CHECKS=0;
SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
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
  `id` int(11) NOT NULL,
  `titre` varchar(255) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `actualite`
--

CREATE TABLE `actualite` (
  `ID` bigint(20) NOT NULL,
  `CONTENUE` longtext,
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
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `annee`
--

CREATE TABLE `annee` (
  `ID` bigint(20) NOT NULL,
  `LIBELLE` int(11) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `anneeinscription`
--

CREATE TABLE `anneeinscription` (
  `ID` bigint(20) NOT NULL,
  `ANNEE` int(11) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `anneuniversitaire`
--

CREATE TABLE `anneuniversitaire` (
  `ID` bigint(20) NOT NULL,
  `ANNEEMAX_ID` bigint(20) DEFAULT NULL,
  `ANNEEMIN_ID` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

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
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `demande`
--

CREATE TABLE `demande` (
  `ID` bigint(20) NOT NULL,
  `DESCRIPTION` varchar(255) DEFAULT NULL,
  `ETAT` int(11) DEFAULT NULL,
  `ETUDIANT_CNE` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `demandeattestaion`
--

CREATE TABLE `demandeattestaion` (
  `ID` bigint(20) NOT NULL,
  `ATTESTATIONDEUST` int(11) DEFAULT NULL,
  `ATTESTATIONLICENCE` int(11) DEFAULT NULL,
  `ETUDIANT_CNE` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

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
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `demandeitem`
--

CREATE TABLE `demandeitem` (
  `ID` bigint(20) NOT NULL,
  `DEMANDE_ID` bigint(20) DEFAULT NULL,
  `MODULE_ID` bigint(20) DEFAULT NULL,
  `SEMESTRE_ID` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `demandelicence`
--

CREATE TABLE `demandelicence` (
  `ID` bigint(20) NOT NULL,
  `ETUDIANT_CNE` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `demandelicenceitem`
--

CREATE TABLE `demandelicenceitem` (
  `ID` bigint(20) NOT NULL,
  `PRIORITE` int(11) DEFAULT NULL,
  `DEMANDELICENCE_ID` bigint(20) DEFAULT NULL,
  `LICENCE_ID` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `demanderelevenote`
--

CREATE TABLE `demanderelevenote` (
  `ID` bigint(20) NOT NULL,
  `ETAT` int(11) DEFAULT NULL,
  `ETUDIANT_CNE` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `demanderelevenoteitem`
--

CREATE TABLE `demanderelevenoteitem` (
  `ID` bigint(20) NOT NULL,
  `DEMANDERELEVENOTE_ID` bigint(20) DEFAULT NULL,
  `SEMESTRE_ID` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

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
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `dernierdiplome`
--

CREATE TABLE `dernierdiplome` (
  `id` int(11) NOT NULL,
  `numapg` varchar(255) NOT NULL,
  `titre` varchar(255) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

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
  `USER_CIN` varchar(255) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

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
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `equiperecherche`
--

CREATE TABLE `equiperecherche` (
  `ID` bigint(20) NOT NULL,
  `NOM` varchar(255) DEFAULT NULL,
  `CHEFEQUIPE_CIN` varchar(255) DEFAULT NULL,
  `LABORATOIRE_ID` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `etablissementtype`
--

CREATE TABLE `etablissementtype` (
  `id` int(11) NOT NULL,
  `abrapg` varchar(255) CHARACTER SET utf8 NOT NULL,
  `titre` varchar(255) CHARACTER SET utf8 NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

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
  `SEMESTREACTUEL_ID` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `etudiantmaster`
--

CREATE TABLE `etudiantmaster` (
  `id` int(11) NOT NULL,
  `nomlat` varchar(255) NOT NULL,
  `prenomlat` varchar(255) NOT NULL,
  `sexe` tinyint(4) NOT NULL,
  `handicap` tinyint(4) NOT NULL,
  `CNE` varchar(255) DEFAULT NULL,
  `CIN` varchar(255) DEFAULT NULL,
  `email` varchar(255) NOT NULL,
  `telephone` varchar(255) NOT NULL,
  `professionpere_id` int(11) NOT NULL,
  `professionmere_id` int(11) NOT NULL,
  `TypeDernierDiplome_id` int(11) NOT NULL,
  `etablissementPreInsc` int(11) NOT NULL,
  `anneeInscriptionEnsSup` varchar(9) NOT NULL,
  `anneeInscriptionUniv` varchar(9) NOT NULL,
  `anneeInscriptionEtab` varchar(9) NOT NULL,
  `nomar` varchar(255) DEFAULT NULL,
  `prenomar` varchar(255) DEFAULT NULL,
  `datenaissance` date DEFAULT NULL,
  `lieunaissance` varchar(255) NOT NULL,
  `pays_id` int(11) NOT NULL,
  `mentionbac_id` int(11) NOT NULL,
  `optionbac_id` int(11) NOT NULL,
  `anneebac` varchar(4) NOT NULL,
  `lycee_id` int(11) DEFAULT NULL,
  `province_id` int(11) NOT NULL,
  `academie_id` int(11) DEFAULT NULL,
  `typeInscription` tinyint(4) NOT NULL,
  `photo` varchar(255) DEFAULT NULL,
  `dateInscription` date NOT NULL,
  `ip` varchar(255) NOT NULL,
  `secret` int(11) DEFAULT NULL,
  `adresse` varchar(255) NOT NULL,
  `filiereMaster_id` int(11) NOT NULL,
  `etat` tinyint(4) NOT NULL,
  `fonctionnaire` tinyint(4) NOT NULL,
  `lieuar` varchar(255) DEFAULT NULL,
  `lastpdf` varchar(255) NOT NULL,
  `export` tinyint(4) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `etudiant_secure`
--

CREATE TABLE `etudiant_secure` (
  `Etudiant_CNE` bigint(20) NOT NULL,
  `secures_ID` bigint(20) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

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
  `RESPONSABLEFILIERE_CIN` varchar(255) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `filieremaster`
--

CREATE TABLE `filieremaster` (
  `id` int(11) NOT NULL,
  `abrapg` varchar(255) NOT NULL,
  `titre` varchar(255) NOT NULL,
  `type` tinyint(4) NOT NULL,
  `Diplome` varchar(11) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `laboratoire`
--

CREATE TABLE `laboratoire` (
  `ID` bigint(20) NOT NULL,
  `DESCRIPTION` varchar(255) DEFAULT NULL,
  `NOM` varchar(255) DEFAULT NULL,
  `DIRECTEUR_CIN` varchar(255) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `licence`
--

CREATE TABLE `licence` (
  `ID` bigint(20) NOT NULL,
  `NOM` varchar(255) DEFAULT NULL,
  `FILIERE_ID` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `lieu`
--

CREATE TABLE `lieu` (
  `id` int(11) NOT NULL,
  `numapg` int(11) NOT NULL,
  `titre` varchar(255) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `lycee`
--

CREATE TABLE `lycee` (
  `id` int(11) NOT NULL,
  `numapg` varchar(255) NOT NULL,
  `titre` varchar(255) NOT NULL,
  `type` int(11) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `mentionbac`
--

CREATE TABLE `mentionbac` (
  `id` int(11) NOT NULL,
  `numapg` varchar(255) NOT NULL,
  `titre` varchar(255) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

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
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

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
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

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
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `notemodulaire`
--

CREATE TABLE `notemodulaire` (
  `ID` bigint(20) NOT NULL,
  `ETAT` int(11) DEFAULT NULL,
  `MENTION` varchar(255) DEFAULT NULL,
  `NOTE` double DEFAULT NULL,
  `PTJURY` double DEFAULT NULL,
  `ETUDIANT_CNE` bigint(20) DEFAULT NULL,
  `MODULE_ID` bigint(20) DEFAULT NULL,
  `NOTEBEFOREJURY` double DEFAULT NULL,
  `mentionBeforeJury` varchar(255) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

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
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `optionbac`
--

CREATE TABLE `optionbac` (
  `id` int(11) NOT NULL,
  `numapg` int(11) NOT NULL,
  `titre` varchar(255) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Structure de la table `ouvrage`
--

CREATE TABLE `ouvrage` (
  `NUMERO` varchar(255) NOT NULL,
  `AUTEUR` varchar(255) DEFAULT NULL,
  `EDITION` varchar(255) DEFAULT NULL,
  `RUBRIQUE_ID` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `pays`
--

CREATE TABLE `pays` (
  `id` int(11) NOT NULL,
  `titre` varchar(255) NOT NULL,
  `numapg` int(11) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `piece`
--

CREATE TABLE `piece` (
  `id` int(11) NOT NULL,
  `titre` varchar(255) NOT NULL,
  `nationalite` int(11) NOT NULL,
  `fonctionnaire` tinyint(4) NOT NULL,
  `typeInscription` int(11) NOT NULL,
  `nombre` int(11) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `pieceetudiant`
--

CREATE TABLE `pieceetudiant` (
  `id` int(11) NOT NULL,
  `etudiantmaster_id` int(11) NOT NULL,
  `piece_id` int(11) NOT NULL,
  `nombre` int(11) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `profession`
--

CREATE TABLE `profession` (
  `id` int(11) NOT NULL,
  `numapg` int(11) NOT NULL,
  `titre` varchar(255) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `pv`
--

CREATE TABLE `pv` (
  `ID` bigint(20) NOT NULL,
  `SEMESTRE_ID` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `pvnotemodulaireitem`
--

CREATE TABLE `pvnotemodulaireitem` (
  `ID` bigint(20) NOT NULL,
  `PVNOTESEMESTREITEM_ID` bigint(20) DEFAULT NULL,
  `NOTEMODULAIRE_ID` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `pvnotesemestreitem`
--

CREATE TABLE `pvnotesemestreitem` (
  `ID` bigint(20) NOT NULL,
  `PV_ID` bigint(20) DEFAULT NULL,
  `NOTESEMESTRE_ID` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `rubrique`
--

CREATE TABLE `rubrique` (
  `ID` bigint(20) NOT NULL,
  `NOM` varchar(255) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

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
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `semestre`
--

CREATE TABLE `semestre` (
  `ID` bigint(20) NOT NULL,
  `LIBELLE` int(11) DEFAULT NULL,
  `ANNEE_ID` bigint(20) DEFAULT NULL,
  `FILIERE_ID` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `sequence`
--

CREATE TABLE `sequence` (
  `SEQ_NAME` varchar(50) NOT NULL,
  `SEQ_COUNT` decimal(38,0) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `themerecherche`
--

CREATE TABLE `themerecherche` (
  `ID` bigint(20) NOT NULL,
  `NOM` varchar(255) DEFAULT NULL,
  `EQUIPERECHERCHE_ID` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `usermaster`
--

CREATE TABLE `usermaster` (
  `id` int(11) NOT NULL,
  `login` varchar(255) NOT NULL,
  `pwd` varchar(40) NOT NULL,
  `type` int(11) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Index pour les tables exportées
--

--
-- Index pour la table `academie`
--
ALTER TABLE `academie`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `actualite`
--
ALTER TABLE `actualite`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `FK_ACTUALITE_AUTEUR_CIN` (`AUTEUR_CIN`);
ALTER TABLE `actualite` ADD FULLTEXT KEY `CONTENUE` (`CONTENUE`);

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
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `device`
--
ALTER TABLE `device`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `FK_DEVICE_USER_CIN` (`USER_CIN`),
  ADD KEY `FK_DEVICE_ETUDIANT_CNE` (`ETUDIANT_CNE`);

--
-- Index pour la table `enseignant`
--
ALTER TABLE `enseignant`
  ADD PRIMARY KEY (`CIN`),
  ADD KEY `FK_ENSEIGNANT_FILIERE_ID` (`FILIERE_ID`),
  ADD KEY `FK_ENSEIGNANT_DEPARTEMENT_ID` (`DEPARTEMENT_ID`);

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
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `etudiant`
--
ALTER TABLE `etudiant`
  ADD PRIMARY KEY (`CNE`),
  ADD KEY `FK_ETUDIANT_SEMESTREACTUEL_ID` (`SEMESTREACTUEL_ID`),
  ADD KEY `FK_ETUDIANT_EQUIPERECHERCHE_ID` (`EQUIPERECHERCHE_ID`),
  ADD KEY `FK_ETUDIANT_FILIERE_ID` (`FILIERE_ID`);

--
-- Index pour la table `etudiantmaster`
--
ALTER TABLE `etudiantmaster`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `etudiant_secure`
--
ALTER TABLE `etudiant_secure`
  ADD PRIMARY KEY (`Etudiant_CNE`,`secures_ID`),
  ADD KEY `FK_ETUDIANT_SECURE_secures_ID` (`secures_ID`);

--
-- Index pour la table `filiere`
--
ALTER TABLE `filiere`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `FK_FILIERE_RESPONSABLEFILIERE_CIN` (`RESPONSABLEFILIERE_CIN`),
  ADD KEY `FK_FILIERE_DEPARTEMENT_ID` (`DEPARTEMENT_ID`);

--
-- Index pour la table `filieremaster`
--
ALTER TABLE `filieremaster`
  ADD PRIMARY KEY (`id`);

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
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `lycee`
--
ALTER TABLE `lycee`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `mentionbac`
--
ALTER TABLE `mentionbac`
  ADD PRIMARY KEY (`id`);

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
  ADD PRIMARY KEY (`id`);

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
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `piece`
--
ALTER TABLE `piece`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `pieceetudiant`
--
ALTER TABLE `pieceetudiant`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `profession`
--
ALTER TABLE `profession`
  ADD PRIMARY KEY (`id`);

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
-- Index pour la table `rubrique`
--
ALTER TABLE `rubrique`
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
  ADD KEY `FK_SEMESTRE_FILIERE_ID` (`FILIERE_ID`);

--
-- Index pour la table `sequence`
--
ALTER TABLE `sequence`
  ADD PRIMARY KEY (`SEQ_NAME`);

--
-- Index pour la table `themerecherche`
--
ALTER TABLE `themerecherche`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `FK_THEMERECHERCHE_EQUIPERECHERCHE_ID` (`EQUIPERECHERCHE_ID`);

--
-- Index pour la table `usermaster`
--
ALTER TABLE `usermaster`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT pour les tables exportées
--

--
-- AUTO_INCREMENT pour la table `academie`
--
ALTER TABLE `academie`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20;
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
  MODIFY `ID` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT pour la table `demandeattestaion`
--
ALTER TABLE `demandeattestaion`
  MODIFY `ID` bigint(20) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT pour la table `demandederogation`
--
ALTER TABLE `demandederogation`
  MODIFY `ID` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;
--
-- AUTO_INCREMENT pour la table `demandeitem`
--
ALTER TABLE `demandeitem`
  MODIFY `ID` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;
--
-- AUTO_INCREMENT pour la table `demandelicence`
--
ALTER TABLE `demandelicence`
  MODIFY `ID` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;
--
-- AUTO_INCREMENT pour la table `demandelicenceitem`
--
ALTER TABLE `demandelicenceitem`
  MODIFY `ID` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=27;
--
-- AUTO_INCREMENT pour la table `demanderelevenote`
--
ALTER TABLE `demanderelevenote`
  MODIFY `ID` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT pour la table `demanderelevenoteitem`
--
ALTER TABLE `demanderelevenoteitem`
  MODIFY `ID` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT pour la table `dernierdiplome`
--
ALTER TABLE `dernierdiplome`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=27;
--
-- AUTO_INCREMENT pour la table `device`
--
ALTER TABLE `device`
  MODIFY `ID` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
--
-- AUTO_INCREMENT pour la table `equiperecherche`
--
ALTER TABLE `equiperecherche`
  MODIFY `ID` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;
--
-- AUTO_INCREMENT pour la table `etablissementtype`
--
ALTER TABLE `etablissementtype`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=34;
--
-- AUTO_INCREMENT pour la table `etudiantmaster`
--
ALTER TABLE `etudiantmaster`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=798;
--
-- AUTO_INCREMENT pour la table `filiere`
--
ALTER TABLE `filiere`
  MODIFY `ID` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;
--
-- AUTO_INCREMENT pour la table `filieremaster`
--
ALTER TABLE `filieremaster`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=24;
--
-- AUTO_INCREMENT pour la table `laboratoire`
--
ALTER TABLE `laboratoire`
  MODIFY `ID` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;
--
-- AUTO_INCREMENT pour la table `licence`
--
ALTER TABLE `licence`
  MODIFY `ID` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;
--
-- AUTO_INCREMENT pour la table `lieu`
--
ALTER TABLE `lieu`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=97;
--
-- AUTO_INCREMENT pour la table `lycee`
--
ALTER TABLE `lycee`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=53;
--
-- AUTO_INCREMENT pour la table `mentionbac`
--
ALTER TABLE `mentionbac`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
--
-- AUTO_INCREMENT pour la table `message`
--
ALTER TABLE `message`
  MODIFY `ID` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT pour la table `module`
--
ALTER TABLE `module`
  MODIFY `ID` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=29;
--
-- AUTO_INCREMENT pour la table `noteannuelle`
--
ALTER TABLE `noteannuelle`
  MODIFY `ID` bigint(20) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT pour la table `notemodulaire`
--
ALTER TABLE `notemodulaire`
  MODIFY `ID` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=68;
--
-- AUTO_INCREMENT pour la table `notesemestre`
--
ALTER TABLE `notesemestre`
  MODIFY `ID` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;
--
-- AUTO_INCREMENT pour la table `optionbac`
--
ALTER TABLE `optionbac`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=64;
--
-- AUTO_INCREMENT pour la table `pays`
--
ALTER TABLE `pays`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=222;
--
-- AUTO_INCREMENT pour la table `piece`
--
ALTER TABLE `piece`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=23;
--
-- AUTO_INCREMENT pour la table `pieceetudiant`
--
ALTER TABLE `pieceetudiant`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9975;
--
-- AUTO_INCREMENT pour la table `profession`
--
ALTER TABLE `profession`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=38;
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
  MODIFY `ID` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;
--
-- AUTO_INCREMENT pour la table `themerecherche`
--
ALTER TABLE `themerecherche`
  MODIFY `ID` bigint(20) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT pour la table `usermaster`
--
ALTER TABLE `usermaster`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;SET FOREIGN_KEY_CHECKS=1;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
