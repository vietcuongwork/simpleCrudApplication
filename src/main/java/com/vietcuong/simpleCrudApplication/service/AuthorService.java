package com.vietcuong.simpleCrudApplication.service;

import com.vietcuong.simpleCrudApplication.exception.AuthorNotExistException;
import com.vietcuong.simpleCrudApplication.model.Author;
import com.vietcuong.simpleCrudApplication.repository.AuthorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorService {
    private final AuthorRepository authorRepository;

    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

/*    public Optional<Author> findByAuthorName(String authorName) throws AuthorNotExistException {
        Optional<Author> author = authorRepository.findByAuthorName(authorName);
        if(author.isEmpty()) {
            throw new AuthorNotExistException();
        }
        return author;
    }*/

    public Optional<Author> findByAuthorId(Integer id) throws AuthorNotExistException{
        Optional<Author> author = authorRepository.findById(id);
        if(author.isEmpty()) {
            throw new AuthorNotExistException();
        }
        return author;
    }

    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }
}
