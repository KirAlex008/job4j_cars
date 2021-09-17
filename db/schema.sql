create TABLE if not exists advertisement (
    id serial primary key,
    description varchar(200) NOT NULL,
    brand varchar(255),
    car_body_id int references advertisement_car_body(id),
    photo_address varchar(255),
    created timestamp NOT NULL,
    sold boolean NOT NULL,
    user_id integer references users(id)
    );

create TABLE if not exists users (
    id serial primary key,
    name varchar(200) NOT NULL,
    email varchar(200) NOT NULL UNIQUE,
    password varchar(200) NOT NULL
);

create TABLE if not exists car_body (
    id serial primary key,
    name varchar(200) NOT NULL
);

create TABLE if not exists advertisement_car_body (
     id serial primary key,
     advertisement_id int references advertisement(id),
     car_body_id int references car_body(id)
);
