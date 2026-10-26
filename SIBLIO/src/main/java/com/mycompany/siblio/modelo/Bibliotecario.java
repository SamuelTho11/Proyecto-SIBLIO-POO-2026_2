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
    private String estado;

    public Bibliotecario(String codigoEmpleado, String cargo, String estado, int id, String nombre, String documentoIdentidad, String correo, String telefono) {
        super(id, nombre, documentoIdentidad, correo, telefono);
        this.codigoEmpleado = codigoEmpleado;
        this.cargo = cargo;
        this.estado = "activo";
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

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    public Prestamo registrarPrestamo(Prestamo solicitud){
        if(!"activo".equals(estado)){
            throw new IllegalStateException("El bibliotecario debe estar activo para registrar prestamos.");
        }
        return null;
    }
    
    public void registrarDevolucion(Prestamo prestamo){
        if(!"activo".equals(estado)){
            throw new IllegalStateException("El bibliotecario debe estar activo para registrar devoluciones.");
        }
        if(prestamo == null){
            throw new IllegalArgumentException("Debe de indicar el prestamo a devolver.");
        }
    }
    
    public void gestionarInventario(Libro libro, String codigoInventario, String ubicacion){
        if(!"activo".equals(estado)){
            throw new IllegalStateException("El bibliotecario debe estar activo para gestionar inventario.");
        }
        if(libro==null){
            throw new IllegalArgumentException("Debe de indicar el libro a gestionar.");
        }
    }
    
    @Override
    public void consultarInfo() {
        System.out.println("ID: "+ getId() + "\nNombre: " + getNombre() + "\nDocumento Identidad: " + getDocumentoIdentidad() + 
                "\nCorreo:" + getCorreo() + "\nTelefono: " + getTelefono() + "\nCodigo empleado: "+ codigoEmpleado + "\nEstado: " + 
                estado + "\nCargo:" + cargo);
    }
    
}
