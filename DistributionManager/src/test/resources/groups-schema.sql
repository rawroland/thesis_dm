CREATE TABLE IF NOT EXISTS groups (
  id int auto_increment,
  name varchar(50),
  constraint account_pk PRIMARY KEY (id),
  constraint unique_groups UNIQUE (name)
);

CREATE TABLE IF NOT EXISTS accounts_groups (
  id int auto_increment,
  accountId int,
  groupId int,
  constraint acg_pk PRIMARY KEY (id)
);