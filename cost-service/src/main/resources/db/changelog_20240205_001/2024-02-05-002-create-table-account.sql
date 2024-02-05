-- liquibase formatted sql

-- changeset KrasovskiyK:1707154427218-3
CREATE TABLE t_account
(
    id      UUID NOT NULL,
    name    VARCHAR(255),
    user_id UUID,
    CONSTRAINT pk_t_account PRIMARY KEY (id)
);