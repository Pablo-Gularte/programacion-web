package ar.edu.centro8.tifigwdaw.escuelagulartep.dto;

import lombok.Value;

@Value // Hace la clase inmutable y genera Constructor, Getters, toString, equals y hashCode.
public class EstudianteResponseDTO {
    private final Long id;
    private final String nombre;
    private final String apellido;
    private final int edad;
    private final String direccion;
    private final String nombreMadre;
    private final String nombrePadre;
    private final boolean hnoEnEscuela;
    private final boolean esRegular;
}