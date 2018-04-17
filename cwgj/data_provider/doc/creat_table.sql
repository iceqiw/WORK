--基础事件统计
create table norm_by_day(ds varchar, event varchar,count_out bigint CONSTRAINT tb_pk PRIMARY KEY (ds, event));
create table norm_by_park_city_day(ds varchar, event varchar,park varchar,city varchar,count_out bigint,parkname varchar,cityname varchar, CONSTRAINT tb_pk PRIMARY KEY (ds, event,park,city));
create table norm_by_city_day(ds varchar, event varchar,city varchar,count_out bigint,cityname varchar, CONSTRAINT tb_pk PRIMARY KEY (ds, event,city));
create table norm_by_bid_city_day(ds varchar, event varchar,bid varchar,city varchar,count_out bigint,bidname varchar,cityname varchar, CONSTRAINT tb_pk PRIMARY KEY (ds, event,bid,city));
--停车场统计
create table PARK_ALL(PARKID varchar, PARKNAME varchar,CITYID varchar,CITYNAME varchar, CONSTRAINT tb_pk PRIMARY KEY (PARKID));

--微信事件报表
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

--广告统计 /废弃 2018-02-26
drop TABLE  event_adv_all_type;
create table event_adv_all_type(
ds varchar,
eventname varchar,
push_type varchar,
adv_id varchar,
sendcount bigint,
castmoney bigint,
CONSTRAINT tb_pk PRIMARY KEY (ds,eventname,push_type,adv_id));

--广告明细表
drop TABLE  adv_push;
create table adv_push(
push_id varchar,
push_type varchar,
adv_id varchar,
adv_name varchar,
money bigint,
telephone varchar,
is_read varchar,
read_time varchar,
push_time varchar,
CONSTRAINT tb_pk PRIMARY KEY (push_id));

--车辆事件报表
create table car_inout_total(
ds varchar ,
park_id varchar,
park_name varchar,
city_id varchar,
city_name varchar,
e_car_month_in bigint,
e_car_temp_in bigint,
temp_car bigint
CONSTRAINT tb_pk PRIMARY KEY (ds, park_id));


drop TABLE  operative_report;
create table operative_report (
parkid varchar,
parkname varchar,
cityid varchar,
cityname varchar,
parktype varchar,
totalparkspacenum bigint,
totalordernum bigint,
totalmonthlynum bigint,
totalnochargenum bigint,
totalchargenum bigint,
totalchargezero bigint,
totalofflinepayment bigint,
totalonlinepayment bigint,
onlinepaymentproportion bigint,
totalcarnum bigint,
totaluser bigint,
newuser bigint,
newmember bigint,
newmemberproportion bigint,
useractive bigint,
totalpush bigint,
actdate varchar,
creattime varchar
CONSTRAINT tb_pk PRIMARY KEY (parkid,actdate));

ALTER TABLE operative_report ADD totalonlinemoney bigint, totalofflinemoney bigint;
