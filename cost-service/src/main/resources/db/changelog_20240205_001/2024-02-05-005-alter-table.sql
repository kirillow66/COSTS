-- liquibase formatted sql

-- changeset KrasovskiyK:1707154427218-6
ALTER TABLE if exists t_account
    ADD CONSTRAINT FK_T_ACCOUNT_ON_USER FOREIGN KEY (user_id) REFERENCES t_users (id);

-- changeset KrasovskiyK:1707154427218-7
ALTER TABLE if exists t_category
    ADD CONSTRAINT FK_T_CATEGORY_ON_USER FOREIGN KEY (user_id) REFERENCES t_users (id);

-- changeset KrasovskiyK:1707154427218-8
ALTER TABLE if exists t_cost
    ADD CONSTRAINT FK_T_COST_ON_ACCOUNT FOREIGN KEY (account_id) REFERENCES t_account (id);

-- changeset KrasovskiyK:1707154427218-9
ALTER TABLE if exists t_cost
    ADD CONSTRAINT FK_T_COST_ON_CATEGORY FOREIGN KEY (category_id) REFERENCES t_category (id);

-- changeset KrasovskiyK:1707154427218-10
ALTER TABLE if exists t_cost
    ADD CONSTRAINT FK_T_COST_ON_USER FOREIGN KEY (user_id) REFERENCES t_users (id);

-- changeset KrasovskiyK:1707154427218-11
ALTER TABLE if exists user_user_roles
    ADD CONSTRAINT fk_user_userroles_on_user FOREIGN KEY (user_id) REFERENCES t_users (id);

