package com.api.jukeboxd.core.port.persistence;

import com.api.jukeboxd.core.model.Artist;
import com.api.jukeboxd.core.model.Paginated;

import java.util.List;

public interface ArtistPersistenceAdapter {
    List<Artist> fetchArtists(String[] ids);
    Paginated<List<Artist>> fetchAllArtists(int page, int size);
}
