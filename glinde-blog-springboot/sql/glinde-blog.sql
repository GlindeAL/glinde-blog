create table mailbox
(
	mid int auto_increment
		primary key,
	content varchar(300) not null,
	date varchar(30) not null,
	state int not null
);

create table user
(
	uid int auto_increment,
	username varchar(30) not null,
	password varchar(30) not null,
	status int not null,
	state int not null,
	constraint user_uid_uindex
		unique (uid),
	constraint user_username_uindex
		unique (username)
);

alter table user
	add primary key (uid);

create table blog
(
	bid int auto_increment
		primary key,
	title varchar(30) not null,
	description varchar(100) not null,
	content varchar(10000) not null,
	date varchar(30) not null,
	author int not null,
	state int null,
	constraint uid
		foreign key (author) references user (uid)
);