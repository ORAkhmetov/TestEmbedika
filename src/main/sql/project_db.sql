CREATE TABLE car (
    id int GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    model varchar(50) NOT NULL,
    state_number varchar(10) NOT NULL UNIQUE,
    color varchar(30) NOT NULL,
    year int NOT NULL,
    date_of_creation timestamp NOT NULL
);