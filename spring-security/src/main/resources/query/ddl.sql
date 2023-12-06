create database spring;

use spring;

create table users(
    id BIGINT NOT NULL AUTO_INCREMENT primary key,
    email varchar(100) not null,
    password varchar(100) not null,
    role varchar(50) not null
)