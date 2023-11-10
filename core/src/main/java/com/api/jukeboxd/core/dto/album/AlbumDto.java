package com.api.jukeboxd.core.dto.album;

import com.api.jukeboxd.core.util.ExternalUrlsDeserializer;
import com.api.jukeboxd.core.dto.ImageDto;
import com.api.jukeboxd.core.dto.artist.ArtistDto;
import com.api.jukeboxd.core.util.ReleaseDateDeserializer;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import lombok.*;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AlbumDto implements Serializable {
    private static final long serialVersionUID = 1L;

    String id;
    String name;
    String type;
    @SerializedName(value = "album_type")
    String albumType;
    @SerializedName(value = "total_tracks")
    Integer totalTracks;
    @SerializedName(value = "release_date")
    Date releaseDate;
    Integer popularity;
    @SerializedName(value = "external_urls")
    @JsonAdapter(ExternalUrlsDeserializer.class)
    String url;
    String[] genres;
    String[] copyrights;
    String[] restrictions;
    List<ImageDto> images;
    List<ArtistDto> artists;
}
