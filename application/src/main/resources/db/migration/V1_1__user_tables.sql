create table account.user_info (
	id serial primary key not null,
	name varchar(255) not null,
	username varchar(60),
	description text
);

create table account.user (
	id serial primary key not null,
	username varchar(60) not null,
	password varchar(255) not null,
	email varchar(500) not null,
	role varchar(10) not null default 'USER',
	info integer not null,
	unique(username),
	unique(email),
	foreign key (info) references account.user_info(id) on update cascade on delete restrict
);

alter table account.user_info
add constraint fk_username
foreign key (username)
references account.user(username)
on update cascade on delete cascade;