package com.api.jukeboxd.core.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class SearchQueryDto {
    String q;
    String type;
}
