/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.siblio.excepciones;

/**
 *
 * @author Samuel
 */
public class UsuarioInhabilitadoException extends Exception {
    private final String nombreUsuario;

    public UsuarioInhabilitadoException(String nombreUsuario, String motivo) {
        super("Usuario: '"+nombreUsuario+"' inhabilitado: "+ motivo);
        this.nombreUsuario = nombreUsuario;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }
    
    
}
