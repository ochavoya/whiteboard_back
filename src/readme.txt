MYSQL SCRIPT TO CREATE THE DATABASE


CREATE DATABASE whiteboard;

USE whiteboard;

CREATE TABLE user (
	id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
        name VARCHAR(64) NOT NULL,
	username VARCHAR(16) NOT NULL,
        password VARCHAR(128) NOT NULL,
        token CHAR(32) DEFAULT NULL,
        active tinyint(1) DEFAULT 1
);


CREATE TABLE items (
  id int(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
  boardId int(11) NOT NULL,
  sectionId int(11) NOT NULL,
  userId int(11) NOT NULL,
  title VARCHAR(64) NOT NULL,
  detail VARCHAR(200) NOT NULL,
  createdOn timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  expiresOn timestamp NULL DEFAULT NULL,
  active tinyint(1) DEFAULT 1,
  CONSTRAINT FOREIGN KEY (userId) REFERENCES user(id)
);

CREATE USER  ‘whiteboard_admin’ IDENTIFIED BY ‘socrates’;

GRANT ALL ON whiteboard.* TO ‘whiteboard_admin’;