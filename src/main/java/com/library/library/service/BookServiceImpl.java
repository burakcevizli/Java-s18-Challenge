package com.library.library.service;

import com.library.library.dao.AuthorRepository;
import com.library.library.dao.BookRepository;
import com.library.library.dao.CategoryRepository;
import com.library.library.entity.Author;
import com.library.library.entity.Book;
import com.library.library.entity.Category;
import com.library.library.exceptions.LibraryException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    private BookRepository bookRepository;
    private AuthorRepository authorRepository;
    private CategoryRepository categoryRepository;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository, AuthorRepository authorRepository, CategoryRepository categoryRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public Book findById(int id) {
        Optional<Book> bookOptional = bookRepository.findById(id);
        if(bookOptional.isPresent()){
            return bookOptional.get();
        }
        throw new LibraryException("Given id is not exist", HttpStatus.NOT_FOUND);
    }

    @Override
    public Book save(Book book) {
        return bookRepository.save(book);
    }


    @Override
    public Book saveByCategoryId(Book book, int id) {
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if(optionalCategory.isPresent()){
            Category findCategory = optionalCategory.get();
            findCategory.addBook(book);
            book.setCategory(findCategory);
            return bookRepository.save(book);
        }
        throw new LibraryException("Given id is not exist", HttpStatus.NOT_FOUND);
    }

    @Override
    public Book saveByAuthorId(Book book, int id) {
        Optional<Author> optionalAuthor = authorRepository.findById(id);
        if(optionalAuthor.isPresent()){
            Author findAuthor = optionalAuthor.get();
            findAuthor.addBook(book);
            book.setAuthor(findAuthor);
            return bookRepository.save(book);
        }
        throw new LibraryException("Given id's are not exist", HttpStatus.NOT_FOUND);
    }

    @Override
    public Book saveByCategoryAndAuthorId(Book book, int categoryId, int authorId) {
        Optional<Category> optionalCategory = categoryRepository.findById(categoryId);
        Optional<Author> optionalAuthor = authorRepository.findById(authorId);
        if(optionalAuthor.isPresent() && optionalCategory.isPresent()){
            Category foundCategory = optionalCategory.get();
            Author foundAuthor = optionalAuthor.get();
            foundCategory.addBook(book);
            book.setCategory(foundCategory);
            foundAuthor.addBook(book);
            book.setAuthor(foundAuthor);
            return bookRepository.save(book);
        }
        throw new LibraryException("Given id's are not exist", HttpStatus.NOT_FOUND);
    }

    @Override
    public Book delete(int id) {
        Book book = findById(id);
        bookRepository.delete(book);
        return book;
    }
}
