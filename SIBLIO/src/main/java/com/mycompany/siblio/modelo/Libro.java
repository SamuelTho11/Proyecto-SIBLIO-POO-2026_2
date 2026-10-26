/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.siblio.modelo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Samuel
 */
public class Libro {
    
    private int idLibro;
    private String titulo;
    private String autor;
    private String editorial;
    private int anioPublicacion;
    private String categoria;
    
    private final List<Ejemplar> ejemplares = new ArrayList<>();
    
    public Libro(int idLibro, String titulo, String autor, String editorial,
                 int anioPublicacion, String categoria) {
        this.idLibro = idLibro;
        this.titulo = titulo;
        this.autor = autor;
        this.editorial = editorial;
        this.anioPublicacion = anioPublicacion;
        this.categoria = categoria;
    }

    public void registrarLibro() {
        if (titulo == null || titulo.trim().isEmpty()
                || autor == null || autor.trim().isEmpty()) {
            throw new IllegalStateException("El libro debe tener título y autor.");
        }
    }
    
    public void actualizarInformacion(String titulo, String autor, String editorial,
                                      int anioPublicacion, String categoria) {
        this.titulo = titulo;
        this.autor = autor;
        this.editorial = editorial;
        this.anioPublicacion = anioPublicacion;
        this.categoria = categoria;
    }
    
    public String mostrarFicha() {
        return "Libro #" + idLibro + "\n"
                + "  Título:     " + titulo + "\n"
                + "  Autor:      " + autor + "\n"
                + "  Editorial:  " + editorial + " (" + anioPublicacion + ")\n"
                + "  Categoría:  " + categoria + "\n"
                + "  Ejemplares: " + ejemplares.size()
                + " (disponibles: " + contarEjemplaresDisponibles() + ")";
    }
    
     public Ejemplar agregarEjemplar(String codigoInventario, String ubicacion) {
        Ejemplar e = new Ejemplar(codigoInventario, ubicacion, this);
        ejemplares.add(e);
        return e;
    }
     
      public boolean puedePrestarse() {
        return !ejemplares.isEmpty();
    }
    
      public int contarEjemplaresDisponibles() {
        int total = 0;
        for (Ejemplar e : ejemplares) {
            if (e.consultarDisponibilidad()) {
                total++;
            }
        }
        return total;
    }
      
      public List<Ejemplar> getEjemplares() {
        return Collections.unmodifiableList(ejemplares);
    }
      
      
      public int getIdLibro() { return idLibro; }
    public void setIdLibro(int idLibro) { this.idLibro = idLibro; }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getAutor() { return autor; }
    public void setAutor(String autor) { this.autor = autor; }

    public String getEditorial() { return editorial; }
    public void setEditorial(String editorial) { this.editorial = editorial; }

    public int getAnioPublicacion() { return anioPublicacion; }
    public void setAnioPublicacion(int anioPublicacion) { this.anioPublicacion = anioPublicacion; }

    public String getCategoria() { return categoria; }
    public void setCategoria(String categoria) { this.categoria = categoria; }
}
