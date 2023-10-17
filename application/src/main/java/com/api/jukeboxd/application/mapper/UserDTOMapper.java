package com.api.jukeboxd.application.mapper;

import com.api.jukeboxd.application.dto.request.UserRequestDTO;
import com.api.jukeboxd.application.dto.response.UserResponseDTO;
import com.api.jukeboxd.core.model.User;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring",
        injectionStrategy = InjectionStrategy.FIELD,
        uses = {UserInfoDTOMapper.class})
public interface UserDTOMapper {
    User toModel(UserRequestDTO dto);
    UserRequestDTO toRequestDTO(User model);
    UserResponseDTO toResponseDTO(User model);
}
