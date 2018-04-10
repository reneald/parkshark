--00
create table postal_codes (
postal_code_id number (6,0) not null,
postal_code varchar2(5) not null,
label varchar2 (50) not null,
constraint postal_codes_pk primary key (postal_code_id)
);

--01
create table addresses (
address_id number (6,0) not null,
street_name varchar2 (100) not null,
street_number varchar2 (5) not null,
fk_postal_code_id number (6,0) not null,
constraint addresses_pk primary key (address_id),
constraint addresses_postal_codes_fk foreign key (fk_postal_code_id) references postal_codes (postal_code_id)
);

--02
create table members (
member_id varchar2 (150) not null,
first_name varchar2 (30) not null,
last_name varchar2 (30) not null,
address varchar2 (100) not null,
fk_address_id number(6,0),
registration_date date not null,
constraint members_pk primary key (member_id),
constraint members_addresses_fk foreign key (fk_address_id) references addresses (address_id)
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
create sequence postal_codes_seq
  start with 1
  increment by 1
  nocache
  nocycle;

--07
create sequence addresses_seq
  start with 1
  increment by 1
  nocache
  nocycle;

--08
create sequence phone_numbers_seq
  start with 1
  increment by 1
  nocache
  nocycle;

--09
create sequence emails_seq
  start with 1
  increment by 1
  nocache
  nocycle;
  
--10
create sequence license_plates_seq
  start with 1
  increment by 1
  nocache
  nocycle;