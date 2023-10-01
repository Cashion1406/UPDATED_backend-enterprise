package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "song_playlist_tbl")
public class Song_Playlist {

    @Id
    @ManyToOne(cascade =  CascadeType.ALL)
    @JoinColumn(name = "playlist_id",referencedColumnName = "id")
    @JsonBackReference
    private Playlist playlist;
    @Id
    @ManyToOne(cascade =  CascadeType.ALL)
    @JoinColumn(name = "song_id",referencedColumnName = "id")
    @JsonIncludeProperties({"id","name"})
    private Song song;


}
