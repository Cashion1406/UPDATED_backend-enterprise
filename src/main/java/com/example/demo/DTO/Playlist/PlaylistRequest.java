package com.example.demo.DTO.Playlist;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PlaylistRequest {
    private long id;

    private String name;
    private String imageurl;

    private String client_id;


}