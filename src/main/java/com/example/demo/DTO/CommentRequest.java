package com.example.demo.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CommentRequest {


    private Long id;
    
    private String comment;
    
    private Long idea_id;

    private String client_id;

    private Boolean isAnonymous;
}
