package com.api.jukeboxd.datasource.repository;

import com.api.jukeboxd.datasource.entity.UserInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserInfoRepository  extends JpaRepository<UserInfoEntity, Integer> {
}
