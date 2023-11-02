package com.library.library.service;


import com.library.library.entity.Category;

import java.util.List;

public interface CategoryService {
    List<Category> findAll();
    Category findById(int id);
    Category save(Category category);
    Category saveByBookId(Category category , int id);
    Category delete(int id);
}
