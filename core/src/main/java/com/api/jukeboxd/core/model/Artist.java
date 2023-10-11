package com.api.jukeboxd.core.model;

import lombok.*;

import java.util.List;
import java.util.Map;
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
    Map<String, String> externalUrls;
}