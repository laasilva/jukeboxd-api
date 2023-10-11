package com.api.jukeboxd.datasource.repository;

import com.api.jukeboxd.datasource.entity.ArtistEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArtistRepository extends JpaRepository<ArtistEntity, String> {
    ArtistEntity getArtistByName(String name);
}
