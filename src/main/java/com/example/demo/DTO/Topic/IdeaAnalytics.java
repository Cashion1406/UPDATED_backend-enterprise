package com.example.demo.DTO.Topic;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class IdeaAnalytics {
    private String topic_name;
    private String cate_name;

    private String idea_title;

    private String department_name;
}
