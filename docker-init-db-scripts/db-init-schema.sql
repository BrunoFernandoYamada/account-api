DROP SCHEMA IF EXISTS accounts;
CREATE SCHEMA accounts;

CREATE EXTENSION pgcrypto SCHEMA accounts;

CREATE TABLESPACE tsdcustomer01 OWNER postgres LOCATION '/var/lib/postgresql/data/pg_tblspc';
CREATE TABLESPACE tsicustomer01 OWNER postgres LOCATION '/var/lib/postgresql/data/';

