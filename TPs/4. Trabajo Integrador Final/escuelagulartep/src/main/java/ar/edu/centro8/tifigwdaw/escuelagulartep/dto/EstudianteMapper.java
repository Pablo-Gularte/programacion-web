package ar.edu.centro8.tifigwdaw.escuelagulartep.dto;

import ar.edu.centro8.tifigwdaw.escuelagulartep.models.Estudiante;

public class EstudianteMapper {
    public static Estudiante toEntity(EstudianteRequestDTO dto) {
        Estudiante estudiante = new Estudiante();
        estudiante.setNombre(dto.getNombre());
        estudiante.setApellido(dto.getApellido());  
        estudiante.setEdad(dto.getEdad());

        return estudiante;
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
            estudiante.isEsRegular()
        );
    }

    public static void updateEntity(Estudiante estudiante, EstudianteRequestDTO dto) {
        estudiante.setNombre(dto.getNombre());
        estudiante.setApellido(dto.getApellido());
        estudiante.setEdad(dto.getEdad());
    }
}
