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

        Libro libro = new Libro(
                1,
                "Cien años de Soledad",
                "Gabriel Garcia Marquez",
                "Sudamericana",
                1967,
                "Novela"
        );

        libro.registrarLibro();

        Ejemplar e1 = libro.agregarEjemplar(
                "LIB-997-A",
                "Estante 2"
        );

        Ejemplar e2 = libro.agregarEjemplar(
                "LIB-672-B",
                "Estante 3"
        );

        Revista revista = new Revista(
                2,
                "Investigación y Ciencia",
                "Varios",
                "Prensa Científica",
                2025,
                512,
                "Mensual"
        );

        revista.agregarEjemplar(
                "REV-101-A",
                "Hemeroteca 1"
        );

        // Polimorfismo sobre Material
        List<Material> catalogo =
                Arrays.asList(libro, revista);

        System.out.println("=== CATÁLOGO DE MATERIALES ===");

        for (Material m : catalogo) {
            System.out.println(m.mostrarFicha() + "\n");
        }

        Usuario carlos = new Usuario(
                1,
                "Carlos Mele",
                "1001",
                "carlos@gmail.com",
                "3256704552",
                "estudiante",
                2
        );

        Bibliotecario jose = new Bibliotecario(
                2,
                "Jose Caicedo",
                "2002",
                "luis@gmail.com",
                "3159238743",
                "EMP-01",
                "Auxiliar"
        );

        // Polimorfismo sobre Persona
        List<Persona> personas =
                Arrays.asList(carlos, jose);

        System.out.println("=== DIRECTORIO DE PERSONAS ===");

        for (Persona p : personas) {
            System.out.println(p.consultarInfo());
        }

        // Reserva
        Reserva reserva =
                carlos.reservarMaterial(revista, 5);

        System.out.println(
                "\nReserva #" + reserva.getIdReserva()
                + " vigente? "
                + reserva.estaVigente()
        );

        reserva.confirmarReserva();

        // Préstamo con días estándar y personalizados
        Prestamo prestamo =
                jose.registrarPrestamo(
                        carlos.solicitarPrestamo(e1)
                );

        System.out.println(
                "Préstamo #"
                + prestamo.getIdPrestamo()
                + " estado: "
                + prestamo.getEstado()
        );

        // Simular retraso, devolución y multa
        prestamo.setFechaPrestamo(
                LocalDate.now().minusDays(20)
        );

        prestamo.setFechaDevolucionEsperada(
                LocalDate.now().minusDays(10)
        );

        jose.registrarDevolucion(prestamo);

        Multa multa = prestamo.getMulta();

        System.out.println(
                "Multa generada: $"
                + multa.getMonto()
                + " ("
                + multa.getEstado()
                + ")"
        );

        try {
            carlos.solicitarPrestamo(e2, 14);
        } catch (IllegalStateException ex) {
            System.out.println(
                    "Bloqueado por multa: "
                    + ex.getMessage()
            );
        }

        carlos.pagarMulta(multa);

        Prestamo segundo =
                jose.registrarPrestamo(
                        carlos.solicitarPrestamo(e2, 14)
                );

        System.out.println(
                "Segundo préstamo (14 días) #"
                + segundo.getIdPrestamo()
                + " estado: "
                + segundo.getEstado()
        );
    }
}
