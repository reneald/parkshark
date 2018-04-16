--00
create table postal_codes (
id varchar2(150) not null,
postal_code varchar2(5) not null,
label varchar2 (50) not null,
constraint postal_codes_pk primary key (id)
);

--03
create table members (
id varchar2(150) not null,
first_name varchar2 (30) not null,
last_name varchar2 (30) not null,
street_name varchar2 (100) not null,
street_number varchar2 (5) not null,
fk_postal_code_id varchar2(150) not null,
email varchar2 (100) not null,
telephone_number varchar2(15),
mobile_phone_number varchar2 (15),
registration_date date not null,
constraint members_pk primary key (id),
constraint members_postal_codes_fk foreign key (fk_postal_code_id) references postal_codes (id)
);


--05
create table license_plates (
id varchar2(150) not null,
license_plate_number varchar2 (100) not null,
issuing_country varchar2 (50) not null,
fk_member_id varchar2(150) not null,
constraint license_plates_pk primary key (id),
constraint license_plates_members_fk foreign key (fk_member_id) references members(id)
);

--06
create table contact_persons (
id varchar2(150) not null,
first_name varchar2(30) not null,
last_name varchar2(30) not null,
telephone_number varchar2(15),
mobile_phone_number varchar2 (15),
email varchar2 (100) not null,
street_name varchar2 (100) not null,
street_number varchar2 (5) not null,
fk_postal_code_id varchar2(150) not null,
constraint contact_persons_pk primary key (id),
constraint contact_persons_postal_codes_fk foreign key (fk_postal_code_id) postal_codes(id)
);

--07
create table parking_lots (
id varchar2(150) not null,
name varchar2(50) not null,
fk_division_id varchar2(150) not null,
building_type varchar2(50) not null,
capacity number not null,
price_per_hour number not null,
street_name varchar2 (100) not null,
street_number varchar2 (5) not null,
fk_postal_code_id varchar2(150) not null,
fk_contact_person_id varchar2(150) not null,
constraint parking_lots_pk primary key (id),
constraint parking_lots_postal_codes_fk foreign key (fk_postal_code_id) references postal_codes(id),
constraint parking_lots_contacts_fk foreign key (fk_contact_person_id) references contact_persons(id)
);

--08
CREATE TABLE divisions(
    id VARCHAR2(150) NOT NULL,
    name VARCHAR2(50CHAR) NOT NULL,
    name_orig VARCHAR2(50CHAR) NOT NULL,
    director VARCHAR2(100CHAR) NOT NULL,
    fk_parent_div_id VARCHAR2(150) NOT NULL,
        CONSTRAINT divisions_pk PRIMARY KEY (id),
        CONSTRAINT divisions_parent_fk FOREIGN KEY (fk_parent_div_id) REFERENCES parkshark.divisions(id)
);