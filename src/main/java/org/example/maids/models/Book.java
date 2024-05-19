package org.example.maids.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})
public class Book {
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private Long id;
        @NotBlank(message = "Title cannot be empty")
        private String title;
        @NotBlank(message = "Author cannot be empty")
        private String author;
        @Pattern(regexp = "\\d{4}", message = "Publication year must be a 4-digit number")
        private String publicationYear;
        @Pattern(regexp = "\\d{10}|\\d{13}", message = "ISBN must be a 10 or 13-digit number")
        private String isbn;
}
