package org.example.maids.validators;

import org.example.maids.exceptions.ReturnDateException;
import org.example.maids.models.BorrowingRecord;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Fail.fail;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class BorrowingRecordValidatorTest {

    @Test
    public void isValidReturnDateTest() {
        BorrowingRecord record = new BorrowingRecord();
        record.setBorrowingDate(LocalDate.of(2022, 1, 1));

        record.setReturnDate(LocalDate.of(2022, 1, 2));
        try {
            BorrowingRecordValidator.isValidReturnDate(record);
        } catch (ReturnDateException e) {
            fail("Exception should not be thrown for a valid return date");
        }

        record.setReturnDate(LocalDate.of(2021, 12, 31));
        assertThrows(ReturnDateException.class, () -> BorrowingRecordValidator.isValidReturnDate(record));
    }
}
