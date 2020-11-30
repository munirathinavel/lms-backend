package com.cmvel.lms.controller;

import com.cmvel.lms.dao.BookRepository;
import com.cmvel.lms.dao.IssuedBookStatusRepository;
import com.cmvel.lms.dao.PersonRepository;
import com.cmvel.lms.model.Book;
import com.cmvel.lms.model.IssuedBookStatus;
import com.cmvel.lms.model.IssuedBookVO;
import com.cmvel.lms.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@CrossOrigin(origins = "*", allowedHeaders = "*", maxAge = 3600)
@RestController
@RequestMapping("admin")
public class AdminController {
    @Autowired
    private IssuedBookStatusRepository issuedBookRepo;

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private BookRepository bookRepository;

    @GetMapping
    public List<IssuedBookStatus> getAllIssuedReturnedBooks(){
        return issuedBookRepo.findAll();
    }

    @GetMapping("/issuedbooks")
    public List<IssuedBookVO> getIssuedBookStatus(){
        List<IssuedBookStatus> issuedBookStatusList = issuedBookRepo.getAllIssuedBookStatus();
        final  List<IssuedBookVO> issuedBookVOList = new ArrayList<>();
        Map<Long,String> bookMap = bookRepository.findAll().stream().collect(Collectors.toMap(book -> book.getId(), book -> book.getTitle()));
        Map<Long, String> personMap = personRepository.findAll().stream().collect(Collectors.toMap(p -> p.getId(), p -> p.getName()));

        issuedBookStatusList.stream().forEach(issuedBook -> {
            buildIssuedBookVO(issuedBookVOList, bookMap, personMap, issuedBook);
        });

        return issuedBookVOList;
    }

    private void buildIssuedBookVO(List<IssuedBookVO> issuedBookVOList, Map<Long, String> bookMap, Map<Long, String> personMap, IssuedBookStatus issuedBook) {
        IssuedBookVO issuedBookVO = new IssuedBookVO();
        issuedBookVO.setId(issuedBook.getId());
        issuedBookVO.setBookId(issuedBook.getBookId());
        issuedBookVO.setBookTitle(bookMap.get(issuedBook.getBookId()));
        issuedBookVO.setBookReturned(issuedBook.isBookReturned());
        issuedBookVO.setFineCalculated(issuedBook.getFineCalculated());
        issuedBookVO.setFinePaid(issuedBook.getFinePaid());
        issuedBookVO.setFinePerDay(issuedBook.getFinePerDay());
        issuedBookVO.setIssuedBy(issuedBook.getIssuedBy());
        issuedBookVO.setIssuedDate(issuedBook.getIssuedDate());
        issuedBookVO.setNoOfDays(issuedBook.getNoOfDays());
        issuedBookVO.setReceivedBy(issuedBook.getReceivedBy());
        issuedBookVO.setReturnedDate(issuedBook.getReturnedDate());
        issuedBookVO.setUserId(issuedBook.getUserId());
        issuedBookVO.setUserName(personMap.get(issuedBook.getUserId()));
        issuedBookVOList.add(issuedBookVO);
    }

    @GetMapping("/returnedbooks")
    public List<IssuedBookVO> getReturnedBookStatus(){
        List<IssuedBookStatus> issuedBookStatusList = issuedBookRepo.getReturnedBookStatus();
        final  List<IssuedBookVO> issuedBookVOList = new ArrayList<>();
        Map<Long,String> bookMap = bookRepository.findAll().stream().collect(Collectors.toMap(book -> book.getId(), book -> book.getTitle()));
        Map<Long, String> personMap = personRepository.findAll().stream().collect(Collectors.toMap(p -> p.getId(), p -> p.getName()));

        issuedBookStatusList.stream().forEach(issuedBook -> {
            buildIssuedBookVO(issuedBookVOList, bookMap, personMap, issuedBook);
        });

        return issuedBookVOList;
    }

    @PostMapping
    public IssuedBookStatus issueBook(@RequestBody IssuedBookStatus issuedBook){
        IssuedBookStatus bookStatusInDB = issuedBookRepo.findByBookIdAndUserId(issuedBook.getBookId(), issuedBook.getUserId(), false);
        if(bookStatusInDB != null){
            throw new RuntimeException("This book is already issued with Book Id"+ bookStatusInDB.getBookId() + " & User Id:"+ bookStatusInDB.getUserId());
        }
        issuedBook.setIssuedDate(new Date(new java.util.Date().getTime()));
        //issuedBook.setBookReturned(false);
        return issuedBookRepo.save(issuedBook);
    }

    @PostMapping("/returnbook")
    public boolean returnBook(@RequestBody IssuedBookStatus issuedBook){
        IssuedBookStatus bookStatusInDB = issuedBookRepo.findByBookIdAndUserId(issuedBook.getBookId(), issuedBook.getUserId(), false);
        if(bookStatusInDB == null){
            throw new RuntimeException("This book is not issued with Book Id"+ bookStatusInDB.getBookId() + " & User Id:"+ bookStatusInDB.getUserId());
        }
        bookStatusInDB.setBookReturned(true);
        bookStatusInDB.setFineCalculated(issuedBook.getFineCalculated());
        bookStatusInDB.setFinePaid(issuedBook.getFinePaid());
        bookStatusInDB.setReturnedDate(new Date(new java.util.Date().getTime()));
        issuedBookRepo.save(bookStatusInDB);
        return true;
    }
}
