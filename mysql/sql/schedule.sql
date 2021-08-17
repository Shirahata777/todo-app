CREATE SCHEMA todo_db; 
USE todo_db;

DROP TABLE IF EXISTS schedule;
CREATE TABLE schedule ( scheduleno SERIAL NOT NULL, todono INT NOT NULL, start_day VARCHAR(255) NOT NULL, end_day VARCHAR(255) NOT NULL, PRIMARY KEY (scheduleno) );

INSERT INTO schedule (todono, start_day, end_day) VALUES (1, "2021-09-01", "2021-09-02");