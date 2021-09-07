DROP SCHEMA IF EXISTS todo_db;
CREATE SCHEMA todo_db;
USE todo_db;

DROP TABLE IF EXISTS schedule;
CREATE TABLE schedule ( scheduleno SERIAL NOT NULL, todono INT NOT NULL, start_day DATE NOT NULL, end_day DATE NOT NULL, PRIMARY KEY (scheduleno) );

INSERT INTO schedule (todono, start_day, end_day) VALUES (1, "2021-09-01", "2021-09-02");


DROP TABLE IF EXISTS todo;
CREATE TABLE todo ( todono SERIAL NOT NULL, userno INT NOT NULL, title VARCHAR(255) NOT NULL, content VARCHAR(255) NOT NULL, PRIMARY KEY (todono) );

INSERT INTO todo (userno, title, content) VALUES (1, "テストタイトル", "テストデータ");


DROP TABLE IF EXISTS user;
CREATE TABLE user ( userno SERIAL NOT NULL, name VARCHAR(255) NOT NULL, email VARCHAR(255), PRIMARY KEY (userno) );

INSERT INTO user (name) VALUES ("中岡");