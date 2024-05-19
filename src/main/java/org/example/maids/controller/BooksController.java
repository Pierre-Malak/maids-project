package org.example.maids.controller;

import jakarta.validation.Valid;
import org.example.maids.models.Book;
import org.example.maids.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/books")
@Validated
public class BooksController {

    @Autowired BookService bookService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Book> getAllBooks(){
        return this.bookService.getAllBooks();
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Book addBookDetails(@RequestBody @Valid Book book){
        return this.bookService.addBookDetails(book);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Book getBookById(@PathVariable Long id){
        return this.bookService.getBookDetails(id);
    }

    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Book updateBookById(@PathVariable Long id, @RequestBody @Valid Book book){
        return this.bookService.updateBookDetails(id,book);
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> removeBookById(@PathVariable Long id){
         this.bookService.removeBookById(id);
         return ResponseEntity.ok().build();
    }


}

