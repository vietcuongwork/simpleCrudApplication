package com.vietcuong.simpleCrudApplication.service;

import com.vietcuong.simpleCrudApplication.exception.BookExistedException;
import com.vietcuong.simpleCrudApplication.exception.BookNotExistException;
import com.vietcuong.simpleCrudApplication.exception.EmptyDatabaseException;
import com.vietcuong.simpleCrudApplication.model.Book;
import com.vietcuong.simpleCrudApplication.repository.AuthorRepository;
import com.vietcuong.simpleCrudApplication.repository.BookRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class BookService {
    private final BookRepository bookRepository;
    private final AuthorService authorService;

    public BookService(BookRepository bookRepository, AuthorRepository authorRepository, AuthorService authorService, AuthorRepository authorRepository1) {
        this.bookRepository = bookRepository;
        this.authorService = authorService;

    }

    public void addBook(Book requestBook) {
        if (!findBookByTitle(requestBook)) {
            throw new BookExistedException();
        }
        bookRepository.save(requestBook);
    }


    public List<Book> getAllBooks() {
        List<Book> bookList = bookRepository.findAll();
        if (bookList.isEmpty()) {
            throw new EmptyDatabaseException();
        }
        return bookList;
    }

    public Optional<Book> getById(Integer id) throws BookNotExistException {
        Optional<Book> book = bookRepository.findById(id);
        if (book.isEmpty()) {
            throw new BookNotExistException();
        }
        return bookRepository.findById(id);
    }


    @Transactional
    public void updateBookById(Integer id, Book updateBook) throws BookNotExistException {
        Optional<Book> book = bookRepository.findById(id);
        if (book.isEmpty()) {
            throw new BookNotExistException();
        }
        Book currentBook = book.get();
        this.setBookInfoUtil(currentBook, updateBook);
        bookRepository.save(currentBook);
    }


    @Transactional
    public void deleteById(Integer id) throws BookNotExistException {
        Optional<Book> book = bookRepository.findById(id);
        if (book.isEmpty()) {
            throw new BookNotExistException();
        }
        bookRepository.deleteById(id);
    }

    public void setBookInfoUtil(Book setBook, Book getBook) {
        Class<?> bookClass = Book.class; // Get the class object for the Book class
        Field[] fields = bookClass.getDeclaredFields(); // Retrieve all declared fields of the Book class, including private ones
        for (Field field : fields) { // Iterate through each field of the Book class
            try {
                field.setAccessible(true); // Enable access to private fields if necessary
                Object value = field.get(getBook); // Get the value of the corresponding field from the source Book object (`getBook`)
                if (value != null) { // Check if the value is not null (to avoid overwriting with null values)
                    field.set(setBook, value); // Set the value of the field in the target Book object (`setBook`)
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean findBookByTitle(Book requestBook) {
        Optional<Book> book = bookRepository.findByTitle(requestBook.getTitle());
        return book.isEmpty();
    }


   /* private void printBookData(Book book) {
        System.out.println("Fetched Book Data:");
        System.out.println("Book ID: " + book.getBookId());
        System.out.println("Title: " + book.getTitle());
        System.out.println("ISBN: " + book.getIsbn());
        System.out.println("Number of Pages: " + book.getNumPages());
        System.out.println("Publication Date: " + book.getPublicationDate());

        // Print Publisher details
        Publisher publisher = book.getPublisher();
        if (publisher != null) {
            System.out.println("Publisher:");
            System.out.println(" - Publisher ID: " + publisher.getPublisherId());
            System.out.println(" - Publisher Name: " + publisher.getPublisherName());
        }

        // Print Language details
        Language language = book.getLanguage();
        if (language != null) {
            System.out.println("Language:");
            System.out.println(" - Language ID: " + language.getLanguageId());
            System.out.println(" - Language Name: " + language.getLanguageName());
        }

        // Print Authors details
        Set<Author> authors = book.getAuthors();
        if (!authors.isEmpty()) {
            System.out.println("Authors:");
            for (Author author : authors) {
                System.out.println(" - Author ID: " + author.getAuthorId());
                System.out.println(" - Author Name: " + author.getAuthorName());
            }
        }
    }*/
}
