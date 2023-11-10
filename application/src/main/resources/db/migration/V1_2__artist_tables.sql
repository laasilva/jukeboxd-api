create table music.artist (
	id varchar(60) primary key not null,
	name varchar(255) not null,
	popularity integer,
	followers integer,
	genres varchar[] not null,
	url varchar(255)
);

create table music.image (
	id serial primary key not null,
	url varchar not null,
	width integer not null,
	height integer not null
);

create table music.artist_image (
	artist varchar(60) not null,
	image integer not null,
	foreign key (artist) references music.artist(id) ON UPDATE cascade ON DELETE RESTRICT,
	foreign key (image) references music.image(id) ON UPDATE cascade ON DELETE RESTRICT
);