CREATE TABLE COURSE (
    add_Id   INTEGER      NOT NULL AUTO_INCREMENT,
    add_Name VARCHAR(128) NOT NULL,
    add_Description VARCHAR(128) NOT NULL,
    add_Price FLOAT NOT NULL,
    add_CategoryID INT NOT NULL,
    add_CategoryName VARCHAR NOT NULL,

    PRIMARY KEY (add_Id)
);

CREATE TABLE CATEGORY (
    category_Id   INTEGER      NOT NULL AUTO_INCREMENT,
    category_Name VARCHAR(128) NOT NULL,

    PRIMARY KEY (category_Id)
);