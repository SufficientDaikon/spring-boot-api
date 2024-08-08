package com.ahmed.Library.controllers;

import com.ahmed.Library.entities.BorrowingRecord;
import com.ahmed.Library.services.BorrowingRecordService;
import com.ahmed.Library.repositories.BookRepository;
import com.ahmed.Library.repositories.PatronRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BorrowingRecordControllerTest {

    @Mock
    private BorrowingRecordService borrowingRecordService;

    @Mock
    private BookRepository bookRepository;

    @Mock
    private PatronRepository patronRepository;

    @InjectMocks
    private BorrowingRecordController borrowingRecordController;

    private BorrowingRecord borrowingRecord;

    @BeforeEach
    public void setUp() {
        borrowingRecord = new BorrowingRecord();
        borrowingRecord.setId(1L);
    }

    @Test
    public void testGetAllBorrowingRecords() {
        when(borrowingRecordService.findAll()).thenReturn(Arrays.asList(borrowingRecord));
        List<BorrowingRecord> result = borrowingRecordController.getAllBorrowingRecords();
        assertEquals(1, result.size());
        assertEquals(borrowingRecord, result.get(0));
    }

    @Test
    public void testGetBorrowingRecordById() {
        when(borrowingRecordService.findById(1L)).thenReturn(borrowingRecord);
        ResponseEntity<BorrowingRecord> response = borrowingRecordController.getBorrowingRecordById(1L);
        assertEquals(ResponseEntity.ok(borrowingRecord), response);
    }

    @Test
    public void testGetBorrowingRecordByIdNotFound() {
        when(borrowingRecordService.findById(1L)).thenReturn(null);
        ResponseEntity<BorrowingRecord> response = borrowingRecordController.getBorrowingRecordById(1L);
        assertEquals(ResponseEntity.notFound().build(), response);
    }

    @Test
    public void testBorrowBook() {
        when(borrowingRecordService.borrowBook(1L, 1L, bookRepository, patronRepository)).thenReturn(borrowingRecord);
        ResponseEntity<BorrowingRecord> response = borrowingRecordController.borrowBook(1L, 1L);
        assertEquals(ResponseEntity.ok(borrowingRecord), response);
    }

    @Test
    public void testBorrowBookBadRequest() {
        when(borrowingRecordService.borrowBook(1L, 1L, bookRepository, patronRepository)).thenReturn(null);
        ResponseEntity<BorrowingRecord> response = borrowingRecordController.borrowBook(1L, 1L);
        assertEquals(ResponseEntity.badRequest().build(), response);
    }

    @Test
    public void testDeleteBorrowingRecord() {
        when(borrowingRecordService.findById(1L)).thenReturn(borrowingRecord);
        doNothing().when(borrowingRecordService).deleteById(1L);
        ResponseEntity<Void> response = borrowingRecordController.deleteBorrowingRecord(1L);
        assertEquals(ResponseEntity.noContent().build(), response);
    }

    @Test
    public void testDeleteBorrowingRecordNotFound() {
        when(borrowingRecordService.findById(1L)).thenReturn(null);
        ResponseEntity<Void> response = borrowingRecordController.deleteBorrowingRecord(1L);
        assertEquals(ResponseEntity.notFound().build(), response);
    }

    @Test
    public void testReturnBook() {
        when(borrowingRecordService.returnBook(1L, 1L)).thenReturn(borrowingRecord);
        ResponseEntity<BorrowingRecord> response = borrowingRecordController.returnBook(1L, 1L);
        assertEquals(ResponseEntity.ok(borrowingRecord), response);
    }

    @Test
    public void testReturnBookNotFound() {
        when(borrowingRecordService.returnBook(1L, 1L)).thenReturn(null);
        ResponseEntity<BorrowingRecord> response = borrowingRecordController.returnBook(1L, 1L);
        assertEquals(ResponseEntity.notFound().build(), response);
    }
}
