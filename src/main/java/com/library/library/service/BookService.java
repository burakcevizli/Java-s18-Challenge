package com.library.library.service;

import com.library.library.entity.Author;
import com.library.library.entity.Book;



import java.util.List;

public interface BookService {
    List<Book> findAll();
    Book findById(int id);
    Book save(Book book);
    Book saveByCategoryId(Book book , int id);
    Book saveByAuthorId(Book book , int id);
    Book saveByCategoryAndAuthorId(Book book , int categoryId , int authorId);
    Book delete(int id);
}
