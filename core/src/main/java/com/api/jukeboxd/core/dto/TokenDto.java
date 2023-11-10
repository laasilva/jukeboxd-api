package com.api.jukeboxd.core.dto;

import lombok.*;

@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TokenDto {
    String access_token;
    String token_type;
    String expires_in;
}