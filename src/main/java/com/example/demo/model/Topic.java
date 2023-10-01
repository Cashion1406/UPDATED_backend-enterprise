package com.example.demo.model;


import com.example.demo.DTO.Topic.TopicWithMostFollowers;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@NamedNativeQuery(name = "Topic.top7followers",
        query = "select t.id as topic_id , t.topic_name as topic_name, t.image_url as image_url,count(*) as numOfFollowers, t.topic_closure_date as idea_closure_date, t.topic_final_date as final_closure_date from follow_tbl f inner join topic_tbl t on t.id =f.topic_id group by t.id order by count(f.topic_id) desc limit 7",
        resultSetMapping = "Mapping.MostFollowedTopic"
)

@SqlResultSetMapping(name = "Mapping.MostFollowedTopic",classes = @ConstructorResult(targetClass = TopicWithMostFollowers.class, columns = {
        @ColumnResult(name = "topic_id"),
        @ColumnResult(name = "topic_name"),
        @ColumnResult(name = "image_url"),
        @ColumnResult(name = "numOfFollowers"),
        @ColumnResult(name = "idea_closure_date"),
        @ColumnResult(name = "final_closure_date"),
}))

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "topic_tbl")
public class Topic {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "topic_name", length = 45)
    private String name;

    @Column(name = "topic_closure_date", length = 50)
    private String topic_closure_date;

    @Column(name = "topic_final_date", length = 50)
    private String final_closure_date;

    @Column(name ="modify_date")
    private String modifyDate;
    @Column(name ="isDeleted")
    private Boolean isDeleted;

    @Column(name = "image_url")
    private String imageURL;

    @Column(name = "topic_description")
    private String description;

    @OneToMany(mappedBy = "topic", fetch =  FetchType.LAZY,cascade = CascadeType.ALL)
    @JsonManagedReference(value = "idea_topic")
    private Set<Idea> ideas = new HashSet<>();

    @OneToMany(mappedBy = "topic_id",orphanRemoval = true, cascade = CascadeType.PERSIST)
    @JsonManagedReference
    @JsonIgnore
    private Set<Client_Topic> clientTopics = new HashSet<>();
}
