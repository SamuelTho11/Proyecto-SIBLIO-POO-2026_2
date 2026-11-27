/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.siblio.modelo;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
//santi67676767
/**
 *
 * @author Samuel
 */
public class Prestamo {
    public static final int DIAS_PRESTAMO =7;
    public static final double MULTA_POR_DIA = 1000;
    private static int contadorId = 1;
    
    private int idPrestamo;
    private int diasPrestamo;
    private LocalDate fechaPrestamo;
    private LocalDate fechaDevolucionEsperada;
    private LocalDate fechaDevolucionReal;
    private EstadoPrestamo estado;
    
    
    private  Usuario usuario;
    private  Ejemplar ejemplar;
    private Bibliotecario bibliotecario;
    private Multa multa;
    
    public Prestamo() {
        this.idPrestamo = contadorId++;
        this.diasPrestamo = DIAS_PRESTAMO;
        this.estado = EstadoPrestamo.SOLICITADO;
    }
    
    Prestamo(Usuario usuario, Ejemplar ejemplar, int diasPrestamo) {
        this.idPrestamo = contadorId++;
        this.usuario = usuario;
        this.ejemplar = ejemplar;
        this.diasPrestamo = diasPrestamo;
        this.estado = EstadoPrestamo.SOLICITADO;
    }
    
    public void crearPrestamo() throws EstadoOperacionException, UsuarioInhabilitadoException, EjemplarNoDisponibleException{
        
        if (estado != EstadoPrestamo.SOLICITADO) {
            throw new EstadoOperacionException("Solo se puede crear un préstamo en estado SOLICITADO.");
        }

        if (bibliotecario == null) {
            throw new EstadoOperacionException("El préstamo debe estar asignado a un bibliotecario.");
        }

        if (!usuario.puedeSolicitarPrestamo()) {
            throw new UsuarioInhabilitadoException(usuario.getNombre()," ya no cumple las condiciones para el préstamo.");
        }

        if (!ejemplar.consultarDisponibilidad()) {
            throw new EjemplarNoDisponibleException(ejemplar.getCodigoInventario());
        }

        this.fechaPrestamo = LocalDate.now();
        this.fechaDevolucionEsperada = fechaPrestamo.plusDays(diasPrestamo);
        this.estado = EstadoPrestamo.ACTIVO;

        ejemplar.cambiarEstado(EstadoEjemplar.PRESTADO);
        usuario.agregarPrestamo(this);
    }
    
    public void registrarDevolucion() throws EstadoOperacionException{
        if (estado != EstadoPrestamo.ACTIVO) {
            throw new EstadoOperacionException("Solo se puede devolver un préstamo activo.");
        }

        this.fechaDevolucionReal = LocalDate.now();
        this.estado = EstadoPrestamo.DEVUELTO;

        ejemplar.cambiarEstado(EstadoEjemplar.DISPONIBLE);

        double monto = calcularMulta();

        if (monto > 0) {
            this.multa = new Multa(idPrestamo, monto, "Retraso en la devolución");
            this.multa.generarMulta();
        }
    }
    
    public double calcularMulta() {
        if (fechaDevolucionEsperada == null) {
            return 0;
        }

        LocalDate referencia = (fechaDevolucionReal != null)
                ? fechaDevolucionReal
                : LocalDate.now();

        long diasRetraso = ChronoUnit.DAYS.between(
                fechaDevolucionEsperada,
                referencia
        );

        return diasRetraso > 0
                ? diasRetraso * MULTA_POR_DIA
                : 0;
    }
    
     public boolean renovarPrestamo() {
        if (estado != EstadoPrestamo.ACTIVO
                || LocalDate.now().isAfter(fechaDevolucionEsperada)) {
            return false;
        }

        fechaDevolucionEsperada =
                fechaDevolucionEsperada.plusDays(diasPrestamo);

        return true;
    }


    public int getDiasPrestamo() {
        return diasPrestamo;
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

    public EstadoPrestamo getEstado() {
        return estado;
    }

    public void setEstado(EstadoPrestamo estado) {
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