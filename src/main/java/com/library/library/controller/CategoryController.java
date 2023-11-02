package com.library.library.controller;

import com.library.library.dto.CategoryResponse;
import com.library.library.entity.Category;
import com.library.library.service.CategoryService;
import com.library.library.utils.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

    private CategoryService categoryService;
    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/")
    public List<CategoryResponse> findAll(){
        return Converter.categoryListConverter(categoryService.findAll()) ;
    }

    @GetMapping("/{id}")
    public CategoryResponse getOne(@PathVariable int id){
        return Converter.categoryConverter(categoryService.findById(id)) ;
    }

    @PostMapping("/")
    public CategoryResponse save(@RequestBody Category category){
        return  Converter.categoryConverter(categoryService.save(category))  ;
    }
    @PostMapping("/{bookid}")
    public CategoryResponse saveByBookId(@RequestBody Category category , @PathVariable int bookid){
        return  Converter.categoryConverter(categoryService.saveByBookId(category,bookid))  ;
    }

    @DeleteMapping("/{id}")
    public CategoryResponse delete(@PathVariable int id){
        return  Converter.categoryConverter(categoryService.delete(id))  ;
    }
}
