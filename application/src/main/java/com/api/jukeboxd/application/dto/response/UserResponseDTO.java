package com.api.jukeboxd.application.dto.response;

import com.api.jukeboxd.application.dto.request.UserInfoRequestDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserResponseDTO {
    @JsonProperty
    String username;
    @JsonIgnore
    String password;
    @JsonProperty
    String email;
    @JsonProperty
    UserInfoRequestDTO info;
}
