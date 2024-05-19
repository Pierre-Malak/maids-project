package org.example.maids.services;

import org.example.maids.exceptions.ReturnDateException;
import org.example.maids.models.Book;
import org.example.maids.models.BorrowingRecord;
import org.example.maids.models.Patron;
import org.example.maids.repository.BookRepository;
import org.example.maids.repository.BorrowingRecordRepository;
import org.example.maids.repository.PatronRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class BorrowingRecordServiceTest {

    @InjectMocks
    BorrowingRecordService borrowingRecordService;

    @Mock
    BorrowingRecordRepository borrowingRecordRepository;

    @Mock
    BookRepository bookRepository;

    @Mock
    PatronRepository patronRepository;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getBorrowingRecByIdTest() {
        when(borrowingRecordRepository.findById(1L)).thenReturn(Optional.of(new BorrowingRecord(1L,new Book(),new Patron(),LocalDate.now(),null)));
        BorrowingRecord borrowingRecord = borrowingRecordService.getBorrowingRecById(1L);
        assertEquals(1, borrowingRecord.getId());
    }

    @Test
    public void addBorrowingRecordTest() {
        Book book = new Book();
        Patron patron = new Patron();
        when(bookRepository.getReferenceById(1L)).thenReturn(book);
        when(patronRepository.getReferenceById(1L)).thenReturn(patron);
        when(borrowingRecordRepository.save(any(BorrowingRecord.class))).thenReturn(new BorrowingRecord(1L,new Book(),new Patron(),LocalDate.now(),null));
        BorrowingRecord borrowingRecord = borrowingRecordService.addBorrowingRecord(1L, 1L);
        assertEquals(1, borrowingRecord.getId());
    }

    @Test
    public void addReturnTimeTest() throws ReturnDateException {
        when(borrowingRecordRepository.findById(1L)).thenReturn(Optional.of(new BorrowingRecord(1L,new Book(),new Patron(),LocalDate.now(),LocalDate.now())));
        when(borrowingRecordRepository.save(any(BorrowingRecord.class))).thenReturn(new BorrowingRecord(1L,new Book(),new Patron(),LocalDate.now(),LocalDate.now()));
        BorrowingRecord borrowingRecord = borrowingRecordService.addReturnTime(LocalDate.now(), 1L);
        assertEquals(LocalDate.now(), borrowingRecord.getReturnDate());
    }
}
