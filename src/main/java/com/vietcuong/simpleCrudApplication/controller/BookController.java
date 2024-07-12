package com.vietcuong.simpleCrudApplication.controller;

import com.vietcuong.simpleCrudApplication.common.ResponseStatus;
import com.vietcuong.simpleCrudApplication.exception.*;
import com.vietcuong.simpleCrudApplication.model.Author;
import com.vietcuong.simpleCrudApplication.model.Book;
import com.vietcuong.simpleCrudApplication.model.Language;
import com.vietcuong.simpleCrudApplication.repository.BookRepository;
import com.vietcuong.simpleCrudApplication.response.BookControllerResponse;
import com.vietcuong.simpleCrudApplication.service.AuthorService;
import com.vietcuong.simpleCrudApplication.service.BookService;
import com.vietcuong.simpleCrudApplication.service.LanguageService;
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
    private final LanguageService languageService;

    public BookController(BookRepository bookRepository, BookService bookService, BookControllerResponse bookControllerResponse, AuthorService authorService, LanguageService languageService) {
        this.bookRepository = bookRepository;
        this.bookService = bookService;
        this.bookControllerResponse = bookControllerResponse;
        this.authorService = authorService;
        this.languageService = languageService;
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

    @GetMapping("/getBooksByAuthor/{id}")
    public BookControllerResponse<?> getBooksByAuthorId(
            @PathVariable
            Integer id) {
        try {
            BookControllerResponse<List<Book>> response = bookControllerResponse.initializeResponse();
            response.setContent(bookService.getByAuthorId(id));
            return response;
        } catch (AuthorNotExistException e) {
            return bookControllerResponse.createErrorResponse(ResponseStatus.BookControllerResponse.AUTHOR_NOT_EXIST, e);
        }
    }

    @GetMapping("/getBooksByLanguage/{language}")
    public BookControllerResponse<?> getBooksByLanguage(
            @PathVariable
            String language) {
        try {
            BookControllerResponse<List<Book>> response = bookControllerResponse.initializeResponse();
            response.setContent(bookService.getByLanguageName(language));
            return response;
        } catch (LanguageNotExistException e) {
            return bookControllerResponse.createErrorResponse(ResponseStatus.BookControllerResponse.LANGUAGE_NOT_EXIST, e);
        }
    }

    @GetMapping("/getAllAuthors")
    public BookControllerResponse<?> getAllAuthors() {
        BookControllerResponse<List<Author>> response = bookControllerResponse.initializeResponse();
        response.setContent(authorService.getAllAuthors());
        return response;
    }

    @GetMapping("/getAllLanguages")
    public BookControllerResponse<?> getAllLanguages() {
        BookControllerResponse<List<Language>> response = bookControllerResponse.initializeResponse();
        response.setContent(languageService.getAllLanguages());
        return response;

    }

    @PostMapping("/addBookList")
    public BookControllerResponse<?> addBookList(
            @RequestBody
            List<Book> bookList) {
        bookService.addListOfBooks(bookList);
        return bookControllerResponse.createSuccessResponse("Add successfully");
    }
}
