-- liquibase formatted sql

-- changeset KrasovskiyK:1707154427218-5
CREATE TABLE t_category
(
    id            UUID NOT NULL,
    name          VARCHAR(255),
    user_id       UUID,
    category_type VARCHAR(255),
    CONSTRAINT pk_t_category PRIMARY KEY (id)
);