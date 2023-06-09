
insert into account (accountName, email, age) values ('Stefan', 'Stefan@yahoo.com', 23);
insert into account (accountName, email, age) values ('Andrei', 'Andrei@yahoo.com', 22);
insert into account (accountName, email, age) values ('Alex', 'Alex@yahoo.com', 19);
insert into account (accountName, email, age) values ('Cristian', 'Cristian@yahoo.com', 35);

insert into book (author, title) values ('Someone Somewhere', 'Somehow');
insert into book (author, title) values ('Ronaldo', 'Ball');
insert into book (author, title) values ('Tester Theone', 'How to test');
insert into book (author, title) values ('Anothaone Khaled', 'And another one');


insert into borrow (accountId, bookId, activeStatus) values (1,1, true);
insert into borrow (accountId, bookId, activeStatus) values (1,3, true);
insert into borrow (accountId, bookId, activeStatus) values (2,2, true);
insert into borrow (accountId, bookId, activeStatus) values (3,0, true);
insert into borrow (accountId, bookId, activeStatus) values (1,2, false);
insert into borrow (accountId, bookId, activeStatus) values (1,3, false);