package com.api.jukeboxd.datasource.repository;

import com.api.jukeboxd.datasource.entity.AlbumEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AlbumRepository extends JpaRepository<AlbumEntity, String> {
    AlbumEntity findAlbumByName(String name);
    List<AlbumEntity> findAllByNameLike(String name);
}
