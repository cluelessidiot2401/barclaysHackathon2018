create user 'diligents'@'localhost' identified by 'diligents123!@#';
create database barclaysUsers;
grant all on barclaysUsers.* to 'diligents'@'localhost';
flush privileges;