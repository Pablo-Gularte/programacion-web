package ar.edu.centro8.daw.tp1.ejercicio5.estaturas.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Jugador {
    private int id;
    private String dni;
    private String nombre;
    private String apellido;
    private int edad;
    private int peso;
    private double estatura;
}
