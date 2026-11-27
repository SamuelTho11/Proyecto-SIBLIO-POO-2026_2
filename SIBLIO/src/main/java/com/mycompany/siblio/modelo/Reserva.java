/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.siblio.modelo;

import java.time.LocalDate;

/**
 *
 * @author Samuel
 */
public class Reserva {
    private static int contadorId = 1; 
    private int idReserva; 
    private LocalDate fechaReserva; 
    private LocalDate fechaExpiracion; 
    private EstadoReserva estado; 
    private Usuario usuario; 
    private Material material;

    public Reserva() {
        this.idReserva = contadorId++;
        this.fechaReserva = LocalDate.now();
        this.fechaExpiracion = fechaReserva.plusDays(3);
        this.estado = EstadoReserva.ACTIVA;
    }
    
    public Reserva(Usuario usuario, Material material, int diasVigencia) { 
        if (usuario == null || material == null) { 
            throw new IllegalArgumentException("La reserva requiere usuario y material.");
        } 
        if (diasVigencia < 1) { 
            throw new IllegalArgumentException("La vigencia mínima de reserva es 1 día."); 
        } 
        this.idReserva = contadorId++; 
        this.usuario = usuario; 
        this.material = material; 
        this.fechaReserva = LocalDate.now(); 
        this.fechaExpiracion = fechaReserva.plusDays(diasVigencia); 
        this.estado = EstadoReserva.ACTIVA;
    }

    public static int getContadorId() {
        return contadorId;
    }

    public static void setContadorId(int contadorId) {
        Reserva.contadorId = contadorId;
    }

    public int getIdReserva() {
        return idReserva;
    }

    public void setIdReserva(int idReserva) {
        this.idReserva = idReserva;
    }

    public LocalDate getFechaReserva() {
        return fechaReserva;
    }

    public void setFechaReserva(LocalDate fechaReserva) {
        this.fechaReserva = fechaReserva;
    }

    public LocalDate getFechaExpiracion() {
        return fechaExpiracion;
    }

    public void setFechaExpiracion(LocalDate fechaExpiracion) {
        this.fechaExpiracion = fechaExpiracion;
    }

    public EstadoReserva getEstado() {
        return estado;
    }

    public void setEstado(EstadoReserva estado) {
        this.estado = estado;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }
    
    public void confirmarReserva() throws EstadoOperacionException{ 
        if (estado != EstadoReserva.ACTIVA) { 
            throw new EstadoOperacionException("Solo se puede confirmar una reserva activa."); 
        } 
        this.estado = EstadoReserva.CUMPLIDA; 
    }
    
    public void cancelarReserva() throws EstadoOperacionException { 
        if (estado != EstadoReserva.ACTIVA) { 
            throw new EstadoOperacionException("Solo se puede cancelar una reserva activa."); 
        } 
        this.estado = EstadoReserva.CANCELADA; 
    }
    
    public boolean estaVigente() { 
        return estado == EstadoReserva.ACTIVA && !LocalDate.now().isAfter( fechaExpiracion); 
    }
}
