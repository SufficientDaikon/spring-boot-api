package com.ahmed.Library.services;

import com.ahmed.Library.entities.BorrowingRecord;
import com.ahmed.Library.repositories.BorrowingRecordRepository;
import com.ahmed.Library.entities.Patron;
import com.ahmed.Library.repositories.PatronRepository;
import com.ahmed.Library.entities.Book;
import com.ahmed.Library.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.time.LocalDate;

@Service
public class BorrowingRecordService {
    @Autowired
    private BorrowingRecordRepository borrowingRecordRepository;

    public List<BorrowingRecord> findAll() {
        return borrowingRecordRepository.findAll();
    }

    public BorrowingRecord findById(Long id) {
        return borrowingRecordRepository.findById(id).orElse(null);
    }

    public BorrowingRecord save(BorrowingRecord borrowingRecord) {
        return borrowingRecordRepository.save(borrowingRecord);
    }

    public void deleteById(Long id) {
        borrowingRecordRepository.deleteById(id);
    }
    
    @Transactional
    public BorrowingRecord borrowBook(Long bookId, Long patronId, BookRepository bookRepository,
            PatronRepository patronRepository) {
        Book book = bookRepository.findById(bookId).orElse(null);
        Patron patron = patronRepository.findById(patronId).orElse(null);
        if (book == null || patron == null) {
            return null;
        }

        BorrowingRecord borrowingRecord = new BorrowingRecord();
        borrowingRecord.setBook(book);
        borrowingRecord.setPatron(patron);
        borrowingRecord.setBorrowingDate(LocalDate.now());

        return borrowingRecordRepository.save(borrowingRecord);
    }
    
    @Transactional
    public BorrowingRecord returnBook(Long bookId, Long patronId) {
        BorrowingRecord borrowingRecord = borrowingRecordRepository.findByBookIdAndPatronId(bookId, patronId);
        if (borrowingRecord == null) {
            return null; // or throw an exception if preferred
        }
        borrowingRecord.setReturnDate(LocalDate.now());
        return borrowingRecordRepository.save(borrowingRecord);
    }
}