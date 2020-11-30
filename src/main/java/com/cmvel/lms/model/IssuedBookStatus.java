package com.cmvel.lms.model;

import lombok.Data;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "issued_book_status")
@Data
public class IssuedBookStatus {
  @Id
  @GeneratedValue
  private long id;
 // @ManyToMany(targetEntity = Book.class)
  private long bookId;
 // @ManyToMany(targetEntity = Person.class)
  private long userId;
  private Date issuedDate;
  private int noOfDays;
  private long issuedBy;
  private double finePerDay;
  private Date returnedDate;
  private long receivedBy;
  private double fineCalculated;
  private double finePaid;
  private boolean bookReturned;
}