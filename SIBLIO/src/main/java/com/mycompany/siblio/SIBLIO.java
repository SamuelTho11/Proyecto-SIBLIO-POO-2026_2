/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.siblio;

import com.mycompany.siblio.modelo.Bibliotecario;
import com.mycompany.siblio.modelo.Ejemplar;
import com.mycompany.siblio.modelo.Libro;
import com.mycompany.siblio.modelo.Multa;
import com.mycompany.siblio.modelo.Prestamo;
import com.mycompany.siblio.modelo.Usuario;
import java.time.LocalDate;

/**
 *
 * @author Samuel
 */
public class SIBLIO {

    public static void main(String[] args) {
        Libro libro = new Libro(1, "Cien años de Soledad", "Gabriel Garcia Marquez", "Sudamericana", 1967, "Novela");
        libro.registrarLibro();
        Ejemplar e1 = libro.agregarEjemplar("LIB-997-A", "Estante 2");
        Ejemplar e2 = libro.agregarEjemplar("LIB-672-B", "Estante 3");
        System.out.println(libro.mostrarFicha());
        
        Usuario carlos = new Usuario(1, "Carlos Mele", "1001", "carlos@gmail.com", "3256704552","estudiante", 2);
        Bibliotecario jose = new Bibliotecario(2, "Jose Caicedo","2002","luis@gmail.com","3159238743", "EMP-01", "Auxiliar");
        System.out.println("\n"+ carlos.consultarInfo());
        
        Prestamo prestamo = jose.registrarPrestamo(carlos.solicitarPrestamo(e1));
        System.out.println("\nPrestamo #"+ prestamo.getIdPrestamo()+ " estado: "+prestamo.getEstado() + " | devolver antes de: "
        + prestamo.getFechaDevolucionEsperada());
        System.out.println("Ejemplar "+ e1.getCodigoInventario() + " disponible? "+e1.consultarDisponibilidad());
        
        try {
            carlos.solicitarPrestamo(e1);
        } catch (IllegalStateException ex){
            System.out.println("Bloqueado exitosamente: "+ ex.getMessage());
        }
        prestamo.setFechaPrestamo(LocalDate.now().minusDays(20));
        prestamo.setFechaDevolucionEsperada(LocalDate.now().minusDays(10));
        jose.registrarDevolucion(prestamo);
        Multa multa = prestamo.getMulta();
        System.out.println("\nDevuelto con retraso. Multa: $"+ multa.getMonto()+ " ("+ multa.getEstado()+")");
        
        try{
            carlos.solicitarPrestamo(e2);
        }catch (IllegalStateException ex){
            System.out.println("Bloqueado exitosamente: " + ex.getMessage());
        }
        
        carlos.pagarMulta(multa);
        System.out.println("Multa "+multa.getEstado()+". Puede solicitar? "+ carlos.puedeSolicitarPrestamo());
        Prestamo segundo =jose.registrarPrestamo(carlos.solicitarPrestamo(e2));
        System.out.println("Segundo prestamo #"+segundo.getIdPrestamo()+ " estado: "+ segundo.getEstado());
        System.out.println("Historial de Carlos: "+ carlos.consultarHistorial().size() + " prestamos.");
    }
}
