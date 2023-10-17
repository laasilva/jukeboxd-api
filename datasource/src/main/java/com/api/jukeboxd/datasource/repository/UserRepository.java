package com.api.jukeboxd.datasource.repository;

import com.api.jukeboxd.datasource.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository  extends JpaRepository<UserEntity, Integer> {
    UserEntity findByUsername(String username);
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);
}
