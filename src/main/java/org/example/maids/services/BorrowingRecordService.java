package org.example.maids.services;

import jakarta.persistence.EntityNotFoundException;
import org.example.maids.exceptions.ReturnDateException;
import org.example.maids.models.Book;
import org.example.maids.models.BorrowingRecord;
import org.example.maids.models.Patron;
import org.example.maids.repository.BookRepository;
import org.example.maids.repository.BorrowingRecordRepository;
import org.example.maids.repository.PatronRepository;
import org.example.maids.validators.BorrowingRecordValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
public class BorrowingRecordService {

    @Autowired
    BorrowingRecordRepository borrowingRecordRepository;

    @Autowired
    BookRepository bookRepository;

    @Autowired
    PatronRepository patronRepository;

    public BorrowingRecord getBorrowingRecById(Long borrowId){
        return this.borrowingRecordRepository.findById(borrowId).orElseThrow(() ->
                new EntityNotFoundException("Borrowing record not found with id: " + borrowId));
    }

    @Transactional
    public BorrowingRecord addBorrowingRecord(Long bookId, Long patronId){
        Book book = this.bookRepository.getReferenceById(bookId);
        Patron patron = this.patronRepository.getReferenceById(patronId);
        BorrowingRecord borrowingRecord = new BorrowingRecord();
        borrowingRecord.setBook(book);
        borrowingRecord.setPatron(patron);
        borrowingRecord.setBorrowingDate(LocalDate.now());
        return this.borrowingRecordRepository.save(borrowingRecord);
    }

    @Transactional
    public BorrowingRecord addReturnTime(LocalDate returnTime, Long borrowId) throws ReturnDateException {
            BorrowingRecord borrowingRecord = this.getBorrowingRecById(borrowId);
            borrowingRecord.setReturnDate(returnTime);
            BorrowingRecordValidator.isValidReturnDate(borrowingRecord);
     return this.borrowingRecordRepository.save(borrowingRecord);
    }
}
