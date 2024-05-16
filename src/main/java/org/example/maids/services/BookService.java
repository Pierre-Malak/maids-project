package org.example.maids.services;

import jakarta.persistence.EntityNotFoundException;
import org.example.maids.models.Book;
import org.example.maids.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    @Autowired
    BookRepository bookRepository;

    public List<Book> getAllBooks(){
        return this.bookRepository.findAll();
    }

    public Book getBookDetails(Long id){
        return this.bookRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Book not found with id: " + id));
    }

    public Book updateBookDetails(Long id, Book newBook){
        Book oldBook = this.getBookDetails(id);
        oldBook.setAuthor(newBook.getAuthor());
        oldBook.setIsbn(newBook.getIsbn());
        oldBook.setTitle(newBook.getTitle());
        oldBook.setPublicationYear(newBook.getPublicationYear());
        return this.bookRepository.save(oldBook);
    }

    public Book addBookDetails(Book book){
        return this.bookRepository.save(book);
    }

    public void removeBookById(Long id){
         this.bookRepository.deleteById(id);
    }
}
