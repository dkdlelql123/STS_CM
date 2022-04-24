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
`name` = '자유';


#게시물 테이블에 게시판 칼럼 추가
ALTER TABLE `article` ADD COLUMN boardId INT(10) UNSIGNED NOT NULL AFTER memberid;

#게시물 1,2번 공지사항 게시판 게시물로 지정
UPDATE article SET boardId = 1 WHERE id IN(1,2);

#게시물 3번 공지사항 게시판 게시물로 지정
UPDATE article SET boardId = 2 WHERE id IN(3);

#페이징을 위한 게시물 추가
/*
insert into article (regDate,updateDate,memberId,boardId,title,`body`)
select NOW(), NOW(), floor(rand() * 2)+1,FLOOR(RAND() * 2)+1,concat('제목_',rand()),CONCAT('내용_',RAND())
from article;
*/

#게시물 조회수 컬럼 추가
ALTER TABLE article
ADD COLUMN hit INT(10) UNSIGNED NOT NULL DEFAULT 0;

#리액트포인터 테이블 추가
CREATE TABLE reactionPoint(
	id INT(10) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
	regDate DATETIME NOT NULL,
	updateDate DATETIME NOT NULL,
	memberId INT(10) UNSIGNED NOT NULL,
	relTypeCode CHAR(30) NOT NULL COMMENT '관련데이터타입코드',
	relId INT(10) UNSIGNED NOT NULL COMMENT '관련데이터번호',
	`point`	SMALLINT(2) NOT NULL
);

#리액트포인터 테스트 데이터
## 1번회원 1번글 좋아요
INSERT INTO reactionPoint
SET	regDate = NOW(),
updateDate = NOW(),
memberId = 1,
relTypeCode = 'article',
relId = 1,
`point` = 1;

## 1번회원 2번글 좋아요
INSERT INTO reactionPoint
SET	regDate = NOW(),
updateDate = NOW(),
memberId = 1,
relTypeCode = 'article',
relId = 2,
`point` = 1;

## 2번회원 1번글 좋아요
INSERT INTO reactionPoint
SET	regDate = NOW(),
updateDate = NOW(),
memberId = 2,
relTypeCode = 'article',
relId = 1,
`point` = 1;


# 반정규화 - 게시물 테이블에서 좋아요 컬럼 추가
ALTER TABLE `article`
ADD COLUMN goodReactionPoint INT(10) UNSIGNED NOT NULL DEFAULT 0;

# 반정규화 - 게시물 테이블에서 싫어요 컬럼 추가
ALTER TABLE `article`
ADD COLUMN badReactionPoint INT(10) UNSIGNED NOT NULL DEFAULT 0;


# 리액션포인트 테이블에서 게시물별 좋아요, 싫어요 합계 구하기
/*
select RP.relId,
sum(if(RP.point>0, RP.point, 0)) as goodReactionPoint,
sum(IF(RP.point<0, RP.point * -1, 0)) AS badReactionPoint
from `reactionPoint` as RP
where RP.relTypeCode = 'article'
group by RP.relId ;
*/

# 각 게시물 별 좋아요, 싫어요 필드의 값 넣기
UPDATE `article` AS A
INNER JOIN (
	SELECT RP.relId,
	SUM(IF(RP.point>0, RP.point, 0)) AS goodReactionPoint,
	SUM(IF(RP.point<0, RP.point * -1, 0)) AS badReactionPoint
	FROM `reactionPoint` AS RP
	WHERE RP.relTypeCode = 'article'
	GROUP BY RP.relId
) AS RP_SUM
ON A.id = RP_SUM.relId
SET A.goodReactionPoint = RP_SUM.goodReactionPoint,
A.badReactionPoint = RP_SUM.badReactionPoint;

# 댓글 테이블 추가
CREATE TABLE reply(
	id INT(10) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
	regDate DATETIME NOT NULL,
	updateDate DATETIME NOT NULL,
	memberId INT(10) UNSIGNED NOT NULL,
	relTypeCode CHAR(30) NOT NULL COMMENT '관련데이터타입코드',
	relId INT(10) UNSIGNED NOT NULL COMMENT '관련데이터번호',
	`body` TEXT NOT NULL
);

/*
INSERT INTO reply
SET regDate = NOW(),
updateDate = NOW(),
memberId = 1,
relTypeCode = 'article',
relId = 1,
`body` = '댓글1';
*/

# relpy 인덱스 추가 - 검색 속도 향상을 위해서
ALTER TABLE reply ADD INDEX(`relTypeCode`, `relId`);