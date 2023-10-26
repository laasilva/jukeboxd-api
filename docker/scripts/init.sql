DROP TABLE IF EXISTS tb_user;
DROP TABLE IF EXISTS tb_user_profile;
DROP TABLE IF EXISTS tb_user_top_five;

CREATE SCHEMA IF NOT EXISTS account;
CREATE SCHEMA IF NOT EXISTS music;

GRANT ALL ON SCHEMA priv to tswizle;

create table music.artist (
	id varchar(50) primary key not null,
	name varchar(255) not null,
	popularity integer,
	followers integer,
	genres varchar[] not null,
	href varchar(255)
);

create table music.image (
	id serial primary key not null,
	url varchar not null,
	width integer not null,
	height integer not null
);

create table music.artist_image (
	artist varchar(20) not null,
	image integer not null,
	foreign key (artist) references music.artist(id) ON UPDATE cascade ON DELETE RESTRICT,
	foreign key (image) references music.image(id) ON UPDATE cascade ON DELETE RESTRICT
);

create table account.user_info (
	id serial primary key not null,
	name varchar(255) not null,
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