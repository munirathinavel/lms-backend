package com.cmvel.lms.dao;

import com.cmvel.lms.model.Book;
import com.cmvel.lms.model.IssuedBookStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
}
