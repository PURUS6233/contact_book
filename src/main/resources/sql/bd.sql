--
-- PostgreSQL database dump
--
-- Dumping database structure for contactbook
CREATE SCHEMA IF NOT EXISTS 'contactbook'  /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_bin */;

USE 'contactbook' /*USE contactbook */;

-- Dumping structure for table contactbook.contacts
CREATE TABLE IF NOT EXISTS contactbook.contacts (
  id BIGINT NOT NULL ,
  name VARCHAR NOT NULL ,
  PRIMARY KEY (id) 
);

--Testing data 
INSERT INTO contactbook.contacts(id, name)
VALUES('1', 'Alexander');

INSERT INTO contactbook.contacts(id, name)
VALUES('2', 'Pavel');

INSERT INTO contactbook.contacts(id, name)
VALUES('56', 'Lesha');

INSERT INTO contactbook.contacts(id, name)
VALUES('58', 'Natasha');

INSERT INTO contactbook.contacts(id, name)
VALUES('12316546', 'Maksim');