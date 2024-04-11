package com.mv.recommender.dbrelationships.manytomany.bi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    CategoryService service;

    @GetMapping
    public List<Category> allCategories() {
        return service.allCategories();
    }

    @GetMapping("/{id}")
    public Category getCategory(@PathVariable int id) {
        return service.getCategory(id);
    }

    @PostMapping
    public Category addCategory(@RequestBody Category category) {
        return service.addCategory(category);
    }

    @DeleteMapping("/{id}")
    public void deleteCategory(@PathVariable int id) {
        service.deleteCategory(id);
    }
}