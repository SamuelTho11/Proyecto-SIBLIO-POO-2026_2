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
        this.estado = estado;
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
        this.limitePrestamos = limitePrestamos;
    }
    
    public Prestamo solicitarPrestamo(Ejemplar ejemplar){
        return null;
    }
    
    public void pagarMulta(Multa m){
        
    }

    public List<Prestamo> consultarHistorial(){
        return new ArrayList<>(prestamos);
    }

    @Override
    public void consultarInfo() {
        System.out.println("ID: "+ getId() + "\nNombre: " + getNombre() + "\nDocumento Identidad: " + getDocumentoIdentidad() + "\nCorreo:" + getCorreo()
                + "\nTelefono: " + getTelefono() + "\nTipo de usuario: "+tipoUsuario + "\nEstado: " + estado + "\nLimite de Prestamos:" +
                limitePrestamos);
    }
    
}
