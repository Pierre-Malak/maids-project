package org.example.maids.services;

import jakarta.persistence.EntityNotFoundException;
import org.example.maids.models.Book;
import org.example.maids.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BookService {

    @Autowired
    BookRepository bookRepository;

    public List<Book> getAllBooks(){
        return this.bookRepository.findAll();
    }

    @Cacheable(value = "maidsCache", key = "#root.targetClass.toString() + #id.toString()")
    public Book getBookDetails(Long id){
        return this.bookRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Book not found with id: " + id));
    }
    @Transactional
    @CachePut(value = "maidsCache", key = "#root.targetClass.toString() + #id.toString()")
    public Book updateBookDetails(Long id, Book newBook){
        Book oldBook = this.getBookDetails(id);
        oldBook.setAuthor(newBook.getAuthor());
        oldBook.setIsbn(newBook.getIsbn());
        oldBook.setTitle(newBook.getTitle());
        oldBook.setPublicationYear(newBook.getPublicationYear());
        return this.bookRepository.save(oldBook);
    }
    @Transactional
    public Book addBookDetails(Book book){
        return this.bookRepository.save(book);
    }

    @Transactional
    @CacheEvict(value = "maidsCache", key = "#root.targetClass.toString() + #id.toString()")
    public void removeBookById(Long id){
         this.bookRepository.deleteById(id);
    }
}
