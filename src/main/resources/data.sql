create table USERS(
                      ID int not null AUTO_INCREMENT,
                      UUID uuid default random_uuid(),
                      NAME VARCHAR(100),
                      EMAIL varchar(100) ,
                      PASSWORD varchar(100),
                      CREATED timestamp default current_timestamp(),
                      MODIFIED timestamp default current_timestamp(),
                      LAST_LOGIN timestamp default current_timestamp(),
                      STATUS boolean,
                      PRIMARY KEY ( ID )
);
CREATE UNIQUE INDEX USERS_EMAIL_IDX ON USERS( EMAIL);

create table TOKENS(
                      ID int not null AUTO_INCREMENT,
                      USER_ID int,
                      TOKEN varchar(300),
                      CREATED timestamp default current_timestamp(),
                      MODIFIED timestamp default current_timestamp(),
                      PRIMARY KEY ( ID ),
                      FOREIGN KEY (USER_ID) REFERENCES USERS(ID)
);

create table PHONES(
                      ID int not null AUTO_INCREMENT,
                      USER_ID int,
                      NUMBER varchar(100) not null,
                      CITY_CODE varchar(10),
                      COUNTRY_CODE varchar(10),
                      PRIMARY KEY ( ID ),
                      FOREIGN KEY (USER_ID) REFERENCES USERS(ID)
);