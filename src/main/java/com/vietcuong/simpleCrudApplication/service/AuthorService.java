package com.vietcuong.simpleCrudApplication.service;

import com.vietcuong.simpleCrudApplication.model.Author;
import com.vietcuong.simpleCrudApplication.repository.AuthorRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional
    public Author findOrCreateAuthorByName(String authorName) {
        Author author = findByAuthorName(authorName);
        if (author == null) {
            author = new Author();
            author.setAuthorName(authorName);
            author = authorRepository.save(author);
        }
        return author;
    }

    public List<Author> getAllAuthors(){
        return authorRepository.findAll();
    }
}
