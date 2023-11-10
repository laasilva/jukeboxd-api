package com.api.jukeboxd.core.business;

import com.api.jukeboxd.core.dto.SearchQueryDto;
import com.api.jukeboxd.core.model.Album;
import com.api.jukeboxd.core.port.persistence.SyncAlbumPersistenceAdapter;
import com.api.jukeboxd.core.port.service.AlbumServiceAdapter;
import com.api.jukeboxd.core.util.SearchType;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class AlbumService implements AlbumServiceAdapter {
    private final SyncAlbumPersistenceAdapter syncAlbum;
    @Override
    public Album syncAlbumFromSpotify(String albumName) throws Exception {
        var query = new SearchQueryDto(albumName.replace(" ", "+"),
                SearchType.ALBUM.getValue());

        return syncAlbum.syncAlbumDataWithSpotify(query, albumName);
    }

    @Override
    public List<Album> searchAlbums(String albumName) throws Exception {
        var query = new SearchQueryDto(albumName.replace(" ", "+"),
                SearchType.ALBUM.getValue());

        return syncAlbum.searchAlbumsOnSpotify(query, albumName);
    }
}
