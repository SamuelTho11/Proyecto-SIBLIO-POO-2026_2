/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.siblio.modelo;

/**
 *
 * @author Samuel
 */
public class EjemplarNoDisponibleException extends Exception {
    private final String codigoInventario;

    public EjemplarNoDisponibleException(String codigoInventario) {
        super("El ejemplar "+ codigoInventario+" no se encuentra disponible.");
        this.codigoInventario = codigoInventario;
    }

    public String getCodigoInventario() {
        return codigoInventario;
    }
    
}
