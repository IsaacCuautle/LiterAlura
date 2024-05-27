package com.aluracursos.literalura.main;

import com.aluracursos.literalura.models.DatosLibro;
import com.aluracursos.literalura.models.DatosResultados;
import com.aluracursos.literalura.models.Libro;
import com.aluracursos.literalura.services.ConvierteDatos;
import com.aluracursos.literalura.services.RequestAPI;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    private RequestAPI requestAPI = new RequestAPI();
    private Scanner scanner = new Scanner(System.in);
    private String urlBase ="https://gutendex.com/books/";
    private ConvierteDatos convierteDatos = new ConvierteDatos();

    public void showMenu()
    {
        var opcion = -1;
        while (opcion != 0){
            var menu ="""
                    
                    LiterAlura - Busqueda de Libros y Autores
                    
                    1 - Buscar un libro
                    0 - Salir
                    
                    """;

            System.out.println(menu);

            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion){
                case 1:
                    buscarLibro();
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

    private void buscarLibro()
    {
        DatosLibro datosLibro = getDatosLibro();
        try {
            Libro libro = new Libro(datosLibro.resultados().get(0));
            System.out.println("Libro["+"Titulo: " + libro.getTitulo() + ", Author: " + libro.getAutor() + ", Lenguaje: " + libro.getLenguaje()+"]");
        }catch (Exception e){
            System.out.println("No se encontro el libro");
        }
    }
}
