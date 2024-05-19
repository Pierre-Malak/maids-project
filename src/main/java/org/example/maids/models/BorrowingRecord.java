package org.example.maids.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class BorrowingRecord {

        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private Long id;

        @ManyToOne
        @JoinColumn(name = "book_id")
        private Book book;

        @ManyToOne
        @JoinColumn(name = "patron_id")
        private Patron patron;

        @NotNull(message = "Borrowing date cannot be null")
        private LocalDate borrowingDate;
        private LocalDate returnDate;

}
