package com.vietcuong.simpleCrudApplication.repository;

import com.vietcuong.simpleCrudApplication.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
    Optional<Book> findByTitle(String title);

    @Query("SELECT b FROM Book b JOIN b.authors a WHERE a.authorId = :authorId")
    List<Book> findBooksByAuthorId(
            @Param("authorId")
            Integer authorId);

    @Query("SELECT b FROM Book b JOIN b.language l WHERE l.languageName = :languageName")
    List<Book> findBooksByLanguageName(
            @Param("languageName")
            String languageName);
}
