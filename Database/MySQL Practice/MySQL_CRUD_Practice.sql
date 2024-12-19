CREATE DATABASE user;

-- user 테이블
CREATE TABLE user(
	id BIGINT(32) NOT NULL AUTO_INCREMENT COMMENT 'index',
    name VARCHAR(50) NOT NULL COMMENT '사용자 이름',
    age INT NULL DEFAULT '1' COMMENT '사용자 나이',
    email VARCHAR(100) NULL DEFAULT '' COMMENT '이메일 주소',
	
    PRIMARY KEY(id)
);
INSERT INTO user(name, age, email) VALUES('홍길동', 20, 'hong@gmail.com');
INSERT INTO user(name) VALUES('유관순');
INSERT INTO user(name) VALUES('강감찬');
UPDATE user SET age = 20,email='kang@gmail.com' WHERE name='강감찬';
UPDATE user SET age = 15,email='you@gmail.com' WHERE name='유관순';
DELETE FROM user WHERE name='유관순';


-- department 테이블user
CREATE TABLE department(
	depNo VARCHAR(25) NOT NULL PRIMARY KEY,
    depName VARCHAR(15)
);
INSERT INTO department VALUES('1','컴퓨터공학과'),('2','경영학과'),('3','통계학과');


-- student 테이블
CREATE TABLE `student` (
  `stdNo` varchar(15) NOT NULL,
  `stdName` varchar(45) NOT NULL,
  `stdGrade` int DEFAULT '4',
  `depNo` varchar(15)  NOT NULL,
  PRIMARY KEY (`stdNo`),
  KEY `FK_student_department` (`depNo`),
  CONSTRAINT `FK_student_department` FOREIGN KEY (`depNo`) REFERENCES `department` (`depNo`)
);
INSERT INTO student VALUES ('20240001',	'홍길동', 1, '1'),('20240002', '이순신', 3, '1'),('20240003','장영실',2,'1')


