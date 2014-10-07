
CREATE TABLE IF NOT EXISTS clients (
  id int auto_increment,
  prefix varchar(50),
  firstName varchar(100),
  lastName varchar(100),
  company varchar(50),
  type varchar(50),
  constraint client_pk PRIMARY KEY (id)
);