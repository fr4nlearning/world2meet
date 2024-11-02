CREATE TABLE IF NOT EXISTS `starships` (
  `id` int NOT NULL AUTO_INCREMENT,
  `faction` enum('EMPIRE','REBEL') DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8mb4;