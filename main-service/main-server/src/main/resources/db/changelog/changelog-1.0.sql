--liquibase formatted sql
--changeset vvv:1
/*DROP TABLE IF EXISTS category CASCADE;
DROP TABLE IF EXISTS muscle CASCADE;
DROP TABLE IF EXISTS training_program CASCADE;
DROP TABLE IF EXISTS muscle_training_program CASCADE;
  DROP TABLE IF EXISTS databasechangelog CASCADE;
  DROP TABLE IF EXISTS databasechangeloglock CASCADE;*/

CREATE TABLE IF NOT EXISTS category
(
    category_id BIGINT GENERATED BY DEFAULT AS IDENTITY,
    name_       VARCHAR(20) NOT NULL UNIQUE,
    description VARCHAR(1000),
    CONSTRAINT pk_category PRIMARY KEY (category_id)
);

CREATE TABLE IF NOT EXISTS muscle
(
    muscle_id   BIGINT GENERATED BY DEFAULT AS IDENTITY,
    category_id BIGINT REFERENCES category (category_id),
    name_       VARCHAR(20) NOT NULL UNIQUE,
    description VARCHAR(1000),
    CONSTRAINT pk_muscle PRIMARY KEY (muscle_id)
);

CREATE TABLE IF NOT EXISTS training_program
(
    training_program_id BIGINT GENERATED BY DEFAULT AS IDENTITY,
    name_               VARCHAR(100) NOT NULL UNIQUE,
    description         VARCHAR(1000),
    CONSTRAINT pk_training_program PRIMARY KEY (training_program_id)
);

CREATE TABLE IF NOT EXISTS muscle_training_program
(
    muscle_training_program_id BIGINT GENERATED BY DEFAULT AS IDENTITY,
    muscle_id                  BIGINT REFERENCES muscle (muscle_id),
    training_program_id        BIGINT REFERENCES training_program (training_program_id),
    CONSTRAINT pk_muscle_training_program PRIMARY KEY (muscle_training_program_id)
);
