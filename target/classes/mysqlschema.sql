CREATE DATABASE jpa_mysqldb;
USE jpa_mysqldb;

CREATE TABLE CONTACT_DTLS(
contact_id INT PRIMARY KEY,
firstName VARCHAR(30),
middleName VARCHAR(30),
lastName VARCHAR(30),
dateOfBirth VARCHAR(20),
homeAddress VARCHAR(50),
workAddress VARCHAR(50),
homePhone VARCHAR(20),
workPhone VARCHAR(20),
cellPhone VARCHAR(20),
fax VARCHAR(30),
emailIds VARCHAR(80)
);	


CREATE TABLE CNT_TAG(
tag_id INT PRIMARY KEY,
tag INT,
contact_id INT UNIQUE,
FOREIGN KEY (contact_id) REFERENCES CONTACT_DTLS (contact_id)
);

