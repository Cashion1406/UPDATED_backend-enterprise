package com.example.demo.service;


import com.example.demo.DTO.DeleteResponse;
import com.example.demo.model.Category;
import com.example.demo.repo.CateRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class CateService {

    @Autowired
    private CateRepo cateRepo;

    public Category saveCate(Category category) {

        return cateRepo.save(category);
    }

    public Category updateCate(Category category) {

        Category existcate = cateRepo.findById(category.getId()).get();
        existcate.setName(category.getName());

        return cateRepo.save(category);
    }

    public List<Category> getAllCate() {
        return cateRepo.findAll();
    }

    public DeleteResponse deleteCate(long id) {
        String name= cateRepo.getCateName(id);
        cateRepo.deleteById(id);
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        return  new DeleteResponse("Deleted Category " + name,timestamp,true);
    }


}
