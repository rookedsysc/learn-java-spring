use jpalearning;
CREATE TABLE MEMBER
(
    MEMBER_ID VARCHAR(255) NOT NULL,
    TEAM_ID   VARCHAR(255),
    USERNAME  VARCHAR(255),
    PRIMARY KEY (MEMBER_ID)
);
CREATE TABLE TEAM
(
    TEAM_ID VARCHAR(255) NOT NULL,
    NAME    VARCHAR(255),
    PRIMARY KEY (TEAM_ID)
);
ALTER TABLE MEMBER
    ADD CONSTRAINT FK_MEMBER_TEAM
        FOREIGN KEY (TEAM_ID)
            REFERENCES TEAM (TEAM_ID)
;

CREATE TABLE PRODUCT
(
    PRODUCT_ID VARCHAR(255) NOT NULL,
    NAME       VARCHAR(255) NOT NULL,
    PRIMARY KEY (PRODUCT_ID)
);

CREATE TABLE ORDERS
(
    ORDER_ID   BIGINT       NOT NULL AUTO_INCREMENT PRIMARY KEY,
    MEMBER_ID  VARCHAR(255) NOT NULL,
    PRODUCT_ID VARCHAR(255) NOT NULL,
    FOREIGN KEY (MEMBER_ID) REFERENCES MEMBER (MEMBER_ID),
    FOREIGN KEY (PRODUCT_ID) REFERENCES PRODUCT (PRODUCT_ID)
);


create Table PHONE_PROVIDER
(
    id   bigint not null auto_increment primary key,
    name varchar(255)
);

create table EMBEDED_MEMBER
(
    id                bigint not null auto_increment primary key,
    street            varchar(255),
    city              varchar(255),
    state             varchar(255),
    zip               varchar(255),
    plusFour          varchar(255),
    areaCode          varchar(255),
    localNumber       varchar(255),
    phone_provider_id bigint,
    foreign key (phone_provider_id) references PHONE_PROVIDER (id)
);

