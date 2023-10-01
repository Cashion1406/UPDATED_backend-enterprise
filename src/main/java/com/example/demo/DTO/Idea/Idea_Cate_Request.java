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
public class Idea_Cate_Request {

    private List<Long> categories;
    private long idea_id;
}
