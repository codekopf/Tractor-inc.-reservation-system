--
--drop table car_stk purge;
--drop table CAR_REPAIR purge;
--drop table LENDING purge;
--drop table CAR_CLIENT purge;
--drop table CAR purge;
--drop Sequence HIBERNATE_SEQ;



CREATE SEQUENCE hibernate_seq start with 10000;

create table car (
  id number(20,0) not null,
  VIN CHAR(17 CHAR) not null,
  cars_type varchar2(20 char) not null,
  date_of_acquisition date not null,
  last_control date,
  
  state_type varchar2(20 char) not null,
  price number(14,2),

  constraint PK_car_id primary key (id),
  constraint chk_car_001 check (state_type in  ('NEW', 'DISABLED', 'LENDED', 'BROKEN', 'IN_GARAGE'))
);


create table car_client (
  id number(20,0) not null,
  name varchar2(500 char) not null, 
  surname varchar2(100 char),
  ico varchar2(100 char),
  registration_date date not null,
  email varchar2(150 char),
  ceil_phone  varchar2(16 char),
  
  constraint PK_car_client_id primary key (id),
  constraint CHK_car_client_001 check ((ico is not null and surname is null) or (ico is null and surname is not null and email is not null))
);


create table lending (
  id number(20,0) not null,
  car number(20,0) not null,
  date_from date not null,
  date_to date not null,
  price number(14,2),
  car_client number(20) not null,
  lattitude number,
  longitude number,
  constraint PK_Lending_id primary key (id),
  constraint FK_Lending_001 FOREIGN key (car) REFERENCES car (id),
  constraint FK_Lending_002 FOREIGN key (car_client) REFERENCES car_client (id)
);

create table car_repair (
  id number(20,0) not null,
  car number(20,0) not null,
  price number(14,2),
  repair_resolution varchar2(1000 char),
  
  constraint PK_car_repair_id primary key (id),
  constraint FK_car_repair_001 FOREIGN key (car) REFERENCES car (id)
);

create table car_stk (
  id number(20,0) not null,
  car number(20,0) not null,
  check_date date not null,
  is_pass char(1 char),
  
  constraint PK_car_stk_id primary key (id),
  constraint FK_car_stk_001 FOREIGN key (car) REFERENCES car (id), 
  constraint CHK_car_stk_001 check (is_pass in ('Y', 'N'))
);

