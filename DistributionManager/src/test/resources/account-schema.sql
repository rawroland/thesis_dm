CREATE TABLE IF NOT EXISTS accounts (
  id int auto_increment,
  clientId int,
  ammount int,
  constraint account_pk PRIMARY KEY (id)
);