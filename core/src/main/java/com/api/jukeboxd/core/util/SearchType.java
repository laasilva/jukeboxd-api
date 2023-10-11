package com.api.jukeboxd.core.util;

import lombok.Getter;

@Getter
public enum SearchType {
    ARTIST("artist"),
    ALBUM("album"),
    TRACK("track");

    private final String value;

    SearchType(String value) {
        this.value = value;
    }

}
