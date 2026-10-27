/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.siblio.modelo;
import java.time.LocalDate;
//santi

/**
 *
 * @author Samuel
 */
public class Multa {
    private int idMulta;
    private double monto;
    private String motivo;
    private String estado;
    private LocalDate fechaGeneracion;

    public Multa(int idMulta, double monto, String motivo) {
        this.idMulta = idMulta;
        setMonto(monto);
        this.motivo = motivo;
        this.estado = "creada";
    }
    
    public void generarMulta() {
        if (monto <= 0) {
            System.out.println("El monto de la multa debe ser mayor a cero.");
            return;
        }
        
        this.estado = "pendiente";
        this.fechaGeneracion = LocalDate.now();
    }
    
    public void registrarPago() {
        if (!"pendiente".equals(estado)) {
            System.out.println("Solo se puede pagar una multa pendiente.");
            return;
        } 
        this.estado = "pagada";
        
    }

    public int getIdMulta() {
        return idMulta;
    }

    public void setIdMulta(int idMulta) {
        this.idMulta = idMulta;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
       if (monto <= 0) {
            throw new IllegalArgumentException("El monto de la multa debe ser mayor a cero.");
        }
        this.monto = monto;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public LocalDate getFechaGeneracion() {
        return fechaGeneracion;
    }

    public void setFechaGeneracion(LocalDate fechaGeneracion) {
        this.fechaGeneracion = fechaGeneracion;
    }
    
    
}
    
    
    

