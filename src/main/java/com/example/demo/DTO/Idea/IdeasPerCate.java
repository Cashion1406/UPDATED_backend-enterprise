package com.example.demo.DTO.Idea;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class IdeasPerCate {
    private long cate_id;

    private String cate_name;

    private long numOfIdeas;
}
