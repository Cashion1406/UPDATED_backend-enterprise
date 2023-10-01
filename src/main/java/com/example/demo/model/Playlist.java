package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "playlist_tbl")
public class Playlist {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;


    @Column(name = "playlist_name")
    private String name;

    @Column(name = "playlist_url")
    private String imageurl;

//    @OneToMany(mappedBy = "playlist", cascade = CascadeType.PERSIST, orphanRemoval = true)
//    @JsonIncludeProperties({"id", "name", "url","imageurl","clients"})
//    private List<Song> songs;


    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "client_id", referencedColumnName = "id")
    @JsonIncludeProperties({"firstname", "lastname", "id"})
    private Client client;


    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinTable(name = "song_playlist", joinColumns = @JoinColumn(name = "playlist_id"), inverseJoinColumns = @JoinColumn(name = "song_id"))
    @JsonIncludeProperties({"id","name"})
    private List<Song> songs;

//    @OneToMany(mappedBy = "playlist",orphanRemoval = true ,cascade = CascadeType.PERSIST)
//    private List<Song_Playlist> song_playlists ;

    @Column(name = "create_date")
    private String date;
}
