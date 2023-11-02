package com.library.library.service;

import com.library.library.dao.AuthorRepository;
import com.library.library.dao.BookRepository;
import com.library.library.entity.Author;
import com.library.library.entity.Book;
import com.library.library.exceptions.LibraryException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorServiceImpl implements AuthorService{

    private AuthorRepository authorRepository;
    private BookRepository bookRepository;
    @Autowired
    public AuthorServiceImpl(AuthorRepository authorRepository,BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    public List<Author> findAll() {

        return authorRepository.findAll();
    }

    @Override
    public Author findById(int id) {
        Optional<Author> optionalAuthor = authorRepository.findById(id);
        if(optionalAuthor.isPresent()){
           return optionalAuthor.get();
        }
        throw new LibraryException("Given id is not exist", HttpStatus.NOT_FOUND);
    }

    @Override
    public Author save(Author author) {
        return authorRepository.save(author);
    }

    @Override
    public Author saveByBookId(Author author, int id) {
       Optional<Book> optionalBook = bookRepository.findById(id);
       if(optionalBook.isPresent()){
           author.addBook(optionalBook.get());
           optionalBook.get().setAuthor(author);
           return authorRepository.save(author);
       }
       throw new LibraryException("Given id is not exist", HttpStatus.NOT_FOUND);
    }

    @Override
    public Author delete(int id) {
        Author author = findById(id);
        authorRepository.delete(author);
        return author;
    }
}
