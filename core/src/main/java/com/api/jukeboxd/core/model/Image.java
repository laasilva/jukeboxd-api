package com.api.jukeboxd.core.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Image {
    String url;
    int height;
    int width;
}