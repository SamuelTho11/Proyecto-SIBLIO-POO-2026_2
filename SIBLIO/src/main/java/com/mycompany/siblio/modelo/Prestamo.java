/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.siblio.modelo;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
//santi6767
/**
 *
 * @author Samuel
 */
public class Prestamo {
    public static final int DIAS_PRESTAMO =0;
    public static final double MULTA_POR_DIA = 1000;
    
    private static int contadorId = 1;
    
    private int idPrestamo;
    private LocalDate fechaPrestamo;
    private LocalDate fechaDevolucionEsperada;
    private LocalDate fechaDevolucionReal;
    private String estado;
    
    
    private final Usuario usuario;
    private final Ejemplar ejemplar;
    private Bibliotecario bibliotecario;
    private Multa multa;
    
    Prestamo(Usuario usuario, Ejemplar ejemplar) {
        this.idPrestamo = contadorId++;
        this.usuario = usuario;
        this.ejemplar = ejemplar;
        this.estado = "solicitado";
    }
    
    public void crearPrestamo(){
        
        if(!"solicitado".equals(estado)){
            System.out.println("Solo se puede crear un préstamo solicitado.");
            return;
        }
        if (bibliotecario == null) {
            System.out.println("El préstamo debe estar asignado a un bibliotecario.");
            return;
        }
        if (!usuario.puedeSolicitarPrestamo()) {
            System.out.println("El usuario ya no cumple las condiciones para el préstamo.");
            return;
        }
        if (!ejemplar.consultarDisponibilidad()) {
            System.out.println("El ejemplar " + ejemplar.getCodigoInventario()
                    + " no está disponible.");
            return;
        }
        
        this.fechaPrestamo = LocalDate.now();
        this.fechaDevolucionEsperada = fechaPrestamo.plusDays(DIAS_PRESTAMO);
        this.estado = "activo";
        ejemplar.cambiarEstado("prestado");
        usuario.agregarPrestamo(this);
         
    }
    
    public void registrarDevolucion(){
        
        if (!"activo".equals(estado)) {
            throw new IllegalStateException("Solo se puede devolver un préstamo activo.");
        }
        this.fechaDevolucionReal = LocalDate.now();
        this.estado = "devuelto";
        ejemplar.cambiarEstado("disponible");
        
        double monto = calcularMulta();
        
        if (monto > 0) {
            this.multa = new Multa(idPrestamo, monto, "Retraso en la devolución");
            this.multa.generarMulta();
        }
        
    }
    
    public double calcularMulta(){
        if (fechaDevolucionEsperada == null) {
            return 0;
        }
        
        LocalDate referencia = (fechaDevolucionReal != null) ? fechaDevolucionReal : LocalDate.now();
        long diasRetraso = ChronoUnit.DAYS.between(fechaDevolucionEsperada, referencia);
        return diasRetraso > 0 ? diasRetraso * MULTA_POR_DIA : 0;
    }
    
     public boolean renovarPrestamo(){
         if (!"activo".equals(estado)) {
            return false;
        }
         if (LocalDate.now().isAfter(fechaDevolucionEsperada)) {
            return false;
        }
         fechaDevolucionEsperada = fechaDevolucionEsperada.plusDays(DIAS_PRESTAMO);
        return true;
     }

    public int getIdPrestamo() {
        return idPrestamo;
    }
     
    public LocalDate getFechaPrestamo() {
        return fechaPrestamo;
    }
    
    public void setFechaPrestamo(LocalDate fechaPrestamo){
        if (fechaDevolucionEsperada != null && !fechaPrestamo.isBefore(fechaDevolucionEsperada)) {
            throw new IllegalArgumentException("La fecha de préstamo debe ser anterior a la fecha de devolución esperada.");
        }
        this.fechaPrestamo = fechaPrestamo;
    }

    public LocalDate getFechaDevolucionEsperada() {
        return fechaDevolucionEsperada;
    }
    
    public void setFechaDevolucionEsperada(LocalDate fechaDevolucionEsperada){
        if (fechaPrestamo != null && !fechaPrestamo.isBefore(fechaDevolucionEsperada)) {
            throw new IllegalArgumentException("La fecha de devolución esperada debe ser posterior a la fecha de préstamo.");
        }
        this.fechaDevolucionEsperada = fechaDevolucionEsperada;
    }

    public LocalDate getFechaDevolucionReal() {
        return fechaDevolucionReal;
    }

    public void setFechaDevolucionReal(LocalDate fechaDevolucionReal) {
        this.fechaDevolucionReal = fechaDevolucionReal;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public Ejemplar getEjemplar() {
        return ejemplar;
    }

    public Bibliotecario getBibliotecario() {
        return bibliotecario;
    }

    public void setBibliotecario(Bibliotecario bibliotecario) {
        this.bibliotecario = bibliotecario;
    }
    
    public Multa getMulta() { 
        return multa; 
    }
    
    
}
//santi