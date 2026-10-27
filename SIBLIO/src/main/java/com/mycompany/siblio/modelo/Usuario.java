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
    private String estado;
    private int limitePrestamos;
    private final List<Prestamo> prestamos = new ArrayList<>();

    public Usuario(String tipoUsuario, String estado, int limitePrestamos, int id, String nombre, String documentoIdentidad, String correo, String telefono) {
        super(id, nombre, documentoIdentidad, correo, telefono);
        this.tipoUsuario = tipoUsuario;
        this.estado = "activo";
        this.limitePrestamos = limitePrestamos;
    }

    public String getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
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
    
    public Prestamo solicitarPrestamo(Ejemplar ejemplar){
        if(ejemplar==null){
            throw new IllegalArgumentException("Debe seleccionar un ejemplar.");
        }
        if(!"activo".equals(estado)){
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
        return new Prestamo(this, ejemplar);
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
    
    public boolean puedeSolicitarPrestamo(){
        return "activo".equals(estado) && prestamosActivos() < limitePrestamos && !multasPendientes();
    }
    
    private int prestamosActivos(){
        int total = 0;
        for(Prestamo p : prestamos){
            if("activo".equals(p.getEstado())){
                total++;
            }
        }
        return total;
    }
    
    private boolean multasPendientes(){
        for(Prestamo p: prestamos){
            Multa m = p.getMulta();
            if(m!=null && "pendiente".equals(m.getEstado())){
                return true;
            }
        }
        return false;
    }
    
    public void agregarPrestamo(Prestamo prestamo){
        prestamos.add(prestamo);
    }

    @Override
    public void consultarInfo() {
        System.out.println("ID: "+ getId() + "\nNombre: " + getNombre() + "\nDocumento Identidad: " + getDocumentoIdentidad() + 
                "\nCorreo:" + getCorreo() + "\nTelefono: " + getTelefono() + "\nTipo de usuario: "+tipoUsuario + "\nEstado: " + estado + 
                "\nLimite de Prestamos:" + limitePrestamos);
    }
}
