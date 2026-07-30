package com.example.AppMusic.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "artists")
@Getter
@Setter
public class ArtistEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "artist_name")
    private String artistName;

    @Column(name = "hometown")
    private String hometown;

    @Column(name = "price")
    private Double price;

    @Column(name = "is_actv")
    private Boolean isActv = true;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "cdate", updatable = false)
    private Date cdate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "udate")
    private Date udate;


    @OneToMany(mappedBy = "artist", cascade = CascadeType.ALL)
    private List<SongEntity> songs;

    @PrePersist
    protected void onCreate() {
        cdate = new Date();
        udate = new Date();
    }

    @PreUpdate
    protected void onUpdate() {
        udate = new Date();
    }
}
