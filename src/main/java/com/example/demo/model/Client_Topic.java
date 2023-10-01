package com.example.demo.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIncludeProperties;
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
@Table(name = "follow_tbl")
public class Client_Topic {


    @Id
    @ManyToOne(cascade =  CascadeType.ALL)
    @JoinColumn(name = "client_id",referencedColumnName = "id")
    @JsonIncludeProperties({"lastname","firstname","client_info"})
    private Client client_id;

    @Id
    @ManyToOne(cascade =  CascadeType.ALL)
    @JoinColumn(name = "topic_id",referencedColumnName = "id")
    @JsonBackReference
    private Topic topic_id;
}

