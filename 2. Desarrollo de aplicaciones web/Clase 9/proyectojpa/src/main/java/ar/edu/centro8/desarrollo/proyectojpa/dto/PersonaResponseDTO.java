package ar.edu.centro8.desarrollo.proyectojpa.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * DTO para enviar datos de Persona al cliente
 * Controla qué información se expone en la API
 */
@Getter @Setter
public class PersonaResponseDTO {
    
    private Long id;
    private String nombre;
    private int edad;

    // Constructor vacío
    public PersonaResponseDTO() {}

    // Constructor con parámetros
    public PersonaResponseDTO(Long id, String nombre, int edad) {
        this.id = id;
        this.nombre = nombre;
        this.edad = edad;
    }
}