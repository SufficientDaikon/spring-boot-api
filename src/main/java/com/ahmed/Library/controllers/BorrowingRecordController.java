package com.ahmed.Library.controllers;

import com.ahmed.Library.entities.BorrowingRecord;
import com.ahmed.Library.services.BorrowingRecordService;
import com.ahmed.Library.repositories.BookRepository;
import com.ahmed.Library.repositories.PatronRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/api/borrowing-records")
public class BorrowingRecordController {
    @Autowired
    private BorrowingRecordService borrowingRecordService;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private PatronRepository patronRepository;

    @GetMapping
    public List<BorrowingRecord> getAllBorrowingRecords() {
        return borrowingRecordService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<BorrowingRecord> getBorrowingRecordById(@PathVariable Long id) {
        BorrowingRecord borrowingRecord = borrowingRecordService.findById(id);
        if (borrowingRecord == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(borrowingRecord);
    }

    @PostMapping("/borrow/{bookId}/patron/{patronId}")
    public ResponseEntity<BorrowingRecord> borrowBook(@PathVariable Long bookId, @PathVariable Long patronId) {
        BorrowingRecord borrowingRecord = borrowingRecordService.borrowBook(bookId, patronId, bookRepository,
                patronRepository);
        if (borrowingRecord == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(borrowingRecord);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBorrowingRecord(@PathVariable Long id) {
        if (borrowingRecordService.findById(id) == null) {
            return ResponseEntity.notFound().build();
        }
        borrowingRecordService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/return/{bookId}/patron/{patronId}")
    public ResponseEntity<BorrowingRecord> returnBook(@PathVariable Long bookId, @PathVariable Long patronId) {
        BorrowingRecord borrowingRecord = borrowingRecordService.returnBook(bookId, patronId);
        if (borrowingRecord == null) {
            return ResponseEntity.notFound().build(); // or return a suitable error response
        }
        return ResponseEntity.ok(borrowingRecord);
    }
}
