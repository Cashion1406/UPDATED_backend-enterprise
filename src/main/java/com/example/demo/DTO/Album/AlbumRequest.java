package com.example.demo.DTO.Album;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AlbumRequest {
    private long id;

    private String name;
    private String imageurl;

    private String client_id;


}