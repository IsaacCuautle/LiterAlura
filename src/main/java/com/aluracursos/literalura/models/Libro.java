package com.aluracursos.literalura.models;

import jakarta.persistence.*;

@Entity
@Table(name = "libros")
public class Libro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique = true)
    private String titulo;
    private String autor;

    private String lenguaje;

    public Libro() {
    }

    public Libro(DatosResultados datosLibro) {

        this.titulo = datosLibro.titulo();
        this.autor = datosLibro.autorList().get(0).nombreAutor();
        this.lenguaje = datosLibro.language().get(0);
    }

    public long getId() {
        return id;
    }


    public String getTitulo() {
        return titulo;
    }


    public String getAutor() {
        return autor;
    }

    public String getLenguaje() {
        return lenguaje;
    }

}
