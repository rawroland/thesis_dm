
CREATE TABLE IF NOT EXISTS employees (
  id int auto_increment,
  givenname varchar(100),
  surname varchar(100),
  username varchar(50),
  password varchar(50),
  role varchar(50),
  constraint employee_pk PRIMARY KEY (id)
);