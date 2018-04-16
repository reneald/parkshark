--00
create table postal_codes (
id varchar2(150) not null,
postal_code varchar2(5) not null,
label varchar2 (50) not null,
constraint postal_codes_pk primary key (id)
);

--01
create table mem_levels (
    name varchar2(15CHAR) NOT NULL,
    CONSTRAINT levels_pk PRIMARY KEY (name),
    CONSTRAINT enum_name CHECK (name IN ('bronze', 'silver', 'gold'))
);

INSERT INTO mem_levels (name) VALUES ('bronze');
INSERT INTO mem_levels (name) VALUES ('silver');
INSERT INTO mem_levels (name) VALUES ('gold');

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
fk_mem_level varchar2(15CHAR),
registration_date date not null,
constraint members_pk primary key (id),
constraint members_postal_codes_fk foreign key (fk_postal_code_id) references postal_codes (id),
constraint members_levels_fk foreign key (fk_mem_level) references mem_levels(name)
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

--8
CREATE TABLE allocations(
    id VARCHAR2(150) NOT NULL,
    fk_member_id VARCHAR2(150) NOT NULL,
    fk_license_plate_id VARCHAR2(150) NOT NULL,
    fk_parking_lot_id VARCHAR2(150) NOT NULL,
    start_time DATE NOT NULL,
        CONSTRAINT allocations_pk PRIMARY KEY (id),
        CONSTRAINT alloc_member_fk FOREIGN KEY (fk_member_id) REFERENCES members(id),
        CONSTRAINT alloc_license_fk FOREIGN KEY (fk_license_plate_id) REFERENCES license_plates(id),
        CONSTRAINT alloc_parking_fk FOREIGN KEY (fk_parking_lot_id) REFERENCES parking_lots(id)
);