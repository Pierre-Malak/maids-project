package org.example.maids.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.maids.models.Book;
import org.example.maids.repository.BookRepository;
import org.example.maids.services.BookService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Collections;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
public class BooksControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private BookService bookService;


    @Test
    void testGetAllBooks() throws Exception {
        when(bookService.getAllBooks()).thenReturn(Collections.singletonList(new Book()));
        mockMvc.perform(MockMvcRequestBuilders.get("/api/books")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void testAddBookDetails() throws Exception {
        Book bookToAdd = new Book();
        bookToAdd.setId(1L);
        bookToAdd.setPublicationYear("1999");
        bookToAdd.setIsbn("1234567890123");
        bookToAdd.setTitle("All the moon");
        bookToAdd.setAuthor("Pierre");
        when(bookService.addBookDetails(any(Book.class))).thenReturn(bookToAdd);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(bookToAdd)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.title").value(bookToAdd.getTitle()));
    }

    @Test
    void testGetBookDetailsById() throws Exception {
        Long bookId = 1L;
        Book book = new Book();
        book.setId(bookId);
        book.setPublicationYear("1999");
        book.setIsbn("1234567890123");
        book.setTitle("All the moon");
        book.setAuthor("Pierre");
        when(bookService.getBookDetails(any())).thenReturn(book);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/books/{id}", bookId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.title").value(book.getTitle()));
    }

    @Test
    void testUpdateBookById() throws Exception {
        Long bookId = 1L;
        Book existingBook = new Book();
        // Set properties for the existingBook
        existingBook.setId(bookId);
        existingBook.setPublicationYear("1999");
        existingBook.setIsbn("1234567890123");
        existingBook.setTitle("All the moon");
        existingBook.setAuthor("Pierre");
        Book updatedBook = new Book();
        // Set properties for the updatedBook
        updatedBook.setId(bookId);
        updatedBook.setPublicationYear("1999");
        updatedBook.setIsbn("1234567890123");
        updatedBook.setTitle("All the moon");
        updatedBook.setAuthor("Pierre Malak.S");
        // Mock bookService.updateBookDetails() to return the updated book

        when(bookService.getBookDetails(eq(bookId))).thenReturn(existingBook);
        when(bookService.updateBookDetails(eq(bookId), any(Book.class))).thenReturn(updatedBook);
        mockMvc.perform(MockMvcRequestBuilders.put("/api/books/{id}", bookId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(updatedBook))) // Convert updatedBook to JSON
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.title").value(updatedBook.getTitle()));
    }


    @Test
    void testRemoveBookById() throws Exception {
        Long bookId = 1L;
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/books/{id}", bookId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
