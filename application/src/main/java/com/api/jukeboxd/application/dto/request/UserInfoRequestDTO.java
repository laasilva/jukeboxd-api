package com.api.jukeboxd.application.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserInfoRequestDTO {
    @JsonProperty
    String name;
    @JsonProperty
    String description;
}
