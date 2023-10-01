package com.example.demo.controller;



import com.example.demo.DTO.DeleteResponse;
import com.example.demo.DTO.Department.DepartmentRequest;
import com.example.demo.model.Department;
import com.example.demo.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/department")
@CrossOrigin(origins = "http://localhost:3000")

public class DepartmentController {
    @Autowired
    private DepartmentService departmentService;

    @GetMapping()
    public List<Department> getallDeparment(){
        return  departmentService.getAllDepartment();
    }

    @PostMapping("/create")
    public  Department createDepartment(@RequestBody Department department){

        return departmentService.createDepartment(department);
    }

    @DeleteMapping("/delete/{id}")
    public DeleteResponse deleteDepartment(@PathVariable long id){

        return departmentService.deletedDepartment(id);
    }

    @PutMapping("/update")
    public Department updateDepartment(@RequestBody DepartmentRequest departmente){

        return departmentService.updateDepartment(departmente);
    }


}
