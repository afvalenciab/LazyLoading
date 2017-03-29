CREATE DATABASE lazyloading;

GRANT ALL ON lazyloading.* TO lazyloadinguser@'%' IDENTIFIED BY 'lazyloadinguser';
GRANT ALL ON lazyloading.* TO lazyloadinguser@localhost IDENTIFIED BY 'lazyloadingpass';

USE lazyloading;

CREATE TABLE Tlog (
  id INTEGER PRIMARY KEY AUTO_INCREMENT,
  cedulaParticipante varchar(255),
  dateExecution varchar(10)
);
