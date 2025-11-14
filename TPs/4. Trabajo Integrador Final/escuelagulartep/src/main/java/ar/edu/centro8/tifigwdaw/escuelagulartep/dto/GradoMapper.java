package ar.edu.centro8.tifigwdaw.escuelagulartep.dto;

import ar.edu.centro8.tifigwdaw.escuelagulartep.models.Grado;

public class GradoMapper {
    public static Grado toEntity(GradoRequestDTO dto) {
        return new Grado(
            null,
            dto.getNombreGrado(),
            dto.getCiclo(),
            dto.getTurno(),
            dto.getDocente(),
            dto.isActivo(),
            dto.getEstudiantes()
        );
    }

    public static GradoResponseDTO toResponseDTO(Grado grado) {
        return new GradoResponseDTO(
            grado.getNombreGrado(),
            grado.getCiclo(),
            grado.getTurno(),
            grado.getDocente(),
            grado.isActivo(),
            grado.getEstudiantes()
        );
    }

    public static void updateEntity(Grado grado, GradoRequestDTO dto) {
        grado.setNombreGrado(dto.getNombreGrado());
        grado.setCiclo(dto.getCiclo());
        grado.setTurno(dto.getTurno());
        grado.setDocente(dto.getDocente());
        grado.setActivo(dto.isActivo());
        grado.setEstudiantes(dto.getEstudiantes());
    }
}
