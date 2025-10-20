package ar.edu.centro8.desarrollo.proyectojpa.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * DTO para recibir datos de Persona desde el cliente
 * Contiene validaciones de formato y estructura
 */
@Getter @Setter
public class PersonaRequestDTO {
    
    private String nombre;
    private int edad;

    // Constructor vacío
    public PersonaRequestDTO() {}

    // Constructor con parámetros
    public PersonaRequestDTO(String nombre, int edad) {
        this.nombre = nombre;
        this.edad = edad;
    }

    /**
     * REGLA DE NEGOCIO EN DTO:
     * Validar que el nombre tenga formato correcto
     * (al menos 2 caracteres y no esté vacío)
     */
    public void validarNombre() {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede estar vacío");
        }
        if (nombre.trim().length() < 2) {
            throw new IllegalArgumentException("El nombre debe tener al menos 2 caracteres");
        }
        // Eliminar espacios extras
        this.nombre = nombre.trim();
    }

    /**
     * Validación completa del DTO
     */
    public void validar() {
        validarNombre();
        // Se pueden agregar más validaciones de formato aquí
    }
}