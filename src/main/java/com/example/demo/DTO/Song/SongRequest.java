package com.example.demo.DTO.Song;

import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SongRequest {
    private long id;

    private String url;

    private String name;
    private String imageurl;

    private String client_id;
    private long playlist_id;

    private long genre_id;
    private long album_id;

}