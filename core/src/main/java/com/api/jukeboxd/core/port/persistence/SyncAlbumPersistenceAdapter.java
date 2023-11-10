package com.api.jukeboxd.core.port.persistence;

import com.api.jukeboxd.core.dto.SearchQueryDto;
import com.api.jukeboxd.core.model.Album;

import java.util.List;

public interface SyncAlbumPersistenceAdapter {
    Album syncAlbumDataWithSpotify(SearchQueryDto query, String albumName) throws Exception;
    List<Album> searchAlbumsOnSpotify(SearchQueryDto query, String albumName) throws Exception;
}
