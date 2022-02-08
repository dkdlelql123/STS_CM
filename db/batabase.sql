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

#회원테이블
CREATE TABLE `member`(
	id INT(10) UNSIGNED PRIMARY KEY NOT NULL AUTO_INCREMENT,
	regDate DATETIME NOT NULL,
	updateDate DATETIME NOT NULL,
	loginId CHAR(20) NOT NULL,
	loginPw CHAR(60) NOT NULL,
	`authLevel` SMALLINT(2) UNSIGNED DEFAULT 3 COMMENT '권한레벨(3=일반,7=관리자)',
	`name` CHAR(20) NOT NULL,
	`nickname` CHAR(20) NOT NULL,
	phoneNumber CHAR(20) NOT NULL, 
	email CHAR(50) NOT NULL,
	delStatus TINYINT(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '0=탈퇴전, 1=탈퇴',
	delDate DATETIME COMMENT '탈퇴날짜'
);

#관리자 계정
INSERT INTO MEMBER
SET regDate = NOW(),
updateDate = NOW(),
loginId= 'admin',
loginPw= 'admin',
`authLevel` = 7,
`name`= '관리자',
`nickname` ='관리자',
phoneNumber = '010-1111-1111', 
email = 'admin@admin.com';


#일반 계정
INSERT INTO MEMBER
SET regDate = NOW(),
updateDate = NOW(),
loginId= 'user1',
loginPw= 'user1', 
`name`= 'user1',
`nickname` ='user1',
phoneNumber = '010-222-2222', 
email = 'user@user.com';

