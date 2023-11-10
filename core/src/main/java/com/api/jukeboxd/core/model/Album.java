package com.api.jukeboxd.core.model;

import lombok.*;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Album {
    String id;
    String name;
    String type;
    String albumType;
    Integer totalTracks;
    Date releaseDate;
    Integer popularity;
    String url;
    String[] genres;
    String[] copyrights;
    String[] restrictions;
    List<Image> images;
    List<Artist> artists;

}
