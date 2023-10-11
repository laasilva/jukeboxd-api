package com.api.jukeboxd.core.port.persistence;

import com.api.jukeboxd.core.dto.SearchQueryDto;
import com.api.jukeboxd.core.model.Artist;

import java.util.List;

public interface SyncArtistPersistenceAdapter {
    Artist syncArtistDataWithSpotify(SearchQueryDto query, String artistName);
}
