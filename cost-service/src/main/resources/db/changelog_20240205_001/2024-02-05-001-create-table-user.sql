-- liquibase formatted sql
    
-- changeset KrasovskiyK:1707154427218-1
CREATE TABLE t_users
(
    id       UUID NOT NULL,
    login    VARCHAR(255),
    name     VARCHAR(255),
    email    VARCHAR(255),
    password VARCHAR(255),
    CONSTRAINT pk_t_users PRIMARY KEY (id)
);

-- changeset KrasovskiyK:1707154427218-2
CREATE TABLE user_user_roles
(
    user_id    UUID NOT NULL,
    user_roles VARCHAR(255)
);