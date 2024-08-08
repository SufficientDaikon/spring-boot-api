package com.ahmed.Library.repositories;

import com.ahmed.Library.entities.BorrowingRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BorrowingRecordRepository extends JpaRepository<BorrowingRecord, Long> {
    BorrowingRecord findByBookIdAndPatronId(Long bookId, Long patronId);
}