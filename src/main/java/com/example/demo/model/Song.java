package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.config.SmartInstantiationAwareBeanPostProcessor;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "song_tbl")
public class Song {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "song_url")
    private String url;

    @Column(name = "song_name")
    private String name;

    @Column(name = "song_pic")
    private String imageurl;

//    @ManyToOne(cascade = CascadeType.PERSIST)
//    @JoinColumn(name = "playlist_id", referencedColumnName = "id")
//    @JsonIncludeProperties({"id", "name"})
//    private Playlist playlist;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "client_id", referencedColumnName = "id")
    @JsonIncludeProperties({"firstname", "lastname", "id"})
    private Client client;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "album_id", referencedColumnName = "id")
    @JsonIncludeProperties({"id", "name"})
    private Album album;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "genre_id", referencedColumnName = "id")
    @JsonIncludeProperties("name")
    private Genre genre;


    @ManyToMany(mappedBy = "songs", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JsonIncludeProperties({"id", "name"})
    private List<Playlist> playlists;

    @Column(name = "create_date")
    private String date;

//    @OneToMany(mappedBy = "song",orphanRemoval = true ,cascade = CascadeType.PERSIST)
//    @JsonIgnore
//    private List<Song_Playlist> song_playlists ;

}
