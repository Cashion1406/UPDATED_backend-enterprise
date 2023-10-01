package com.example.demo.model;

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
@Table(name = "genre_tbl")
public class Genre {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "genre_name")
    private String name;

    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "genre", orphanRemoval = true)
    @JsonIncludeProperties({"id", "name","url","imageurl"})
    private List<Song> songs;
}
