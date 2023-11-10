package com.api.jukeboxd.datasource.mapper;

import com.api.jukeboxd.core.dto.album.AlbumDto;
import com.api.jukeboxd.core.model.Album;
import com.api.jukeboxd.datasource.entity.AlbumEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring",
        uses = {ImageMapper.class, ArtistMapper.class})
public interface AlbumMapper {
    Album toModel(AlbumDto dto);
    Album toModel(AlbumEntity entity);
    AlbumDto toDto(Album model);
    AlbumEntity toEntity(Album model);
}
