package com.api.jukeboxd.datasource.mapper;

import com.api.jukeboxd.core.dto.artist.ArtistDto;
import com.api.jukeboxd.core.model.Artist;
import com.api.jukeboxd.datasource.entity.ArtistEntity;
import org.mapstruct.CollectionMappingStrategy;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring",
        uses = {ImageMapper.class, AlbumMapper.class})
public interface ArtistMapper {
    Artist toModel(ArtistDto dto);
    Artist toModel(ArtistEntity entity);
    ArtistDto toDto(Artist model);
    ArtistEntity toEntity(Artist model);
}
