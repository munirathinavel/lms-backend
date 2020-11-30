package com.cmvel.lms.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "person")
public class Person {
    @GeneratedValue
    @Id
    private Long id;
    @Column(name = "name")
    private String name;
//    @OneToOne
//    private Address address;
    @Column(name = "email")
    private String email;
    @Column(name = "phone")
    private String phone;

}
