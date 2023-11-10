package com.api.jukeboxd.datasource.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "album", schema = "music")
public class AlbumEntity {
    @Id
    private String id;
    @Column
    private String name;
    @Column
    private String url;
    @Column
    private String albumType;
    @Column
    private Integer totalTracks;
    @Column
    @Temporal(TemporalType.DATE)
    private Date releaseDate;
    @Column
    private String[] restrictions;
    @Column
    private String[] copyrights;
    @Column
    private String label;
    @Column
    private Integer popularity;
    @Column
    private String[] genres;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "album_image", schema = "music",
            joinColumns = {@JoinColumn(name = "album", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "image", referencedColumnName = "id")}
    )
    @Fetch(value = FetchMode.SUBSELECT)
    @JsonIgnoreProperties({"artist", "album"})
    private List<ImageEntity> images;
    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name = "album_artist", schema = "music",
            joinColumns = {@JoinColumn(name = "album", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "artist", referencedColumnName = "id")}
    )
    private List<ArtistEntity> artists;
}
