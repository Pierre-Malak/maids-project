package org.example.maids.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
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
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})
public class BorrowingRecord {

        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private Long id;

        @ManyToOne
        @JoinColumn(name = "book_id", referencedColumnName = "id")
        private Book book;

        @ManyToOne
        @JoinColumn(name = "patron_id", referencedColumnName = "id")
        private Patron patron;

        private LocalDate borrowingDate;
        private LocalDate returnDate;

}
