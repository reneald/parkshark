--00
create table postal_codes (
id varchar2(150) not null,
postal_code varchar2(5) not null,
label varchar2 (50) not null,
constraint postal_codes_pk primary key (id)
);

--01
create table addresses (
id varchar2(150) not null,
street_name varchar2 (100) not null,
street_number varchar2 (5) not null,
fk_postal_code_id varchar2(150) not null,
constraint addresses_pk primary key (id),
constraint addresses_postal_codes_fk foreign key (fk_postal_code_id) references postal_codes (id)
);

--02
create table emails (
id varchar2(150) not null,
email varchar2 (100) not null,
fk_member_id varchar2(150) not null,
constraint emails_pk primary key (id)
);

--03
create table members (
id varchar2(150) not null,
first_name varchar2 (30) not null,
last_name varchar2 (30) not null,
address varchar2 (100) not null,
fk_address_id varchar2(150),
fk_email_id varchar2(150),
registration_date date not null,
constraint members_pk primary key (id),
constraint members_addresses_fk foreign key (fk_address_id) references addresses (id),
constraint members_emails_fk foreign key (fk_email_id) references EMAILS(id)
);

--04
create table phone_numbers (
id varchar2(150) not null,
country_prefix varchar2 (4) not null,
phone_number varchar2 (10) not null,
fk_member_id varchar2(150) not null,
constraint phone_numbers_pk primary key (id),
constraint phones_members_fk foreign key (fk_member_id) references members (id)
);

--05
create table license_plates (
id varchar2(150) not null,
license_plate_number varchar2 (100) not null,
issuing_country varchar2 (50) not null,
fk_member_id varchar2(150) not null,
constraint license_plates_pk primary key (id),
constraint license_plates_members_fk foreign key (fk_member_id) references members (id)
);

  
