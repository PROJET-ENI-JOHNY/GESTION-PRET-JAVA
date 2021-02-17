-- phpMyAdmin SQL Dump
-- version 4.1.14
-- http://www.phpmyadmin.net
--
-- Client :  127.0.0.1
-- Généré le :  Sam 06 Février 2021 à 23:04
-- Version du serveur :  5.6.17
-- Version de PHP :  5.5.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de données :  `gestion_pret`
--

-- --------------------------------------------------------

--
-- Structure de la table `banque`
--

CREATE TABLE IF NOT EXISTS `banque` (
  `n_banque` varchar(10) NOT NULL,
  `designation` varchar(60) NOT NULL,
  `Taux` int(11) NOT NULL,
  PRIMARY KEY (`n_banque`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `banque`
--

INSERT INTO `banque` (`n_banque`, `designation`, `Taux`) VALUES
('1', 'BFV', 10),
('13', 'BOA', 1),
('3', 'BNI', 5),
('4', 'jkh', 10);

-- --------------------------------------------------------

--
-- Structure de la table `client`
--

CREATE TABLE IF NOT EXISTS `client` (
  `n_compte` varchar(30) NOT NULL,
  `nom` varchar(60) NOT NULL,
  `adresse` varchar(60) NOT NULL,
  PRIMARY KEY (`n_compte`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `client`
--

INSERT INTO `client` (`n_compte`, `nom`, `adresse`) VALUES
('C01', 'johny lino', 'Isada'),
('C02', 'Hajatiana', 'Tanambao'),
('C03', 'ravaoaritina', 'igaga'),
('C04', 'julie', 'igaga');

-- --------------------------------------------------------

--
-- Structure de la table `pret`
--

CREATE TABLE IF NOT EXISTS `pret` (
  `id_pret` int(11) NOT NULL AUTO_INCREMENT,
  `n_banque` varchar(10) NOT NULL,
  `n_client` varchar(10) NOT NULL,
  `montant` int(11) NOT NULL,
  `date_pret` date NOT NULL,
  PRIMARY KEY (`id_pret`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=8 ;

--
-- Contenu de la table `pret`
--

INSERT INTO `pret` (`id_pret`, `n_banque`, `n_client`, `montant`, `date_pret`) VALUES
(1, '1', 'C01', 2000, '2021-02-14'),
(2, '12', 'C01', 2000, '2021-02-14'),
(3, '13', 'C01', 1230, '2021-02-14'),
(4, '1', 'C01', 2000, '2021-02-14'),
(5, '1', 'C02', 1120, '2021-02-14'),
(6, '12', 'C01', 120, '2021-02-06'),
(7, '12', 'C02', 1000, '2021-02-06');

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
