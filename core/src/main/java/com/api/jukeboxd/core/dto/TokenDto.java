package com.api.jukeboxd.core.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class TokenDto {
    @JsonProperty
    String access_token;
    @JsonProperty
    String token_type;
    @JsonProperty
    String expires_in;

    public TokenDto(@JsonProperty String access_token,
                    @JsonProperty String token_type,
                    @JsonProperty String expires_in) {
        this.access_token = access_token;
        this.token_type = token_type;
        this.expires_in = expires_in;
    }
}