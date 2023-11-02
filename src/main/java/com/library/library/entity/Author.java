package com.library.library.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "author",schema = "fsweb")
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "author")
    private List<Book> bookList;

    public void addBook(Book book){
        if(bookList ==null){
            bookList = new ArrayList<>();
        }
        bookList.add(book);
    }
}
