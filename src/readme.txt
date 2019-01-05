DROP USER 'whiteboard_admin';

DROP DATABASE whiteboard;

CREATE DATABASE whiteboard;

USE whiteboard;

CREATE TABLE user (
	user_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
        name VARCHAR(64) NOT NULL,
	username VARCHAR(16) NOT NULL,
        password VARCHAR(128) NOT NULL,
        token CHAR(32) DEFAULT NULL,
        active tinyint(1) DEFAULT 1
);


CREATE TABLE items (
  item_id int(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
  board_id int(11) NOT NULL,
  section_id int(11) NOT NULL,
  user_id int(11) NOT NULL,
  title VARCHAR(64) NOT NULL,
  detail VARCHAR(200) NOT NULL,
  createdOn timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  expiresOn timestamp NULL DEFAULT NULL,
  active tinyint(1) DEFAULT 1,
  CONSTRAINT FOREIGN KEY (user_id) REFERENCES user(user_id)
);

CREATE USER 'whiteboard_admin' IDENTIFIED BY 'Socrates';

GRANT ALL ON whiteboard.* TO 'whiteboard_admin';