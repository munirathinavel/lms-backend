package com.cmvel.lms.dao;

import com.cmvel.lms.model.Book;
import com.cmvel.lms.model.IssuedBookStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.sql.Date;
import java.util.List;

public interface IssuedBookStatusRepository extends JpaRepository<IssuedBookStatus, Long> {
    @Query(value = "select * from issued_book_status where book_id = ?1 and user_id = ?2 and book_returned = ?3", nativeQuery = true)
    IssuedBookStatus findByBookIdAndUserId(long bookId, long userId, boolean bookReturned);

    @Query(value = "update issued_book_status set bookReturned = ?3, fineCalculated = ?4, finePaid = ?5, returnedDate = ?6  where bookId = ?1 and userId = ?2" , nativeQuery = true)
    void updateIssuedBookByUserIdAndBookId(long bookId, long userId, boolean bookReturned, double fineCalculated, double finePaid, Date returnedDate);

    @Query(value = "select * from issued_book_status where book_returned = false and returned_date is null", nativeQuery = true)
    List<IssuedBookStatus> getAllIssuedBookStatus();

    @Query(value = "select * from issued_book_status where book_returned = true and returned_date is not null", nativeQuery = true)
    List<IssuedBookStatus> getReturnedBookStatus();
}
