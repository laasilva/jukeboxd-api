create table music.album (
	id varchar(60) primary key not null,
	name varchar(255) not null,
	album_type varchar(20) not null,
	total_tracks int not null,
	url varchar(255),
	release_date date not null,
	genres varchar[],
	restrictions varchar[],
	copyrights varchar[],
	label varchar(50),
	popularity int
);

create table music.album_image (
	album varchar(60) not null,
	image integer not null,
	foreign key (album) references music.album(id) ON UPDATE cascade ON DELETE RESTRICT,
	foreign key (image) references music.image(id) ON UPDATE cascade ON DELETE RESTRICT
);