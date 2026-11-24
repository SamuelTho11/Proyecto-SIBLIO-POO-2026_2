package com.mycompany.siblio.modelo;

/**
 * Nivel intermedio de herencia para materiales publicados (libros y revistas).
 */
public abstract class Publicacion extends Material {
    private String autor;
    private String editorial;
    private int anioPublicacion;

    public Publicacion() {
        super();
    }

    public Publicacion(int idMaterial, String titulo, String codigoClasificacion,
                       String autor, String editorial, int anioPublicacion) {
        super(idMaterial, titulo, codigoClasificacion);
        this.autor = autor;
        this.editorial = editorial;
        this.anioPublicacion = anioPublicacion;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getEditorial() {
        return editorial;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    public int getAnioPublicacion() {
        return anioPublicacion;
    }

    public void setAnioPublicacion(int anioPublicacion) {
        this.anioPublicacion = anioPublicacion;
    }
}
