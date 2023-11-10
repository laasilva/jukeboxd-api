package com.api.jukeboxd.datasource.persistence;

import com.api.jukeboxd.core.dto.SearchQueryDto;
import com.api.jukeboxd.core.dto.artist.ArtistDto;
import com.api.jukeboxd.core.exception.CoreException;
import com.api.jukeboxd.core.model.Artist;
import com.api.jukeboxd.core.port.persistence.SyncArtistPersistenceAdapter;
import com.api.jukeboxd.datasource.integration.spotify.SearchRequest;
import com.api.jukeboxd.datasource.mapper.ArtistMapper;
import com.api.jukeboxd.datasource.repository.ArtistRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class SyncArtistPersistence implements SyncArtistPersistenceAdapter {
    private final SearchRequest<ArtistDto> search;
    private final ArtistRepository repository;
    private final ArtistMapper artistMapper;
    @Override
    public Artist syncArtistDataWithSpotify(SearchQueryDto query, String artistName) {
        try {
            var artist = repository.getArtistByName(artistName);

            if(artist != null) {
                return artistMapper.toModel(artist);
            } else {
                var syncedData = artistMapper.toModel(search.sync(query));
                var savedData = repository.save(artistMapper.toEntity(syncedData));
                return artistMapper.toModel(savedData);
            }
        } catch (Exception e) {
            throw new CoreException(HttpStatus.INTERNAL_SERVER_ERROR, "Error");
        }
    }

    @Override
    public List<Artist> searchArtistsOnSpotify(SearchQueryDto query, String artistName) throws Exception {
        var artists = repository.findAllByNameLike(artistName);

        if (artists.size() == 5) {
            return artists.stream().map(artistMapper::toModel).collect(Collectors.toList());
        } else {
            var spotifyData = search.search(query, artists.size());
            List<Artist> syncedData = spotifyData.stream().map(dto ->
                    artistMapper.toModel((ArtistDto) dto)).collect(Collectors.toList());

            var savedData = new ArrayList<Artist>();

            syncedData.forEach(data -> {
                var saved = repository.save(artistMapper.toEntity(data));
                savedData.add(artistMapper.toModel(saved));
            });

            return savedData;
        }
    }
}
