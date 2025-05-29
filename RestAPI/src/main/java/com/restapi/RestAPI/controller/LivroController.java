package com.restapi.RestAPI.controller;

import com.restapi.RestAPI.model.Livro;
import com.restapi.RestAPI.service.LivroService;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
public class LivroController {
    private final LivroService service;

    public LivroController(LivroService service) {
        this.service = service;
    }

    @PostMapping("/create")
    public ResponseEntity<Livro> create(@Validated @RequestBody Livro model){
        Livro create = service.create(model);
        return ResponseEntity.created(URI.create("/create/" + create.getId())).body(create);
    }

    @PostMapping("/createAll")
    public ResponseEntity<List<Livro>> createAll(@Validated @RequestBody List<Livro> model){
        List<Livro> createAll = service.createAll(model);
        return ResponseEntity.created(URI.create("/createAll/")).body(createAll);
    }

    @GetMapping("/list")
    public ResponseEntity<List<Livro>> list(){
        return ResponseEntity.ok(service.listAll());
    }

    @GetMapping("/list/sort/{query}")
    public ResponseEntity<List<Livro>> listSortQuery(@Validated @PathVariable String query){
        return ResponseEntity.ok(service.listSortQuery(query));
    }

    @GetMapping("/list/{query}")
    public ResponseEntity<List<String>> listOne(@Validated @PathVariable String query){
        return ResponseEntity.ok(service.listOne(query));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id){
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @Validated @RequestBody Livro model){
        try {
            Livro updatedModel = service.update(id, model);
            return ResponseEntity.ok(updatedModel);
        } catch (ConfigDataResourceNotFoundException ex) {
            return ResponseEntity.notFound().build();
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }
}
