package com.example.demo.DTO.Topic;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TopicWithMostFollowers {

    private long topic_id;

    private String topic_name;

    private String image_url;

    private long numOfFollowers;

    private String idea_closure_date;

    private String final_closure_date;
}
