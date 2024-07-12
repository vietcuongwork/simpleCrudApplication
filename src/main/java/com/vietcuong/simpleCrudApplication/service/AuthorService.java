package com.vietcuong.simpleCrudApplication.service;

import com.vietcuong.simpleCrudApplication.model.Author;
import com.vietcuong.simpleCrudApplication.repository.AuthorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService {
    private final AuthorRepository authorRepository;

    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public Author findByAuthorName(String authorName) {
        return authorRepository.findByAuthorName(authorName);
    }

    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }
}
