package com.restapi.RestAPI.service;

import com.restapi.RestAPI.model.Livro;
import com.restapi.RestAPI.repository.LivroRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@org.springframework.stereotype.Service
public class LivroService {
    private final LivroRepository repository;

    public LivroService(LivroRepository repository) {
        this.repository = repository;
    }

    public Livro create(Livro model){
        if(model == null){
            throw new IllegalArgumentException("Ta vazio isso ai man!");
        }
        if(model.getAutor().isBlank() || model.getTitulo().isBlank() || model.getDescricao().isBlank()){
            throw new IllegalArgumentException("Ta errado os atributo nisso ai man!");
        }

        return repository.save(model);
    }

    public List<Livro> createAll(List<Livro> model){
        if(model == null){
            throw new IllegalArgumentException("Ta vazio isso ai man!");
        }
        for (Livro modelI : model){
            if(modelI.getAutor().isBlank() || modelI.getTitulo().isBlank() || modelI.getGenero().isBlank()){
                throw new IllegalArgumentException("Ta errado os atributo nisso ai man!");
            }
        }

        return repository.saveAll(model);
    }

    public List<Livro> listAll(){
        List<Livro> list = repository.findAll();
        if(list.isEmpty()){
            throw new EmptyResultDataAccessException("Nenhum registro encontrado!", 1);
        }

        return repository.findAll();
    }

    public List<String> listOne(String query){
        return switch (query) {
            case "autor" -> repository.listAutor();
            case "titulo" -> repository.listTitulo();
            case "genero" -> repository.listGenero();
            case "ano_lancamento" -> repository.listAnoLancamento();
            case "descricao" -> repository.listDescricao();
            default -> null;
        };
    }

    public List<Livro> listSortQuery(String query){
        return repository.findAll(Sort.by(query));
    }

    public void deleteById(Long id){
        repository.deleteById(id);
    }

    @Transactional
    public Livro update(Long id, Livro model){
        Livro modelUpdated = repository.findById(id).orElseThrow(() -> new RuntimeException("Livro com o id: " + id + " não encontrado!"));

        modelUpdated.setTitulo(model.getTitulo());
        modelUpdated.setAutor(model.getAutor());
        modelUpdated.setGenero(model.getGenero());

        return repository.save(modelUpdated);
    }
}
