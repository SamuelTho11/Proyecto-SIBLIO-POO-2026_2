/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.siblio.modelo;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Samuel
 */
public class Usuario extends Persona {
    private String tipoUsuario;
    private int limitePrestamos;
    private final List<Prestamo> prestamos = new ArrayList<>();
    private final List<Reserva> reservas = new ArrayList<>();

    public Usuario() {
        super();
        this.limitePrestamos = 1;
    }

    public Usuario(int id, String nombre, String documentoIdentidad, String correo, String telefono, String tipoUsuario, int limitePrestamos) {
        super(id, nombre, documentoIdentidad, correo, telefono);
        this.tipoUsuario = tipoUsuario;
        setLimitePrestamos(limitePrestamos);
    }

    public String getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public int getLimitePrestamos() {
        return limitePrestamos;
    }

    public void setLimitePrestamos(int limitePrestamos) {
        if(limitePrestamos<1){
            throw new IllegalArgumentException("El limite de prestamos debe ser minimo de 1.");
        }
        this.limitePrestamos = limitePrestamos;
    }
    public Prestamo solicitarPrestamo(Ejemplar ejemplar) { 
        return solicitarPrestamo(ejemplar, Prestamo.DIAS_PRESTAMO); 
    }
    
    public Prestamo solicitarPrestamo(Ejemplar ejemplar, int dias){
        if(ejemplar==null){
            throw new IllegalArgumentException("Debe seleccionar un ejemplar.");
        }
        if(dias<1){
            throw new IllegalArgumentException("Los días de préstamo deben ser al menos 1.");
        }
        if(getEstado() != EstadoPersona.ACTIVO){
            throw new IllegalStateException("El usuario debe estar activo para solicitar prestamos.");
        }
        if(prestamosActivos()>= limitePrestamos){
            throw new IllegalStateException("El usuario alcanzó el limite de prestamos activos.");
        }
        if(multasPendientes()){
            throw new IllegalStateException("El usuario cuenta con multas pendientes");
        }
        if(!ejemplar.consultarDisponibilidad()){
            throw new IllegalStateException("El ejemplar "+ ejemplar.getCodigoInventario() + " no se encuentra disponible.");
        }
        return new Prestamo(this, ejemplar, dias);
    }
    
    public Reserva reservarMaterial(Material material){
        return reservarMaterial(material, 3);
    }
    
    public Reserva reservarMaterial(Material material, int diasVigencia){
        if(material == null){
            throw new IllegalArgumentException("Debe indicar un material para reservar");
        }
        synchronized(material){
            if (!puedeSolicitarPrestamo()) {
                throw new IllegalStateException("El usuario no está habilitado para realizar reservas");
        }
        Reserva reserva = new Reserva(this, material, diasVigencia);
        reservas.add(reserva);
        return reserva;
        }
    }
    
    public void pagarMulta(Multa m){
        if(m==null){
            throw new IllegalArgumentException("Debe de indicar la multa a pagar.");
        }
        boolean esMia=false;
        for(Prestamo p: prestamos){
            if(p.getMulta() == m){
                esMia=true;
                break;
            }
        }
        if(!esMia){
            throw new IllegalArgumentException("Esta multa no es de este usuario.");
        }
        m.registrarPago();
    }

    public List<Prestamo> consultarHistorial(){
        return new ArrayList<>(prestamos);
    }
    
    public List<Reserva> consultarReservas(){
        return new ArrayList<>(reservas);
    }
    
    public boolean puedeSolicitarPrestamo(){
        return getEstado() == EstadoPersona.ACTIVO && prestamosActivos() < limitePrestamos && !multasPendientes();
    }
    
    private int prestamosActivos(){
        int total = 0;
        for(Prestamo p : prestamos){
            if(p.getEstado() == EstadoPrestamo.ACTIVO){
                total++;
            }
        }
        return total;
    }
    
    private boolean multasPendientes(){
        for(Prestamo p: prestamos){
            Multa m = p.getMulta();
            if(m != null && m.getEstado() == EstadoMulta.PENDIENTE){
                return true;
            }
        }
        return false;
    }
    
    void agregarPrestamo(Prestamo prestamo){
        prestamos.add(prestamo);
    }

    @Override
    public String consultarInfo() {
        return "ID: "+ getId() + "\nNombre: " + getNombre() + "\nDocumento Identidad: " + getDocumentoIdentidad() + 
                "\nCorreo: " + getCorreo() + "\nTelefono: " + getTelefono() + "\nTipo de usuario: "+tipoUsuario + "\nEstado: " + 
                getEstado()+"\nLimite de Prestamos: " + limitePrestamos;
    }
}
