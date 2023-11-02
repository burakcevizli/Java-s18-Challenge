package com.library.library.service;

import com.library.library.dao.BookRepository;
import com.library.library.dao.CategoryRepository;
import com.library.library.entity.Book;
import com.library.library.entity.Category;
import com.library.library.exceptions.LibraryException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService{

    private CategoryRepository categoryRepository;
    private BookRepository bookRepository;
    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository,BookRepository bookRepository) {
        this.categoryRepository = categoryRepository;
        this.bookRepository=bookRepository;
    }

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Category findById(int id) {
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if(optionalCategory.isPresent()){
            return optionalCategory.get();
        }
        throw new LibraryException("Given id is not exist", HttpStatus.NOT_FOUND);
    }

    @Override
    public Category save(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public Category saveByBookId(Category category, int id) {
        Optional<Book> optionalBook = bookRepository.findById(id);
        if(optionalBook.isPresent()){
            category.addBook(optionalBook.get());
            optionalBook.get().setCategory(category);
            return categoryRepository.save(category);
        }
        throw new LibraryException("Given id is not exist", HttpStatus.NOT_FOUND);

    }

    @Override
    public Category delete(int id) {
        Category category = findById(id);
        categoryRepository.delete(category);
        return category;
    }
}
