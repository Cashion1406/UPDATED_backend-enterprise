package com.example.demo.DTO.Song;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SongUpdateRequest {

    private long id;

    private String name;


    private String imageurl;

    private String attached_path;
    private String client_id;
    private Long topic_id;

    private List<Long> categories;

    private Boolean isAnonymous;
}
