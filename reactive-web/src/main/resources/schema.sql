CREATE TABLE IF NOT EXISTS BOOK
(
    ID INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT,
    TITLE VARCHAR(100),
    AUTHOR VARCHAR(100),
    PUBLISHER VARCHAR(100),
    ISBN VARCHAR(100),
    GENRE VARCHAR(100),
    CREATED_DATE TIMESTAMP,
    UPDATED_DATE TIMESTAMP
);