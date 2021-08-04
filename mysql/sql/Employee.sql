DROP SCHEMA IF EXISTS todo_db;
CREATE SCHEMA todo_db;
USE todo_db;

DROP TABLE IF EXISTS todo;

CREATE TABLE todo
(
  todono           INT auto_increment,
  name             VARCHAR(40),
  content          VARCHAR(255)、
  index(todono)
);

INSERT INTO todo (name, content) VALUES ("Nagaoka", "テストコメント");