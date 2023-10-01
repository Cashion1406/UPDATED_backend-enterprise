package com.example.demo.DTO.Topic;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TopicRequest {

    private long id;
    private String name;

    private String topic_closure_date;

    private String final_closure_date;

    private Boolean isDeleted;

    private String imageURL;

    private String description;
}
