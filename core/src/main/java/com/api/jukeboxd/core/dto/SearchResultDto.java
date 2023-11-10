package com.api.jukeboxd.core.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SearchResultDto<T> {
    String href;
    List<T> items;
}
