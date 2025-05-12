package com.restapi.RestAPI.service;

import com.restapi.RestAPI.model.Model;
import com.restapi.RestAPI.repository.Repository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

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

    public List<Model> list(){
        List<Model> list = repository.findAll();
        if(list.isEmpty()){
            throw new EmptyResultDataAccessException("Nenhum registro encontrado!", 1);
        }

        return repository.findAll();
    }

    public void deleteById(Long id){
        repository.deleteById(id);
    }

    @Transactional
    public Model update(Long id, Model model){
        Model modelUpdated = repository.findById(id).orElseThrow(() -> new RuntimeException("Livro com o id: " + id + " não econtrado!"));

        modelUpdated.setTitulo(model.getTitulo());
        modelUpdated.setAutor(model.getAutor());
        modelUpdated.setGenero(model.getGenero());

        return repository.save(modelUpdated);
    }
}
