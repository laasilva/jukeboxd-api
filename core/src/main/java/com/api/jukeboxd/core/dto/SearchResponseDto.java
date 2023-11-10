package com.api.jukeboxd.core.dto;

import com.api.jukeboxd.core.dto.album.AlbumDto;
import com.api.jukeboxd.core.dto.artist.ArtistDto;
import lombok.*;

@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SearchResponseDto {
    SearchResultDto<ArtistDto> artists;
    SearchResultDto<AlbumDto> albums;
}
