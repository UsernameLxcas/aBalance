CREATE DATABASE AnyEconomy;
USE AnyEconomy;

CREATE TABLE users(
    uuid varchar(64) PRIMARY KEY,
    player varchar(64) NOT NULL,
    coins int(9) NOT NULL
)ENGINE=InnoDB;