use barclaysUsers;
create table userInfo (uId int auto_increment primary key not null, name varchar(50) not null,emailId varchar(30) not null,phoneNo char(10) not null,enabled tinyint not null default 1,dob Date not null,panNo char(10) not null,gender enum('male','female','other'),address varchar(100));
create table branchInfo (branchId int  primary key not null auto_increment,branchName varchar(30) not null,branchAddress varchar(100) not null,branchCode varchar(10) not null);
 create table account (accNo varchar(20) not null primary key, branchId int not null,accType enum('saving','current') default 'saving', balance int not null,foreign key(branchId) references branchInfo(branchId));
create table loginDetails(uId int not null,uName varchar(30) not null,uPassword varchar(60) not null,foreign key(uId) references userInfo(uId));
create table userAcc (uId int, accNo varchar(20), foreign key(uId) references userInfo(uId),foreign key(accNo) references account(accNo));

desc userInfo;

select * from userInfo;
select * from branchInfo;
select * from account;
select * from loginDetails;
select * from userAcc;
