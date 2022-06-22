CREATE TABLE users (
    login varchar(200) not null primary key,
    name varchar(50) not null,
    password varchar(50) not null
);

create table role (
    id serial primary key,
    name varchar(200) not null
);

create table user_role (
    login varchar(200) not null,
    id int not null,
    primary key (login, id),
    constraint login_role_ibkf_1 foreign key (login) references users (login),
    constraint login_role_ibfk_2 foreign key (id) references role (id)
);