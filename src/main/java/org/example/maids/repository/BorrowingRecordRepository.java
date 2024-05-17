package org.example.maids.repository;

import org.example.maids.models.BorrowingRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BorrowingRecordRepository extends JpaRepository<BorrowingRecord,Long> {
    BorrowingRecord findByPatronIdAndBookId(Long patronId, Long bookId);
}
