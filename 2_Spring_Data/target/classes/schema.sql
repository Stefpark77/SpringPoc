DROP TABLE IF EXISTS borrow
DROP TABLE IF EXISTS account
DROP TABLE IF EXISTS book

create table account
(
    id          INTEGER  IDENTITY
        primary key,
    accountName varchar(30) null,
    email       varchar(30) null,
    age         int         null
);

create table book
(
    id     INTEGER  IDENTITY
        primary key,
    author varchar(30) null,
    title  varchar(30) null
);

create table borrow
(
    id           INTEGER  IDENTITY
        primary key,
    accountId    INTEGER                   not null,
    bookId       INTEGER                   not null,
    activeStatus BOOLEAN default TRUE not null
);

ALTER TABLE borrow ADD FOREIGN KEY (accountId) REFERENCES account(id);
ALTER TABLE borrow ADD FOREIGN KEY (bookId) REFERENCES book(id);