package com.api.jukeboxd.core.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Paginated<T> {
    int page;
    int size;
    Long total;
    T data;
}
