package com.api.jukeboxd.core.model;

import lombok.*;

import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Artist {
    String id;
    String name;
    int popularity;
    List<Image> images;
    List<String> genres;
    String url;
}