package com.api.jukeboxd.datasource.repository;

import com.api.jukeboxd.datasource.entity.ArtistEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArtistRepository extends JpaRepository<ArtistEntity, String> {
    ArtistEntity getArtistByName(String name);
    List<ArtistEntity> findAllByNameLike(String name);
}
