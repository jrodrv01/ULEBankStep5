CREATE DATABASE springapp;

GRANT ALL ON springapp.* TO springappuser@'%' IDENTIFIED BY 'pspringappuser';
GRANT ALL ON springapp.* TO springappuser@localhost IDENTIFIED BY 'pspringappuser';

USE springapp;

CREATE TABLE fees (
  id INTEGER PRIMARY KEY,
  description varchar(255),
  fee decimal(15,2),
  idAccount varchar (20)
);