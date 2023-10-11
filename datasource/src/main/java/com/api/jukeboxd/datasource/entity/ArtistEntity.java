package com.api.jukeboxd.datasource.entity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "artist", schema = "music")
public class ArtistEntity {
    @Id
    private String id;
    @Column
    private String name;
    @Column
    private Integer popularity;
    @Column
    private String[] genres;
    @Column
    private String href;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "artist_image", schema = "music",
            joinColumns = {@JoinColumn(name = "artist", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "image", referencedColumnName = "id")}
    )
    @Fetch(value = FetchMode.SUBSELECT)
    @JsonIgnoreProperties("artist")
    private List<ImageEntity> images;
}
