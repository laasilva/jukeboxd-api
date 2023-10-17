package com.api.jukeboxd.application.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserRequestDTO {
    @JsonProperty
    String username;
    @JsonProperty
    String password;
    @JsonProperty
    String email;
    @JsonProperty
    UserInfoRequestDTO info;

}
