package com.library.library.controller;

import com.library.library.dto.BookResponse;
import com.library.library.entity.Author;
import com.library.library.entity.Book;
import com.library.library.service.BookService;
import com.library.library.utils.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {
    private BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/")
    public List<BookResponse> getAll(){
        return Converter.bookResponseListConverter(bookService.findAll()) ;
    }

    @GetMapping("/{id}")
    public BookResponse getOne(@PathVariable int id){
        return Converter.bookConverter(bookService.findById(id)) ;
    }

    @PostMapping("/")
    public BookResponse save(@RequestBody Book book){
        return Converter.bookConverter( bookService.save(book));
    }

    @PostMapping("/{categoryId}/{authorId}")
    public BookResponse saveByCategoryAuthorId(@RequestBody Book book , @PathVariable int categoryId ,@PathVariable int authorId){
        return Converter.bookConverter(bookService.saveByCategoryAndAuthorId(book ,categoryId,authorId)) ;
    }

    @DeleteMapping("/{id}")
    public BookResponse delete(@PathVariable int id){
        return Converter.bookConverter(bookService.delete(id)) ;
    }
}
