CREATE TABLE people (
    id integer primary key,
    name text,
    age integer,
    driver_license boolean,
    car_id integer references cars(id)
);

CREATE TABLE cars (
    id integer primary key,
    brand text,
    model text,
    cost integer
);