package ar.edu.centro8.daw.tp1.ejercicio4.odontologo.model;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Paciente {
    private int id;
    private String dni;
    private String nombre;
    private String apellido;
    private LocalDate fechaNacimiento;
}
