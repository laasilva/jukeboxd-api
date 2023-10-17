package com.api.jukeboxd.core.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserInfo {
    Integer id;
    String name;
    String description;
}
