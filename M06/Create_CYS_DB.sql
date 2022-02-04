CREATE DATABASE `prueba` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `prueba`;
CREATE TABLE `adventure` (
  `idAdventure` int NOT NULL,
  `Name` varchar(45) NOT NULL,
  `Description` varchar(2000) NOT NULL,
  PRIMARY KEY (`idAdventure`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

CREATE TABLE `characters` (
  `idCharacter` int NOT NULL,
  `CharacterName` varchar(45) NOT NULL,
  PRIMARY KEY (`idCharacter`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

CREATE TABLE `adventure_has_characters` (
  `adventure_idAdventure` int NOT NULL,
  `characters_idCharacter` int NOT NULL,
  PRIMARY KEY (`adventure_idAdventure`,`characters_idCharacter`),
  KEY `fk_adventure_has_characters_characters1_idx` (`characters_idCharacter`),
  KEY `fk_adventure_has_characters_adventure1_idx` (`adventure_idAdventure`),
  CONSTRAINT `fk_adventure_has_characters_adventure1` FOREIGN KEY (`adventure_idAdventure`) REFERENCES `adventure` (`idAdventure`),
  CONSTRAINT `fk_adventure_has_characters_characters1` FOREIGN KEY (`characters_idCharacter`) REFERENCES `characters` (`idCharacter`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

CREATE TABLE `bystep_adventure` (
  `idByStep_Adventure` int NOT NULL,
  `idAdventure` int NOT NULL,
  `Description` varchar(4000) NOT NULL,
  `Final_Step` tinyint NOT NULL,
  PRIMARY KEY (`idByStep_Adventure`),
  KEY `fk_ByStep_Adventure_1_idx` (`idAdventure`),
  CONSTRAINT `fk_ByStep_Adventure_1` FOREIGN KEY (`idAdventure`) REFERENCES `adventure` (`idAdventure`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

CREATE TABLE `answers_bystep_adventure` (
  `idAnswers_ByStep_Adventure` int NOT NULL,
  `idByStep_Adventure` int NOT NULL,
  `Description` varchar(2000) NOT NULL,
  `Resolution_Anwer` varchar(400) NOT NULL,
  `NextStep_Adventure` int NOT NULL,
  PRIMARY KEY (`idAnswers_ByStep_Adventure`),
  KEY `fk_Answers_ByStep_Adventure_1_idx` (`idByStep_Adventure`),
  KEY `fk_Answers_ByStep_Adventure_2_idx` (`NextStep_Adventure`),
  CONSTRAINT `fk_Answers_ByStep_Adventure_1` FOREIGN KEY (`idByStep_Adventure`) REFERENCES `bystep_adventure` (`idByStep_Adventure`),
  CONSTRAINT `fk_Answers_ByStep_Adventure_2` FOREIGN KEY (`NextStep_Adventure`) REFERENCES `bystep_adventure` (`idByStep_Adventure`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

CREATE TABLE `user` (
  `idUser` int NOT NULL,
  `Username` varchar(10) NOT NULL,
  `Password` varchar(45) NOT NULL,
  PRIMARY KEY (`idUser`),
  UNIQUE KEY `Username_UNIQUE` (`Username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

CREATE TABLE `game` (
  `idGame` int NOT NULL,
  `idUser` int NOT NULL,
  `idCharacter` int NOT NULL,
  `idAdventure` int NOT NULL,
  `Date` datetime NOT NULL,
  PRIMARY KEY (`idGame`),
  KEY `fk_Game_1_idx` (`idUser`),
  KEY `fk_Game_2_idx` (`idCharacter`),
  KEY `fk_Game_3_idx` (`idAdventure`),
  KEY `fk_Game_2` (`idAdventure`,`idCharacter`),
  CONSTRAINT `fk_Game_1` FOREIGN KEY (`idUser`) REFERENCES `user` (`idUser`),
  CONSTRAINT `fk_Game_2` FOREIGN KEY (`idAdventure`) REFERENCES `adventure` (`idAdventure`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- ChoseYourStory.choices definition

CREATE TABLE `choices` (
  `idGame` int NOT NULL,
  `idByStep_Adventure` int NOT NULL,
  `idAnswers_ByStep_Adventure` int NOT NULL,
  KEY `fk_Choices_2_idx` (`idByStep_Adventure`),
  KEY `fk_Choices_1_idx` (`idGame`),
  KEY `fk_Choices_3_idx` (`idAnswers_ByStep_Adventure`),
  CONSTRAINT `fk_Choices_1` FOREIGN KEY (`idGame`) REFERENCES `game` (`idGame`),
  CONSTRAINT `fk_Choices_2` FOREIGN KEY (`idByStep_Adventure`) REFERENCES `bystep_adventure` (`idByStep_Adventure`),
  CONSTRAINT `fk_Choices_3` FOREIGN KEY (`idAnswers_ByStep_Adventure`) REFERENCES `answers_bystep_adventure` (`idAnswers_ByStep_Adventure`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;