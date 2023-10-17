package com.api.jukeboxd.datasource.mapper;

import com.api.jukeboxd.core.model.UserInfo;
import com.api.jukeboxd.datasource.entity.UserInfoEntity;
import org.mapstruct.CollectionMappingStrategy;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring",
        injectionStrategy = InjectionStrategy.FIELD,
        collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED)
public interface UserInfoMapper {
    UserInfo toModel(UserInfoEntity entity);
    UserInfoEntity toEntity(UserInfo model);
}
