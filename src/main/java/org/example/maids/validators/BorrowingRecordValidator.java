package org.example.maids.validators;

import org.example.maids.exceptions.ReturnDateException;
import org.example.maids.models.BorrowingRecord;
import java.time.LocalDate;

public class BorrowingRecordValidator {

    public static void isValidReturnDate(BorrowingRecord record) throws ReturnDateException {
        LocalDate borrowingDate = record.getBorrowingDate();
        LocalDate returnDate = record.getReturnDate();
        if(returnDate.isBefore(borrowingDate)){
            throw new ReturnDateException("The return date cannot be before the borrow date");
        }
    }


}
