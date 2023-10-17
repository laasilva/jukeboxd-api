package com.api.jukeboxd.core.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {
    Integer id;
    String username;
    String password;
    String email;
    UserInfo info;
}
