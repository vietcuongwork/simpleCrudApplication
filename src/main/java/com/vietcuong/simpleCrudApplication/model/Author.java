package com.vietcuong.simpleCrudApplication.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "author")
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "author_id")
    private Integer authorId;

    @Column(name = "author_name")
    private String authorName;

    @JsonIgnore
    @ManyToMany(mappedBy = "authors", fetch = FetchType.EAGER) // Ensure the other side is also eagerly fetched
    private Set<Book> books = new HashSet<>();
}
