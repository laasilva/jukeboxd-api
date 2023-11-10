package com.api.jukeboxd.core.port.persistence;

import com.api.jukeboxd.core.model.Album;

import java.util.List;

public interface AlbumPersistenceAdapter {
    List<Album> fetchAlbumsByName(String albumName);
}
