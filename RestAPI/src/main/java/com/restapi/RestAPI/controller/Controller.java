package com.restapi.RestAPI.controller;

import com.restapi.RestAPI.service.Service;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
    private final Service service;

    public Controller(Service service) {
        this.service = service;
    }
}
