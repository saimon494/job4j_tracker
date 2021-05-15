create table items (
                       id serial primary key,
                       name text,
                       created timestamp
);
alter table items add column created timestamp;
truncate table items restart identity;