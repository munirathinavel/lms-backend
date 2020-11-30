package com.cmvel.lms.model;

import lombok.Data;

@Data
public class Address {
    private String streetAddress;
    private String city;
    private String state;
    private String zipCode;
    private String country;
}
