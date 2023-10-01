package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "cate_tbl")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    //TESTING PUSH MASTER BRANCH
    @Column(name = "cate_name", length = 45)
    private String name;


    @OneToMany(mappedBy = "cate_id",cascade = CascadeType.PERSIST, orphanRemoval = true)
    @JsonIgnore
    private  Set<Idea_cate> idea_cate = new HashSet<>();

}
