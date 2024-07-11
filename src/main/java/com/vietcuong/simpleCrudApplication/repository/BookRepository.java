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
    @Query("SELECT b FROM Book b LEFT JOIN FETCH b.publisher LEFT JOIN FETCH b.language LEFT JOIN FETCH b.authors")
    List<Book> getAllBooks();
    @Query("SELECT b FROM Book b " +
            "LEFT JOIN FETCH b.language l " +
            "LEFT JOIN FETCH b.publisher p " +
            "LEFT JOIN FETCH b.authors a " +
            "WHERE b.bookId = :bookId")
    Optional<Book> findBookById(@Param("bookId") Integer bookId);
}
