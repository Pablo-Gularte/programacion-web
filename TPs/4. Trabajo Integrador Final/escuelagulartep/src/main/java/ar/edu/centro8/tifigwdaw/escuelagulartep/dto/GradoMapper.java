package ar.edu.centro8.tifigwdaw.escuelagulartep.dto;

import java.util.List;
import java.util.stream.Collectors;

import ar.edu.centro8.tifigwdaw.escuelagulartep.models.Estudiante;
import ar.edu.centro8.tifigwdaw.escuelagulartep.models.Grado;

public class GradoMapper {
    public static Grado toEntity(GradoRequestDTO dto) {
        // Convertir la lista de EstudianteRequestDTO a entidades Estudiante
        List<Estudiante> estudiantes = dto.getEstudiantes().stream()
            .map(EstudianteMapper::toEntity)
            .collect(Collectors.toList());

        return new Grado(
            null,
            dto.getNombreGrado(),
            dto.getCiclo(),
            dto.getTurno(),
            dto.getDocente(),
            dto.isActivo(),
            estudiantes
        );
    }

    public static GradoResponseDTO toResponseDTO(Grado grado) {
        // 1. Convertir la lista de entidades Estudiante a DTOs
        List<EstudianteResponseDTO> estudiantesDTO = grado.getEstudiantes().stream()
            .map(EstudianteMapper::toResponseDTO) // Usamos el mapper existente para Estudiante
            .collect(Collectors.toList());

        // 2. Construir el GradoResponseDTO con la lista de DTOs de Estudiante
        return new GradoResponseDTO(
            grado.getId(),
            grado.getNombreGrado(),
            grado.getCiclo(),
            grado.getTurno(),
            grado.getDocente(),
            grado.isActivo(),
            estudiantesDTO // Lista de DTOs sin recursión
        );
    }

    public static void updateEntity(Grado grado, GradoRequestDTO dto) {
        // Actualización directa de atributos
        grado.setNombreGrado(dto.getNombreGrado());
        grado.setCiclo(dto.getCiclo());
        grado.setTurno(dto.getTurno());
        grado.setDocente(dto.getDocente());
        grado.setActivo(dto.isActivo());

        // 2. Lógica de Actualización de la Colección (Estudiantes)
        // Crear la nueva lista de entidades Estudiante a partir del DTO
        List<Estudiante> nuevosEstudiantes = dto.getEstudiantes().stream()
            .map(EstudianteMapper::toEntity)
            .collect(Collectors.toList());

        // Establecer la relación bidireccional (Estudiante -> Grado)
        for (Estudiante estudiante : nuevosEstudiantes) {
            estudiante.setGrado(grado);
        }

        grado.getEstudiantes().clear(); // Limpiar la lista existente
        grado.getEstudiantes().addAll(nuevosEstudiantes);   // Regenerar la lista de estudiantes
    }
}