package com.restapi.RestAPI.controller;

import com.restapi.RestAPI.model.Model;
import com.restapi.RestAPI.service.Service;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
public class Controller {
    private final Service service;

    public Controller(Service service) {
        this.service = service;
    }

    @PostMapping("/create")
    public ResponseEntity<Model> create(@Validated @RequestBody Model model){
        Model create = service.create(model);
        return ResponseEntity.created(URI.create("/create/" + create.getId())).body(create);
    }

    @PostMapping("/createAll")
    public ResponseEntity<List<Model>> createAll(@Validated @RequestBody List<Model> model){
        List<Model> createAll = service.createAll(model);
        return ResponseEntity.created(URI.create("/createAll/")).body(createAll);
    }

    @GetMapping("/list")
    public ResponseEntity<List<Model>> list(){
        return ResponseEntity.ok(service.list());
    }

    @GetMapping("/list/{query}")
    public ResponseEntity<List<Model>> listQuery(@Validated @PathVariable String query){
        return ResponseEntity.ok(service.listQuery(query));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id){
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @Validated @RequestBody Model model){
        try {
            Model updatedModel = service.update(id, model);
            return ResponseEntity.ok(updatedModel);
        } catch (ConfigDataResourceNotFoundException ex) {
            return ResponseEntity.notFound().build();
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }
}
