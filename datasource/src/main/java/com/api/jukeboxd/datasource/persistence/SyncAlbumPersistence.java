package com.api.jukeboxd.datasource.persistence;

import com.api.jukeboxd.core.dto.SearchQueryDto;
import com.api.jukeboxd.core.dto.album.AlbumDto;
import com.api.jukeboxd.core.exception.CoreException;
import com.api.jukeboxd.core.model.Album;
import com.api.jukeboxd.core.model.Artist;
import com.api.jukeboxd.core.port.persistence.SyncAlbumPersistenceAdapter;
import com.api.jukeboxd.core.util.SearchType;
import com.api.jukeboxd.datasource.integration.spotify.SearchRequest;
import com.api.jukeboxd.datasource.mapper.AlbumMapper;
import com.api.jukeboxd.datasource.repository.AlbumRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class SyncAlbumPersistence implements SyncAlbumPersistenceAdapter {
    private final SearchRequest<AlbumDto> search;
    private final AlbumRepository repository;
    private final AlbumMapper mapper;
    private final SyncArtistPersistence syncArtist;
    @Override
    public Album syncAlbumDataWithSpotify(SearchQueryDto query, String albumName) throws Exception {
        try {
            var album = repository.findAlbumByName(albumName);

            if(album != null) {
                return mapper.toModel(album);
            } else {
                var syncedData = mapper.toModel(search.sync(query));

                var artists = new ArrayList<Artist>();

                syncedData.getArtists().forEach(artist -> {
                    var artistQuery = new SearchQueryDto(artist.getName().replace(" ", "+"),
                            SearchType.ARTIST.getValue());
                    artists.add(syncArtist.syncArtistDataWithSpotify(artistQuery, artist.getName()));
                });

                syncedData.setArtists(artists);

                var savedData = repository.save(mapper.toEntity(syncedData));

                return mapper.toModel(savedData);
            }

        } catch(Exception e) {
            throw new CoreException(HttpStatus.INTERNAL_SERVER_ERROR, "Error");
        }
    }

    @Override
    public List<Album> searchAlbumsOnSpotify(SearchQueryDto query, String albumName) throws Exception {
        var album = repository.findAllByNameLike(albumName);

        if (album.size() == 5) {
            return album.stream().map(mapper::toModel).collect(Collectors.toList());
        } else {
            var spotifyData = search.search(query, album.size());
            List<Album> syncedData = spotifyData.stream().map(dto ->
                mapper.toModel((AlbumDto) dto)
            ).collect(Collectors.toList());

            var savedData = new ArrayList<Album>();

            syncedData.forEach(data -> {
                var artists = new ArrayList<Artist>();

                data.getArtists().forEach(artist -> {
                    var artistQuery = new SearchQueryDto(artist.getName().replace(" ", "+"),
                            SearchType.ARTIST.getValue());
                    artists.add(syncArtist.syncArtistDataWithSpotify(artistQuery, artist.getName()));
                });

                data.setArtists(artists);

                var saved = repository.save(mapper.toEntity(data));

                savedData.add(mapper.toModel(saved));
            });

         return savedData;
        }
    }
}
