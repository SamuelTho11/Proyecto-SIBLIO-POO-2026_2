/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.siblio.modelo;

/**
 *
 * @author Samuel
 */
public class Bibliotecario extends Persona {
    private String codigoEmpleado;
    private String cargo;

    public Bibliotecario() {
        super();
    }
    
    public Bibliotecario(int id, String nombre, String documentoIdentidad, String correo, String telefono, String codigoEmpleado, String cargo) {
        super(id, nombre, documentoIdentidad, correo, telefono);
        this.codigoEmpleado = codigoEmpleado;
        this.cargo = cargo;
    }

    public String getCodigoEmpleado() {
        return codigoEmpleado;
    }

    public void setCodigoEmpleado(String codigoEmpleado) {
        this.codigoEmpleado = codigoEmpleado;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }
    
    public Prestamo registrarPrestamo(Prestamo solicitud){
        if(getEstado() != EstadoPersona.ACTIVO){
            throw new IllegalStateException("El bibliotecario debe estar activo para registrar prestamos.");
        }
        if(solicitud == null || solicitud.getEstado() != EstadoPrestamo.SOLICITADO){
            throw new IllegalArgumentException("Debe indicar un prestamo en estado 'solicitado'.");
        }
        solicitud.setBibliotecario(this);
        solicitud.crearPrestamo();
        return solicitud;
    }
    
    public void registrarDevolucion(Prestamo prestamo){
        if(getEstado() != EstadoPersona.ACTIVO){
            throw new IllegalStateException("El bibliotecario debe estar activo para registrar devoluciones.");
        }
        if(prestamo == null){
            throw new IllegalArgumentException("Debe de indicar el prestamo a devolver.");
        }
        prestamo.registrarDevolucion();
    }
    
    public void gestionarInventario(Material material, String codigoInventario, String ubicacion){
        if(getEstado() != EstadoPersona.ACTIVO){
            throw new IllegalStateException("El bibliotecario debe estar activo para gestionar inventario.");
        }
        if(material==null){
            throw new IllegalArgumentException("Debe de indicar el libro a gestionar.");
        }
        material.agregarEjemplar(codigoInventario, ubicacion);
    }
    
    @Override
    public String consultarInfo() {
        return "ID: "+ getId() + "\nNombre: " + getNombre() + "\nDocumento Identidad: " + getDocumentoIdentidad() + 
                "\nCorreo:" + getCorreo() + "\nTelefono: " + getTelefono() + "\nCodigo empleado: "+ codigoEmpleado + "\nEstado: " + 
                getEstado() + "\nCargo:" + cargo;
    }
}
