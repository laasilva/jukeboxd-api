package com.api.jukeboxd.core.dto;

import com.api.jukeboxd.core.dto.artist.ArtistDto;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class SearchResultDto {
    @JsonProperty("href")
    String href;
    @JsonProperty("items")
    List<ArtistDto> items;

    public SearchResultDto(@JsonProperty("href") String href,
                           @JsonProperty("items") List<ArtistDto> items) {
        this.href = href;
        this.items = items;
    }
}
