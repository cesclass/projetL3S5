-- phpMyAdmin SQL Dump
-- version 4.9.2
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le :  mer. 08 jan. 2020 à 20:41
-- Version du serveur :  10.4.10-MariaDB
-- Version de PHP :  7.3.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `nts`
--

-- --------------------------------------------------------

--
-- Structure de la table `groups`
--

DROP TABLE IF EXISTS `groups`;
CREATE TABLE IF NOT EXISTS `groups` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(32) COLLATE utf8_unicode_ci NOT NULL,
  `info` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL,
  `is_tech` tinyint(1) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name_unique` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='Table des groupes';

--
-- Déchargement des données de la table `groups`
--

INSERT INTO `groups` (`id`, `name`, `info`, `is_tech`) VALUES
(1, 'DEV', 'Les hommes bo', 1),
(2, 'ADMIN', 'Les têtes pensantes', 1),
(3, 'Animal', '*Strange Noises*', 0),
(4, 'Human', 'On est normaux enfaite', 0),
(5, 'Things', 'WTF', 0),
(6, 'Singer', '*Titanic flute earrape*', 0),
(7, 'Divinity', '*Clin d\'oeil*', 0),
(8, 'Dark Boys', 'Too edgy for you ', 0);

-- --------------------------------------------------------

--
-- Structure de la table `members`
--

DROP TABLE IF EXISTS `members`;
CREATE TABLE IF NOT EXISTS `members` (
  `user_id` int(11) NOT NULL,
  `group_id` int(11) NOT NULL,
  PRIMARY KEY (`user_id`,`group_id`),
  KEY `members_fk_group_id` (`group_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='Table d''association utilisateur-groupe';

--
-- Déchargement des données de la table `members`
--

INSERT INTO `members` (`user_id`, `group_id`) VALUES
(2, 1),
(2, 2),
(2, 4),
(3, 1),
(3, 2),
(3, 4),
(4, 1),
(4, 2),
(4, 3),
(4, 4),
(4, 5),
(5, 5),
(7, 4),
(8, 3),
(9, 3),
(10, 5),
(11, 5),
(12, 2),
(12, 4),
(12, 7),
(13, 3),
(14, 4),
(14, 6),
(15, 4),
(15, 7),
(16, 4),
(16, 6),
(16, 7),
(16, 8),
(17, 5),
(18, 4),
(18, 8);

-- --------------------------------------------------------

--
-- Structure de la table `messages`
--

DROP TABLE IF EXISTS `messages`;
CREATE TABLE IF NOT EXISTS `messages` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `date` datetime NOT NULL,
  `content` text COLLATE utf8_unicode_ci NOT NULL,
  `status` enum('WAITING','RECEIVED','READ') COLLATE utf8_unicode_ci NOT NULL DEFAULT 'WAITING',
  `author_id` int(11) DEFAULT NULL,
  `ticket_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `messages_fk_author_id` (`author_id`),
  KEY `messages_fk_ticket_id` (`ticket_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='Table des messages';

--
-- Déchargement des données de la table `messages`
--

INSERT INTO `messages` (`id`, `date`, `content`, `status`, `author_id`, `ticket_id`) VALUES
(1, '2020-01-08 00:00:00', 'Bonjour,\r\nJe souhaites me tenir au courant suite à votre mise en place de cette application.\r\n\r\nEn effet, je souhaiterais être mis au courant de potentiel implémentation de \"KAPCHA\".\r\n\r\nCordialement', 'READ', 11, 2),
(2, '2020-01-08 00:00:00', 'Bonjour,\r\nJ\'ai faim!\r\nVeuillez mettre un terme à cela.\r\n\r\nCordialement', 'RECEIVED', 9, 1),
(3, '2020-01-08 00:00:00', 'Bonjour,\r\nJe vous informes que moi et mes amies chanteur souhaitons une amélioration du matériels mis à dispositions.\r\n\r\nCordialement', 'RECEIVED', 14, 3),
(4, '2020-01-08 00:01:00', 'Bonjour,\r\nSuite à des restrictions budgétaire, nous vous demandons de bien vouloir patienter.\r\n\r\nCordialement ', 'WAITING', 4, 3);

-- --------------------------------------------------------

--
-- Structure de la table `statuses`
--

