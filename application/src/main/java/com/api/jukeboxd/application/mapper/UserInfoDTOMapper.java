package com.api.jukeboxd.application.mapper;

import com.api.jukeboxd.application.dto.request.UserInfoRequestDTO;
import com.api.jukeboxd.core.model.UserInfo;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserInfoDTOMapper {
    UserInfo toModel(UserInfoRequestDTO dto);
    UserInfoRequestDTO toDTO(UserInfo dto);
}
