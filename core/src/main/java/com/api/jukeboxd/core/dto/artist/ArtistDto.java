package com.api.jukeboxd.core.dto.artist;

import com.api.jukeboxd.core.util.ExternalUrlsDeserializer;
import com.api.jukeboxd.core.dto.ImageDto;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArtistDto {
    String id;
    String name;
    int popularity;
    @SerializedName(value = "external_urls")
    @JsonAdapter(ExternalUrlsDeserializer.class)
    String url;
    List<String> genres;
    List<ImageDto> images;
}