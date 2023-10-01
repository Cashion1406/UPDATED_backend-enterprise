package com.example.demo.controller;


import com.example.demo.DTO.DeleteResponse;
import com.example.demo.model.Category;
import com.example.demo.service.CateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cate")
@CrossOrigin(origins = "http://localhost:3000")
public class CateCotroller {
    @Autowired
    private CateService cateService;

    @GetMapping()
    public List<Category> getAllCate() {

        return cateService.getAllCate();
    }

    @PostMapping("/create")
        public Category createCate(@RequestBody Category category ) {
        return cateService.saveCate(category);
    }


    @PutMapping("/update")
    public Category updateCate(@RequestBody Category category){

        return cateService.updateCate(category);
    }

    @DeleteMapping("/delete/{id}")
    public DeleteResponse deleteCate(@PathVariable long id){

        return cateService.deleteCate(id);
    }
}
