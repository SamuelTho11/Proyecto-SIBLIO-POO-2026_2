package com.mycompany.siblio.modelo;

import com.mycompany.siblio.excepciones.EstadoOperacionException;
import com.mycompany.siblio.interfaces.Exportable;
import com.mycompany.siblio.interfaces.Prestable;
import com.mycompany.siblio.interfaces.Reservable;

/**
 * Representa un libro dentro del catálogo de publicaciones de SIBLIO.
 *
 * @author Samuel
 */
public class Libro extends Publicacion implements Prestable, Reservable, Exportable {
    private String categoria;

    public Libro() {
        super();
    }

    public Libro(int idLibro, String titulo, String autor, String editorial,
                 int anioPublicacion, String categoria) {
        super(idLibro, titulo, "LIB-" + idLibro, autor, editorial, anioPublicacion);
        setCategoria(categoria);
    }

    public void registrarLibro() throws EstadoOperacionException{
        if (getTitulo() == null || getTitulo().trim().isEmpty()
                || getAutor() == null || getAutor().trim().isEmpty()) {
            throw new EstadoOperacionException("El libro debe tener título y autor.");
        }
    }

    public void actualizarInformacion(String titulo, String autor, String editorial, int anioPublicacion, String categoria) {
        setTitulo(titulo);
        setAutor(autor);
        setEditorial(editorial);
        setAnioPublicacion(anioPublicacion);
        setCategoria(categoria);
    }

    @Override
    public String getTipoMaterial() {
        return "Libro";
    }

    @Override
    public String mostrarFicha() {
        return "Libro #" + getIdMaterial() + "\n"
                + "  Título:     " + getTitulo() + "\n"
                + "  Autor:      " + getAutor() + "\n"
                + "  Editorial:  " + getEditorial() + " (" + getAnioPublicacion() + ")\n"
                + "  Categoría:  " + categoria + "\n"
                + "  Ejemplares: " + getEjemplares().size()
                + " (disponibles: " + contarEjemplaresDisponibles() + ")";
    }

    public int getIdLibro() {
        return getIdMaterial();
    }

    public void setIdLibro(int idLibro) {
        setIdMaterial(idLibro);
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        if (categoria == null || categoria.isBlank()) {
            throw new IllegalArgumentException("La categoría del libro no puede ser nula ni estar vacía.");
        }
        this.categoria = categoria;
    }

    @Override
    public boolean estaDisponible() {
        return puedePrestarse();
    }

    @Override
    public int getDiasMaximosPrestamo() {
        return 14;
    }

    @Override
    public boolean admiteReserva() {
        return !getEjemplares().isEmpty();
    }

    @Override
    public int getDiasVigenciaReserva() {
        return 5;
    }

    @Override
    public String toJson() {
        return String.format("{\"tipo\":\"Libro\",\"id\":%d,\"titulo\":\"%s\",\"autor\":\"%s\",\"editorial\":\"%s\",\"anio\":%d,\"categoria\":\"%s\",\"disponibles\":%d}",
            getIdMaterial(), getTitulo(), getAutor(), getEditorial(), getAnioPublicacion(), categoria, contarEjemplaresDisponibles());
    }
}
