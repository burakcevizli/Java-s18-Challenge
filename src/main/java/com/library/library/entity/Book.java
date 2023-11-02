package com.library.library.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "book",schema = "fsweb")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @JoinColumn(name = "category_id")
    @ManyToOne(cascade = {CascadeType.MERGE , CascadeType.DETACH,CascadeType.PERSIST,CascadeType.REFRESH})
    private Category category;

    @JoinColumn(name = "author_id")
    @ManyToOne(cascade = {CascadeType.MERGE , CascadeType.DETACH,CascadeType.PERSIST,CascadeType.REFRESH})
    private Author author;
}
