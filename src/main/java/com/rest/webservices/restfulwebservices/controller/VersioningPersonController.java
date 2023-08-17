package com.rest.webservices.restfulwebservices.controller;

import com.rest.webservices.restfulwebservices.entitiy.Name;
import com.rest.webservices.restfulwebservices.entitiy.PersonV1;
import com.rest.webservices.restfulwebservices.entitiy.PersonV2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VersioningPersonController {

    @GetMapping("/v1/person")
    public PersonV1 getFirstVersionOfPerson(){
        return new PersonV1("John deo");
    }

    @GetMapping("/v2/person")
    public PersonV2 getSecondVersionOfPerson(){
        return new PersonV2(new Name("john" , "deo"));
    }

    @GetMapping( path = "person" , params = "version=1")
    public PersonV1 getFirstVersionOfPersonParameter(){
        return new PersonV1("John deo");
    }
    @GetMapping( path = "person" , params = "version=2")
    public PersonV2 getSecondVersionOfPersonParameter(){
        return new PersonV2(new Name("john" , "deo"));
    }
    @GetMapping( path = "person/header" , headers = "X-API-VERSION=1")
    public PersonV1 getFirstVersionOfPersonRequestHeader(){
        return new PersonV1("John deo");
    }

    @GetMapping( path = "person/header" , headers = "X-API-VERSION=2")
    public PersonV2 getSecondVersionOfPersonRequestHeader(){
        return new PersonV2(new Name("john" , "deo"));
    }

    @GetMapping( path = "person/accept" , produces = "application/vnd.company.app-v1+json")
    public PersonV1 getFirstVersionOfPersonAcceptHeader(){
        return new PersonV1("John deo");
    }

    @GetMapping( path = "person/accept" , produces = "application/vnd.company.app-v2+json")
    public PersonV2 getSecondVersionOfPersonAcceptHeader(){
        return new PersonV2(new Name("john" , "deo"));
    }

}
