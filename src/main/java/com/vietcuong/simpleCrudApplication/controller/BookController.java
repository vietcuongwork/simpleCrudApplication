package com.vietcuong.simpleCrudApplication.controller;

import com.vietcuong.simpleCrudApplication.common.ResponseStatus;
import com.vietcuong.simpleCrudApplication.exception.BookExistedException;
import com.vietcuong.simpleCrudApplication.exception.BookNotExistException;
import com.vietcuong.simpleCrudApplication.exception.EmptyDatabaseException;
import com.vietcuong.simpleCrudApplication.model.Author;
import com.vietcuong.simpleCrudApplication.model.Book;
import com.vietcuong.simpleCrudApplication.repository.BookRepository;
import com.vietcuong.simpleCrudApplication.response.BookControllerResponse;
import com.vietcuong.simpleCrudApplication.service.AuthorService;
import com.vietcuong.simpleCrudApplication.service.BookService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class BookController {
    /*    @GetMapping("/helloWorld")
        public String helloWorld() {
            return "Hello World";
        }*/
    private final BookRepository bookRepository;
    private final BookService bookService;
    private final BookControllerResponse bookControllerResponse;
    private final AuthorService authorService;

    public BookController(BookRepository bookRepository, BookService bookService, BookControllerResponse bookControllerResponse, AuthorService authorService) {
        this.bookRepository = bookRepository;
        this.bookService = bookService;
        this.bookControllerResponse = bookControllerResponse;
        this.authorService = authorService;
    }

    @PostMapping("/addBook")
    public BookControllerResponse<?> addBook(
            @RequestBody
            Book requestBook) {
        try {
            bookService.addBook(requestBook);
            return bookControllerResponse.createSuccessResponse("Add successfully");
        } catch (BookExistedException e) {
            return bookControllerResponse.createErrorResponse(ResponseStatus.BookControllerResponse.BOOK_EXISTED, e);
        }
    }

    @GetMapping("/getAllBooks")
    public BookControllerResponse<?> getAllBooks() {
        try {
            BookControllerResponse<List<Book>> response = bookControllerResponse.initializeResponse();
            response.setContent(bookService.getAllBooks());
            return response;
        } catch (EmptyDatabaseException e) {
            return bookControllerResponse.createErrorResponse(ResponseStatus.BookControllerResponse.EMPTY_DATABASE, e);
        }
    }

    @DeleteMapping("/deleteBook/{id}")
    public BookControllerResponse<?> deleteById(
            @PathVariable
            Integer id) {
        try {
            bookService.deleteById(id);
            return bookControllerResponse.createSuccessResponse("Delete successfully");
        } catch (BookNotExistException e) {
            return bookControllerResponse.createErrorResponse(ResponseStatus.BookControllerResponse.BOOK_NOT_EXIST, e);
        }
    }

    @GetMapping("/getBook/{id}")
    public BookControllerResponse<?> getBookById(
            @PathVariable
            Integer id) {

        try {
            BookControllerResponse<Book> response = bookControllerResponse.initializeResponse();
            Optional<Book> book = bookService.getById(id);
            if (book.isPresent()) {
                response.setContent(book.get());
            } else {
                throw new BookNotExistException();
            }
            return response;
        } catch (Exception e) {
            return bookControllerResponse.createErrorResponse(ResponseStatus.BookControllerResponse.BOOK_NOT_EXIST, e);
        }
    }

    @PostMapping("/updateBook/{id}")
    public BookControllerResponse<?> updateBookById(
            @PathVariable
            Integer id,
            @RequestBody
            Book requestBook) {
        try {
            bookService.updateBookById(id, requestBook);
            return bookControllerResponse.createSuccessResponse("Update successfully");
        } catch (BookNotExistException e) {
            return bookControllerResponse.createErrorResponse(ResponseStatus.BookControllerResponse.BOOK_NOT_EXIST, e);
        }
    }


    @GetMapping("allAuthors")
    public BookControllerResponse<?> getAllAuthors() {
        BookControllerResponse<List<Author>> response = bookControllerResponse.initializeResponse();
        response.setContent(authorService.getAllAuthors());
        return response;

    }


}
