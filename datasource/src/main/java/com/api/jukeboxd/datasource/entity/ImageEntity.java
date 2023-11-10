package com.api.jukeboxd.datasource.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "image", schema = "music")
public class ImageEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    private String url;
    @Column
    private Integer width;
    @Column
    private Integer height;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinTable(name = "artist_image", schema = "music",
        joinColumns = {@JoinColumn(name = "image", referencedColumnName = "id")},
        inverseJoinColumns = {
                @JoinColumn(name = "artist", insertable = false,
                updatable = false, referencedColumnName = "id")
        }
    )
    private ArtistEntity artist;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinTable(name = "album_image", schema = "music",
            joinColumns = {@JoinColumn(name = "image", referencedColumnName = "id")},
            inverseJoinColumns = {
                    @JoinColumn(name = "album", insertable = false,
                            updatable = false, referencedColumnName = "id")
            }
    )
    private AlbumEntity album;
}
