create table users(
    id int not null AUTO_INCREMENT,
    name varchar(100) not null,
    PRIMARY KEY (id)
);

create table address(
    id int not null AUTO_INCREMENT,
    user_id int not null,
    content varchar(100),
    foreign key (user_id) references users(id)
);

create table orders(
    id int not null AUTO_INCREMENT,
    user_id int not null ,
    details varchar(100),
    foreign key (user_id) references users(id)
);

insert into users(id, name) values
                                ( 999, 'Cristian' ),
                                ( 998, 'Eliza' );

insert into address(id, user_id, content) values
                                              (99, 999, 'Faget 5A' ),
                                              (98, 998, 'Coposu');

insert into orders(id, user_id, details) values
                                             ( 99, 999, 'Mere' ),
                                             (98, 999, 'Pere');