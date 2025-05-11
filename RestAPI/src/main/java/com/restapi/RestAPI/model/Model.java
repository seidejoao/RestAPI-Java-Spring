package com.restapi.RestAPI.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="livros")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Model {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String autor;
    private String genero;

    @Override
    public String toString() {
        return
            "\n-Id: " + id +
            "\n-Autor: " + autor +
            "\n-Título: " + titulo +
            "\n-Genêro: " + genero;
    }
}
