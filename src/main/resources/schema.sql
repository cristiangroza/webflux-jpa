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

insert into users(id, name) values
                                ( 1, 'Cristian' ),
                                ( 2, 'Eliza' );

insert into address(id, user_id, content) values
                                              (1, 1, 'Faget 5A' ),
                                              (2, 2, 'Coposu');