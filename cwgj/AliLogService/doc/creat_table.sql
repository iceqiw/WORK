drop TABLE  stat_data;
create table stat_data(
statkey varchar,
statvalue bigint,
CONSTRAINT tb_pk PRIMARY KEY (statkey));
DROP SEQUENCE STAT.USERS;
CREATE SEQUENCE STAT.USERS START WITH 1861635;
DROP SEQUENCE STAT.ORDERS;
CREATE SEQUENCE STAT.ORDERS START WITH 1522285;


drop TABLE  stat_day_data;
create table stat_day_data(
statkey varchar,
statdate varchar,
statvalue varchar,
CONSTRAINT tb_pk PRIMARY KEY (statkey,statdate));