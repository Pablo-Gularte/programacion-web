package ar.edu.centro8.desarrollo.proyectojpa.dto;

import ar.edu.centro8.desarrollo.proyectojpa.model.Persona;

/**
 * Mapper para convertir entre DTOs y Entidades
 * Centraliza la lógica de conversión
 */
public class PersonaMapper {

    /**
     * Convierte PersonaRequestDTO a Persona (entidad)
     */
    public static Persona toEntity(PersonaRequestDTO dto) {
        Persona persona = new Persona();
        persona.setNombre(dto.getNombre());
        persona.setEdad(dto.getEdad());
        return persona;
    }

    /**
     * Convierte Persona (entidad) a PersonaResponseDTO
     */
    public static PersonaResponseDTO toResponseDTO(Persona persona) {
        return new PersonaResponseDTO(
            persona.getId(),
            persona.getNombre(),
            persona.getEdad()
        );
    }

    /**
     * Actualiza una entidad Persona con datos del DTO
     */
    public static void updateEntity(Persona persona, PersonaRequestDTO dto) {
        persona.setNombre(dto.getNombre());
        persona.setEdad(dto.getEdad());
    }
}