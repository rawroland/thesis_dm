CREATE TABLE IF NOT EXISTS groups (
  id int auto_increment,
  name varchar(50),
  constraint group_pk PRIMARY KEY (id),
  constraint unique_groups UNIQUE (name)
);

CREATE TABLE IF NOT EXISTS clients_groups (
  id int auto_increment,
  clientId int,
  groupId int,
  constraint acg_pk PRIMARY KEY (id)
);