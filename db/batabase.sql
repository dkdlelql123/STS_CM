DROP DATABASE IF EXISTS `stscm`;
CREATE DATABASE `stscm`;
USE stscm;


CREATE TABLE `article`(
	id INT(10) UNSIGNED PRIMARY KEY NOT NULL AUTO_INCREMENT,
	regData DATETIME NOT NULL,
	updateData DATETIME NOT NULL,
	title CHAR(100) NOT NULL,
	`body` TEXT NOT NULL
);

INSERT INTO `article`
SET regData = NOW(),
updateData = NOW(),
title = "제목1",
`body` = "아아아";

INSERT INTO `article`
SET regData = NOW(),
updateData = NOW(),
title = "제목2",
`body` = "아아아";

INSERT INTO `article`
SET regData = NOW(),
updateData = NOW(),
title = "제목3",
`body` = "아아아";
