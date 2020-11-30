package com.cmvel.lms.model;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.sql.Date;

@Data
public class IssuedBookVO {
        private long id;
        private long bookId;
        private String bookTitle;
        private long userId;
        private String userName;
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
