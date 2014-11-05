CREATE TABLE IF NOT EXISTS products (
  id int auto_increment,
  name varchar(50),
  quantity int,
  clientId int,
  price int,
  constraint product_pk PRIMARY KEY (id),
  constraint unique_product UNIQUE (name, clientId)
);