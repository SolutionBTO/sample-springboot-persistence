DROP TABLE IF EXISTS person;

create table person(
   id integer not null,
   name varchar(255) not null,
   location varchar(255),
   birth_date timestamp,
   primary key(id)
);

INSERT INTO person (ID, NAME, LOCATION, BIRTH_DATE ) 
VALUES(10001,  'Ranga', 'Hyderabad',CURRENT_TIMESTAMP());
INSERT INTO person (ID, NAME, LOCATION, BIRTH_DATE ) 
VALUES(10002,  'James', 'New York',CURRENT_TIMESTAMP());
INSERT INTO person (ID, NAME, LOCATION, BIRTH_DATE ) 
VALUES(10003,  'Pieter', 'Amsterdam',CURRENT_TIMESTAMP());