package org.example.maids.services;

import org.example.maids.models.Book;
import org.example.maids.repository.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class BookServiceTest {
    @InjectMocks
    BookService bookService;

    @Mock
    BookRepository bookRepository;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getAllBooksTest() {
        when(bookRepository.findAll()).thenReturn(Arrays.asList(new Book(1L,"Test","test","1999","1234567890123")));
        List<Book> books = bookService.getAllBooks();
        assertEquals(1, books.size());
    }

    @Test
    public void getBookDetailsTest() {
        when(bookRepository.findById(1L)).thenReturn(Optional.of(new Book(1L,"Test","test","1999","1234567890123")));
        Book book = bookService.getBookDetails(1L);
        assertEquals("Test", book.getTitle());
    }

    @Test
    public void updateBookDetailsTest() {
        Book oldBook = new Book(1L,"Test","test","1999","1234567890123");
        Book newBook = new Book(1L,"Updated","test","1999","1234567890123");
        when(bookRepository.findById(1L)).thenReturn(Optional.of(oldBook));
        when(bookRepository.save(oldBook)).thenReturn(newBook);
        Book updatedBook = bookService.updateBookDetails(1L, newBook);
        assertEquals("Updated", updatedBook.getTitle());
    }

    @Test
    public void addBookDetailsTest() {
        Book book = new Book(1L,"Test","test","1999","1234567890123");
        when(bookRepository.save(book)).thenReturn(book);
        Book savedBook = bookService.addBookDetails(book);
        assertEquals("Test", savedBook.getTitle());
    }

    @Test
    public void removeBookByIdTest() {
        doNothing().when(bookRepository).deleteById(1L);
        bookService.removeBookById(1L);
        verify(bookRepository, times(1)).deleteById(1L);
    }
}
