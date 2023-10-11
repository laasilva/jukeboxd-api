package com.api.jukeboxd.core.dto.artist;

import com.api.jukeboxd.core.dto.ImageDto;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Map;
@Getter
@Setter
@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ArtistDto {
    @JsonProperty("id")
    String id;
    @JsonProperty("name")
    String name;
    @JsonProperty("popularity")
    int popularity;
    @JsonProperty("external_urls")
    Map<String, String> externalUrls;
    @JsonProperty("genres")
    List<String> genres;
    @JsonProperty("images")
    List<ImageDto> images;
    @JsonProperty("href")
    String href;

    public ArtistDto(@JsonProperty("id") String id,
                     @JsonProperty("name") String name,
                     @JsonProperty("popularity") int popularity,
                     @JsonProperty("external_urls") Map<String, String> externalUrls,
                     @JsonProperty("genres") List<String> genres,
                     @JsonProperty("images") List<ImageDto> images,
                     @JsonProperty("href") String href) {
        this.id = id;
        this.name = name;
        this.popularity = popularity;
        this.externalUrls = externalUrls;
        this.genres = genres;
        this.images = images;
        this.href = href;
    }
}