create table norm_by_day(ds varchar, event varchar,count_out bigint CONSTRAINT tb_pk PRIMARY KEY (ds, event));
create table norm_by_park_city_day(ds varchar, event varchar,park varchar,city varchar,count_out bigint,parkname varchar,cityname varchar, CONSTRAINT tb_pk PRIMARY KEY (ds, event,park,city));
create table norm_by_city_day(ds varchar, event varchar,city varchar,count_out bigint,cityname varchar, CONSTRAINT tb_pk PRIMARY KEY (ds, event,city));
create table norm_by_bid_city_day(ds varchar, event varchar,bid varchar,city varchar,count_out bigint,bidname varchar,cityname varchar, CONSTRAINT tb_pk PRIMARY KEY (ds, event,bid,city));

create table PARK_ALL(PARKID varchar, PARKNAME varchar,CITYID varchar,CITYNAME varchar, CONSTRAINT tb_pk PRIMARY KEY (PARKID));

create table report_wechat_push(
ds varchar ,
park_id varchar,
park_name varchar,
city_id varchar,
city_name varchar,
e_event_wechat_in_push bigint,
e_event_wechat_out_push bigint,
e_event_advance_pay_push bigint,
e_event_pay_success_push bigint,
CONSTRAINT tb_pk PRIMARY KEY (ds, park_id));



create table test_storm( ds varchar,event varchar,context varchar CONSTRAINT tb_pk PRIMARY KEY (ds,event));




---hive
CREATE EXTERNAL TABLE citys (
  cityid string,
  cityname string)
ROW FORMAT DELIMITED
FIELDS TERMINATED BY ','
STORED AS TEXTFILE
LOCATION 'oss://LTAIiINLHoKk8GCh:qjiLw1uSnxmsjIM0y3Fh9eRy3FAqSd@cwgj.oss-cn-shanghai.aliyuncs.com/mysql/table/hive_sys_city';
