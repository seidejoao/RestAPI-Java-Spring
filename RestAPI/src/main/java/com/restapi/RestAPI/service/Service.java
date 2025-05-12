package com.restapi.RestAPI.service;

import com.restapi.RestAPI.model.Model;
import com.restapi.RestAPI.repository.Repository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

@org.springframework.stereotype.Service
public class Service {
    private final Repository repository;

    public Service(Repository repository) {
        this.repository = repository;
    }

    public Model create(Model model){
        if(model == null){
            throw new IllegalArgumentException("Ta vazio isso ai man!");
        }
        if(model.getAutor().isBlank() || model.getTitulo().isBlank() || model.getGenero().isBlank()){
            throw new IllegalArgumentException("Ta errado os atributo nisso ai man!");
        }

        return repository.save(model);
    }

    public Iterable<Model> list(){
        List<Model> list = repository.findAll();
        if(list.isEmpty()){
            throw new EmptyResultDataAccessException("Nenhum registro encontrado!", 1);
        }

        return repository.findAll();
    }
}
