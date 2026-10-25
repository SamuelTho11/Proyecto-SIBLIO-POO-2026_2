/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.siblio.modelo;

/**
 *
 * @author Samuel
 */
public abstract class Persona {
    private int id;
    private String nombre;
    private String documentoIdentidad;
    private String correo;
    private String telefono;

    public Persona(int id, String nombre, String documentoIdentidad, String correo, String telefono) {
        this.id = id;
        this.nombre = nombre;
        this.documentoIdentidad = documentoIdentidad;
        this.correo = correo;
        this.telefono = telefono;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDocumentoIdentidad() {
        return documentoIdentidad;
    }

    public void setDocumentoIdentidad(String documentoIdentidad) {
        this.documentoIdentidad = documentoIdentidad;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    
    public void actualizarDatos(String nombre, String correo, String telefono){
        this.nombre = nombre;
        this.correo = correo;
        this.telefono = telefono;
    }
    
    public abstract String consultarInfo();
}
