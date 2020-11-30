package com.cmvel.lms.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "book")
@Data
public class Book {
  @Id
  @GeneratedValue
  private long id;
  private String ISBN;
  private String title;
  private String subject;
  private String publisher;
  private String language;
  private int numberOfPages;
  private String authors;
  private String donatedBy;
}

