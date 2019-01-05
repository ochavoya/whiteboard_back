MYSQL SCRIPT TO CREATE THE DATABASE

DROP USER 'whiteboard_admin';

DROP DATABASE whiteboard;

CREATE DATABASE whiteboard;

USE whiteboard;

CREATE TABLE user (
   id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
   name VARCHAR(64) NOT NULL,
   username VARCHAR(16) NOT NULL,
   password VARCHAR(128) NOT NULL,
   token CHAR(32) NOT NULL,
   active TINYINT(1) DEFAULT 1
);
                                                                                                                                               CREATE TABLE items (
    id INT(11) NOT NULLL AUTO_INCREMENT PRIMARY KEY,
    boardId INT(11) NOT NULL,
    sectionId INT(11) NOT NULL,
    userId INT(11) NOT NULL,
    title VARCHAR(64) NOT NULL,
    detail VARCHAR(200) NOT NULL,
    createdOn TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    expiresOn TIMESTAMP NULL DEFAULT NULL,
    active TINYINT(1) DEFAULT 1,
    CONSTRAINT FOREIGN KEY (userId) REFERENCES user(id)
);
