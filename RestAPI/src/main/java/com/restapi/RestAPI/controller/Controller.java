package com.restapi.RestAPI.controller;

import com.restapi.RestAPI.model.Model;
import com.restapi.RestAPI.service.Service;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
    private final Service service;

    public Controller(Service service) {
        this.service = service;
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@Validated @RequestBody Model model){
        return new ResponseEntity<Model>(service.create(model), HttpStatus.CREATED);
    }

    @GetMapping("/list")
    public ResponseEntity<?> list(){
        return new ResponseEntity<>(service.list(), HttpStatus.OK);
    }
}
