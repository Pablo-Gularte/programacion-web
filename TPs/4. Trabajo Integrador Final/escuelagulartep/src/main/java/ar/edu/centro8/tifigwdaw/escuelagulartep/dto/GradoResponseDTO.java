package ar.edu.centro8.tifigwdaw.escuelagulartep.dto;

import java.util.List;

import lombok.Value; 

@Value  // Hace la clase inmutable y genera Constructor, Getters, toString, equals y hashCode.
public class GradoResponseDTO {
    private final Long id;
    private final String nombreGrado;
    private final int ciclo;
    private final String turno;
    private final String docente;
    private final boolean activo;
    private final List<EstudianteResponseDTO> estudiantes; 
}