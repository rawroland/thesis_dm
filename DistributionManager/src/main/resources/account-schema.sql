CREATE TABLE IF NOT EXISTS accounts (
  id int auto_increment,
  clientId int,
  Amount int,
  constraint account_pk PRIMARY KEY (id),
  constraint unique_client_account UNIQUE (clientId)
);