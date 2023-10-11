package com.api.jukeboxd.datasource.persistence;

import com.api.jukeboxd.core.model.Artist;
import com.api.jukeboxd.core.model.Paginated;
import com.api.jukeboxd.core.port.persistence.ArtistPersistenceAdapter;
import com.api.jukeboxd.datasource.mapper.ArtistMapper;
import com.api.jukeboxd.datasource.repository.ArtistRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
@Component
@RequiredArgsConstructor
public class ArtistPersistence implements ArtistPersistenceAdapter {
    private final ArtistRepository repository;
    private final ArtistMapper artistMapper;
    @Override
    public List<Artist> fetchArtists(String[] ids) {
        var artists = repository.findAllById(Arrays.asList(ids));
        return artists.stream().map(artistMapper::toModel).collect(Collectors.toList());
    }

    @Override
    public Paginated<List<Artist>> fetchAllArtists(int page, int size) {
        Pageable paging = PageRequest.of(page, size);

        var entity = repository.findAll(paging);
        var artists = entity.getContent().stream().map(artistMapper::toModel).collect(Collectors.toList());
        return new Paginated<>(page, size, entity.getTotalElements(), artists);
    }
}
