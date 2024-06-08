create database jpa_json_performance_test;

use jpa_json_performance_test;

create table jpa_post
(
    id      bigint not null auto_increment primary key,
    content varchar(255),
    title   varchar(255)
);

create table vote
(
    id        bigint not null auto_increment primary key,
    member_id bigint,
    vote      boolean,
    post_id   bigint
);

CREATE TABLE json_post
(
    id      BIGINT AUTO_INCREMENT PRIMARY KEY,
    title   VARCHAR(255),
    content TEXT,
    votes   JSON
);
