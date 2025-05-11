package com.restapi.RestAPI.service;

import com.restapi.RestAPI.model.Model;
import com.restapi.RestAPI.repository.Repository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@org.springframework.stereotype.Service
public class Service {
    private final Repository repository;

    public Service(Repository repository) {
        this.repository = repository;
    }

    public Model create(Model model){
        if(model == null){
            throw new IllegalArgumentException("Ta vazio isso ai man");
        }
        if(model.getAutor().isBlank() || model.getTitulo().isBlank() || model.getGenero().isBlank()){
            throw new IllegalArgumentException("Ta errado os atributo nisso ai man");
        }

        return repository.save(model);
    }
}
