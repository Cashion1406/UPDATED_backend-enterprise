package com.example.demo.DTO.Idea;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class IdeaRequest {
    private long id;

    private String name;

    private String body;


    private String modify_date;

    private String attached_path;
    private String client_id;
    private Long topic_id;

    private List<Long> categories;

    private Boolean isAnonymous;
}
