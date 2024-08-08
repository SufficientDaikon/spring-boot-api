Prerequisites

    Java 17 or higher
    Maven
    Git


```
git clone https://github.com/your-username/library-management-system.git
cd library-management-system
```

```
mvn clean install
```

```
mvn spring-boot:run
```

# API Documentation
Get All Books

    URL: /api/books
    Method: GET
    Description: Retrieve a list of all books.
```
curl -X GET http://localhost:8080/api/books
```
    Response:
        200 OK: Returns a list of books.

Get Book by ID

    URL: /api/books/{id}
    Method: GET
    Description: Retrieve a book by its ID.
```
curl -X GET http://localhost:8080/api/books/{id}
```
    Response:
      200 OK: Returns the book.
      404 Not Found: If the book is not found.
      
Create Book

    URL: /api/books
    Method: POST
    Description: Create a new book.
```
curl -X POST http://localhost:8080/api/books -H "Content-Type: application/json" -d '{"title": "Book Title","author": "Book Author","isbn": "1234567890","publishedDate": "2023-01-01"}'
```
    Response:
      201 Created: Returns the created book.
      
Update Book

    URL: /api/books/{id}
    Method: PUT
    Description: Update a book by its ID.
```
curl -X PUT http://localhost:8080/api/books -H "Content-Type: application/json" -d '{"title": "Book Title","author": "Book Author","isbn": "1234567890","publishedDate": "2023-01-01"}'
```
    Response:
      200 OK: Returns the updated book.
      404 Not Found: If the book is not found.
Delete Book

    URL: /api/books/{id}
    Method: DELETE
    Description: Delete a book by its ID.
```
curl -X DELETE http://localhost:8080/api/books/{id}
```
    Response:
      204 No Content: If the deletion is successful.
      404 Not Found: If the book is not found.

## Borrowing Record Endpoints
Get All Borrowing Records

    URL: /api/borrowing-records
    Method: GET
```
curl -X GET http://localhost:8080/api/borrowing-records
```
    Description: Retrieve a list of all borrowing records.
    Response:
        200 OK: Returns a list of borrowing records.

Get Borrowing Record by ID

    URL: /api/borrowing-records/{id}
    Method: GET
    Description: Retrieve a borrowing record by its ID.
```
curl -X GET http://localhost:8080/api/borrowing-records/{id}
```
    Response:
        200 OK: Returns the borrowing record.
        404 Not Found: If the borrowing record is not found.

Borrow a Book

    URL: /api/borrowing-records/borrow/{bookId}/patron/{patronId}
    Method: POST
    Description: Borrow a book by providing the book ID and patron ID.
```
curl -X POST http://localhost:8080/api/borrowing-records/borrow/{bookId}/patron/{patronId}
```
    Response:
        200 OK: Returns the borrowing record.
        400 Bad Request: If the borrowing operation fails.

Delete Borrowing Record

    URL: /api/borrowing-records/{id}
    Method: DELETE
    Description: Delete a borrowing record by its ID.
```
curl -X DELETE http://localhost:8080/api/borrowing-records/{id}
```
    Response:
        204 No Content: If the deletion is successful.
        404 Not Found: If the borrowing record is not found.

Return a Book

    URL: /api/borrowing-records/return/{bookId}/patron/{patronId}
    Method: PUT
    Description: Return a borrowed book by providing the book ID and patron ID.
```
curl -X PUT http://localhost:8080/api/borrowing-records/return/{bookId}/patron/{patronId}
```
    Response:
        200 OK: Returns the updated borrowing record.
        404 Not Found: If the borrowing record is not found.

## Patron Endpoints
Get All Patrons

    URL: /api/patrons
    Method: GET
    Description: Retrieve a list of all patrons.
```
curl -X GET http://localhost:8080/api/patrons
```
    Response:
        200 OK: Returns a list of patrons.

Get Patron by ID

    URL: /api/patrons/{id}
    Method: GET
    Description: Retrieve a patron by its ID.
```
curl -X GET http://localhost:8080/api/patrons/{id}
```
    Response:
        200 OK: Returns the patron.
        404 Not Found: If the patron is not found.

Create Patron

    URL: /api/patrons
    Method: POST
    Description: Create a new patron.
    Request Body: Patron object
```
curl -X POST http://localhost:8080/api/patrons -H "Content-Type: application/json" -d '{"name": "Patron Name","contactInformation": "Contact Info"}'
```
    Response:
        200 OK: Returns the created patron.

Update Patron

    URL: /api/patrons/{id}
    Method: PUT
    Description: Update a patron by its ID.
    Request Body: Patron object
```
curl -X PUT http://localhost:8080/api/patrons/{id} -H "Content-Type: application/json" -d '{"name": "Updated Name","contactInformation": "Updated Contact Info"}'
```
    Response:
        200 OK: Returns the updated patron.
        404 Not Found: If the patron is not found.

Delete Patron

    URL: /api/patrons/{id}
    Method: DELETE
    Description: Delete a patron by its ID.
```
curl -X DELETE http://localhost:8080/api/patrons/{id}
```
    Response:
        204 No Content: If the deletion is successful.
        404 Not Found: If the patron is not found.
