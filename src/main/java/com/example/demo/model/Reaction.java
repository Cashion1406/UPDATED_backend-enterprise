package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
@Table(name = "reaction_tbl")
public class Reaction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

//    @ManyToOne(cascade = CascadeType.PERSIST)
//    @JoinColumn(name = "client_id", referencedColumnName = "id")
//    @JsonBackReference
//    private Client client;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "idea_id", referencedColumnName = "id")
    @JsonBackReference(value = "idea_reaction")
    private Idea idea;

    @Column(name = "reaction")
    private Boolean reaction;
}
