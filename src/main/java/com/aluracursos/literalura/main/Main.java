package com.aluracursos.literalura.main;

import com.aluracursos.literalura.models.Author;
import com.aluracursos.literalura.models.DatosLibro;
import com.aluracursos.literalura.models.Libro;
import com.aluracursos.literalura.repository.AuthorRepository;
import com.aluracursos.literalura.repository.LibroRepository;
import com.aluracursos.literalura.services.ConvierteDatos;
import com.aluracursos.literalura.services.RequestAPI;

import java.util.List;
import java.util.Scanner;

public class Main {
    private RequestAPI requestAPI = new RequestAPI();
    private Scanner scanner = new Scanner(System.in);
    private String urlBase ="https://gutendex.com/books/";
    private ConvierteDatos convierteDatos = new ConvierteDatos();
    private LibroRepository libroRepository;
    private AuthorRepository authorRepository;
    private List<Libro> libros;
    private List<Author> autores;

    public Main(LibroRepository libroRepository, AuthorRepository authorRepository) {
        this.libroRepository = libroRepository;
        this.authorRepository = authorRepository;
    }

    // Mostrar el menu en consola
    public void showMenu()
    {
        var opcion = -1;
        while (opcion != 0){
            var menu ="""
                    **************************************************
                        LiterAlura - Busqueda de Libros y Autores
                    **************************************************
                    
                    Selecciona una opcion acontinuacion: 
                    
                    1 - Buscar un libro
                    2 - Consultar libros buscados
                    3 - Consultar autores
                    2 - Consultar autores de un año especifico
                    0 - Salir               
                    """;

            try {
                System.out.println(menu);
                opcion = scanner.nextInt();
                scanner.nextLine();
            }catch (Exception e){

                System.out.println("Ingresa una opcion valida");
            }

            switch (opcion){
                case 1:
                    buscarLibro();
                    break;
                case 2:
                    consultarLibros();
                    break;
                case 3:
                    consultarAutores();
                    break;
                case 0:
                    System.out.println("Hasta luego");
                    break;
                default:
                    System.out.println("Ingresa una opcion valida");
            }
        }
    }

    // Extrae los datos de un libro
    private DatosLibro getDatosLibro() {
        System.out.println("Ingrese el nombre del libro");
        var busqueda = scanner.nextLine().toLowerCase().replace(" ","%20");
        var json = requestAPI.getData(urlBase +
                "?search=" +
                busqueda);

        DatosLibro datosLibro = convierteDatos.obtenerDatos(json, DatosLibro.class);
        return datosLibro;
    }

    // Busca un libro y guarda infromacion en la BD en sus tablas correspondientes
    private void buscarLibro()
    {
        DatosLibro datosLibro = getDatosLibro();

        try {
            Libro libro = new Libro(datosLibro.resultados().get(0));
            Author author = new Author(datosLibro.resultados().get(0).autorList().get(0));

            System.out.println("""
                    libro[
                        titulo: %s
                        author: %s
                        lenguaje: %s
                        descargas: %s
                    ]
                    """.formatted(libro.getTitulo(),
                    libro.getAutor(),
                    libro.getLenguaje(),
                    libro.getDescargas().toString()));

            libroRepository.save(libro);
            authorRepository.save(author);

        }catch (Exception e){
            System.out.println("no se encontro ese libro");
        }

    }

    // Trae los libros guardados en la BD
    private void consultarLibros() {
        libros = libroRepository.findAll();
        libros.stream().forEach(l -> {
            System.out.println("""    
                        Titulo: %s
                        Author: %s
                        Lenguaje: %s
                        Descargas: %s
                    """.formatted(l.getTitulo(),
                    l.getAutor(),
                    l.getLenguaje(),
                    l.getDescargas().toString()));
        });
    }

    // Trae todos los autores de los libros consultados en la BD
    private void consultarAutores() {
        autores = authorRepository.findAll();
        autores.stream().forEach(a -> {
            System.out.println("""
                        Autor: %s
                        Año de nacimiento: %s
                        Año de defuncion: %s
                    """.formatted(a.getAutor(),
                    a.getNacimiento().toString(),
                    a.getDefuncion().toString()));
        });
    }
}
