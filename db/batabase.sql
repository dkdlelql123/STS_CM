DROP DATABASE IF EXISTS `stscm`;
CREATE DATABASE `stscm`;
USE stscm;


CREATE TABLE `article`(
	id INT(10) UNSIGNED PRIMARY KEY NOT NULL AUTO_INCREMENT,
	regDate DATETIME NOT NULL,
	updateDate DATETIME NOT NULL,
	title CHAR(100) NOT NULL,
	`body` TEXT NOT NULL
);

INSERT INTO `article`
SET regDate = NOW(),
updateDate = NOW(),
title = "제목1",
`body` = "아아아";

INSERT INTO `article`
SET regDate = NOW(),
updateDate = NOW(),
title = "제목2",
`body` = "아아아";

INSERT INTO `article`
SET regDate = NOW(),
updateDate = NOW(),
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

#article에 멤버아이디 추가
ALTER TABLE `article` ADD COLUMN memberId INT(10) UNSIGNED NOT NULL AFTER updateDate;

#기존의 article에 memberId = 2 넣어주기
UPDATE `article` SET memberId = 2;

#게시판
CREATE TABLE board (
	id INT(10) UNSIGNED PRIMARY KEY NOT NULL AUTO_INCREMENT,
	regDate DATETIME NOT NULL,
	updateDate DATETIME NOT NULL,
	`code` CHAR(50) NOT NULL UNIQUE DEFAULT 0 COMMENT 'notice(공지사항),free1(자유게시판)...',
	`name` CHAR(50) NOT NULL UNIQUE COMMENT '게시판이름',
	delStatus TINYINT(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '0=삭제전, 1=삭제',
	delDate DATETIME COMMENT '삭제날짜'	
);

#게시판 생성 - 공지사항, 자유게시판
INSERT INTO board 
SET regDate = NOW(),
updateDate = NOW(),
`code` = 'notice',
`name` = '공지사항';

INSERT INTO board 
SET regDate = NOW(),
updateDate = NOW(),
`code` = 'free1',
`name` = '자유게시판';


#게시물 테이블에 게시판 칼럼 추가
ALTER TABLE `article` ADD COLUMN boardId INT(10) UNSIGNED NOT NULL AFTER memberid;

#게시물 1,2번 공지사항 게시판 게시물로 지정
UPDATE article SET boardId = 1 WHERE id IN(1,2);

#게시물 3번 공지사항 게시판 게시물로 지정
UPDATE article SET boardId = 2 WHERE id IN(3);
