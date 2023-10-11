package com.api.jukeboxd.core.dto.artist;

import com.api.jukeboxd.core.dto.SearchResultDto;
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
public class SearchArtistResponseDto {
    @JsonProperty("artists")
    SearchResultDto artists;

    public SearchArtistResponseDto(@JsonProperty("artists") SearchResultDto artists) {
        this.artists = artists;
    }
}
