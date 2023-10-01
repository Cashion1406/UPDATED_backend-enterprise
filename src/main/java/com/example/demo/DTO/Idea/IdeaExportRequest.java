package com.example.demo.DTO.Idea;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class IdeaExportRequest {

    private long id;

    private String name;

    private String body;

    private String date;

    private String modify_date;

    private String attached_path;

    private String topic_name;

    private String client_name;

    private String category_name;

    private String client_email;

    private String client_id;

}
