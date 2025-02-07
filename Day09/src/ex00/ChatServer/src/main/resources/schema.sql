CREATE TABLE users
(
    id       SERIAL PRIMARY KEY,
    login    VARCHAR(50),
    password VARCHAR(500)
);