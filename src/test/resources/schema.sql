CREATE DATABASE psqldriver_test;

CREATE TABLE TestTable (
    Id SERIAL,
    ColString CHARACTER VARYING(25),
    ColDouble DOUBLE,
    ColInteger INTEGER,
    ColTimestamp TIMESTAMP WITHOUT TIME ZONE
);

INSERT INTO TestTable (Id, ColString, ColDouble, ColInteger, ColTimestamp)
    VALUES (1, 'test 1', 55.5, 108, '1989-04-15 09:00:45');

INSERT INTO TestTable (Id, ColString, ColDouble, ColInteger, ColTimestamp)
    VALUES (2, 'test 2', 1989.0405, 1989, '2018-01-01 01:01:00');