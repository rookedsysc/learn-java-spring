create database jpa_json_performance_test;

use jpa_json_performance_test;

create table jpa_post (
    id bigint not null,
    content varchar(255),
    title varchar(255),
    primary key (id)
);

create table vote (
    id bigint not null,
    vote boolean,
    post_id bigint,
    primary key (id)
);

CREATE TABLE json_post (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255),
    content TEXT,
    votes JSON
);
