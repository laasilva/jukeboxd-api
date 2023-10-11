package com.api.jukeboxd.datasource.mapper;

import com.api.jukeboxd.core.dto.ImageDto;
import com.api.jukeboxd.core.model.Image;
import org.mapstruct.CollectionMappingStrategy;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring",
        injectionStrategy = InjectionStrategy.FIELD,
        collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED)
public interface ImageMapper {
    Image toModel(ImageDto dto);
    ImageDto toDto(Image model);
}
