package ar.edu.centro8.tifigwdaw.escuelagulartep.dto;

import ar.edu.centro8.tifigwdaw.escuelagulartep.models.Estudiante;

public class EstudianteMapper {
    public static Estudiante toEntity(EstudianteRequestDTO dto) {
        return new Estudiante(
            null,
            dto.getNombre(),
            dto.getApellido(),
            dto.getEdad(),
            dto.getDireccion(),
            dto.getNombreMadre(),
            dto.getNombrePadre(),
            dto.isHnoEnEscuela(),
            dto.isEsRegular(),
            dto.getGrado()
        );
    }

    public static EstudianteResponseDTO toResponseDTO(Estudiante estudiante) {
        return new EstudianteResponseDTO(
            estudiante.getId(),
            estudiante.getNombre(),
            estudiante.getApellido(), 
            estudiante.getEdad(),
            estudiante.getDireccion(),
            estudiante.getNombreMadre(),
            estudiante.getNombrePadre(),
            estudiante.isHnoEnEscuela(),
            estudiante.isEsRegular(),
            estudiante.getGrado());
    }

    public static void updateEntity(Estudiante estudiante, EstudianteRequestDTO dto) {
        estudiante.setNombre(dto.getNombre());
        estudiante.setApellido(dto.getApellido());
        estudiante.setEdad(dto.getEdad());
        estudiante.setDireccion(dto.getDireccion());
        estudiante.setNombreMadre(dto.getNombreMadre());
        estudiante.setNombrePadre(dto.getNombrePadre());
        estudiante.setHnoEnEscuela(dto.isHnoEnEscuela());
        estudiante.setEsRegular(dto.isEsRegular());
    }
}
