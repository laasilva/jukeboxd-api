package com.api.jukeboxd.datasource.persistence;

import com.api.jukeboxd.core.model.Album;
import com.api.jukeboxd.core.port.persistence.AlbumPersistenceAdapter;
import com.api.jukeboxd.datasource.mapper.AlbumMapper;
import com.api.jukeboxd.datasource.repository.AlbumRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class AlbumPersistence implements AlbumPersistenceAdapter {
    private final AlbumRepository repository;
    private final AlbumMapper mapper;

    @Override
    public List<Album> fetchAlbumsByName(String albumName) {
        var albums = repository.findAllByNameLike(albumName);

        return albums.stream().map(mapper::toModel).collect(Collectors.toList());
    }
}
