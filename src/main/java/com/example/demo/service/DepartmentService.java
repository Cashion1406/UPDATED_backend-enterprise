package com.example.demo.service;

import com.example.demo.DTO.DeleteResponse;
import com.example.demo.DTO.Department.DepartmentRequest;
import com.example.demo.model.Department;
import com.example.demo.repo.DepartmentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class DepartmentService {
    @Autowired
    private DepartmentRepo departmentRepo;


    public Department createDepartment(Department department){

        if (department.getIsDeleted()==null){

            department.setIsDeleted(false);
        }
        return departmentRepo.save(department);

    }

    public List<Department> getAllDepartment (){

        //use departmentRepo.findAll() to get all current department
        return departmentRepo.findByisDeletedFalse();
    }

    public DeleteResponse deletedDepartment (long id){


        departmentRepo.deleteById(id);
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());

        return new DeleteResponse("Deleted "+ departmentRepo.departmentnamebyid(id),timestamp, true);

    }


    public Department updateDepartment(DepartmentRequest departmentRequest) {

        Department existDepartment = departmentRepo.findById(departmentRequest.getId()).get();

        existDepartment.setDepartment_info(departmentRequest.getDepartment_info());
        existDepartment.setName(departmentRequest.getName());

        existDepartment.setDepartment_info(departmentRequest.getDepartment_info());
        return departmentRepo.save(existDepartment);
    }
}
