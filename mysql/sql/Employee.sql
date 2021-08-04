DROP SCHEMA IF EXISTS todo_db;
CREATE SCHEMA todo_db;
USE todo_db;

DROP TABLE IF EXISTS todo;

CREATE TABLE todo ( todono SERIAL NOT NULL,name VARCHAR(255) NOT NULL,content VARCHAR(255) NOT NULL,PRIMARY KEY (todono) );

INSERT INTO todo (name, content) VALUES ("Nagaoka", "テストコメント");