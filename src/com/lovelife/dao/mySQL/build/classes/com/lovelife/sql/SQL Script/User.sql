CREATE SCHEMA `LoveLife` DEFAULT CHARACTER SET utf8 ;

Use LoveLife;

CREATE TABLE IF NOT EXISTS User(
userID int AUTO_INCREMENT,
username nvarchar(50),
password nvarchar(200),
fullname nvarchar(50),
nickname nvarchar(50),
dob nvarchar(10),
contactno nvarchar(20),
email nvarchar(100),
withUserID int,
CONSTRAINT pk_userID PRIMARY KEY (userID),
CONSTRAINT fk_withUserID FOREIGN KEY (withUserID) REFERENCES User(userID)
);
INSERT INTO LoveLife.User (username,password) values ('thetminko', '4f3ab5402a1e25042719a61228cceedc8f7893b6','Thet Min Ko', 'Buthee','01/11/1991','(+65)98278559','thetbright@gmail.com',2);