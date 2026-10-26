/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.siblio.modelo;

import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Samuel
 */
public class Ejemplar {
    
    private static final List<String> ESTADOS_VALIDOS =
            Arrays.asList("disponible", "prestado", "dañado", "perdido");

    private String codigoInventario;
    private String estado;
    private String ubicacion;
    private final Libro libro; 
    
    
    Ejemplar(String codigoInventario, String ubicacion, Libro libro) {
        if (libro == null) {
            throw new IllegalArgumentException("Un ejemplar debe pertenecer a un libro.");
        }
        this.codigoInventario = codigoInventario;
        this.ubicacion = ubicacion;
        this.libro = libro;
        this.estado = "disponible";
    }
    
      public void cambiarEstado(String estado) {
        if (!ESTADOS_VALIDOS.contains(estado)) {
            throw new IllegalArgumentException("Estado no válido: " + estado
                    + ". Use: " + ESTADOS_VALIDOS);
        }
        this.estado = estado;
    }

    public boolean consultarDisponibilidad() {
        return "disponible".equals(estado);
    }
    
     public String getCodigoInventario() { return codigoInventario; }
    public void setCodigoInventario(String codigoInventario) { this.codigoInventario = codigoInventario; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { cambiarEstado(estado); }

    public String getUbicacion() { return ubicacion; }
    public void setUbicacion(String ubicacion) { this.ubicacion = ubicacion; }

    public Libro getLibro() { return libro; }
    
}
