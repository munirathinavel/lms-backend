package com.cmvel.lms.controller;

import com.cmvel.lms.dao.PersonRepository;
import com.cmvel.lms.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", allowedHeaders = "*", maxAge = 3600)
@RestController
@RequestMapping("member")
public class MemberController {

    @Autowired
    private PersonRepository personRepository;

    @GetMapping
    public List<Person> getMembers(){
       return personRepository.findAll();
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*", maxAge = 3600)
    @GetMapping("/{id}")
    public Person getMembers(@PathVariable("id") long id){
        return personRepository.findById(id).orElseThrow(() -> new RuntimeException("Member not found for this id :: " + id));
    }


    @CrossOrigin(origins = "*", allowedHeaders = "*", maxAge = 3600)
    @PostMapping
    public Person createMember(@RequestBody Person person){
        return personRepository.save(person);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*", maxAge = 3600)
    @PutMapping("{id}")
    public ResponseEntity<Person> updateMember(@PathVariable("id") long id, @RequestBody Person person){
        Person personInDB = personRepository.findById(id).orElseThrow(() -> new RuntimeException("Member not found for this id :: " + id));

        personInDB.setEmail(person.getEmail());
        personInDB.setName(person.getName());
        personInDB.setPhone(person.getPhone());

        return ResponseEntity.ok(personRepository.save(personInDB));
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*", maxAge = 3600)
    @DeleteMapping("{id}")
    public Boolean deleteMember(@PathVariable("id") long id){
        Person personInDB = personRepository.findById(id).orElseThrow(() ->new RuntimeException("Member not found for this id:" + id));

        personRepository.deleteById(id);
        return  true;
    }

}
