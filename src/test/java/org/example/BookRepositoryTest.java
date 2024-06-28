package org.example;

import org.example.exception.BookAlreadyExistException;
import org.example.exception.BookFilterNotFountException;
import org.example.exception.BookNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class BookRepositoryTest {

    private Set<Book> bookList;

    private Book book;

    BookRepository bookRepository;

    String title = "Test Book";
    String author = "Test Author";
    int year = 2024;
    @BeforeEach
    public void setUp() {
        bookRepository = new BookRepository();
        bookRepository.createBook(title, author, year);
        bookList = bookRepository.getBookList();
        book = bookList.iterator().next();
    }

    @Test
    void testCreateBookCheckSize() {
        assertEquals(1, bookList.size());

    }
    @Test
    void testCreateBookNotNull() {
        assertNotNull(book);
    }

    @Test
    void testCreateBookEqualFields() {
        assertEquals(title, book.getTitle());
        assertEquals(author, book.getAuthor());
        assertEquals(year, book.getYear());

    }

    @Test
    void testAddBookSuccess() {
        assertTrue(bookList.contains(book));
    }
    @Test
    void testDeleteBookSuccess(){
        bookList.remove(book);
        assertTrue(bookList.isEmpty());
    }
    @Test
    void testDeleteBookNotFoundException(){
        bookList.forEach(book1 -> book1.setId(100L));
        bookRepository.setBookList(bookList);
         assertThrows(BookNotFoundException.class,()-> bookRepository.deleteBook(2L));
    }

    @Test
    void testFindBooksByFilterTitle() {
        bookRepository.findBooksByFilter("Test Book", null, null);
        assertEquals("Test Book", book.getTitle(), "Название книги не соответствует ожидаемому");
    }

    @Test
    void testFindBooksByFilterAuthor() {
        bookRepository.findBooksByFilter(null, "Test Author", null);
        assertEquals("Test Author", book.getAuthor(), "Автор книги не соответствует ожидаемому");
    }

    @Test
    void testFindBooksByFilterYear() {
        bookRepository.findBooksByFilter(null, null, 2024);
        assertEquals(2024, book.getYear(), "Год издания книги не соответствует ожидаемому");
    }


    @Test
    void testFindBooksByFilterBookFilterNotFoundException() {
        assertThrows(BookFilterNotFountException.class,()-> bookRepository.findBooksByFilter("Test book2", "Test", 0));
    }







    }