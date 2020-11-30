package com.cmvel.lms.controller;

import com.cmvel.lms.dao.BookRepository;
import com.cmvel.lms.model.Book;
import com.cmvel.lms.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*", maxAge = 3600)
@RestController
@RequestMapping("book")
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    @GetMapping
    public List<Book> getBooks(){
       return bookRepository.findAll();
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*", maxAge = 3600)
    @GetMapping("/{id}")
    public Book getBooksById(@PathVariable("id") long id){
        return bookRepository.findById(id).orElseThrow(() -> new RuntimeException("Book not found for this id :: " + id));
    }


    @PostMapping
    public Book addBook(@RequestBody Book book){
        return bookRepository.save(book);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*", maxAge = 3600)
    @PutMapping("{id}")
    public ResponseEntity<Book> updateBook(@PathVariable("id") long id, @RequestBody Book book){
        Book bookInDB = bookRepository.findById(id).orElseThrow(() -> new RuntimeException("Book not found for this id :: " + id));

        bookInDB.setAuthors(book.getAuthors());
        bookInDB.setDonatedBy(book.getDonatedBy());
        bookInDB.setISBN(book.getISBN());
        bookInDB.setLanguage(book.getLanguage());
        bookInDB.setNumberOfPages(book.getNumberOfPages());
        bookInDB.setPublisher(book.getPublisher());
        bookInDB.setSubject(book.getSubject());
        bookInDB.setTitle(book.getTitle());
        return ResponseEntity.ok(bookRepository.save(bookInDB));
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*", maxAge = 3600)
    @DeleteMapping("{id}")
    public Boolean deleteBook(@PathVariable("id") long id){
        bookRepository.findById(id).orElseThrow(() ->new RuntimeException("Book not found for this id:" + id));

        bookRepository.deleteById(id);
        return  true;
    }
}
