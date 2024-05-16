package org.example.maids.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Data
public class Book {
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private Long id;
        private String title;
        private String author;
        private String publicationYear;
        private String isbn;

}
