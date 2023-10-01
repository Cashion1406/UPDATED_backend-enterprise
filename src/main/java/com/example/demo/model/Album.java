package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIncludeProperties;
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
@Table(name = "album_tbl")
public class Album {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name = "album_name")
    private String name;
    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "album")
    @JsonIncludeProperties({"id", "name", "url", "imageurl"})
    private List<Song> songs;
    @Column(name = "album_pic")
    private String imageurl;

//    @ManyToOne(cascade = CascadeType.PERSIST)
//    @JoinColumn(name = "playlist_id", referencedColumnName = "id")
//    @JsonIncludeProperties({"id", "name"})
//    private Playlist playlist;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "client_id", referencedColumnName = "id")
    @JsonIncludeProperties({"firstname", "lastname", "id"})
    private Client client;


    @Column(name = "create_date")
    private String date;

//    @OneToMany(mappedBy = "song",orphanRemoval = true ,cascade = CascadeType.PERSIST)
//    @JsonIgnore
//    private List<Song_Playlist> song_playlists ;

}
