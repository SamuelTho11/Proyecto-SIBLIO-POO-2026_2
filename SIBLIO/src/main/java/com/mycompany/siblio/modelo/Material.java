package com.mycompany.siblio.modelo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Clase base abstracta para todos los materiales bibliográficos del sistema SIBLIO.
 * Mantiene una relación de composición con sus ejemplares físicos.
 */
public abstract class Material {
    private int idMaterial;
    private String titulo;
    private String codigoClasificacion;
    private final List<Ejemplar> ejemplares = new ArrayList<>();

    public Material() {
    }

    public Material(int idMaterial, String titulo, String codigoClasificacion) {
        this.idMaterial = idMaterial;
        this.titulo = titulo;
        this.codigoClasificacion = codigoClasificacion;
    }

    public Ejemplar agregarEjemplar(String codigoInventario, String ubicacion) {
        Ejemplar e = new Ejemplar(codigoInventario, ubicacion, this);
        ejemplares.add(e);
        return e;
    }

    public boolean puedePrestarse() {
        return contarEjemplaresDisponibles() > 0;
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

    public abstract String mostrarFicha();

    public abstract String getTipoMaterial();

    public int getIdMaterial() {
        return idMaterial;
    }

    public void setIdMaterial(int idMaterial) {
        this.idMaterial = idMaterial;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getCodigoClasificacion() {
        return codigoClasificacion;
    }

    public void setCodigoClasificacion(String codigoClasificacion) {
        this.codigoClasificacion = codigoClasificacion;
    }
}
