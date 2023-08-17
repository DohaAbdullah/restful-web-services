package com.rest.webservices.restfulwebservices.controller;


import com.rest.webservices.restfulwebservices.entitiy.User;
import com.rest.webservices.restfulwebservices.UserDaoService;
import com.rest.webservices.restfulwebservices.UserNotFoundException;
import jakarta.validation.Valid;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class UserController {
    private UserDaoService service;
    public UserController(UserDaoService service) {
        this.service = service;
    }
    @GetMapping("/users")
    public List<User> retrieveAllUsers() {
        return service.findAll();
    }
    @GetMapping("/users/{id}")
    public EntityModel<User> retrieveOneUser(@PathVariable int id) {
        User user = service.findOne(id);//it will return null if do not find any record
        if(user == null){
            throw new UserNotFoundException("id" +id);
        }

        EntityModel<User> entityModel = EntityModel.of(user);
        WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).retrieveAllUsers());
        entityModel.add(link.withRel("all-users"));
        return entityModel;
    }
    @DeleteMapping("users/{id}")
    public void deleteUser(@PathVariable int id){
        service.deleteById(id);

    }

    @PostMapping("/createUser")
    public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
        User savedUser = service.save(user);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUser.getId())
                .toUri();
        return ResponseEntity.created(location).build();

    }
}
