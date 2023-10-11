package com.api.jukeboxd.core.port.service;

import com.api.jukeboxd.core.model.Artist;
import com.api.jukeboxd.core.model.Paginated;

import java.util.List;

public interface ArtistServiceAdapter {
    Artist syncArtistFromSpotify(String artistName);
    List<Artist> getArtistsById(String[] ids);
    Paginated<List<Artist>> getAllArtists(int page, int size);
}
