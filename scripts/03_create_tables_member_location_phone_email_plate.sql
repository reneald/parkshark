--01
create table locations (
location_id number (6,0) not null,
zip varchar2 (7) not null,
city varchar2 (20) not null,
state varchar2 (20),
constraint locations_pk primary key (location_id)
);

--02
create table members (
member_id varchar2 (150) not null,
first_name varchar2 (30) not null,
last_name varchar2 (30) not null,
address varchar2 (100) not null,
fk_location_id number(6,0),
registration_date date not null,
constraint members_pk primary key (member_id),
constraint members_locations_fk foreign key (fk_location_id) references locations (location_id)
);

--03
create table phone_numbers (
phone_id number (6,0) not null,
country_prefix varchar2 (4) not null,
phone_number varchar2 (10) not null,
fk_member_id varchar2(150) not null,
constraint phone_numbers_pk primary key (phone_id),
constraint phones_members_fk foreign key (fk_member_id) references members (member_id)
);

--04
create table emails (
email_id number (6,0) not null,
email varchar2 (100) not null,
fk_member_id varchar2(150) not null,
constraint emails_pk primary key (email_id),
constraint emails_members_fk foreign key (fk_member_id) references members (member_id)
);

--05
create table license_plates (
license_plate_id number (6,0) not null,
license_plate_number varchar2 (100) not null,
issuing_country varchar2 (50) not null,
fk_member_id varchar2(150) not null,
constraint license_plates_pk primary key (license_plate_id),
constraint license_plates_members_fk foreign key (fk_member_id) references members (member_id)
);

--06
create sequence locations_seq
  start with 1
  increment by 1
  nocache
  nocycle;
  
--07
create sequence phone_numbers_seq
  start with 1
  increment by 1
  nocache
  nocycle;

--08
create sequence emails_seq
  start with 1
  increment by 1
  nocache
  nocycle;
  
--09
create sequence license_plates_seq
  start with 1
  increment by 1
  nocache
  nocycle;
