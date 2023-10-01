package com.example.demo.repo;


import com.example.demo.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartmentRepo extends JpaRepository<Department, Long> {

    @Query("select d.name from Department d where d.id=:id")
    String departmentnamebyid(@Param("id") Long id);

    List<Department> findByisDeletedFalse();


}
