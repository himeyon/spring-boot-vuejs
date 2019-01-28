CREATE SEQUENCE IF NOT EXISTS hibernate_sequence START 1;

CREATE TABLE IF NOT EXISTS users(
   id          bigint primary key
  ,code        varchar(15)
  ,name        varchar(100)
  ,role        varchar(10)
  ,status      varchar(10)
  ,password    varchar(30)
  ,registered  timestamp
);
