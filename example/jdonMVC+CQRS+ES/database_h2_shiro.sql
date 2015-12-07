drop table if exists users;
drop table if exists user_roles;
drop table if exists roles_permissions;

create table users (
  id bigint auto_increment,
  username varchar(100),
  password varchar(100),
  password_salt varchar(100),
  primary key(id)
)charset=utf8;

create table user_roles(
  id bigint auto_increment,
  username varchar(100),
  role_name varchar(100),
  primary key(id)
)charset=utf8;
create unique index idx_user_roles on user_roles(username, role_name);

create table roles_permissions(
  id bigint auto_increment,
  role_name varchar(100),
  permission varchar(100),
  primary key(id)
)charset=utf8;
create unique index idx_roles_permissions on roles_permissions(role_name, permission);

insert into users(username,password) values('zhang','123');

insert into user_roles(username,role_name) values('zhang','role1');

insert into user_roles(username,role_name) values('zhang','role2');

insert into roles_permissions(role_name,permission) values('role1','user:create');

insert into roles_permissions(role_name,permission) values('role2','user:delete');

insert into users(username,password) values('root','secret');
insert into users(username,password) values('joe','bob');
insert into user_roles(username,role_name) values('root','admin');
insert into user_roles(username,role_name) values('joe','user');
insert into roles_permissions(role_name,permission) values('admin','dummy:*');
insert into roles_permissions(role_name,permission) values('user','dummy:user');






