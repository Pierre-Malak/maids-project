package org.example.maids.controller;

import org.example.maids.models.Book;
import org.example.maids.models.BorrowingRecord;
import org.example.maids.models.Patron;
import org.example.maids.repository.BookRepository;
import org.example.maids.repository.BorrowingRecordRepository;
import org.example.maids.repository.PatronRepository;
import org.example.maids.services.BorrowingRecordService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;

import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
public class BorrowingRecordControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Mock
    BorrowingRecordService borrowingRecordService;

    @Autowired
    BookRepository bookRepository;

    @Autowired
    PatronRepository patronRepository;
    @Autowired
    BorrowingRecordRepository borrowingRecordRepository;


    @Test
    public void testAddBorrowingReturnTime() throws Exception {
        Book book = new Book(null,"Test","test","1999","1234567890123");
        Patron patron = new Patron(null,"Test","01227371089");
        BorrowingRecord borrowingRecord = new BorrowingRecord(null, book,patron,LocalDate.of(2024, 3, 19),null);
        bookRepository.save(book);
        patronRepository.save(patron);
        borrowingRecordRepository.save(borrowingRecord);

        LocalDate returnDate = LocalDate.of(2024, 5, 19);
        BorrowingRecord expectedRecord = new BorrowingRecord();
        expectedRecord.setId(1L); // Set an expected ID
        expectedRecord.setReturnDate(returnDate);

        when(borrowingRecordService.addReturnTime(returnDate, 1L))
                .thenReturn(expectedRecord);

        mockMvc.perform(MockMvcRequestBuilders.put("/api/return/{bookId}/patron/{patronId}", 1L, 2L)
                        .param("returnDate", "2024-05-19")
                        .param("borrowId", "1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1L)) // Verify the ID
                .andExpect(MockMvcResultMatchers.jsonPath("$.returnDate").value("2024-05-19")); // Verify the return date

    }

    @Test
    public void testAddBorrowingReturnTime_fail() throws Exception {

        Book book = new Book(null,"Test","test","1999","1234567890123");
        Patron patron = new Patron(null,"Test","01227371089");
        BorrowingRecord borrowingRecord = new BorrowingRecord(null, book,patron,LocalDate.of(2024, 3, 19),null);
        bookRepository.save(book);
        patronRepository.save(patron);
        borrowingRecordRepository.save(borrowingRecord);

        LocalDate returnDate = LocalDate.of(2024, 2, 19);
        BorrowingRecord expectedRecord = new BorrowingRecord();
        expectedRecord.setId(1L); // Set an expected ID
        expectedRecord.setReturnDate(returnDate);

        when(borrowingRecordService.addReturnTime(returnDate, 1L))
                .thenReturn(expectedRecord);

        mockMvc.perform(MockMvcRequestBuilders.put("/api/return/{bookId}/patron/{patronId}", 2L, 2L)
                        .param("returnDate", "2024-02-19")
                        .param("borrowId", "1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());

    }

}
