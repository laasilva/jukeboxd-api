package com.api.jukeboxd.core.dto;

import lombok.*;

@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ImageDto {
    String url;
    int height;
    int width;
}