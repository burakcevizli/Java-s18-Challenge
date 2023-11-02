package com.library.library.controller;

import com.library.library.dto.AuthorResponse;
import com.library.library.entity.Author;
import com.library.library.exceptions.LibraryException;
import com.library.library.service.AuthorService;
import com.library.library.utils.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/author")
public class AuthorController {

    private AuthorService authorService;
    @Autowired
    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping("/")
    public List<AuthorResponse> getAll(){
       return Converter.authorResponsesListConverter(authorService.findAll());
    }
    @GetMapping("/{id}")
    public AuthorResponse getOne(@PathVariable int id){
        return Converter.authorConverter( authorService.findById(id));
    }

    @PostMapping("/")
    public AuthorResponse save(@RequestBody Author author){
        return Converter.authorConverter(authorService.save(author)) ;
    }

    @PostMapping("/{bookId}")
    public AuthorResponse saveByBookId(@PathVariable int bookId, @RequestBody Author author){
        return Converter.authorConverter(authorService.saveByBookId(author,bookId)) ;
    }

    @DeleteMapping("/{id}")
    public AuthorResponse delete(@PathVariable int id){
        return  Converter.authorConverter( authorService.delete(id));
    }
}
