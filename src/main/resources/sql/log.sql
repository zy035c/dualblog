CREATE TABLE Blog (         
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,         
    content TEXT     
);
INSERT INTO Blog VALUES (0,'aaaaa','aaaaaaaaaa'),(1,'bbbbb','bbbbbbbbbb'),(2,'ccccc','cccccccccc');
SELECT * FROM Blog;

CREATE TABLE User (
    id VARCHAR(36) PRIMARY KEY,
    username VARCHAR(255),
    phone VARCHAR(26),
    email VARCHAR(255),
    password VARCHAR(255)
);
