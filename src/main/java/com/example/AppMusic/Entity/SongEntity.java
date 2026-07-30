package com.example.AppMusic.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.Date;

@Entity
@Table(name = "songs")
@Getter
@Setter
public class SongEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "song_name", nullable = false)
    private String songName;

    @Column(name = "song_time")
    private String songTime;

    @Column(name = "is_actv", nullable = false)
    private Boolean isActv = true;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "cdate", updatable = false)
    private Date cdate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "udate")
    private Date udate;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id")
    private CategoryEntity category;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "artist_id")
    private ArtistEntity artist;

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