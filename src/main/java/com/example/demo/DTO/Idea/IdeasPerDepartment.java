package com.example.demo.DTO.Idea;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class IdeasPerDepartment {
    private long department_id;

    private String department_name;

    private String department_info;

    private long numberOfIdeas;
}
