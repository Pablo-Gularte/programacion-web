package ar.edu.centro8.daw.tp1.ejercicio3.restaurante.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Menu {
    private int id;
    private String nombre;
    private double precio;
    private String descripcion;
}
