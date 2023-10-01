package com.example.demo.repo;

import com.example.demo.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CateRepo extends JpaRepository<Category, Long> {


    @Query("select c.name from Category c where c.id = :id")
    String getCateName (@Param("id") Long id);
}
