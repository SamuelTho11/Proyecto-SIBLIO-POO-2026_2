package com.mycompany.siblio.modelo;

import com.mycompany.siblio.enums.EstadoEjemplar;
import com.mycompany.siblio.interfaces.Exportable;
import com.mycompany.siblio.interfaces.Prestable;

/**
 * Representa un ejemplar físico perteneciente a un Material en SIBLIO.
 *
 * @author Samuel
 */
public class Ejemplar implements Prestable, Exportable{
    private String codigoInventario;
    private EstadoEjemplar estado;
    private String ubicacion;
    private Material material;

    Ejemplar() {
        this.estado = EstadoEjemplar.DISPONIBLE;
    }

    Ejemplar(String codigoInventario, String ubicacion, Material material) {
        if (material == null) {
            throw new IllegalArgumentException("Un ejemplar debe pertenecer a un material.");
        }
        this.codigoInventario = codigoInventario;
        this.ubicacion = ubicacion;
        this.material = material;
        this.estado = EstadoEjemplar.DISPONIBLE;
    }

    public void cambiarEstado(EstadoEjemplar estado) {
        if (estado == null) {
            throw new IllegalArgumentException("El estado del ejemplar no puede ser nulo.");
        }
        this.estado = estado;
    }

    public boolean consultarDisponibilidad() {
        return estado == EstadoEjemplar.DISPONIBLE;
    }

    public String getCodigoInventario() {
        return codigoInventario;
    }

    public void setCodigoInventario(String codigoInventario) {
        this.codigoInventario = codigoInventario;
    }

    public EstadoEjemplar getEstado() {
        return estado;
    }

    public void setEstado(EstadoEjemplar estado) {
        cambiarEstado(estado);
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public Material getMaterial() {
        return material;
    }

    @Override
    public boolean estaDisponible() {
        return consultarDisponibilidad();
    }

    @Override
    public int getDiasMaximosPrestamo() {
        return 7;
    }

    @Override
    public String toJson() {
        return String.format("{\"codigoInventario\":\"%s\",\"ubicacion\":\"%s\",\"estado\":\"%s\",\"material\":\"%s\"}",
            codigoInventario, ubicacion, estado, material.getTitulo());
    }
}
