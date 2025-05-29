package com.restapi.RestAPI.repository;

import com.restapi.RestAPI.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LivroRepository extends JpaRepository<Livro, Long> {

    @Query("SELECT autor FROM Livro")
    public List<String> listTitulo();

    @Query("SELECT autor FROM Livro")
    public List<String> listAutor();

    @Query("SELECT autor FROM Livro")
    public List<String> listGenero();

    @Query("SELECT autor FROM Livro")
    public List<String> listAnoLancamento();

    @Query("SELECT autor FROM Livro")
    public List<String> listDescricao();
}
