# Maids - A Spring Boot Application

## Overview
This is a Spring Boot application named 'Maids'. It is designed to manage books, patrons, and borrow records.

## Prerequisites
- Java 17
- Maven
- Spring Boot 3.2.5
- H2 Database
- Lombok

## Getting Started
1. Clone the repository to your local machine.
2. Navigate to the project directory.
3. Run the application using the command: `mvn spring-boot:run`

## APIs

### Book APIs
1. **Add a Book**
    - Endpoint: `POST http://localhost:8080/api/books`
    - Request Body:
      ```json
      {
          "title": "The Great Gatsby",
          "author": "F. Scott Fitzgerald",
          "publicationYear": "1999",
          "isbn": "9780743271234"
      }
      ```

2. **Update a Book**
    - Endpoint: `PUT http://localhost:8080/api/books`
    - Request Body:
      ```json
      {
          "title": "The Great Gatsby",
          "author": "F. Scott Fitzgerald",
          "publicationYear": "1999",
          "isbn": "9780743271234"
      }
      ```

3. **Get All Books**
    - Endpoint: `GET http://localhost:8080/api/books`

4. **Get a Book by ID**
    - Endpoint: `GET http://localhost:8080/api/books/{bookID}`

5. **Remove a Book**
    - Endpoint: `DELETE http://localhost:8080/api/books/{bookID}`

### Patron APIs
1. **Add a Patron**
    - Endpoint: `POST http://localhost:8080/api/patrons`
    - Request Body:
      ```json
      {
          "name": "Pierre.MSamy",
          "phoneNumber": "01227371089999"
      }
      ```

2. **Update a Patron**
    - Endpoint: `PUT http://localhost:8080/api/patrons`
    - Request Body:
      ```json
      {
          "name": "Pierre.MSamy",
          "phoneNumber": "01227371089999"
      }
      ```

3. **Get All Patrons**
    - Endpoint: `GET http://localhost:8080/api/patrons`

4. **Get a Patron by ID**
    - Endpoint: `GET http://localhost:8080/api/patrons/{patronId}`

5. **Remove a Patron**
    - Endpoint: `DELETE http://localhost:8080/api/patrons/{patronId}`

### BorrowRecords APIs
1. **Add a BorrowRecord**
    - Endpoint: `POST http://localhost:8080/api/borrow/{bookId}/patron/{patronId}`

2. **Add a BorrowRecord Return Date**
    - Endpoint: `POST http://localhost:8080/api/return/{bookId}/patron/{patronId}?returnDate=2024-05-15&borrowId={borrowRecId}`

## Contributing
Please read CONTRIBUTING.md for details on our code of conduct, and the process for submitting pull requests to us.

## License
This project is licensed under the MIT License - see the LICENSE.md file for details
