CREATE TABLE Blog (
    id VARCHAR(255) PRIMARY KEY,
    title VARCHAR(255),
    authorUUID VARCHAR(255),
    timestamp TIMESTAMP
);

SELECT * FROM Blog;

CREATE TABLE User (
    id VARCHAR(36) PRIMARY KEY,
    username VARCHAR(255),
    phone VARCHAR(26),
    email VARCHAR(255),
    password VARCHAR(255)
);
