create table tutorial
(
   id IDENTITY not null PRIMARY KEY,
   title varchar(255) not null,
   description varchar(255) not null,
   published bit not null
);