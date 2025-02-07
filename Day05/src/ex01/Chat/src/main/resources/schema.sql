CREATE TABLE IF NOT EXISTS users (
    id SERIAL NOT NULL PRIMARY KEY,
    login varchar NOT NULL,
    password varchar NOT NULL
);

CREATE TABLE IF NOT EXISTS chatrooms (
    id serial NOT NULL PRIMARY KEY,
    name varchar NOT NULL,
    ownerUserId int NOT NULL,
    FOREIGN KEY (ownerUserId) REFERENCES users(id)
);

CREATE TABLE IF NOT EXISTS messages (
    id serial NOT NULL PRIMARY KEY,
    userId int NOT NULL,
    chatroomId int NOT NULL,
    message text NOT NULL,
    date timestamp DEFAULT current_timestamp,
    FOREIGN KEY (userId) REFERENCES users(id),
    FOREIGN KEY (chatroomId) REFERENCES chatrooms(id)
);
