
CREATE TABLE IF NOT EXISTS transactions (
  id int auto_increment,
  accountId int,
  quantity int,
  productId int,
  cost int,
  date date,
  type varchar(50),
  constraint client_pk PRIMARY KEY (id)
);