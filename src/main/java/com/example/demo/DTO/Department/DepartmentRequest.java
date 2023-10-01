package com.example.demo.DTO.Department;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentRequest {

    private long id;

    private String name;

    private String department_info;

    private Boolean isDeleted;

}
