package com.aluracursos.literalura.models;

import jakarta.persistence.*;

@Entity
@Table (name = "autores")
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String autor;
    private String nacimiento;
    private String defuncion;

    public Author(){}

    public Author(DatosAutor datosAutors) {
        this.autor = datosAutors.nombreAutor();
        this.nacimiento = datosAutors.nacimiento().toString();
        this.defuncion = datosAutors.defuncion().toString();
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getNacimiento() {
        return nacimiento;
    }

    public void setNacimiento(String nacimiento) {
        this.nacimiento = nacimiento;
    }

    public String getDefuncion() {
        return defuncion;
    }

    public void setDefuncion(String defuncion) {
        this.defuncion = defuncion;
    }
}
