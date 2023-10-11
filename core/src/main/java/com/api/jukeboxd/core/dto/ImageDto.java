package com.api.jukeboxd.core.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ImageDto {
    @JsonProperty("url")
    String url;
    @JsonProperty("height")
    int height;
    @JsonProperty("width")
    int width;

    public ImageDto(@JsonProperty("url") String url,
                    @JsonProperty("height") int height,
                    @JsonProperty("width") int width) {
        this.url = url;
        this.height = height;
        this.width = width;
    }
}