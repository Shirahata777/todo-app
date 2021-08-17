USE todo_db;

DROP TABLE IF EXISTS todo;
CREATE TABLE todo ( todono SERIAL NOT NULL, userno INT NOT NULL, title VARCHAR(255) NOT NULL, content VARCHAR(255) NOT NULL, PRIMARY KEY (todono) );

INSERT INTO todo (name, title, content) VALUES ("テストタイトル", "テストデータ");