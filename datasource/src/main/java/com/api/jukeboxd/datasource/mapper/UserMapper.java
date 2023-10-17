package com.api.jukeboxd.datasource.mapper;

import com.api.jukeboxd.core.model.User;
import com.api.jukeboxd.datasource.entity.UserEntity;
import org.mapstruct.CollectionMappingStrategy;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring",
        injectionStrategy = InjectionStrategy.FIELD,
        collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED,
        uses = {UserInfoMapper.class})
public interface UserMapper {
    User toModel(UserEntity entity);
    UserEntity toEntity(User model);
}
