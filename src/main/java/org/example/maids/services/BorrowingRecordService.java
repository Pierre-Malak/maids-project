package org.example.maids.services;

import org.example.maids.models.Book;
import org.example.maids.models.BorrowingRecord;
import org.example.maids.models.Patron;
import org.example.maids.repository.BookRepository;
import org.example.maids.repository.BorrowingRecordRepository;
import org.example.maids.repository.PatronRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class BorrowingRecordService {

    @Autowired
    BorrowingRecordRepository borrowingRecordRepository;

    @Autowired
    BookRepository bookRepository;

    @Autowired
    PatronRepository patronRepository;

    public BorrowingRecord addBorrowingRecord(Long bookId, Long patronId){
        Book book = this.bookRepository.getReferenceById(bookId);
        Patron patron = this.patronRepository.getReferenceById(patronId);
        BorrowingRecord borrowingRecord = new BorrowingRecord();
        borrowingRecord.setBook(book);
        borrowingRecord.setPatron(patron);
        borrowingRecord.setBorrowingDate(LocalDate.now());
        return this.borrowingRecordRepository.save(borrowingRecord);
    }

    public BorrowingRecord addReturnTime(Long bookId, Long patronId, LocalDate returnTime, Long borrowId){
     BorrowingRecord borrowingRecord = this.borrowingRecordRepository.getReferenceById(borrowId);
     borrowingRecord.setReturnDate(returnTime);
     return this.borrowingRecordRepository.save(borrowingRecord);
    }
}
