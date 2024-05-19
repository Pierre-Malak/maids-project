package org.example.maids.controller;

import org.example.maids.exceptions.ReturnDateException;
import org.example.maids.models.BorrowingRecord;
import org.example.maids.models.Patron;
import org.example.maids.services.BorrowingRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api")
@Validated
public class BorrowingRecordController {

    @Autowired
    BorrowingRecordService borrowingRecordService;

    @PostMapping(value = "/borrow/{bookId}/patron/{patronId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public BorrowingRecord addBorrowingRecord(@PathVariable Long bookId, @PathVariable Long patronId){
        return this.borrowingRecordService.addBorrowingRecord(bookId, patronId);
    }

    @PutMapping(value = "/return/{bookId}/patron/{patronId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public BorrowingRecord addBorrowingReturnTime(@PathVariable Long bookId, @PathVariable Long patronId,
                                                  @RequestParam(name = "returnDate", required = true) LocalDate returnDate,
                                                  @RequestParam(name = "borrowId", required = true) Long borrowId) throws ReturnDateException {
        return this.borrowingRecordService.addReturnTime(returnDate, borrowId);
    }
}
