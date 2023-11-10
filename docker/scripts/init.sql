DROP TABLE IF EXISTS tb_user;
DROP TABLE IF EXISTS tb_user_profile;
DROP TABLE IF EXISTS tb_user_top_five;

CREATE SCHEMA IF NOT EXISTS account;
CREATE SCHEMA IF NOT EXISTS music;

GRANT ALL ON SCHEMA account to usr_admin;
GRANT ALL ON SCHEMA music to usr_admin;