DROP TABLE IF EXISTS `statuses`;
CREATE TABLE IF NOT EXISTS `statuses` (
  `message_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `status` enum('WAITING','RECEIVED','READ','AUTHOR') COLLATE utf8_unicode_ci NOT NULL DEFAULT 'WAITING',
  PRIMARY KEY (`message_id`,`user_id`),
  KEY `status_fk_user_id` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='Table d''association (message-utilisateur)-status';

--
-- Déchargement des données de la table `statuses`
--

INSERT INTO `statuses` (`message_id`, `user_id`, `status`) VALUES
(1, 2, 'READ'),
(1, 3, 'READ'),
(1, 4, 'READ'),
(1, 11, 'AUTHOR'),
(2, 2, 'READ'),
(2, 3, 'READ'),
(2, 4, 'READ'),
(2, 9, 'AUTHOR'),
(2, 12, 'RECEIVED'),
(3, 2, 'READ'),
(3, 3, 'READ'),
(3, 4, 'READ'),
(3, 12, 'RECEIVED'),
(3, 14, 'AUTHOR'),
(4, 2, 'READ'),
(4, 3, 'READ'),
(4, 4, 'AUTHOR'),
(4, 12, 'WAITING'),
(4, 14, 'READ');

-- --------------------------------------------------------

--
-- Structure de la table `tickets`
--

DROP TABLE IF EXISTS `tickets`;
CREATE TABLE IF NOT EXISTS `tickets` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) COLLATE utf8_unicode_ci NOT NULL,
  `date` datetime NOT NULL,
  `author_id` int(11) DEFAULT NULL,
  `group_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `title_unique` (`name`),
  KEY `tickets_fk_author_id` (`author_id`),
  KEY `tickets_fk_group_id` (`group_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='Table des tickets';

--
-- Déchargement des données de la table `tickets`
--

INSERT INTO `tickets` (`id`, `name`, `date`, `author_id`, `group_id`) VALUES
(1, 'Demande de croquettes', '2020-01-08 00:00:00', 9, 2),
(2, 'Informations sur l’évolution de l’application', '2020-01-08 00:00:00', 11, 1),
(3, 'Demande de matériels', '2020-01-08 00:00:00', 14, 2);

-- --------------------------------------------------------

--
-- Structure de la table `users`
--

DROP TABLE IF EXISTS `users`;
CREATE TABLE IF NOT EXISTS `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `login` varchar(8) COLLATE utf8_unicode_ci NOT NULL,
  `password` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `first_name` varchar(32) COLLATE utf8_unicode_ci NOT NULL,
  `last_name` varchar(32) COLLATE utf8_unicode_ci NOT NULL,
  `is_tech` tinyint(1) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `login_unique` (`login`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='Table des utilisateurs';

--
-- Déchargement des données de la table `users`
--

INSERT INTO `users` (`id`, `login`, `password`, `first_name`, `last_name`, `is_tech`) VALUES
(1, 'ADMIN', 'password', 'Admin', 'ROOT', 1),
(2, 'SCC1255A', 'foobar', 'Cyril', 'ESCLASSAN', 1),
(3, 'CRD1789A', 'foobar', 'Dylan', 'CARON', 1),
(4, 'ZZF2800A', 'foobar', 'Florian', 'AZIZEN', 1),
(5, 'MDR1337B', 'foobar', 'Machin', 'TRUC', 0),
(7, 'BOB', 'Coucourge!', 'Bobby', 'BOB', 0),
(8, 'DESPE', 'Mia', 'Desperado', 'CHAT', 0),
(9, 'SONIC', 'Graou', 'Sonic', 'CHAT', 0),
(10, 'SUSHI', 'MakiCalifornia', 'California', 'ROLLS', 0),
(11, 'HUMAN', 'ImArObOt', 'Real', 'HUMAN', 0),
(12, 'SMASHBRO', 'Painting', 'Bob', 'ROSS', 1),
(13, 'CHICK', 'OurChickenLife', 'Millie', 'SILLY', 0),
(14, 'RICKY', 'NeverGonnaGiveYouUp', 'Rick', 'ASTLEY', 0),
(15, 'CHRIST', '1UpEZ', 'Jesus', 'CHRIST', 0),
(16, 'KRAUSER', 'DetroitMetalCity', 'Soichi', 'NEGISHI', 0),
(17, 'WITCHER', 'YenneferJeTAime', 'Gerald', 'DE RIV', 0),
(18, 'BATMAN', 'JeSuisLaNuit', 'Bruce', 'WAYNE', 0);

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `members`
--
ALTER TABLE `members`
  ADD CONSTRAINT `members_fk_group_id` FOREIGN KEY (`group_id`) REFERENCES `groups` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `members_fk_user_id` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `messages`
--
ALTER TABLE `messages`
  ADD CONSTRAINT `messages_fk_author_id` FOREIGN KEY (`author_id`) REFERENCES `users` (`id`) ON DELETE SET NULL ON UPDATE CASCADE,
  ADD CONSTRAINT `messages_fk_ticket_id` FOREIGN KEY (`ticket_id`) REFERENCES `tickets` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `statuses`
--
ALTER TABLE `statuses`
  ADD CONSTRAINT `status_fk_message_id` FOREIGN KEY (`message_id`) REFERENCES `messages` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `status_fk_user_id` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `tickets`
--
ALTER TABLE `tickets`
  ADD CONSTRAINT `tickets_fk_author_id` FOREIGN KEY (`author_id`) REFERENCES `users` (`id`) ON DELETE SET NULL ON UPDATE CASCADE,
  ADD CONSTRAINT `tickets_fk_group_id` FOREIGN KEY (`group_id`) REFERENCES `groups` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
