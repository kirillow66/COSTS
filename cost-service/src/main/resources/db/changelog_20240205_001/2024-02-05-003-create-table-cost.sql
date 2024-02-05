-- liquibase formatted sql

-- changeset KrasovskiyK:1707154427218-4
CREATE TABLE t_cost 
(
    id          UUID NOT NULL,
    name        VARCHAR(255),
    date        date,
    category_id UUID,
    account_id  UUID,
    user_id     UUID,
    CONSTRAINT pk_t_cost PRIMARY KEY (id)
);

