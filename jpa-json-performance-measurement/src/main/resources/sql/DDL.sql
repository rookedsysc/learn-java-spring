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
    post_id   bigint,
    unique (member_id, post_id) # 하나의 회원이 하나의 게시글에 여러번 투표할 수 없도록 유니크 제약조건 추가
);

CREATE TABLE json_post
(
    id      BIGINT AUTO_INCREMENT PRIMARY KEY,
    title   VARCHAR(255),
    content TEXT,
    votes   JSON
);
