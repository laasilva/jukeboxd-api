package com.api.jukeboxd.core.port.service;

import com.api.jukeboxd.core.model.Album;

import java.util.List;

public interface AlbumServiceAdapter {
    Album syncAlbumFromSpotify(String albumName) throws Exception;
    List<Album> searchAlbums(String albumName) throws Exception;
}
