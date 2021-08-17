
USE todo_db;

DROP TABLE IF EXISTS user;
CREATE TABLE user ( userno SERIAL NOT NULL, name VARCHAR(255) NOT NULL, email VARCHAR(255), PRIMARY KEY (userno) );

INSERT INTO user (name) VALUES ("中岡");