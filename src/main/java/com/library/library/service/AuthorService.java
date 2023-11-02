package com.library.library.service;

import com.library.library.entity.Author;

import java.util.List;

public interface AuthorService {
    List<Author> findAll();
    Author findById(int id);
    Author save(Author author);
    Author saveByBookId(Author author , int id);
    Author delete(int id);

}
