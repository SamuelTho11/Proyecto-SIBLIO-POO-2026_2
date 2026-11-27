package com.mycompany.siblio.modelo;

import com.mycompany.siblio.interfaces.Exportable;
import com.mycompany.siblio.interfaces.Prestable;
import com.mycompany.siblio.interfaces.Reservable;

/**
 * Representa una revista dentro del catálogo de publicaciones de SIBLIO.
 */
public class Revista extends Publicacion implements Exportable, Prestable, Reservable {
    private int numeroEdicion;
    private String periodicidad;

    public Revista() {
        super();
    }

    public Revista(int idRevista, String titulo, String autor, String editorial,
                   int anioPublicacion, int numeroEdicion, String periodicidad) {
        super(idRevista, titulo, "REV-" + idRevista, autor, editorial, anioPublicacion);
        this.numeroEdicion = numeroEdicion;
        this.periodicidad = periodicidad;
    }

    @Override
    public String getTipoMaterial() {
        return "Revista";
    }

    @Override
    public String mostrarFicha() {
        return "Revista #" + getIdMaterial() + "\n"
                + "  Título:       " + getTitulo() + "\n"
                + "  Editor:       " + getAutor() + "\n"
                + "  Edición:      #" + numeroEdicion + " (" + periodicidad + ", " + getAnioPublicacion() + ")\n"
                + "  Ejemplares:   " + getEjemplares().size()
                + " (disponibles: " + contarEjemplaresDisponibles() + ")";
    }

    public int getNumeroEdicion() {
        return numeroEdicion;
    }

    public void setNumeroEdicion(int numeroEdicion) {
        this.numeroEdicion = numeroEdicion;
    }

    public String getPeriodicidad() {
        return periodicidad;
    }

    public void setPeriodicidad(String periodicidad) {
        this.periodicidad = periodicidad;
    }

    @Override
    public String toJson() {
        return String.format("{\"tipo\":\"Revista\",\"id\":%d,\"titulo\":\"%s\",\"editor\":\"%s\",\"edicion\":%d,\"periodicidad\":\"%s\",\"anio\":%d,\"disponibles\":%d}",
            getIdMaterial(), getTitulo(), getAutor(), numeroEdicion,periodicidad, getAnioPublicacion(), contarEjemplaresDisponibles());
    }

    @Override
    public boolean estaDisponible() {
        return puedePrestarse();
    }

    @Override
    public int getDiasMaximosPrestamo() {
        return 3;
    }

    @Override
    public boolean admiteReserva() {
        return !getEjemplares().isEmpty();
    }

    @Override
    public int getDiasVigenciaReserva() {
        return 2;
    }
}
