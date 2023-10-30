package com.api.jukeboxd.core.business;

import com.api.jukeboxd.core.dto.SearchQueryDto;
import com.api.jukeboxd.core.model.Artist;
import com.api.jukeboxd.core.model.Paginated;
import com.api.jukeboxd.core.port.persistence.ArtistPersistenceAdapter;
import com.api.jukeboxd.core.port.persistence.SyncArtistPersistenceAdapter;
import com.api.jukeboxd.core.port.service.ArtistServiceAdapter;
import com.api.jukeboxd.core.util.SearchType;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class ArtistService implements ArtistServiceAdapter {
    private final SyncArtistPersistenceAdapter syncArtist;
    private final ArtistPersistenceAdapter persistence;
    @Override
    public Artist syncArtistFromSpotify(String artistName) {
        var query = new SearchQueryDto(artistName.replace(" ", "+"),
                SearchType.ARTIST.getValue());

        return syncArtist.syncArtistDataWithSpotify(query, artistName);
    }

    @Override
    public List<Artist> searchArtists(String artistName) throws Exception {
        var query = new SearchQueryDto(artistName.replace(" ", "+"),
                SearchType.ARTIST.getValue());

        return syncArtist.searchArtistsOnSpotify(query, artistName);
    }

    @Override
    public List<Artist> getArtistsById(String[] ids) {
        return persistence.fetchArtists(ids);
    }

    @Override
    public Paginated<List<Artist>> getAllArtists(int page, int size) {
        return persistence.fetchAllArtists(page, size);
    }
}
