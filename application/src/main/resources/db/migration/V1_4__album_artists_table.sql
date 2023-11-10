create table music.album_artist (
	artist varchar(60) not null,
	album varchar(60) not null,
	foreign key (artist) references music.artist(id) ON UPDATE cascade ON DELETE RESTRICT,
	foreign key (album) references music.album(id) ON UPDATE cascade ON DELETE RESTRICT
);